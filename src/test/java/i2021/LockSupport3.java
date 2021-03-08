package i2021;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/3/8 15:47
 * Blog: bengle.me
 * <p>
 * 再来看 LockSupport 的优点
 */
public class LockSupport3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {//1.如果unpark先于park执行，也不影响。
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1");
            try {//模拟业务
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.park();
            //LockSupport.park();//2.因为所谓的unpark就是先给了一个permit,不过这个permit最大为1。所以park次数>1就有可能不能被唤醒了
            System.out.println("t1 was notified");
        });
        t1.start();

        new Thread(() -> {
            System.out.println("t2 notify t1");
            LockSupport.unpark(t1);
//            LockSupport.unpark(t1);//2
        }, "t2").start();
    }
}
