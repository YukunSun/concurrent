package 线程基础;

/**
 * Author: sun.yukun@foxmail.com
 * Time: 2017/9/20 下午9:37
 * Blog: coderdaily.net
 */
public class Priority {
    public static class High extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (Priority.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("High");
                        break;
                    }
                }
            }
        }
    }

    public static class Low extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (Priority.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("Low");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        High high = new High();
        Low low = new Low();
        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        low.start();
        high.start();
    }
}
