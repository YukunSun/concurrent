package i2021;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/7 22:05
 * Blog: bengle.me
 */
public class DeadLock2 {
    static Object lockA = new Object();
    static Object lockB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                //sleep
                try {
                    Thread.sleep(3000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB) {

                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                //sleep
                try {
                    Thread.sleep(3000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA) {

                }
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
