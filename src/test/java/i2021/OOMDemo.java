package i2021;

import org.junit.Test;
import sun.misc.VM;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/3/17 17:37
 * Blog: bengle.me
 */
public class OOMDemo {
    /**
     * java.lang.StackOverflowError
     * at i2021.OOMDemo.stackOverflowErrorTest(OOMDemo.java:13)
     * at i2021.OOMDemo.stackOverflowErrorTest(OOMDemo.java:13)
     * ...
     */
    @Test
    public void stackOverflowErrorTest() {
        stackOverflowErrorTest();//栈管的是运行
    }

    /**
     * -Xmx10m
     */
    @Test
    public void OOMOfJavaHeapSpace() {
        byte[] bytes = new byte[20 * 1024 * 1024];
    }

    /**
     * -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     */
    @Test
    public void OOMOfDirectMemory() {
        long maxDirectMemory = VM.maxDirectMemory();//默认大概为内存的1/4
        System.out.println("maxDirectMemory(Mb) = " + maxDirectMemory / 1024 / 1024);

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);
    }

    /**
     * -Xmx10m -Xms10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     */
    @Test
    public void gcOverheadLimitExceeded() {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("i = " + i);
        }
    }

    /**
     * eg.linux可创建1024个线程,ulimit -u
     */
    @Test
    public void unableToCreateNativeThread() {

    }

    /**
     * 元空间用的也是本地内存，并没有用虚拟机内存
     */
    @Test
    public void MetaSpace() {

    }
}