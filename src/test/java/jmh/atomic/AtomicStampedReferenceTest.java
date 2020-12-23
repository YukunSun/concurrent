package jmh.atomic;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author sunyk
 **/
public class AtomicStampedReferenceTest {
    /**
     * 解决 AtomicReference 的 ABA 问题
     */
    @Test
    public void atomicStampedReference() {
        AtomicStampedReference<String> reference = new AtomicStampedReference<>("v1", 1);
        Assert.assertEquals(1, reference.getStamp());
        Assert.assertEquals("v1", reference.getReference());

        Assert.assertEquals(false, reference.compareAndSet("v1", "v2", 2, 2));
        Assert.assertEquals(true, reference.compareAndSet("v1", "v2", 1, 2));
        Assert.assertEquals(true, reference.attemptStamp("v2", 100));
    }
}
