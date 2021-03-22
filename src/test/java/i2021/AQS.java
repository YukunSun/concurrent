package i2021;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/3/22 11:42
 * Blog: bengle.me
 */
public class AQS {
    @Test
    public void aqsSourceCodeRead() {

    }

    /**
     * aqs debug and source code read
     * @param args
     */
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            //模拟业务
            try {
                TimeUnit.MINUTES.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "A").start();

        new Thread(() -> {
            lock.lock();
            //模拟业务
            try {
                TimeUnit.MINUTES.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "B").start();

        new Thread(() -> {
            lock.lock();
            //模拟业务
            try {
                TimeUnit.MINUTES.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "C").start();
    }
}
