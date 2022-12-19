package com.model.operations.parallelism;

import com.model.operations.sequential.MatrixMultiplication;
import com.model.builders.SparseMatrixCOOBuilder;
import com.model.matrixes.SparseMatrixCCS;
import com.model.matrixes.SparseMatrixCOO;
import com.model.matrixes.SparseMatrixCRS;

import java.util.stream.IntStream;

public class SparseMatrixStreamsMultiplication implements MatrixMultiplication<SparseMatrixCOO, SparseMatrixCRS, SparseMatrixCCS> {
    @Override
    public SparseMatrixCOO multiply(SparseMatrixCRS matrix1, SparseMatrixCCS matrix2) {
        int size = matrix1.size();
        SparseMatrixCOOBuilder builder = new SparseMatrixCOOBuilder(size);
        IntStream.range(0, size).forEach(i->
                IntStream.range(0, size).forEach(j -> {
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
                })
        );
        return builder.toMatrix();
    }
}
