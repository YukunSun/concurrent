package 线程基础;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:volatile不具备原子性
 * <p>
 * 1. 创建10个线程去操作一个static值，每次加1，结果应该是10000，但是结果却不是，所以volatile不具备原子性。
 *
 * @author SunYukun
 * @date 2017/7/2
 */
public class Volatile_02 extends Thread {
//    private static volatile int count = 0;
    private static AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] args) {
        Volatile_02[] ths = new Volatile_02[10];
        for (int i = 0; i < 10; i++) {
            ths[i] = new Volatile_02();
            ths[i].start();
        }
    }

    @Override
    public void run() {
        addCount();
    }

    private void addCount() {
        for (int i = 0; i < 1000; i++) {
//            count++;
            count.incrementAndGet();
        }
        System.out.println("result : " + count);
    }
}
