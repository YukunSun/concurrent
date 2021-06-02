package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/6/1 13:34
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode-solution/
 */
public class Solution0070 {
    /**
     * 1:1,2:2,3:3,4:5
     */
    @Test
    public void climbStairsTest() {
        Assert.assertEquals(5, climbStairs(4));
    }

    /**
     * @param n
     * @return
     */
    private int climbStairs(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("wrong param");
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }


    @Test
    public void climbStairs2Test() {
        Assert.assertEquals(5, climbStairs2(4));
    }

    public int climbStairs2(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("wrong param");
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int n1 = 1;
        int n2 = 2;
        int value = 0;
        for (int i = 3; i <= n; i++) {
            value = n1 + n2;//计算后一个值
            n1 = n2;//移动n1,n2
            n2 = value;
        }
        return value;
    }
}
