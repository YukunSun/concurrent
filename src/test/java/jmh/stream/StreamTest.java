package jmh.stream;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author sunyk
 **/
public class StreamTest {
    @Test
    public void streamOperation() {
        //create stream
        Stream stream = Stream.of(1, 2, "3");
        Stream stream2 = Stream.<Integer>of(1, 2, 3);
        Stream stream3 = Stream.<Integer>builder().add(1).add(2).add(3).build();
        Stream stream4 = Stream.empty();

        Stream<Integer> stream5 = Stream.generate(() -> ThreadLocalRandom.current().nextInt(1, 50));
    }

    @Test
    public void filesStream() throws IOException {
        IntStream.range(1, 5).forEach(i -> System.out.println(i));
        IntStream.rangeClosed(1, 5).limit(10).forEach(i -> System.out.println(i));

        Files.lines(Paths.get("/Users/sunyk/Documents/ideaworks/concurrent/src/test/java/jmh/stream/StreamTest.java")).forEach(i -> System.out.println(i));
    }

    @Test
    public void flatStream() throws IOException {
        //v1
        Files.lines(Paths.get("/Users/sunyk/Documents/ideaworks/concurrent/src/test/java/jmh/stream/StreamTest.java"))
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .forEach(System.out::println);
        //v2：帮助理解v1
        Files.lines(Paths.get("/Users/sunyk/Documents/ideaworks/concurrent/src/test/java/jmh/stream/StreamTest.java"))
                .map(line -> Arrays.stream(line.split("\\s+")))//这里是返回的一个个stream
                .flatMap(i -> i)//这里是对stream进行操作，相当于平铺stream，否则的话就得双层循环去遍历
                .forEach(System.out::println);
        //v3:其实就是一个双层stream，即：Stream<Stream<Integer>>
        Stream<Stream<Integer>> s = Stream.of(1, 2, 3, 5).map(i -> Stream.of(2 * i));
        s.map(m -> m.map(i -> i * 10))
                .forEach(i -> i.forEach(ii -> System.out.println(ii)));
    }

    @Test
    public void terminateOps() {
        Assert.assertEquals(IntStream.range(1, 10).allMatch(i -> i > 0), true);

        List<String> words = Arrays.asList("Scala", "Java", "Stream", "Java", "Alex", "Scala", "Scala");
        // 根据words创建一个Stream
        Map<String, Long> count = words.stream()
                // 执行collect操作
                .collect(
                        // 进行分组操作
                        Collectors.groupingBy(Function.identity(), Collectors.counting())
                );
        System.out.println(count);
    }

    @Test
    public void name() {
        Integer sum = Stream.of(1, 2, 3, 4, 5).reduce(0, Integer::sum);
        Integer sum2 = Stream.of(1, 2, 3, 4, 5).reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        Stream stream = Stream.of(1, 2, "3");
        stream.collect(Collectors.toSet());
    }
}
