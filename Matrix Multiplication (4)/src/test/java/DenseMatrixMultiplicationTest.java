import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;
import com.model.operations.parallelism.*;
import com.model.operations.sequential.DenseMatrixLoopInterchangeMultiplication;
import com.model.operations.sequential.DenseMatrixStandardMultiplication;
import com.model.operations.sequential.DenseMatrixTransposedMultiplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;

@RunWith(Parameterized.class)
public class DenseMatrixMultiplicationTest {

    public int size;

    public DenseMatrixMultiplicationTest(int size) {
        this.size = size;
    }

    DenseMatrix denseMatrixA = createRandomMatrix(this.size);
    DenseMatrix denseMatrixB = createRandomMatrix(this.size);

    @Test
    public void multiply_two_random_dense_matrix() {
        DenseMatrixStandardMultiplication denseMatrixStandardMultiplication = new DenseMatrixStandardMultiplication();
        DenseMatrix c = denseMatrixStandardMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
    }

    @Test
    public void multiply_two_random_dense_matrix_with_loop_interchange() {
        DenseMatrixLoopInterchangeMultiplication denseMatrixLoopInterchangeMultiplication = new DenseMatrixLoopInterchangeMultiplication();
        DenseMatrix c = denseMatrixLoopInterchangeMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
    }

    @Test
    public void multiply_two_random_dense_matrix_by_transpose() {
        DenseMatrixTransposedMultiplication denseMatrixTransposedMultiplication = new DenseMatrixTransposedMultiplication();
        DenseMatrix c = denseMatrixTransposedMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
    }

    @Test
    public void multiply_two_random_dense_matrix_with_executer_service() {
        DenseMatrixExecuterServiceMultiplication denseMatrixExecuterServiceMultiplication = new DenseMatrixExecuterServiceMultiplication();
        DenseMatrix c = denseMatrixExecuterServiceMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
    }

    @Test
    public void multiply_two_random_dense_matrix_with_threads() {
        DenseMatrixThreadsMultiplication threadStandardMultiplication2 = new DenseMatrixThreadsMultiplication();
        DenseMatrix c = threadStandardMultiplication2.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
    }

    @Test
    public void multiply_two_random_dense_matrix_with_streams() {
        DenseMatrixStreamsMultiplication streamsMatrixMultiplication= new DenseMatrixStreamsMultiplication();
        DenseMatrix c = streamsMatrixMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
    }

    @Test
    public void multiply_two_random_dense_matrix_with_semaphore() {
        DenseMatrixSemaphoreMultiplication denseMatrixSemaphoreMultiplication = new DenseMatrixSemaphoreMultiplication();
        DenseMatrix c = denseMatrixSemaphoreMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
    }

    @Test
    public void multiply_two_random_dense_matrix_with_atomic_double() {
        DenseMatrixAtomicMultiplication denseMatrixAtomicMultiplication = new DenseMatrixAtomicMultiplication();
        DenseMatrix c = denseMatrixAtomicMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
    }

    private DenseMatrix createRandomMatrix(int size) {
        DenseMatrixBuilder builder = new DenseMatrixBuilder(this.size, this.size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                builder.set(i, j, random.nextDouble());
            }
        }
        return builder.toMatrix();
    }

    @Parameterized.Parameters
    public static List<Integer> parameters(){
        return Arrays.asList(10,100,1000);
    }
}