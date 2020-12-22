package jmh.atomic;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sunyk
 **/
@Measurement(iterations = 3)
@Warmup(iterations = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class AtomicIntegerLazySetVsSet {
    private AtomicInteger i;

    @Setup(Level.Iteration)
    public void setUp() {
        this.i = new AtomicInteger(0);
    }

    @Benchmark
    public void lazySet() {
        i.lazySet(10);
    }

    @Benchmark
    public void set() {
        i.set(10);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(AtomicIntegerLazySetVsSet.class.getSimpleName())
                .forks(1)
                .timeout(TimeValue.seconds(10))
                .addProfiler(StackProfiler.class)
                .build();
        new Runner(options).run();
    }
}
