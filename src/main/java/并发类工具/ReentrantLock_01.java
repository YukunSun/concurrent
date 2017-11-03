package 并发类工具;

import javax.management.relation.RoleUnresolved;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: sun.yukun@foxmail.com
 * Time: 2017/9/14 下午9:53
 * Blog: coderdaily.net
 * <p>
 * 有两个线程都对i进行++操作，为了保证线程安全，使用了 ReentrantLock，从用法上可以看出，与 synchronized相比， ReentrantLock就稍微复杂一点。因为必须在finally中进行解锁操作，如果不在 finally解锁，有可能代码出现异常锁没被释放，而synchronized是由JVM来释放锁。
 */
public class ReentrantLock_01 implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    public void run() {
        for (int j = 0; j < 10000000; j++) {
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ReentrantLock_01 instance = new ReentrantLock_01();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();
//等t1,t2都退出了，主线程才退出。
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
