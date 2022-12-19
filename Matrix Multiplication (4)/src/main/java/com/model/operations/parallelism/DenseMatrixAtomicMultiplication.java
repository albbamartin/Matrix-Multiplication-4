package com.model.operations.parallelism;

import com.model.operations.sequential.MatrixMultiplication;
import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.AtomicDouble;

public class DenseMatrixAtomicMultiplication implements MatrixMultiplication<DenseMatrix, DenseMatrix, DenseMatrix> {

    int threadCount = Runtime.getRuntime().availableProcessors();
    ExecutorService es = Executors.newFixedThreadPool(threadCount);
    AtomicDouble[][] atomicDouble;
    DenseMatrixBuilder builder;

    @Override
    public DenseMatrix multiply(DenseMatrix matrix1, DenseMatrix matrix2) {

        builder = new DenseMatrixBuilder(matrix1.size(), matrix1.size());
        atomicDouble = createEmptyAtomicDenseMatrix(matrix1.size());

        for (int m1Row = 0; m1Row < matrix1.size(); m1Row++) {
            for (int m2Col = 0; m2Col < matrix1.size(); m2Col++) {
                submit(matrix1, matrix2, m1Row, m2Col);
            }
        }
        es.shutdown();
        getValues();
        return builder.toMatrix();
    }

    private void submit(DenseMatrix matrix1, DenseMatrix matrix2, int m1Row, int m2Col) {
        es.submit(() -> {
            for (int m1Col = 0; m1Col< matrix1.size(); m1Col++)
                atomicDouble[m1Row][m1Col].set(matrix1.get(m1Row,m2Col)*matrix2.get(m2Col,m1Col));
        });
    }

    public AtomicDouble[][] createEmptyAtomicDenseMatrix(int size) {
        AtomicDouble[][] atomicDouble = new AtomicDouble[size][size];
        for (int row = 0; row < size; row++)
            for (int column = 0; column < size; column++)
                atomicDouble[row][column] = new AtomicDouble();
        return atomicDouble;
    }

    private double[][] getValues() {
        double[][] result = new double[atomicDouble.length][atomicDouble.length];
        for (int i = 0; i < atomicDouble.length; i++) {
            for (int j = 0; j < atomicDouble.length; j++) {
                builder.set(i, j, atomicDouble[i][j].doubleValue());
            }
        }
        return result;
    }
}