package benchmarking;


import com.model.operations.parallelism.*;
import com.model.operations.sequential.DenseMatrixLoopInterchangeMultiplication;
import com.model.operations.sequential.MatrixMultiplication;
import com.model.builders.DenseMatrixBuilder;
import com.model.matrixes.DenseMatrix;
import com.model.operations.sequential.DenseMatrixStandardMultiplication;
import com.model.operations.sequential.DenseMatrixTransposedMultiplication;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Fork(value = 2)
@Warmup(iterations = 3, time = 2)
@Measurement(iterations = 3, time = 2)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class DenseBenchMark {
    private static final int SIZE = 1024;

    @Benchmark
    public void standardMatrixMultiplication() {
        executeWith(new DenseMatrixStandardMultiplication());
    }

    @Benchmark
    public void transposedMultiplication() {
        executeWith(new DenseMatrixTransposedMultiplication());
    }

    @Benchmark
    public void loopInterchangeMultiplication() {
        executeWith(new DenseMatrixLoopInterchangeMultiplication());
    }

    @Benchmark
    public void atomicMultiplication() {
        executeWith(new DenseMatrixAtomicMultiplication());
    }

    @Benchmark
    public void executerServiceMultiplication() {
        executeWith(new DenseMatrixExecuterServiceMultiplication());
    }

    @Benchmark
    public void semaphoreMultiplication() {
        executeWith(new DenseMatrixSemaphoreMultiplication());
    }

    @Benchmark
    public void streamsMultiplication() {
        executeWith(new DenseMatrixStreamsMultiplication());
    }

    @Benchmark
    public void threadsMultiplication() {
        executeWith(new DenseMatrixThreadsMultiplication());
    }


    private void executeWith(MatrixMultiplication matrixMultiplication) {
        matrixMultiplication.multiply(createRandomMatrix(SIZE), createRandomMatrix(SIZE));
    }

    private DenseMatrix createRandomMatrix(int size) {
        DenseMatrixBuilder builder = new DenseMatrixBuilder(size, size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                builder.set(i, j, random.nextDouble());
            }
        }
        return builder.toMatrix();
    }


}
