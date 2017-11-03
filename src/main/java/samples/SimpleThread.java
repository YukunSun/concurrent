package samples;

/**
 * Author: sunyukun.china@gmail.com
 * Time: 2016/12/8 22:22
 * Blog: coderdaily.net
 *
 * 2.传统的任务类需要实现Runnable，当然本方法就是直接继承Thread
 */
public class SimpleThread extends Thread {
    private int countDown = 5;
    private static int threadCount = 0;

    public SimpleThread() {
        super(Integer.toString(++threadCount));//thread name
        start();
    }

    @Override
    public String toString() {
        return getName()+"---"+countDown;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(this);
            if (--countDown==0)
                return;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread();
        }
    }
}
