package 代理;

import java.util.Random;

/**
 * Description:
 *
 * @author SunYukun
 * @date 2017/7/18
 */
public class Cart implements Moveable {
    public void move() {
        System.out.println("the car start running...");
        long start = System.currentTimeMillis();
        try {
            // 休眠少于5s的一个时间
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("the car running spent :" + (end - start));
    }
}
