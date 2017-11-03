package 线程基础;

/**
 * Description:todo 没太明白
 *
 * @author SunYukun
 * @date 2017/7/5
 */
public class Synchronized_03 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                Sub sub = new Sub();
                sub.operationSub();
            }
        });
        t1.start();
    }

    static class Main {
        public  int i = 10000;

        public synchronized void operationMain() {
            try {
                i--;
                System.out.println("main print i = " + i);
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    static class Sub extends Main {
        public synchronized void operationSub() {
            try {
                while (i > 0) {
                    i--;
                    System.out.println("sub print i = " + i);
                    Thread.sleep(100);
                    this.operationMain();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
