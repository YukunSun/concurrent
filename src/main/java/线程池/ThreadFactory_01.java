package 线程池;

import java.util.concurrent.*;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2018/2/6 下午8:30
 * Blog: coderdaily.net
 * <p>
 * 线程是靠ThreadFactory来产生的
 */
public class ThreadFactory_01 {
    static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println("mytask-" + Thread.currentThread().getId());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        ExecutorService service = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                System.out.println("create--" + t);
                return t;
            }
        });

        for (int i = 0; i < 5; i++) {
            service.submit(task);
        }
        Thread.sleep(2000);
    }
}
