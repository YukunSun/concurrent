package jmh.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author sunyk
 **/
public class TryLockTest {
    private static final Object VALUE = new Object();
    private static final List<Object> VALIDATE = new ArrayList<>();
    final static TryLock lock = new TryLock();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        if (lock.tryLock()) {
                            System.out.println(Thread.currentThread() + ":get the lock");
                            if (VALIDATE.size() > 1) {
                                throw new IllegalStateException();
                            }
                            VALIDATE.add(VALUE);
                            TimeUnit.MILLISECONDS.sleep(100);
                        } else {
                            TimeUnit.MILLISECONDS.sleep(100);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock.release()) {
                            System.out.println(Thread.currentThread() + ":release the lock");
                            VALIDATE.remove(VALUE);
                        }
                    }
                }
            }).start();
        }
    }
}

/**
 * lock free
 */
class TryLock {
    private final AtomicBoolean lock = new AtomicBoolean(false);
    private final ThreadLocal<Boolean> threadLocal = ThreadLocal.withInitial(() -> false);

    public boolean tryLock() {
        boolean result = lock.compareAndSet(false, true);
        if (result) {
            threadLocal.set(true);
        }
        return result;
    }

    public boolean release() {
        if (threadLocal.get()) {
            threadLocal.set(false);
            return lock.compareAndSet(true, false);
        } else {
            return false;
        }
    }
}