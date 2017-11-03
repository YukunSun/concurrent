package 线程基础;

/**
 * Description:
 *
 * @author SunYukun
 * @date 2017/7/1
 */
public class Synchronized_02 {
    private static int num = 0;

    public static void main(String[] args) {
        final Synchronized_02 obj1 = new Synchronized_02();
        final Synchronized_02 obj2 = new Synchronized_02();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                obj1.printNum("a");
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                obj2.printNum("b");
            }
        }, "t2");
        t1.start();
        t2.start();
    }

    private static synchronized void printNum(String tag) {
        try {
            if ("a".equals(tag)) {
                num = 100;
                System.out.println("tag a set " + "num:" + num);
                Thread.sleep(1000);
            } else {
                num = 200;
                System.out.println("tag b set " + "num:" + num);
            }
            System.out.println("tag:" + tag + ",num:" + num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
