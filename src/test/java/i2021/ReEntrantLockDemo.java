package i2021;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/3/8 11:48
 * Blog: bengle.me
 */
public class ReEntrantLockDemo {
    static Object lock = new Object();

    //eg1：被synchronized的lock对象是可重入的
    static void func() {//这就说明 synchronized 是可重入的
        synchronized (lock) {
            System.out.println("layer 1");
            synchronized (lock) {
                System.out.println("layer 2");
                synchronized (lock) {
                    System.out.println("layer 3");
                }
            }
        }
    }

    //eg2：被synchronized修饰的不同的方法也是可重入的
    static synchronized void f1() {
        System.out.println("layer 1");
        f2();
    }

    static synchronized void f2() {
        System.out.println("layer 2");
        f3();
    }

    static synchronized void f3() {
        System.out.println("layer 3");
    }

    //eg3:也是可重入的
    static Lock lock2 = new ReentrantLock();

    static void func5() {
        lock2.lock();
        System.out.println("reentrant 1");

        lock2.lock();
        System.out.println("reentrant 2");
        lock2.unlock();

        lock2.unlock();
    }

    public static void main(String[] args) {
//        func();
//        f1();
        func5();//lock,unlock需要成对出现，否则其他线程可能获取不了锁.
    }
}
