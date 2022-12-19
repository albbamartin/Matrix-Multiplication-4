package com.model.matrixes;
import java.util.List;

public class SparseMatrixCRS implements Matrix {

    private List<Integer> pointersToTheFirstNonNullElementsOfEachRowPointers;
    private List<Integer> columnIndexes;
    private List<Double> values;

    int size;
    public SparseMatrixCRS(int size) {
        this.size = size;
    }

    public SparseMatrixCRS setPointersToTheFirstNonNullElementsOfEachRowPointers(List<Integer> pointersToTheFirstNonNullElementsOfEachRowPointers) {
        this.pointersToTheFirstNonNullElementsOfEachRowPointers = pointersToTheFirstNonNullElementsOfEachRowPointers;
        return this;
    }

    public SparseMatrixCRS setColumnIndexes(List<Integer> columnIndexes) {
        this.columnIndexes = columnIndexes;
        return this;
    }

    public SparseMatrixCRS setValues(List<Double> values) {
        this.values = values;
        return this;
    }

    public List<Integer> getPointersToTheFirstNonNullElementsOfEachRowPointers() {
        return pointersToTheFirstNonNullElementsOfEachRowPointers;
    }

    public List<Integer> getColumnIndexes() {
        return columnIndexes;
    }

    public List<Double> getValues() {
        return values;
    }

    @Override
    public int size() {
        return this.size;
    }
}
