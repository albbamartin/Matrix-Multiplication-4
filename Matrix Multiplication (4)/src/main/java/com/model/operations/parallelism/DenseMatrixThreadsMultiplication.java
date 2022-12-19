package com.model.operations.parallelism;
import com.model.operations.sequential.MatrixMultiplication;
import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;
import java.util.LinkedList;


public class DenseMatrixThreadsMultiplication implements MatrixMultiplication<DenseMatrix, DenseMatrix, DenseMatrix> {

    @Override
    public DenseMatrix multiply(DenseMatrix matrix1, DenseMatrix matrix2) {
        LinkedList<Thread> threads = new LinkedList<Thread>();
        DenseMatrixBuilder builder = new DenseMatrixBuilder(matrix1.size(), matrix1.size());
        for (int m1Row = 0; m1Row < matrix1.size(); m1Row++) {
            for (int m2Col = 0; m2Col < matrix1.size(); m2Col++) {
                int finalRow = m1Row;
                int finalColumn = m2Col;
                Thread thread = new Thread(() -> {
                    double sum = 0;
                    for (int m1Col = 0; m1Col< matrix1.size(); m1Col++)
                        sum+= matrix1.get(finalRow,m1Col) * matrix2.get(m1Col, finalColumn);
                    builder.set(finalRow, finalColumn, sum);
                });
                threads.add(thread);
                thread.start();
            }
        }
        for (Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return builder.toMatrix();
    }
}
