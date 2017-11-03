package 并发的设计模式;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Description:
 *
 * @author SunYukun
 * @date 2017/8/8
 */
public class Consumer implements Runnable {
    private static Random r = new Random();
    private BlockingQueue<Data> blockingQueue;

    public Consumer(BlockingQueue<Data> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        while (true) {
            try {
                Data data = this.blockingQueue.take();
                Thread.sleep(r.nextInt(1000));
                System.out.println("当前线程：" + Thread.currentThread().getName() + "，消费成功：" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
