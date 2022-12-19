package com.model.operations.sequential;

import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;

public class DenseMatrixTransposedMultiplication implements MatrixMultiplication<DenseMatrix, DenseMatrix, DenseMatrix> {
    @Override
    public DenseMatrix multiply(DenseMatrix matrix1, DenseMatrix matrix2) {
        DenseMatrixBuilder matrixBuilder = new DenseMatrixBuilder(matrix1.size(), matrix2.size());
        double[][] transposed = transpose(matrix2);
        for (int m1Row = 0; m1Row < matrix1.size(); m1Row++) {
            for (int m2Col = 0; m2Col < matrix1.size(); m2Col++) {
                double sum = 0.0;
                for (int m1Col = 0; m1Col < matrix1.size(); m1Col++) {
                    sum += matrix1.get(m1Row,m1Col) * transposed[m2Col][m1Col];
                }
                matrixBuilder.set(m1Row, m2Col, sum);
            }
        }
        return matrixBuilder.toMatrix();
    }

    private static double[][] transpose(DenseMatrix matrix) {
        double[][] transposed = new double[matrix.size()][matrix.size()];
        for (int row = 0; row < matrix.size(); row++) {
            for (int column = 0; column < matrix.size(); column++) {
                transposed[column][row] = matrix.get(row,column);
            }
        }
        return transposed;
    }
}