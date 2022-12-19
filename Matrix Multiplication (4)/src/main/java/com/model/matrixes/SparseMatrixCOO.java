package com.model.matrixes;

import java.util.List;

public class SparseMatrixCOO implements Matrix {

    private List<Integer> rowIndexes;
    private List<Integer> columnIndexes;
    private List<Double> values;

    int size;
    public SparseMatrixCOO(int size) {
        this.size = size;
    }

    public SparseMatrixCOO setRowIndexes(List<Integer> rowIndexes) {
        this.rowIndexes = rowIndexes;
        return this;
    }

    public SparseMatrixCOO setColumnIndexes(List<Integer> columnIndexes) {
        this.columnIndexes = columnIndexes;
        return this;
    }

    public SparseMatrixCOO setValues(List<Double> values) {
        this.values = values;
        return this;
    }

    public List<Integer> getRowIndexes() {
        return rowIndexes;
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
