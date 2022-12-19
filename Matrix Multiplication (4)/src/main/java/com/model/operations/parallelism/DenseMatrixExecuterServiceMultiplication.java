package com.model.operations.parallelism;

import com.model.operations.sequential.MatrixMultiplication;
import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DenseMatrixExecuterServiceMultiplication implements MatrixMultiplication<DenseMatrix, DenseMatrix, DenseMatrix> {

    @Override
    public DenseMatrix multiply(DenseMatrix matrix1, DenseMatrix matrix2) {
        int threadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService es = Executors.newFixedThreadPool(threadCount);
        DenseMatrixBuilder builder = new DenseMatrixBuilder(matrix1.size(), matrix1.size());
        for (int m1Row = 0; m1Row < matrix1.size(); m1Row++) {
            for (int m2Col = 0; m2Col < matrix1.size(); m2Col++) {
                int finalRow = m1Row;
                int finalColumn = m2Col;
                es.submit(() -> {
                    double sum = 0;
                    for (int m1Col = 0; m1Col< matrix1.size(); m1Col++)
                        sum+= matrix1.get(finalRow,m1Col) * matrix2.get(m1Col, finalColumn);
                    builder.set(finalRow, finalColumn, sum);
                });
            }
        }
        es.shutdown();
        return builder.toMatrix();
    }
}