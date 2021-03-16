package i2021;

import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/3/16 16:54
 * Blog: bengle.me
 */
public class JVMDemo {
    @Test
    public void memory() {
        //Xms：大约1/64
        long totalMemory = Runtime.getRuntime().totalMemory();
        //Xmx：约1/4
        long maxMemory = Runtime.getRuntime().maxMemory();

        System.out.println("totalMemory = " + totalMemory / 1024 / 1024);
        System.out.println("maxMemory = " + maxMemory / 1024 / 1024);
    }

    /**
     * -XX:+PrintGCDetails -Xmx10m
     */
    @Test
    public void gcLog() {
        System.out.println("gc log...............");
        byte[] array = new byte[20 * 1024 * 1024];
    }
}
