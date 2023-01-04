import com.model.deserializer.COOfromMTXtoSparseMatrixCCSObjectDeserializer;
import com.model.deserializer.COOfromMTXtoSparseMatrixCRSObjectDeserializer;
import com.model.matrixes.SparseMatrixCCS;
import com.model.matrixes.SparseMatrixCOO;
import com.model.matrixes.SparseMatrixCRS;
import com.model.operations.sequential.SparseMatrixMultiplication;
import org.junit.Test;

import java.io.IOException;


public class BigSparseMultiplicationTest {
    public int size;


    @Test
    public void multiply_two_MTX_Compressed_Matrix() throws IOException {

        COOfromMTXtoSparseMatrixCRSObjectDeserializer deserializer1 = new COOfromMTXtoSparseMatrixCRSObjectDeserializer();
        SparseMatrixCRS m1 = deserializer1.deserialize(System.getProperty("user.dir") + "/src/test/mtx/mc2depi.mtx");

        COOfromMTXtoSparseMatrixCCSObjectDeserializer deserializer2 = new COOfromMTXtoSparseMatrixCCSObjectDeserializer();
        SparseMatrixCCS m2 = deserializer2.deserialize(System.getProperty("user.dir") + "/src/test/mtx/mc2depi.mtx");

        SparseMatrixMultiplication sparseMatrixMultiplication = new SparseMatrixMultiplication();
        SparseMatrixCOO m3 = sparseMatrixMultiplication.multiply(m1, m2);

    }
}