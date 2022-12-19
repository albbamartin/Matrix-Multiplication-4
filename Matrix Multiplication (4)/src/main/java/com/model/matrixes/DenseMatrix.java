package com.model.matrixes;

public class DenseMatrix implements Matrix {

    private double[][] matrix;

    private int nRows;
    private int nColumns;

    public DenseMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public int size() {
        return this.matrix.length;
    }

    public double get(int row, int column) {
        return this.matrix[row][column];
    }

    public DenseMatrix setnRows(int nRows) {
        this.nRows = nRows;
        return this;
    }

    public DenseMatrix setnColumns(int nColumns) {
        this.nColumns = nColumns;
        return this;
    }

    public int getnRows() {
        return nRows;
    }

    public int getnColumns() {
        return nColumns;
    }

    public void printear() {
        for (int x=0; x < 8; x++) {
            for (int y=0; y < 8; y++) {
                System.out.print (this.matrix[x][y]);
                if (y!=this.matrix[x].length-1) System.out.print("\t");
            }
            System.out.print("\n");
        }
    }
}
