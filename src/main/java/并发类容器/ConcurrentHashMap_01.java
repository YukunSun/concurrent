package 并发类容器;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 *
 * @author SunYukun
 * @date 2017/8/3
 */
public class ConcurrentHashMap_01 {
    private static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();

    public static void main(String[] args) throws InterruptedException {
        new Thread("Thread1") {
            @Override
            public void run() {
                map.put(3, 33);
            }
        }.start();

        new Thread("Thread2") {
            @Override
            public void run() {
                map.put(4, 44);
            }
        }.start();

        Thread t3 = new Thread("Thread3") {
            @Override
            public void run() {
                map.put(7, 77);
            }
        };
        t3.start();
        t3.join();

        System.out.println(map);
    }
}
