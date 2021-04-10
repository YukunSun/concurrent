package i2021;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/10 14:17
 * Blog: bengle.me
 */
public class SynchroniedDemo2 {
    @Test
    public void classLayout() {
        Object obj = new Object();
        String s = ClassLayout.parseInstance(obj).toPrintable();
        System.out.println(s);
    }

    @Test
    public void classLayout2() {
        Object obj = new Object();
        synchronized (obj) {
            String s = ClassLayout.parseInstance(obj).toPrintable();
            System.out.println(s);
        }
    }
}
