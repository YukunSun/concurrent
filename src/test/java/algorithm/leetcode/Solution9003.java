package algorithm.leetcode;

import algorithm.QuickSort;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/19 20:16
 * Blog: bengle.me
 * <p>
 * 各种排序：快排、
 */
public class Solution9003 {
    @Test
    public void quickSortTest() {
        int[] nums = new int[]{2, 4, 3, 6, 9, 7, 8};
        quickSort(nums, 0, nums.length - 1);
        Assert.assertArrayEquals(nums, new int[]{2, 3, 4, 6, 7, 8, 9});

        int[] nums2 = new int[]{2, 4, 3, 3, 9, 1, 5};
        quickSort(nums2, 0, nums.length - 1);
        Assert.assertArrayEquals(nums2, new int[]{1, 2, 3, 3, 4, 5, 9});
    }

    /**
     * @param nums
     * @param start
     * @param end
     * @see QuickSort#quickSortRecursive(int, int)
     */
    public void quickSort(int[] nums, int start, int end) {
        if (nums == null) {
            return;
        }
        if (start >= end) {
            return;
        }
        int i = start, j = end, pivot = nums[start];
        while (i < j) {
            while (i < j && nums[j] >= pivot) {//1： 1,2的这两处的代码不能互换
                j--;
            }
            if (i < j) {
                nums[i++] = nums[j];
            }

            while (i < j && nums[i] < pivot) {//2
                i++;
            }
            if (i < j) {
                nums[j--] = nums[i];
            }
            nums[i] = pivot;
            quickSort(nums, start, i - 1);
            quickSort(nums, i + 1, end);
        }
    }
}
