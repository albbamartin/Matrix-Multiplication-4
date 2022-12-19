package com.model.operations.sequential;
import com.model.matrixes.SparseMatrixCOO;
import com.model.builders.SparseMatrixCOOBuilder;
import com.model.matrixes.SparseMatrixCCS;
import com.model.matrixes.SparseMatrixCRS;

public class SparseMatrixMultiplication implements MatrixMultiplication<SparseMatrixCOO, SparseMatrixCRS, SparseMatrixCCS> {

    @Override
    public SparseMatrixCOO multiply(SparseMatrixCRS matrix1, SparseMatrixCCS matrix2) {
            SparseMatrixCOOBuilder builder = new SparseMatrixCOOBuilder(matrix1.size());
            for (int i = 0; i < matrix1.size(); i++) {
                for (int j = 0; j < matrix2.size(); j++) {
                    int ii = matrix1.getPointersToTheFirstNonNullElementsOfEachRowPointers().get(i);
                    int iEnd = matrix1.getPointersToTheFirstNonNullElementsOfEachRowPointers().get(i + 1);
                    int jj = matrix2.getPointersToTheFirstNonNullElementsOfEachColumnPointers().get(j);
                    int jEnd = matrix2.getPointersToTheFirstNonNullElementsOfEachColumnPointers().get(j + 1);
                    double value = 0;
                    while (ii < iEnd && jj < jEnd) {
                        int aa = matrix1.getColumnIndexes().get(ii);
                        int bb = matrix2.getRowIndexes().get(jj);
                        if (aa == bb) {
                            value += matrix1.getValues().get(ii) * matrix2.getValues().get(jj);
                            ii++;
                            jj++;
                        }
                        else if (aa < bb) ii++;
                        else jj++;
                    }
                    if (value != 0) builder.set(i, j, value);
                }
            }
            return builder.toMatrix();
        }
}
