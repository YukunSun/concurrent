package 线程基础;

/**
 * Author: sun.yukun@foxmail.com
 * Time: 2017/11/3 上午10:01
 * Blog: coderdaily.net
 * <p>
 * 不可以覆盖start（），然后在该方法里调用run（）
 */
public class Thread_01 extends Thread {
    @Override
    public void run() {
        System.out.println("task....");
    }

//    @Override
//    public synchronized void start() {
//        run();
//    }

    public static void main(String[] args) {
        Thread_01 t = new Thread_01();
        t.start();
    }
}
