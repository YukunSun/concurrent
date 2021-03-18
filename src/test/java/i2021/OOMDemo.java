package i2021;

import org.junit.Test;

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

    @Test
    public void OOMOfDirectMemory() {
        //maxDirectMemory
        //不用copy
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
