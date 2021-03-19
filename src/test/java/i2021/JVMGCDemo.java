package i2021;

import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/3/17 21:45
 * Blog: bengle.me
 */
public class JVMGCDemo {
    /**
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseSerialGC
     * <p>
     * 会使用Serial+SerialOld
     */
    @Test
    public void useSerialGC() {
        byte[] bytes = new byte[20 * 1024 * 1024];
    }

    /**
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseParNewGC
     */
    @Test
    public void useParNewGC() {
        byte[] bytes = new byte[20 * 1024 * 1024];
    }

    /**
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseParallelGC
     */
    @Test
    public void useParallelGC() {
        byte[] bytes = new byte[20 * 1024 * 1024];
    }

    /**
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseParallelOldGC -XX:+PrintCommandLineFlags
     */
    @Test
    public void parallelOld() {
        byte[] bytes = new byte[20 * 1024 * 1024];
    }

    /**
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC
     */
    @Test
    public void cms() {
        byte[] bytes = new byte[20 * 1024 * 1024];
    }

    /**
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
     */
    @Test
    public void g1() {
        byte[] bytes = new byte[20 * 1024 * 1024];
    }
}
