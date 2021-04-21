package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/21 11:40
 * Blog: bengle.me
 * <p>
 * 理解递归：
 * 0 《算法图解》
 * 1 首先确定基线条件（基线条件要尽可能简单）
 * 2 不变缩小规模，直到达到基线条件
 */
public class Solution9004 {
    @Test
    public void divideSquareTest() {
        //比如一块1680*640的长方形，尽可能少且大的分出正方形
        dividedSquare(1680, 640);
    }

    public void dividedSquare(int length, int width) {
        if (length % width == 0) {//1 这里是基线条件：也就是如果能够整除的话也就不需要再分了
            System.out.println("final divide: width = " + width + ", count = " + (length / width));
            return;
        }
        int lengthNew = length - (length / width) * width;//2 然后不断地缩小规模
        System.out.println("divide: width = " + width + ", count = " + (length / width));
        dividedSquare(width, lengthNew);
    }

    @Test
    public void sumByRecursiveTest() {
        //比如使用递归求一个数组{1,2,3,4,5}的和，类似于reduce
        int sum1 = sumByRecursive(new int[]{1, 2, 3, 4, 5}, 0);
        Assert.assertEquals(15, sum1);
    }

    public int sumByRecursive(int[] nums, int beginIndex) {
        if (beginIndex > nums.length - 1) {//1 基准条件：遍历到最后一位就没必要再缩小规模了
            return 0;
        }
        return nums[beginIndex] + sumByRecursive(nums, beginIndex + 1);//2 逐渐缩小规模
    }
}
