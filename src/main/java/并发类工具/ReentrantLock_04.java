package 并发类工具;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2017/12/11 下午9:15
 * Blog: coderdaily.net
 */
public class ReentrantLock_04 implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            lock.tryLock(5, TimeUnit.SECONDS);
//            只会等待5s
//            boolean tryLockFlag = lock.tryLock();
            boolean tryLockFlag = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread() + ":申请状态:" + tryLockFlag);
            if (tryLockFlag) {
                Thread.sleep(6000);
            } else {
                System.out.println("get lock failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock_04 t = new ReentrantLock_04();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();
    }
}
