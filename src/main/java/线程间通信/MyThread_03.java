package 线程间通信;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 实时通知对方
 * <p>
 * Author: sunyukun.china@gmail.com
 * Time: 2017/4/17 23:12
 * Blog: coderdaily.net
 */
public class MyThread_03 {
    //声明一个 实例变量
    private volatile static List<String> list = new ArrayList<String>();

    public static void main(String[] args) {

        final MyThread_03 t = new MyThread_03();

        //只需要执行1次c.countDown();
        final CountDownLatch c = new CountDownLatch(1);

        //线程1 往list中加数据
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        t.add();
                        System.out.println("当前线程" + Thread.currentThread().getName() + ":添加了一个数据。。");
                        Thread.sleep(500);
                        if (list.size() == 5) {
                            //发送通知 但是notifi方法不释放锁   锁被释放是在10次循环结束后释放(即synchronized块结束后)
                            System.out.println("已经发出通知。。");
                            c.countDown();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t1");

        //线程2 当list数组中有5个元素时 就打印一句话
        Thread t2 = new Thread(new Runnable() {

            public void run() {
                try {
                    if (t.size() != 5) {
                        c.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //打印一句话
                System.out.println("当前线程" + Thread.currentThread().getName() + ":接收到通知。。");
                throw new RuntimeException();
            }
            //}
        }, "t2");

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
