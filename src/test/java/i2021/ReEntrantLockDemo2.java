package i2021;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/3/8 12:19
 * Blog: bengle.me
 */
public class ReEntrantLockDemo2 {
    static Lock lock = new ReentrantLock();

    static void func() {
        new Thread(() -> {
            lock.lock();
            System.out.println("t1");

            try {
                lock.lock();
                System.out.println("t1-1");
            } finally {
                lock.unlock();
            }

            lock.unlock();
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            System.out.println("t2");
        }, "t2").start();
    }

    public static void main(String[] args) {
        func();//lock,unlock需要成对出现，否则t2可能获取不了锁
    }
}
