package i2021;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Test;
import sun.misc.VM;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
     * -Xmx10m -Xms10m -XX:+PrintGCDetails
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
        for (int i = 0; ; i++) {
            System.out.println("i = " + i);
            new Thread(() -> {//创建线程，并且一直不退出
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 元空间用的也是本地内存，并没有用虚拟机内存
     * <p>
     * -XX:+PrintGCDetails -XX:MaxMetaspaceSize=10M -XX:MetaspaceSize=5M
     */
    @Test
    public void oomOfMetaSpace() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMDemo.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o, objects));
            enhancer.create();
        }
    }
}
