package 并发的设计模式;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description:
 *
 * @author SunYukun
 * @date 2017/8/8
 */
public class ProviderConsumer_01 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Data> queue = new LinkedBlockingQueue<Data>(10);
//生产者引用缓冲区
        Provider p1 = new Provider(queue);
        Provider p2 = new Provider(queue);
        Provider p3 = new Provider(queue);
//消费者引用缓冲区
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);
//        Executors相当于工厂方法
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(p1);
        pool.execute(p2);
        pool.execute(p3);
        pool.execute(c1);
        pool.execute(c2);
        pool.execute(c3);
//3s以后停掉消费者
        Thread.sleep(3000);
        p1.stop();
        p2.stop();
        p3.stop();

    }
}
