package 线程基础;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:这些原子性的类只能保证本身方法的原子性，并不能保证多次操作的原子性。
 * <p>
 * 1. 启动100个线程，加到10，结果应该是1000才对。
 * 1. 可以加上synchronized关键字
 *
 * @author SunYukun
 * @date 2017/7/2
 */
public class Volatile_03 extends Thread {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        final Volatile_03 ato = new Volatile_03();
        List<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(new Runnable() {
                public void run() {
                    System.out.println(ato.addCount());
                }
            }));
        }
        for (Thread i : threads) {
            i.start();
        }
    }

    private synchronized static int addCount() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.addAndGet(1);
        count.addAndGet(2);
        count.addAndGet(3);
        count.addAndGet(4);
        return count.get();
    }
}
