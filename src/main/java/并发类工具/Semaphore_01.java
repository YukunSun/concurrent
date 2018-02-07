package 并发类工具;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2017/12/12 下午10:05
 * Blog: coderdaily.net
 */
public class Semaphore_01 implements Runnable {
    //    允许5个线程共享一个锁
    final Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {
        try {
//            申请获得锁
            semaphore.acquire();
//            模拟耗时操作
            Thread.sleep(2000);
            System.out.println(Thread.currentThread() + ":完成");
//            务必释放锁，否则别的线程进不去
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final Semaphore_01 obj = new Semaphore_01();
        for (int i = 0; i < 20; i++) {
            executorService.submit(obj);
        }
    }
}
