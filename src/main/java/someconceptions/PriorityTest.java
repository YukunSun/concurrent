package someconceptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: sunyukun.china@gmail.com
 * Time: 2016/11/29 22:25
 * Blog: coderdaily.net
 */
public class PriorityTest implements Runnable {
    private int countDown = 5;
    private volatile double d;//确保不进行任何编译器优化
    private int priority;

    public PriorityTest(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread() + "--" + countDown;
    }

    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 0; i < 10000; i++) {
                d += (Math.PI + Math.E) / (double) i;//运算浮点类型，让线程调度机制来得及介入
                if (i % 1000 == 0)
                    Thread.yield();//“让步”只起到一个“建议”的作用，对于任何重要的控制都不能依赖于yield()
            }
            System.out.println(this);
            if (--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new PriorityTest(Thread.MIN_PRIORITY));
        }
        exec.execute(new PriorityTest(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}

