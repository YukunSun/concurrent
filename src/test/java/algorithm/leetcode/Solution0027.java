package algorithm.leetcode;

import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/17 22:59
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/remove-element/
 */
public class Solution0027 {
    @Test
    public void removeElementTest() {
        int[] nums = {1, 3, 2, 3, 4};
        System.out.println(removeElement(nums, 3));//3
        System.out.println(removeElement(nums, 2));//4
    }

    /**
     * 当 nums[j] 与给定的值相等时，递增j以跳过该元素，如果不相等就复制
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] == val) {
                //直接跳过，即fast++
            } else {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
