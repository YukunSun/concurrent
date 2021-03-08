package i2021;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/3/8 15:30
 * Blog: bengle.me
 * <p>
 * 使用 await/singal 来做线程间通信。可能会有2个问题 -》证明了为什么需要有LockSupport
 */
public class LockSupport2 {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {

        new Thread(() -> {
            try {//2.如果signal先于await执行，可能会导致t1不能再被唤醒
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();//1.如果await/singal不被lock包围，怎会报错。Exception in thread "t1" java.lang.IllegalMonitorStateException

            System.out.println("t1");
            try {//这里模拟业务
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 was notified");
            lock.unlock();
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            System.out.println("t2 notify t1");
            condition.signal();
            lock.unlock();
        }, "t2").start();
    }
}
