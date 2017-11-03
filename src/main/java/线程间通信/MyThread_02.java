package 线程间通信;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: sunyukun.china@gmail.com
 * Time: 2017/4/13 23:04
 * Blog: coderdaily.net
 * <p>
 * 1. 并未实时通知对方；因为t1拿到线程之后不释放锁，所以非得等到它执行完以后，才有机会执行t2，到那时也就不等于5了。
 * 2. wait()会阻塞。
 */
public class MyThread_02 {
    //声明一个 实例变量
    private volatile static List<String> list = new ArrayList<String>();

    public static void main(String[] args) {

        final MyThread_02 t = new MyThread_02();
        //全局对象锁
        final Object lock = new Object();
        //线程1 往list中加数据
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    synchronized (lock) {//说明会对整个这块代码块加上锁。
                        for (int i = 0; i < 10; i++) {
                            t.add();
                            System.out.println("当前线程" + Thread.currentThread().getName() + ":添加了一个数据。。");
                            Thread.sleep(500);
                            if (list.size() == 5) {
                                //发送通知 但是notifi方法不释放锁   锁被释放 是10次循环结束后释放
                                System.out.println("已经发出通知。。");
                                lock.notify();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        //线程2 当list数组中有5个元素时,就打印一句话，表示自己收到了通知
        Thread t2 = new Thread(new Runnable() {

            public void run() {
                synchronized (lock) {
                    try {
                        if (t.size() != 5) {
                            //wait释放锁 此时的线程就在这儿阻塞 所以下面的print语句没有打印 相反的t1线程获得了锁 也发出了通知
                            //这时 线程2就从阻塞状态中到active状态 于是就有了下面的打印 线程阻塞时 就让出了cpu
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //打印一句话
                    System.out.println("当前线程" + Thread.currentThread().getName() + ":接收到通知。。");
                    throw new RuntimeException();
                }
            }
        }, "t2");

        //注意肯定t2先启动，若是后启动的话，因为notify不释放锁，所以会造成t2不会执行。
        t2.start();
        t1.start();
    }

    public void add() {
        list.add("1");
    }

    public int size() {
        return list.size();
    }
}
