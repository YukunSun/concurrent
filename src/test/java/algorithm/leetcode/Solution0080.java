package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/18 19:34
 * Blog: bengle.me
 * @see Solution0026
 * <p>
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
 */
public class Solution0080 {
    @Test
    public void removeDuplicatesTest() {
        Assert.assertEquals(removeDuplicates(new int[]{1, 2, 2, 3, 4}), 5);
        Assert.assertEquals(removeDuplicates(new int[]{1, 1}), 2);
        Assert.assertEquals(removeDuplicates(new int[]{1, 1, 2}), 3);
        Assert.assertEquals(removeDuplicates(new int[]{1, 1, 2, 3, 4, 5,}), 6);
        Assert.assertEquals(removeDuplicates(new int[]{1, 1, 1, 3, 4, 5,}), 5);
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return -1;
        }
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int i = 2, j = 2;//i,j分别为慢、快指针,这次不从0开始了，直接从2开始
        for (; j < nums.length; ) {
            if (nums[i - 2] != nums[j]) {
                nums[i] = nums[j];
                i++;
            }
            j++;
        }
        return i;
    }
}
