package jmh.forkjoin;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/6/10 22:57
 * Blog: sunyukun.com
 */
public class ForkJoinTest {
    @Test
    public void taskOfForkJoinTest() throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        DemoTask task = new DemoTask(1, 100);
        Future<Integer> result = pool.submit(task);
        Assert.assertEquals(5050, result.get().intValue());
        System.out.println("result.get() = " + result.get());
    }

    /**
     * 有返回结果
     */
    class DemoTask extends RecursiveTask<Integer> {
        int threshold = 2;
        int start, end;
        int sum = 0;

        public DemoTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            boolean canCompute = (end - start) <= threshold;
            if (canCompute) {
                for (int i = start; i <= end; i++) {//这里是i<=end
                    sum += i;
                }
            } else {
                int middle = (start + end) / 2;
                DemoTask left = new DemoTask(start, middle);
                DemoTask right = new DemoTask(middle + 1, end);
                left.fork();
                right.fork();
                //等待他们分配完并拿到结果
                int v1 = left.join();
                int v2 = right.join();
                //合并子任务
                sum = v1 + v2;
            }
            return sum;
        }
    }

    @Test
    public void actionOfForkJoinTest() throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        DemoAction action = new DemoAction(1, 1000);
        pool.submit(action);
    }

    /**
     * 无返回结果
     */
    class DemoAction extends RecursiveAction {
        int threshold = 2;
        int start, end;
        int sum = 0;

        public DemoAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            boolean canCompute = (end - start) <= threshold;
            if (canCompute) {
                for (int i = start; i <= end; i++) {//这里是i<=end
                    sum += i;
                }
            } else {
                int middle = (start + end) / 2;
                DemoTask left = new DemoTask(start, middle);
                DemoTask right = new DemoTask(middle + 1, end);
                left.fork();
                right.fork();
                //等待他们分配完并拿到结果
                int v1 = left.join();
                int v2 = right.join();
                //合并子任务
                sum = v1 + v2;
            }
            System.out.println("sum = " + sum);
        }
    }
}
