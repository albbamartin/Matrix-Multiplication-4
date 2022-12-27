package benchmarking;

import com.model.builders.SparseMatrixCCSBuilder;
import com.model.builders.SparseMatrixCRSBuilder;
import com.model.matrixes.*;
import com.model.operations.parallelism.SparseMatrixExecuterServiceMultiplication;
import com.model.operations.parallelism.SparseMatrixSemaphoreMultiplication;
import com.model.operations.parallelism.SparseMatrixStreamsMultiplication;
import com.model.operations.sequential.MatrixMultiplication;
import com.model.operations.sequential.SparseMatrixMultiplication;
import org.openjdk.jmh.annotations.*;

import java.util.Random;

@BenchmarkMode(Mode.AverageTime)
@Fork(value = 2)
@Warmup(iterations = 3, time = 2)
@Measurement(iterations = 3, time = 2)

public class SparseBenchMark {

    public static final int SIZE = 1024;
    public static final SparseMatrixCCS a = createRandomSparseMatrixCCS(SIZE);
    public static final SparseMatrixCRS b = createRandomSparseMatrixCRS(SIZE);


    @Benchmark
    public void standardSparseMultiplication() {
        executeWith(new SparseMatrixMultiplication());
    }

    @Benchmark
    public void executorServiceMultiplication() {
        executeWith(new SparseMatrixExecuterServiceMultiplication());
    }

    @Benchmark
    public void streamMultiplication() {
        executeWith(new SparseMatrixStreamsMultiplication());
    }

    @Benchmark
    public void semaphoreMultiplication() {
        executeWith(new SparseMatrixSemaphoreMultiplication());
    }

    private void executeWith(MatrixMultiplication matrixMultiplication)  {
        matrixMultiplication.multiply(a, b);
    }


    private static SparseMatrixCCS createRandomSparseMatrixCCS(int size) {
        SparseMatrixCCSBuilder builder = new SparseMatrixCCSBuilder(size);
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int numNonZeros = random.nextInt(size) + 1;

            for (int j = 0; j < numNonZeros; j++) {
                builder.addRowIndex(random.nextInt(size));
                builder.addValue(random.nextDouble());
            }

            builder.addColumnPrt(i * numNonZeros);
        }

        return builder.toMatrix();
    }

    private static SparseMatrixCRS createRandomSparseMatrixCRS(int size) {

        SparseMatrixCRSBuilder builder = new SparseMatrixCRSBuilder(size);
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int numNonZeros = random.nextInt(size) + 1;

            for (int j = 0; j < numNonZeros; j++) {
                builder.addColumnIndex(random.nextInt(size));
                builder.addValue(random.nextDouble());
            }

            builder.addRowPrt(i * numNonZeros);
        }

        return builder.toMatrix();
    }
}