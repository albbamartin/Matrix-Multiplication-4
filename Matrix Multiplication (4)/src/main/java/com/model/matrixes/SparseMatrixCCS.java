package com.model.matrixes;

import java.util.List;

public class SparseMatrixCCS implements Matrix {

    private List<Integer> rowIndexes;
    private List<Integer> pointersToTheFirstNonNullElementsOfEachColumnPointers;
    private List<Double> values;

    int size;
    public SparseMatrixCCS(int size) {
        this.size = size;
    }

    public SparseMatrixCCS setRowIndexes(List<Integer> rowIndexes) {
        this.rowIndexes = rowIndexes;
        return this;
    }

    public SparseMatrixCCS setPointersToTheFirstNonNullElementsOfEachColumnPointers(List<Integer> pointersToTheFirstNonNullElementsOfEachColumnPointers) {
        this.pointersToTheFirstNonNullElementsOfEachColumnPointers = pointersToTheFirstNonNullElementsOfEachColumnPointers;
        return this;
    }

    public SparseMatrixCCS setValues(List<Double> values) {
        this.values = values;
        return this;
    }

    public List<Integer> getRowIndexes() {
        return rowIndexes;
    }

    public List<Integer> getPointersToTheFirstNonNullElementsOfEachColumnPointers() {
        return pointersToTheFirstNonNullElementsOfEachColumnPointers;
    }

    public List<Double> getValues() {
        return values;
    }

    @Override
    public int size() {
        return this.size;
    }
}
