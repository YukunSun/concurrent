package i2021;

import java.util.concurrent.TimeUnit;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/10 11:01
 * Blog: bengle.me
 * <p>
 * 参考：https://blog.csdn.net/import_sadaharu/article/details/78846675
 */
public class SynchronizedDemo {
    static class LockDemo {
        public void f() {
            System.out.println(Thread.currentThread().getName() + " exec f============");
        }

        //锁住LockDemo.class：其作用的范围是synchronized后面括号括起来的部分，作用的对象是这个类的所有对象；
        public void f1() {
            System.out.println(Thread.currentThread().getName() + "before exec f1");
            synchronized (LockDemo.class) {
                System.out.println(Thread.currentThread().getName() + " exec f1");
                sleepLong();
            }
            System.out.println(Thread.currentThread().getName() + "after exec f1");
        }

        //锁住当前对象：其作用范围是大括号{}括起来的代码块，作用的对象是调用这个代码块的对象
        public void f2() {
            System.out.println(Thread.currentThread().getName() + "before exec f2");
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " exec f2");
                sleepLong();
            }
            System.out.println(Thread.currentThread().getName() + "after exec f2");
        }

        //锁住一个普通方法：其作用的范围是整个方法，作用的对象是调用这个方法的对象；
        public synchronized void f3() {
            System.out.println(Thread.currentThread().getName() + "before exec f3");

            System.out.println(Thread.currentThread().getName() + " exec f3");
            sleepLong();

            System.out.println(Thread.currentThread().getName() + "after exec f3");
        }

        //锁住一个静态方法：其作用的范围是整个方法，作用的对象是这个类的所有对象；
        public synchronized static void f4() {
            System.out.println(Thread.currentThread().getName() + "before exec f4");
            System.out.println(Thread.currentThread().getName() + " exec f4");
            sleepLong();
            System.out.println(Thread.currentThread().getName() + "after exec f4");
        }
    }

    public static void sleepLong() {
        System.out.println(Thread.currentThread().getName() + " sleep...");
        try {
            TimeUnit.SECONDS.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo lock = new LockDemo();
        LockDemo lock2 = new LockDemo();

        Thread t1 = new Thread(() -> {
            lock.f4();
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);//1.t1先于t2执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.f();
            lock2.f();
            //2 分别和t1使用同一个锁对象/不一个锁对象，以方便测试
//            lock.f3();
            lock2.f4();
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
