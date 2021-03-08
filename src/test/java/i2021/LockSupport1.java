package i2021;

import java.util.concurrent.TimeUnit;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/3/8 15:08
 * Blog: bengle.me
 * <p>
 * 使用 wait/notify 来做线程间通信。可能会有xx问题 -》证明了为什么需要有LockSupport
 */
public class LockSupport1 {
    static Object lock = new Object();

    static void func() {
        new Thread(() -> {
            synchronized (lock) {//如果没有synchronized也就不可以使用。Exception in thread "t1" java.lang.IllegalMonitorStateException: current thread is not owner
                System.out.println("t1");
                try {//这里模拟业务
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 notified");
            }
        }, "t1").start();
    }

    static void func2() {
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 notify t1");
                lock.notify();
            }
        }, "t2").start();
    }

    public static void main(String[] args) {
        func();
        func2();
//        func2();func();//如果两个线程调换顺序，也就是先让t2通知t1的话，可能导致t1永远也激活不了了
    }
}
