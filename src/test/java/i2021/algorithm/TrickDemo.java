package i2021.algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/2 23:38
 * Blog: bengle.me
 */
public class TrickDemo {
    //判断一个数是不是2的整数次方
    @Test
    public void determine2Power() {
        //n & n-1 == 0
        Assert.assertEquals(0, 4 & 3);
        Assert.assertEquals(0, 32 & 31);
    }
}
