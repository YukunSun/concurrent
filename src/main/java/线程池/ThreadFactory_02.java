package 线程池;

import java.util.concurrent.*;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2018/2/6 下午8:30
 * Blog: coderdaily.net
 * <p>
 * 可以扩展线程池
 */
public class ThreadFactory_02 {
    static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println("mytask-" + Thread.currentThread().getId());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        ExecutorService service = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("after");
            }

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("before");
            }

            @Override
            protected void terminated() {
                System.out.println("exit");
            }
        };

        for (int i = 0; i < 5; i++) {
            service.submit(task);
        }
        Thread.sleep(2000);
        service.shutdown();
    }
}
