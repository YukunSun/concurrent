package 并发类工具;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2017/12/17 下午3:57
 * Blog: coderdaily.net
 */
public class CyclicBarrier_01 {
    public static class Soldier implements Runnable {
        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        public Soldier(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
//                等待士兵到齐
                cyclicBarrier.await();

//                工作
                doWork();
//              等待他们一起完成工作
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void doWork() {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(soldier + ": done");
        }
    }

    public static class BarrierRun implements Runnable {
        boolean flag;
        int n;

        public BarrierRun(boolean flag, int n) {
            super();
            this.flag = flag;
            this.n = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println(n + "个任务完成");
            } else {
                System.out.println(n + "个士兵集合完成");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int n = 10;
        Thread[] threads = new Thread[n];
        boolean flag = false;
//        设置n个线程，并设置任务
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n, new BarrierRun(flag, n));
        System.out.println("开始集合：");
        for (int i = 0; i < n; i++) {
            System.out.println(i + "集合");
            threads[i] = new Thread(new Soldier("士兵" + i, cyclicBarrier));
            threads[i].start();
        }
    }
}


