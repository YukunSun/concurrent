package samples;

/**
 * Author: sunyukun.china@gmail.com
 * Time: 2016/12/8 22:34
 * Blog: coderdaily.net
 * <p>
 * 3.也可以实现Runnable接口，比继承Thread的好处是还可以再继承一个类。
 *
 * FIXME start()方法是在构造函数中调用的，在构造器中启动线程可能会变得有一些问题。
 */
public class SelfManaged implements Runnable {
    private int countDown = 5;
    private Thread thread = new Thread(this);

    public SelfManaged() {
        thread.start();
    }

    @Override
    public String toString() {
//        return thread.getName()+"---"+countDown;
        return Thread.currentThread().getName() + "-----" + countDown;
    }

    public void run() {
        while (true) {
            System.out.println(this);
            if (--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SelfManaged();
        }
    }
}
