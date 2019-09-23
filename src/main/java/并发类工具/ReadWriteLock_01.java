package 并发类工具;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2017/12/16 上午9:29
 * Blog: coderdaily.net
 * <p>
 * 利用 ReentrantReadWriteLock 读写锁保证 HashMap 线程安全
 */
public class ReadWriteLock_01 {
    static Map<String, Object> map = new HashMap<String, Object>();
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock read = readWriteLock.readLock();
    static Lock write = readWriteLock.writeLock();

    public static final Object get(String key) {
        read.lock();
        try {
            return map.get(key);
        } finally {
            read.unlock();
        }
    }

    public static final Object put(String key, Object value) {
        write.lock();

        try {
            return map.put(key, value);
        } finally {
            write.unlock();
        }
    }

    public static final void clear() {
        write.lock();
        try {
            map.clear();
        } finally {
            write.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLock_01 readWriteLock01 = new ReadWriteLock_01();
        put("k1",1);
        put("k2",2);
        put("k3",3);
        put("k4",4);
        put("k5",5);

        System.out.println();
    }
}
