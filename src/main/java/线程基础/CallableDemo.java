package 线程基础;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Author: sun.yukun@foxmail.com
 * Time: 2017/9/20 下午8:37
 * Blog: coderdaily.net
 */
public class CallableDemo {
    static class TaskWithResult implements Callable<String> {
        private int id;

        public TaskWithResult(int id) {
            this.id = id;
        }

        public String call() throws Exception {
            return "result  :" + id;
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> futures = new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++) {
            Future<String> f = executorService.submit(new TaskWithResult(i));
            futures.add(f);
        }
        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                //防止新任务提交，当前线程将继续运行在此方法调用之前所提交的所有任务，此程序将在所有任务完成之后退出。
                executorService.shutdown();
            }
        }
    }
}
