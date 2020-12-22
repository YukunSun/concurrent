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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2020/12/21 22:31
 * Blog: bengle.me
 */
@Measurement(iterations = 3)
@Warmup(iterations = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class AtomicIntegerVsSynchronizedVsLock {
    @State(Scope.Group)
    public static class IntMonitor {
        private int x;
        private final Lock lock = new ReentrantLock();

        public void lockInc() {
            lock.lock();
            try {
                x++;
            } finally {
                lock.unlock();
            }
        }

        public void synInc() {
            synchronized (this) {
                x++;
            }
        }
    }

    @State(Scope.Group)
    public static class AtomicIntegerMonitor {
        AtomicInteger x = new AtomicInteger();

        public void inc() {
            x.incrementAndGet();
        }
    }

    @Group("groupLock")
    @GroupThreads(10)
    @Benchmark
    public void lockInc(IntMonitor intMonitor) {
        intMonitor.lockInc();
    }

    @Group("groupSync")
    @GroupThreads(10)
    @Benchmark
    public void syncInc(IntMonitor intMonitor) {
        intMonitor.synInc();
    }

    @Group("groupAtomicInteger")
    @GroupThreads(10)
    @Benchmark
    public void atomicIntegerInc(AtomicIntegerMonitor atomicIntegerMonitor) {
        atomicIntegerMonitor.inc();
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(AtomicIntegerVsSynchronizedVsLock.class.getSimpleName())
                .forks(1)
                .timeout(TimeValue.seconds(10))
                .addProfiler(StackProfiler.class)
                .build();
        new Runner(options).run();
    }
}
