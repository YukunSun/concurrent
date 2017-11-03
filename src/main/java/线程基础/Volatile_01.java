package 线程基础;

/**
 * Description:volatile具备可见性
 * <p>
 * 1. 在设置成false之后，线程依然没有关闭，原因是只改变了本线程的isRunning变量的副本，而没有改变全局变量的值.<br>
 * 2. 可以加上volatile关键字设置该变量为其他线程可见。
 *
 * @author SunYukun
 * @date 2017/7/2
 */
public class Volatile_01 extends Thread {
    private volatile boolean isRunning = true;

    public static void main(String[] args) throws Exception {
        Volatile_01 thread = new Volatile_01();
        thread.start();

        Thread.sleep(1000);
        thread.setIsRunning(false);
        System.out.println("设置成false");
    }

    private void setIsRunning(boolean flag) {
        isRunning = flag;
    }

    @Override
    public void run() {
        while (isRunning) {
            System.out.println("running ....");
        }
        System.out.println("stop");
    }
}
