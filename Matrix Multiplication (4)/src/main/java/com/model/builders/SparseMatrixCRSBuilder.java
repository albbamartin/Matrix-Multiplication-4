package com.model.builders;
import com.model.matrixes.SparseMatrixCRS;
import java.util.ArrayList;
import java.util.List;

public class SparseMatrixCRSBuilder implements Builder {

    private final List<Integer> pointersToTheFirstNonNullElementsOfEachRowPointers = new ArrayList<>();
    private final List<Integer> columnIndexes = new ArrayList<>();
    private final List<Double> values = new ArrayList<>();

    int size;
    public SparseMatrixCRSBuilder(int size) {
        this.size = size;
    }

    public void addRowPrt(int rowPrt) {
        pointersToTheFirstNonNullElementsOfEachRowPointers.add(rowPrt);
    }

    public void addColumnIndex(int columnInd) {
        columnIndexes.add(columnInd);
    }

    public void addValue(double value) {
        values.add(value);
    }

    @Override
    public SparseMatrixCRS toMatrix() {
        return new SparseMatrixCRS(this.size)
                .setPointersToTheFirstNonNullElementsOfEachRowPointers(pointersToTheFirstNonNullElementsOfEachRowPointers)
                .setColumnIndexes(columnIndexes)
                .setValues(values);
    }
}


