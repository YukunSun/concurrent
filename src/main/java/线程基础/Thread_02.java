package 线程基础;

/**
 * Author: sun.yukun@foxmail.com
 * Time: 2017/11/3 上午11:41
 * Blog: coderdaily.net
 */
public class Thread_02 extends Thread {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("interrupted...");
                        break;
                    }
                    System.out.println("running...");
                }
            }
        });
        t.start();
        t.interrupt();
    }
}
