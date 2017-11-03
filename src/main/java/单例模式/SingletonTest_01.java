package 单例模式;

/**
 * Description:内部类形式
 * <p>
 * 访问的时候才去初始化
 *
 * @author SunYukun
 * @date 2017/7/31
 */
public class SingletonTest_01 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                System.out.println(SingletonTest_01.getInstance());
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                System.out.println(SingletonTest_01.getInstance());
            }
        }).start();
    }

    public static SingletonTest_01 getInstance() {
        return InnerClass.single;
    }

    private static class InnerClass {
        static final SingletonTest_01 single = new SingletonTest_01();
    }

}
