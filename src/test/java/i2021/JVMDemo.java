package i2021;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;
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
    public void weakHashMapTest() throws InterruptedException {
        //1 强引用的 HashMap
        HashMap<Integer, String> hashMap = new HashMap<>();
        Integer key = 1;
        hashMap.put(key, "v1");

        System.out.println("hashMap = " + hashMap);

        key = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);

        System.out.println("hashMap.get(1) = " + hashMap.get(1));//hashMap.get(1) = v1

        //2 弱引用的WeakHashMap
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap();
        Integer k2 = 2;
        weakHashMap.put(k2, "v2");
        System.out.println("weakHashMap = " + weakHashMap);

        k2 = null;
        System.gc();//也就是发生gc之后，v会被回收
        TimeUnit.SECONDS.sleep(3);

        System.out.println("weakHashMap.get(1) = " + weakHashMap.get(1));//weakHashMap.get(1) = null
    }

    /**
     * soft,weak,phantom都会把回收的对象放到ReferenceQueue中
     */
    @Test
    public void referenceQueueTest() throws InterruptedException {
        Object obj = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        WeakReference<Object> softRef = new WeakReference<>(obj, refQueue);
        System.out.println("obj = " + obj);
        System.out.println("softRef.get() = " + softRef.get());//1 softRef.get() = java.lang.Object@2b552920
        System.out.println("refQueue.poll() = " + refQueue.poll());//2 refQueue.poll() = null

        obj = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);

        System.out.println("obj = " + obj);
        System.out.println("softRef.get() = " + softRef.get());//1.1 softRef.get() = null
        System.out.println("refQueue.poll() = " + refQueue.poll());//2.1 refQueue.poll() = java.lang.ref.WeakReference@42f93a98
    }

    /**
     * 代码同referenceQueueTest，
     */
    @Test
    public void phantomReferenceTest() throws InterruptedException {
        Object obj = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        PhantomReference<Object> softRef = new PhantomReference<>(obj, refQueue);
        System.out.println("obj = " + obj);
        System.out.println("softRef.get() = " + softRef.get());//null，却get不到！！！所以如果没有这个queue，虚引用竟然没法工作了
        System.out.println("refQueue.poll() = " + refQueue.poll());//null

        obj = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);

        System.out.println("obj = " + obj);
        System.out.println("softRef.get() = " + softRef.get());//null
        System.out.println("refQueue.poll() = " + refQueue.poll());//refQueue.poll() = java.lang.ref.PhantomReference@726f3b58
    }
}
