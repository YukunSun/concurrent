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

    public static void main(String[] args) {
        new Thread("Thread1") {
            @Override
            public void run() {
                map.put(3, 33);
            }
        };

        new Thread("Thread2") {
            @Override
            public void run() {
                map.put(4, 44);
            }
        };

        new Thread("Thread3") {
            @Override
            public void run() {
                map.put(7, 77);
            }
        };
        System.out.println(map);
    }
}
