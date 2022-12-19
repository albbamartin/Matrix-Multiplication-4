package com.model.operations.parallelism;

import com.model.operations.sequential.MatrixMultiplication;
import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;

import java.util.stream.IntStream;

public class DenseMatrixStreamsMultiplication implements MatrixMultiplication<DenseMatrix, DenseMatrix, DenseMatrix> {

    @Override
    public DenseMatrix multiply(DenseMatrix matrix1, DenseMatrix matrix2) {
        DenseMatrixBuilder builder = new DenseMatrixBuilder(matrix1.getnColumns(), matrix2.getnRows());
        IntStream.range(0, matrix1.getnRows()).forEach(m1Row->
                IntStream.range(0, matrix2.getnColumns()).forEach(m2Col -> {
                    double sum = IntStream.range(0, matrix1.getnColumns()).mapToDouble(m1Col -> matrix1.get(m1Row, m1Col) * matrix2.get(m1Col, m2Col)).sum();
                    builder.set(m1Row, m2Col, sum);
                })
        );
        return builder.toMatrix();
    }
}
