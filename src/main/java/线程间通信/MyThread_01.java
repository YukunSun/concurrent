package 线程间通信;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 * <p>
 * 1. t1线程去添加list，希望加够5个之后，让t2线程感知到。
 * 2. 但是不好的地方是，t2线程是一直启动着的。
 *
 * @author SunYukun
 * @date 2017/7/2
 */
public class MyThread_01 {
            private volatile static List<String> list = new ArrayList<String>();

            public static void main(String[] args) {

                final MyThread_02 t = new MyThread_02();
                //线程1 往list中加数据
                Thread t1 = new Thread(new Runnable() {
                    public void run() {
                        try {
                            for (int i = 0; i < 10; i++) {
                                t.add();
                                System.out.println("当前线程" + Thread.currentThread().getName() + ":添加了一个数据。。");
                                Thread.sleep(500);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }, "t1");

                //线程2 当list数组中有5个元素时,就打印一句话，表示自己收到了通知
                Thread t2 = new Thread(new Runnable() {
                    public void run() {
                        while (true) {
                            if (t.size() == 5) {
                                System.out.println("当前线程" + Thread.currentThread().getName() + ":接收到通知。。并停止线程");
                                throw new RuntimeException();
                            }
                        }
                        //打印一句话
                    }
                }, "t2");

                t1.start();
                t2.start();
            }

            public void add() {
                list.add("1");
            }

            public int size() {
                return list.size();
    }
}
