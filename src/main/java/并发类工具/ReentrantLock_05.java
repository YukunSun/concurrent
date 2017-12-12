package 并发类工具;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2017/12/11 下午9:48
 * Blog: coderdaily.net
 */
public class ReentrantLock_05 implements Runnable {
    public static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        ReentrantLock_05 obj = new ReentrantLock_05();
        Thread t1 = new Thread(obj, "thread 1");
        Thread t2 = new Thread(obj, "thread 2");

        t1.start();
        t2.start();
    }
}
