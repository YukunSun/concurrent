package lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2018/2/8 下午8:12
 * Blog: coderdaily.net
 * <p>
 * cas
 */
public class AtomicInteger_01 {
    static AtomicInteger atomicInteger = new AtomicInteger();

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                atomicInteger.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new AddThread());
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
        System.out.println(atomicInteger);
    }
}
