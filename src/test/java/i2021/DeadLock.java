package i2021;

import java.util.concurrent.TimeUnit;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/3/11 15:34
 * Blog: bengle.me
 */
public class DeadLock implements Runnable {
    Object lockA = new Object();
    Object lockB = new Object();

    public DeadLock(Object lockA, Object lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "current hold:" + lockA + ",try get:" + lockB);
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "current hold:" + lockB + ",try get:" + lockA);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new DeadLock("A", "B"), "t1").start();
        new Thread(new DeadLock("B", "A"), "t2").start();
    }
}
