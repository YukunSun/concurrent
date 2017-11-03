package 线程基础;

/**
 * Description:
 *
 * @author SunYukun
 * @date 2017/7/1
 */
public class Synchronized_01 extends Thread {
    private int count = 10000;

    public static void main(String[] args) {
        Synchronized_01 th = new Synchronized_01();
        Thread t1 = new Thread(th, "t1-name");
        Thread t2 = new Thread(th, "t2-name");
        Thread t3 = new Thread(th, "t3-name");
        Thread t4 = new Thread(th, "t4-name");
        Thread t5 = new Thread(th, "t5-name");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    @Override
    public synchronized void run() {
        count--;
        System.out.println(this.currentThread().getName() + "------" + count);
    }
}
