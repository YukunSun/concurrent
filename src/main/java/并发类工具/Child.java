package 并发类工具;

/**
 * Author: sun.yukun@foxmail.com
 * Time: 2017/10/20 下午3:18
 * Blog: coderdaily.net
 */
public class Child extends Father implements Runnable {
    final static Child child = new Child();//为了保证锁唯一

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(child).start();
        }
    }

    public synchronized void doSomething() {
        System.out.println("1child.doSomething()");
        doAnotherThing(); // 调用自己类中其他的synchronized方法
    }

    private synchronized void doAnotherThing() {
        super.doSomething(); // 调用父类的synchronized方法
        System.out.println("3child.doAnotherThing()");
    }

    public void run() {
        child.doSomething();
    }
}

class Father {
    public synchronized void doSomething() {
        System.out.println("2father.doSomething()");
    }
}
