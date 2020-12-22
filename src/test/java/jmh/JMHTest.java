package jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sunyk
 **/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class JMHTest {
    String data = "example data";

    private ArrayList<String> arrayList;
    private LinkedList<String> linkedList;

    @Setup(Level.Iteration)
    public void setUp() {
        arrayList = new ArrayList<String>(1024*100);
        linkedList = new LinkedList<String>();
    }

    @Benchmark
    public List<String> arrayListAdd() {
        this.arrayList.add(data);
        return this.arrayList;
    }

    @Benchmark
    public List<String> linkedListAdd() {
        this.linkedList.add(data);
        return this.linkedList;
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(JMHTest.class.getSimpleName())
                .forks(1)
                .measurementIterations(3)
                .warmupIterations(1)
                .build();
        new Runner(options).run();
    }
}

