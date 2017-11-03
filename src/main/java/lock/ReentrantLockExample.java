package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁
 */
public class ReentrantLockExample {
    int a = 0;
    ReentrantLock lock = new ReentrantLock();

    public void writer() {
        lock.lock();         //获取锁
        try {
            a++;
        } finally {
            lock.unlock();  //释放锁
        }
    }

    public void reader() {
        lock.lock();        //获取锁
        try {
            int i = a;
        } finally {
            lock.unlock();  //释放锁
        }
    }
}
