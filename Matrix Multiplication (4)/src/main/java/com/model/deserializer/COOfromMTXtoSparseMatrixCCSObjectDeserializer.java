package com.model.deserializer;
import com.model.builders.SparseMatrixCCSBuilder;
import com.model.matrixes.SparseMatrixCCS;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class COOfromMTXtoSparseMatrixCCSObjectDeserializer implements COOfromMTXReader, Deserializer {
    private int nRows;
    private int nColumns;
    private int nNonZeros;

    private final List<Integer> rowIndexes = new ArrayList<>();
    private final List<Integer> columnIndexes = new ArrayList<>();
    private final List<Double> values = new ArrayList<>();

    public COOfromMTXtoSparseMatrixCCSObjectDeserializer() {
        this.nRows = 0;
        this.nColumns = 0;
        this.nNonZeros = 0;
    }

    @Override
    public SparseMatrixCCS deserialize(String filename) throws IOException {
        BufferedReader br = MTXReader(filename);
        String[] matrixFormat = getMatrixFormat(br);
        String line;

        this.nRows = Integer.parseInt(matrixFormat[0]);
        this.nColumns = Integer.parseInt(matrixFormat[1]);
        this.nNonZeros = Integer.parseInt(matrixFormat[2]);

        while (true) {
            line = br.readLine();
            if (line == null)  break;
            matrixFormat = line.split("( )+");

            rowIndexes.add(Integer.parseInt(matrixFormat[0]) - 1);
            columnIndexes.add(Integer.parseInt(matrixFormat[1]) - 1);
            values.add(Double.parseDouble(matrixFormat[2]));
        }

        int columnIndFromLastFirstNonNullElementOfColumnPointer = -1;
        int valueOfTheCurrentNonNullElement = 0;

        SparseMatrixCCSBuilder sparseMatrixCCSBuilder = new SparseMatrixCCSBuilder(nRows);

        for (int i = 0; i<this.nColumns;i++) {
            for (int j = 0; j<this.nNonZeros;j++) {
                if (i == columnIndexes.get(j)) {
                    if (columnIndFromLastFirstNonNullElementOfColumnPointer != columnIndexes.get(j)) {
                        sparseMatrixCCSBuilder.addColumnPrt(valueOfTheCurrentNonNullElement);
                        columnIndFromLastFirstNonNullElementOfColumnPointer = columnIndexes.get(j);
                    }
                    sparseMatrixCCSBuilder.addValue(values.get(j));
                    sparseMatrixCCSBuilder.addRowIndex(rowIndexes.get(j));
                    valueOfTheCurrentNonNullElement ++;
                }
            }
        }

        sparseMatrixCCSBuilder.addColumnPrt(valueOfTheCurrentNonNullElement);
        return sparseMatrixCCSBuilder.toMatrix();
    }
}
