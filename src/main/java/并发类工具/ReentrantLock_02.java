package 并发类工具;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: sun.yukun@foxmail.com
 * Time: 2017/10/23 下午5:33
 * Blog: coderdaily.net
 */
public class ReentrantLock_02 implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    public ReentrantLock_02(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                lock1.lockInterruptibly();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + ":线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock_02 t1 = new ReentrantLock_02(1);
        ReentrantLock_02 t2 = new ReentrantLock_02(2);
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        DeadlockChecker.check();
    }

    static class DeadlockChecker {
        private final static ThreadMXBean mbean = ManagementFactory
                .getThreadMXBean();
        final static Runnable deadlockChecker = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    long[] deadlockedThreadIds = mbean.findDeadlockedThreads();
                    if (deadlockedThreadIds != null) {
                        ThreadInfo[] threadInfos = mbean.getThreadInfo(deadlockedThreadIds);
                        for (Thread t : Thread.getAllStackTraces().keySet()) {
                            for (int i = 0; i < threadInfos.length; i++) {
                                if (t.getId() == threadInfos[i].getThreadId()) {
                                    t.interrupt();
                                }
                            }
                        }
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        public static void check() {
            Thread t = new Thread(deadlockChecker);
            t.setDaemon(true);
            t.start();
        }
    }
}
