package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/27 18:39
 * Blog: bengle.me
 */
public class Solution0011 {
    @Test
    public void maxAreaTest() {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        Assert.assertEquals(maxArea(height), 49);
    }

    /**
     * 这种暴力遍历是最容易想到的方法
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        //不一定是top 2
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i; j < height.length; j++) {
                int width = j - i;
                int h = height[i] >= height[j] ? height[j] : height[i];
                int square = h * width;
                if (square > max) {
                    max = square;
                }
            }
        }
        return max;
    }


    @Test
    public void maxArea2Test() {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        Assert.assertEquals(maxArea2(height), 49);
    }

    /**
     * 还是双指针，动态规划，时：O(N)
     *
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int i = 0, j = height.length - 1;
        int max = 0;
        while (i < j) {
            int width = j - i;
            int h = height[i] >= height[j] ? height[j] : height[i];
            int square = h * width;
            if (square >= max) {
                max = square;
            }
            if (height[i] > height[j]) {//如果两个指针哪个小的话，就直接丢弃
                j--;
            } else {
                i++;
            }
        }
        return max;
    }
}
