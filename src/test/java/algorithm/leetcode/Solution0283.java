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
    public void o1() {
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

    @Test
    public void o12() {
//        int[] nums = {0, 1, 0, 3, 12};
        int[] nums = {4, 2, 4, 0, 0, 3, 0, 5, 1, 0};
        moveZeroes2(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    public void moveZeroes2(int[] nums) {
        int i = 0, j = 0, n = nums.length;
        while (j < n) {
            if (nums[j] != 0) {//只要nums[j]!=0就调换，根本不用管nums[i]
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
            }
            j++;
        }
    }
}
