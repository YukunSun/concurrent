package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/23 23:08
 * Blog: bengle.me
 */
public class Solution0215 {
    @Test
    public void findKthLargestTest() {
        Assert.assertEquals(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2), 5);
        Assert.assertEquals(findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4), 4);
    }


    /**
     * 先排个序
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        Solution9003 sort = new Solution9003();
        int length = nums.length;
        sort.quickSort2(nums, 0, length - 1);
        return nums[length - k];
    }
}
