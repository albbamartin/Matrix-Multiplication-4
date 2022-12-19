package com.model.operations.parallelism;

import com.model.operations.sequential.MatrixMultiplication;
import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class DenseMatrixSemaphoreMultiplication implements MatrixMultiplication<DenseMatrix, DenseMatrix, DenseMatrix> {
    int threadCount = Runtime.getRuntime().availableProcessors();
    ExecutorService es = Executors.newFixedThreadPool(threadCount);
    Semaphore semaphore = new Semaphore(1);

    @Override
    public DenseMatrix multiply(DenseMatrix matrix1, DenseMatrix matrix2) {

        DenseMatrixBuilder builder = new DenseMatrixBuilder(matrix1.size(), matrix1.size());

        for (int m1Row = 0; m1Row < matrix1.size(); m1Row++) {
            for (int m2Col = 0; m2Col < matrix1.size(); m2Col++)
                submit(matrix1, matrix2, builder, m1Row, m2Col);
        }
        es.shutdown();
        return builder.toMatrix();
    }

    private void submit(DenseMatrix matrix1, DenseMatrix matrix2, DenseMatrixBuilder builder, int m1Row, int m2Col) {
        es.submit(() -> {
            double sum = 0.0;
            for (int m1Col = 0; m1Col< matrix1.size(); m1Col++) {
                try {
                    semaphore.acquire();
                    sum += matrix1.get(m1Row, m1Col) * matrix2.get(m1Col, m2Col);
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            builder.set(m1Row, m2Col, sum);
        });
    }
}
