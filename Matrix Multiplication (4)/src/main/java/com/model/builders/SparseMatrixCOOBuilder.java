package com.model.builders;
import com.model.matrixes.SparseMatrixCOO;
import java.util.ArrayList;
import java.util.List;

public class SparseMatrixCOOBuilder implements Builder {

    private final List<Integer> rowIndexes = new ArrayList<>();
    private final List<Integer> columnIndexes = new ArrayList<>();
    private final List<Double> values = new ArrayList<>();

    int size;
    public SparseMatrixCOOBuilder(int size) {
        this.size = size;
    }

    public void set(int rowPrt, int columnInd, double value) {
        rowIndexes.add(rowPrt);
        columnIndexes.add(columnInd);
        values.add(value);
    }

    @Override
    public SparseMatrixCOO toMatrix() {
        return new SparseMatrixCOO(size)
                .setRowIndexes(rowIndexes)
                .setColumnIndexes(columnIndexes)
                .setValues(values);
    }
}
