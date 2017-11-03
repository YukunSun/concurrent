package someconceptions;

/**
 * Author: sunyukun.china@gmail.com
 * Time: 2016/11/27 20:43
 * Blog: coderdaily.net
 */
public class RunnableTest implements Runnable {
    protected int count = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;//区分任务的多个实例,一旦初始化以后不希望去修改它，所以定义成final的

    public RunnableTest(){}

    public RunnableTest(int count) {
        this.count = count;
    }

    public String status() {
        return "id="+id+","+(count>0?count:"end...");
    }

    public void run() {
        while (count-->0){
            System.out.println(status());
//            MyThread_02.yield();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*public static void main(String[] args) {
        someconceptions.RunnableTest r = new someconceptions.RunnableTest();
        r.run();
    }*/

    public static void main(String[] args) {
        /*这实际上运行着两个线程，一个主线程一个RunnableTest.run()*/
//        MyThread_02 thread = new MyThread_02(new someconceptions.RunnableTest());//Thread需要一个Runnable对象
//        thread.start();
//        System.out.println("waiting.....");

        //也可以创建更多的线程去驱动更多的任务。
        for (int i = 0; i < 5; i++)
            new Thread(new RunnableTest()).start();
        System.out.println("waiting.....");
    }
}
