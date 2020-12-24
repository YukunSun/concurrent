package jmh.atomic;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author sunyk
 **/
public class AtomicIntegerFieldUpdaterTest {
    @Test
    public void update() {
        AtomicIntegerFieldUpdater<Alex> updater = AtomicIntegerFieldUpdater.newUpdater(Alex.class, "salary");
        Alex alex = new Alex();
        Assert.assertEquals(updater.addAndGet(alex, 2), 2);
    }

    @Test(expected = RuntimeException.class)
    public void updateParent() {
        AtomicIntegerFieldUpdater<Alex> updater = AtomicIntegerFieldUpdater.newUpdater(Alex.class, "age");
        Alex alex = new Alex();
        Assert.assertEquals(alex.age, 0);
    }

    static class Parent {
        volatile int age;
    }

    static class Alex extends Parent {
        //        int salary;
//        static volatile int salary;
        volatile int salary;

        public int getSalary() {
            return salary;
        }
    }
}

