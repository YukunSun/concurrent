package samples;

/**
 * Author: sunyukun.china@gmail.com
 * Time: 2016/11/28 22:59
 * Blog: coderdaily.net
 * <p>
 * 任何线程都可以启动另一个线程。
 * <p>
 * n种写法创建线程
 */

/*4.内部类的方法（使用内部类继承Thread）*/
class InnerThread1 {
    private int countDown = 5;
    private Inner inner;

    public InnerThread1(String name) {
        inner = new Inner(name);
    }

    private class Inner extends Thread {
        Inner(String name) {
            super(name);
            start();
        }

        @Override
        public String toString() {
            return getName() + "---" + countDown;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(this);
                if (--countDown == 0)
                    return;
            }
        }
    }
}

/*5. 使用匿名内部类*/
class InnerThread2 {
    private int countDown = 5;
    private Thread thread;

    public InnerThread2(String name) {
        thread = new Thread(name) {//向上转型
            @Override
            public String toString() {
                return getName() + "====" + countDown;
            }

            @Override
            public void run() {
                while (true) {
                    System.out.println(this);
                    if (--countDown == 0)
                        return;
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
}

/*6.内部类（使用内部类实现Runnable）*/
class InnerRunnable1 {
    private int countDown = 5;
    private Inner inner;

    public InnerRunnable1(String name) {
        inner = new Inner(name);
    }

    private class Inner implements Runnable {
        Thread t;

        Inner(String name) {
            t = new Thread(this, name);//TODO 为当前线程命名为name
            t.start();
        }

        @Override
        public String toString() {
            return t.getName() + "-------" + countDown;
        }

        public void run() {
            while (true) {
                System.out.println(this);
                if (--countDown == 0)
                    return;
            }
        }
    }
}

/*7.匿名类（实现Runnable接口）*/
class InnerRunnable2 {
    private int countDown = 5;
    private Thread t;

    public InnerRunnable2(String name) {
        t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    System.out.println(this);
                    if (--countDown == 0)
                        return;
                }
            }

            @Override
            public String toString() {
                return Thread.currentThread().getName() + "----" + countDown;
            }
        }, name);//命名
        t.start();
    }
}

/*
* 8.在方法内部创建线程<br>
* TODO 在执行完run()后，该方法将返回，如果该线程不是该类的主要操作，而是一种辅助操作， 那么在方法内部创建线程是一个比较好的方式。
* */
class MethodThread {
    private int countDown = 5;
    private Thread t;
    private String name;

    public MethodThread(String name) {
        this.name = name;
    }

    public void runTask() {
        if (t == null) {
            t = new Thread(name) {
                public void run() {
                    while (true) {
                        System.out.println(this);
                        if (--countDown == 0)
                            return;
                    }
                }

                @Override
                public String toString() {
                    return Thread.currentThread().getName() + "----" + countDown;
                }
            };
            t.start();//线程需要显式启动
        }
    }
}


public class ThreadTest {
    public static void main(String[] args) {
//        new InnerThread1("InnerThread1");
//        new InnerThread2("InnerThread2");
//        new InnerRunnable1("InnerRunnable1");
//        new InnerRunnable2("InnerRunnable2");
        new MethodThread("methodThread").runTask();
    }
}
