package com.model.operations.sequential;

import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;

public class DenseMatrixStandardMultiplication implements MatrixMultiplication<DenseMatrix, DenseMatrix, DenseMatrix> {

    @Override
    public DenseMatrix multiply(DenseMatrix matrix1, DenseMatrix matrix2) {

        DenseMatrixBuilder matrixBuilder = new DenseMatrixBuilder(matrix1.getnColumns(), matrix2.getnRows());

        for (int m1Row = 0; m1Row < matrix1.getnRows(); m1Row++) {
            for (int m2Col = 0; m2Col < matrix2.getnColumns(); m2Col++) {
                double sum = 0;
                for (int m1Col = 0; m1Col < matrix1.getnColumns(); m1Col++) {
                    sum += matrix1.get(m1Row, m1Col) * matrix2.get(m1Col, m2Col);
                }
                matrixBuilder.set(m1Row, m2Col, sum);
            }
        }
        return matrixBuilder.toMatrix();
    }
}
