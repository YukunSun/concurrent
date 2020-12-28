package jmh.stream;

/**
 * @author sunyk
 **/

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Warmup(iterations = 5)
@Measurement(iterations = 5)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class StreamIntegerVsIntStream {
    // 定义Stream<Integer>
    private Stream<Integer> integerStream;
    // 定义IntStream
    private IntStream intStream;

    // 注意Level必须是Invocation，原因是Stream只能操作一次，前文中已经解释过
    @Setup(Level.Invocation)
    public void init() {
        this.integerStream = IntStream.range(0, 100).boxed();
        this.intStream = IntStream.range(0, 100);
    }

    // Stream<Integer>所有的操作都需要经历拆箱和封箱的过程
    @Benchmark
    public void streamIntegerReduce(Blackhole hole) {
        int result = this.integerStream
                .map((Integer i) -> i * 10)
                .reduce(0, (Integer a, Integer b) ->
                {
                    return a + b;
                });
        hole.consume(result);
    }

    // Stream<Integer>在进行操作之前先主动拆箱，然后再进行其他的操作
    @Benchmark
    public void streamIntegerUnboxThenReduce(Blackhole hole) {
        int result = integerStream
                .mapToInt(Integer::intValue)
                .map((int i) -> i * 10)
                .reduce(0, (int a, int b) ->
                {
                    return a + b;
                });
        hole.consume(result);
    }

    // 所有的操作都是基于基本类型int的
    @Benchmark
    public void intStreamReduce(Blackhole hole) {
        int result = intStream
                .map((int i) -> i * 10)
                .reduce(0, (int a, int b) ->
                {
                    return a + b;
                });
        hole.consume(result);
    }

    public static void main(String[] args) throws RunnerException {
        final Options opt = new OptionsBuilder()
                .include(StreamIntegerVsIntStream.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}