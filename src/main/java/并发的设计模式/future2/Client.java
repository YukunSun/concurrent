package 并发的设计模式.future2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2019/9/23 21:19
 * Blog: coderdaily.net
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        //构造 FutureTask
        FutureTask<String> futureTask = new FutureTask<String>(new RealData("ping"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(futureTask);

        System.out.println("请求完毕:" + futureTask.isDone());

        Thread.sleep(2 * 1000);//模拟其他耗时业务

        try {
            System.out.println("真实数据：" + futureTask.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
