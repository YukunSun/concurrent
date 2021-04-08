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

    @Test
    public void xor() {
        Assert.assertEquals(10, 10);
        Assert.assertEquals(8, 010);
        Assert.assertEquals(16, 0x10);

        int a = 0x10000;
        System.out.println("a = " + a);
        int result = a ^ (a >>> 16);
        System.out.println("result = " + result);
    }

    @Test
    public void count1ForBinary() {
        int k = 7;//3:11,4:100,7:111

        int countOf1 = 0;
        while (k != 0) {
            k = k & (k - 1);
            countOf1++;
        }
        System.out.println("countOf1 = " + countOf1);
    }
}
