package 并发的设计模式;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @author SunYukun
 * @date 2017/8/8
 */
public class Provider implements Runnable {
    private static AtomicInteger count = new AtomicInteger();
    private static Random r = new Random();
    //共享缓存区
    private BlockingQueue<Data> blockingQueue;
    //保持可见
    private volatile boolean isRunning = true;

    public Provider(BlockingQueue<Data> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        while (isRunning) {
            try {
                //假设的生产数据耗时
                Thread.sleep(r.nextInt(1000));
                int id = count.incrementAndGet();
                Data data = new Data(id, "数据为：" + id);
                System.out.println("当前线程：" + Thread.currentThread().getName() + "，放入数据：" + data.toString());
                if (!this.blockingQueue.offer(data, 5, TimeUnit.SECONDS)) {
                    System.out.println("放入缓冲区失败。。。");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
