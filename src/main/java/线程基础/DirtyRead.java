package 线程基础;

/**
 * Description:脏读
 * <p>
 * 1.新开的线程去执行setValue,假设执行了两秒，在设值期间，主线程去get，我想得到的456，而不是123。
 * 2.为了解决这个问题，需要在setValue和getValue上都加上synchronized关键字。
 *
 * @author SunYukun
 * @date 2017/7/1
 */
public class DirtyRead {
    private String userName = "test";
    private String password = "123";

    public static void main(String[] args) throws Exception {
        final DirtyRead dr = new DirtyRead();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                dr.setValue("test", "456");
            }
        });
        t1.start();
        Thread.sleep(1000);//TAG：不会释放对象锁
        dr.getValue();
    }

    public synchronized void setValue(String userName, String password) {
        this.userName = userName;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password = password;
        System.out.println("setValue,userName:" + userName + ",password:" + password);
    }

    public synchronized void getValue() {
        System.out.println("getValue,userName:" + userName + ",password:" + password);
    }
}
