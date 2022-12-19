package com.model.deserializer;
import com.model.builders.SparseMatrixCRSBuilder;
import com.model.matrixes.SparseMatrixCRS;
import java.io.BufferedReader;
import java.io.IOException;

public class COOfromMTXtoSparseMatrixCRSObjectDeserializer implements COOfromMTXReader, Deserializer {
    private int nRows;
    private int nColumns;
    private int nNonZeros;

    public COOfromMTXtoSparseMatrixCRSObjectDeserializer() {
        this.nRows = 0;
        this.nColumns = 0;
        this.nNonZeros = 0;
    }

    @Override
    public SparseMatrixCRS deserialize(String filename) throws IOException {
        BufferedReader br = MTXReader(filename);
        String[] matrixFormat = getMatrixFormat(br);
        String line;

        this.nRows = Integer.parseInt(matrixFormat[0]);
        this.nColumns = Integer.parseInt(matrixFormat[1]);
        this.nNonZeros = Integer.parseInt(matrixFormat[2]);

        SparseMatrixCRSBuilder sparseMatrixCRSBuilder = new SparseMatrixCRSBuilder(this.nRows);

        int rowIndFromLastFirstNonNullElementOfRowPointer = -1;
        int valueOfTheCurrentNonNullElement = 0;

        while (true) {
            line = br.readLine();
            if (line == null)  break;
            matrixFormat = line.split("( )+");

            if (rowIndFromLastFirstNonNullElementOfRowPointer != Integer.parseInt(matrixFormat[0]) - 1) {
                sparseMatrixCRSBuilder.addRowPrt(valueOfTheCurrentNonNullElement);
                rowIndFromLastFirstNonNullElementOfRowPointer = Integer.parseInt(matrixFormat[0]) - 1;
            }
            sparseMatrixCRSBuilder.addValue((Double.valueOf(matrixFormat[2].trim())).doubleValue());
            sparseMatrixCRSBuilder.addColumnIndex(Integer.parseInt(matrixFormat[1]) - 1);
            valueOfTheCurrentNonNullElement++;

        }
        sparseMatrixCRSBuilder.addRowPrt(valueOfTheCurrentNonNullElement);
        return sparseMatrixCRSBuilder.toMatrix();
    }
}
