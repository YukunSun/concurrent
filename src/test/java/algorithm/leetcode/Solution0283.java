package algorithm.leetcode;

import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/12 23:26
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/move-zeroes/solution/
 */
public class Solution0283 {
    @Test
    public void name() {
        int[] nums = {0, 1, 0, 3, 12};
//        int[] nums = {4, 2, 4, 0, 0, 3, 0, 5, 1, 0};
        moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        for (int i = 0, j = 1; i < nums.length && j < nums.length; ) {
            if (nums[i] == 0 && nums[j] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;

                i++;
                j++;
            } else {
                if (nums[i] == 0 && nums[j] == 0) {
                    j++;
                } else if (nums[i] != 0 && nums[j] == 0) {
                    i++;
                    j++;
                } else {
                    i++;
                    j++;
                }
            }
        }
    }
}
