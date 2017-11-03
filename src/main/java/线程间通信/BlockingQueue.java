package 线程间通信;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:使用list实现一个阻塞的队列。
 * <p>
 * 1.t1线程存，t2线程取。
 * 2.若队列满时，t1需要阻塞，等待t2线程取，这个时候，如果t2取值需要通知t1进行存储。
 * 3.若线程为空时，t2在阻塞着，所以需要t1需要通知t2去取值。
 * 4.put()为存入方法，take()为取值方法。
 *
 * @author SunYukun
 * @date 2017/7/8
 */
public class BlockingQueue {
    private final Object lock = new Object();
    //一个空间进行存储
    private LinkedList<Object> list = new LinkedList<Object>();
    //计数器
    private AtomicInteger count = new AtomicInteger(0);
    //指定界限
    private int minSize = 0;
    private int maxSize;

    public BlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public static void main(String[] args) {
        final BlockingQueue queue = new BlockingQueue(5);
        queue.put("1");
        queue.put("2");
        queue.put("3");
        queue.put("4");
        queue.put("5");

        System.out.println("当前容器长度：" + queue.getSize());

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                queue.put(7);
                queue.put(8);
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                Object e = queue.take();
                System.out.println("移除元素：" + e);
                Object e1 = queue.take();
                System.out.println("移除元素：" + e1);
            }
        }, "t2");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
    }

    public int getSize() {
        return count.get();
    }

    public void put(Object obj) {
        synchronized (lock) {
            if (count.get() == maxSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(obj);
            count.incrementAndGet();
            //通知take
            lock.notify();
        }
        System.out.println("put---值为：" + obj);
    }

    public Object take() {
        Object ret = null;
        synchronized (lock) {
            if (count.get() == minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ret = list.removeFirst();
            count.decrementAndGet();
            //通知take
            lock.notify();
        }
        return ret;
    }
}


