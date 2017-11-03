package 单例模式;

/**
 * Description:
 *
 * @author SunYukun
 * @date 2017/7/31
 */
public class SingletonTest_02 {
    private volatile static SingletonTest_02 singleton;

    private SingletonTest_02() {
    }

    public static SingletonTest_02 getSingleton() {
        if (singleton == null) {//若不为空，就不再锁住该对象了，更好。
            try {
                Thread.sleep(5000);//假如类初始化耗时5s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (SingletonTest_02.class) {
                if (singleton == null) {//为什么必须仍然加上if判断
                    singleton = new SingletonTest_02();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                System.out.println(SingletonTest_02.getSingleton());
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                System.out.println(SingletonTest_02.getSingleton());
            }
        }).start();
    }
}
