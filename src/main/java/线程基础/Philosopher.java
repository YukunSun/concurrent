package 线程基础;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author: sun.yukun@foxmail.com
 * Time: 2017/9/17 下午8:33
 * Blog: coderdaily.net
 * <p>
 * 哲学家进餐问题-模拟死锁
 */
public class Philosopher implements Runnable {
    //    左边的筷子
    private Chopstick left;
    //    右边的筷子
    private Chopstick right;

    private final int id;
    private final int ponderFactor;
    private Random rand = new Random(47);

    public Philosopher(Chopstick left, Chopstick right, int id, int ponder) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponder;
    }

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }
        TimeUnit.MICROSECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + "   thinking...");
                pause();

                System.out.println(this + "   grabbing right");
                right.take();

                System.out.println(this + "   grabbing left");
                left.take();

                System.out.println(this + "   eating.....");
                pause();

                right.drop();
                left.drop();
            }
        } catch (Exception e) {
            System.out.println(this + "   exiting via interrupt.....");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                '}';
    }
}

class Chopstick {
    //    筷子是否被使用
    private boolean taken = false;

    //拿筷子
    public synchronized void take() throws InterruptedException {
        while (taken) {
            wait();
            taken = true;
        }
    }

    //放下筷子
    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}

class DeadLockingDining {
    public static void main(String[] args) {
        Chopstick[] chopsticks = new Chopstick[5];
        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new Chopstick();
        }
        for (int i = 0; i < 5; i++) {
//            把思考时间减少至0，即ponder = 0，会更容易发生死锁。
            exec.execute(new Philosopher(chopsticks[i], chopsticks[(i + 1) % 5], i, 5));
        }
        exec.shutdown();
    }
}