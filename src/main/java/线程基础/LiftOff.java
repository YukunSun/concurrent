package 线程基础;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: sun.yukun@foxmail.com
 * Time: 2017/9/19 下午10:02
 * Blog: coderdaily.net
 */
public class LiftOff implements Runnable {
    private static int taskCount = 0;
    int countDown = 10;

    final int id = taskCount++;


    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + " " + (countDown > 0 ? countDown : "发射");
    }

    //  表示要执行的任务
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            Thread.yield();
        }
    }

    public static void main(String[] args) {
//        LiftOff liftOff = new LiftOff(10);
//        liftOff.run();

        ExecutorService service = Executors.newCachedThreadPool();
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        ExecutorService service = Executors.newSingleThreadExecutor();
//        ExecutorService service = Executors.newScheduledThreadPool(1);
        for (int i = 0; i < 10; i++) {
            service.execute(new LiftOff());
        }
        service.shutdown();
        System.out.println("task over ....");
    }
}
