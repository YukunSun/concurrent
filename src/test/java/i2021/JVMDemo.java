package i2021;

import org.junit.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

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
        byte[] array = new byte[50 * 1024 * 1024];
    }

    /**
     * 即使发生gc，ref2 也不会回收，我们通常用的都是这种强引用
     */
    @Test
    public void strongReferenceTest() {
        Object ref = new Object();
        Object ref2 = ref;
        ref = null;
        System.gc();

        System.out.println("ref = " + ref);
        System.out.println("ref2 = " + ref2);//ref2 = java.lang.Object@1b701da1
    }

    /**
     * -Xms1m -Xmx1m -XX:+PrintGCDetails
     * <p>
     * 如果发生gc，如果内存够用就不回收ø
     */
    @Test
    public void softReferenceTest() throws InterruptedException {
        Object obj = new Object();
        SoftReference<Object> ref1 = new SoftReference<>(obj);
        System.out.println("obj = " + obj);

        obj = null;//在这里置为空
        System.gc();
        TimeUnit.SECONDS.sleep(5);

        System.out.println("ref1.get() = " + ref1.get());//此时内存够用，不会回收

        try {
            byte[] bytes = new byte[50 * 1024 * 1024];//故意配置的小内存，分配大对象，会导致回收软引用
        } finally {
            System.gc();
            TimeUnit.SECONDS.sleep(5);

            System.out.println("ref1.get() = " + ref1.get());//ref1.get() = null
        }
    }

    @Test
    public void weakReferenceTest() {
        Object obj = new Object();
        WeakReference<Object> ref = new WeakReference<>(obj);
        System.out.println("obj = " + obj);
        System.out.println("ref.get() = " + ref.get());

        obj = null;

        System.gc();//只要发生gc，无论内存充不充足，弱引用都会回收

        System.out.println("obj = " + obj);
        System.out.println("ref.get() = " + ref.get());//ref.get() = null
    }

    @Test
    public void weakHashMapTest() {

    }


    @Test
    public void phantomReferenceTest() {

    }
}
