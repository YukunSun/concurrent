package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/28 23:18
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/solution/
 * <p>
 * @see algorithm.leetcode.Solution0011#maxArea2(int[])
 */
public class Solution0209 {
    @Test
    public void onTest() {
        Assert.assertEquals(minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}), 0);
        Assert.assertEquals(minSubArrayLen(4, new int[]{1, 4, 4}), 1);
        Assert.assertEquals(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}), 2);
    }

    /**
     * 审题审错了，它的意思并不是直接挑最少组成的元素之和=target的个数，而是最短的字数组求和>=target；
     * 唉，相当于理解错了两个点，1是满足>=就行而不是=；2是连续子数组，相当于画个滑动窗口而非跳跃着找几个元素....
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null) {
            return -1;
        }
        int length = nums.length;
        int i = 0, j = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        while (j < length) {
            sum += nums[j];
            while (sum >= target) {
                result = Math.min(result, j - i + 1);
                sum -= nums[i];
                i++;
            }
            j++;
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
