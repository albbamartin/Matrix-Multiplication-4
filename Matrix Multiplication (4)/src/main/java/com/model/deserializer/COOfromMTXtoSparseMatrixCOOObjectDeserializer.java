package com.model.deserializer;
import com.model.builders.SparseMatrixCOOBuilder;
import com.model.matrixes.SparseMatrixCOO;
import java.io.BufferedReader;
import java.io.IOException;

public class COOfromMTXtoSparseMatrixCOOObjectDeserializer implements COOfromMTXReader, Deserializer {

    private int nRows;
    private int nColumns;
    private int nNonZeros;

    public COOfromMTXtoSparseMatrixCOOObjectDeserializer() {
        this.nRows = 0;
        this.nColumns = 0;
        this.nNonZeros = 0;
    }

    @Override
    public SparseMatrixCOO deserialize(String filename) throws IOException {
        BufferedReader br = MTXReader(filename);
        String[] matrixFormat = getMatrixFormat(br);
        String line;

        this.nRows = Integer.parseInt(matrixFormat[0]);
        this.nColumns = Integer.parseInt(matrixFormat[1]);
        this.nNonZeros = Integer.parseInt(matrixFormat[2]);

        SparseMatrixCOOBuilder sparseMatrixCOOBuilder = new SparseMatrixCOOBuilder(nRows);

        while (true) {
            line = br.readLine();
            if (line == null)  break;
            matrixFormat = line.split("( )+");

            sparseMatrixCOOBuilder.set( (Integer.valueOf(matrixFormat[0].trim())).intValue() - 1,
                    (Integer.valueOf(matrixFormat[1].trim())).intValue() - 1,
                    (Double.valueOf(matrixFormat[2].trim())).doubleValue());
        }
        return sparseMatrixCOOBuilder.toMatrix();
    }
}