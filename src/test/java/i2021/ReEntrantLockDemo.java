package i2021;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/3/8 11:48
 * Blog: bengle.me
 */
public class ReEntrantLockDemo {
    static Object lock = new Object();

    static void func() {//这就说明 synchronized 是可重入的
        synchronized (lock) {
            System.out.println("layer 1");
            synchronized (lock) {
                System.out.println("layer 2");
                synchronized (lock) {
                    System.out.println("layer 3");
                }
            }
        }
    }

    static synchronized void f1() {
        System.out.println("layer 1");
        f2();
    }

    static synchronized void f2() {
        System.out.println("layer 2");
        f3();
    }

    static synchronized void f3() {
        System.out.println("layer 3");
    }

    public static void main(String[] args) {
//        func();
        f1();
    }
}
