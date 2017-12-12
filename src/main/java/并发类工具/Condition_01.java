package 并发类工具;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2017/12/11 下午10:24
 * Blog: coderdaily.net
 */
public class Condition_01 implements Runnable {
    public static ReentrantLock reentrantLock = new ReentrantLock();
    public static Condition condition = reentrantLock.newCondition();

    @Override
    public void run() {
        try {
            reentrantLock.lock();
            condition.await();
            System.out.println("thread is going on...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Condition_01 obj = new Condition_01();
        Thread t1 = new Thread(obj);
        t1.start();
        Thread.sleep(2000);
//        通知thread1继续执行
        reentrantLock.lock();
        //从等待队列中唤醒线程
        condition.signal();
        reentrantLock.unlock();
    }
}
