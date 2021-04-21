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

    @Test
    public void quickSortTest2() {
        //1 基准条件：要排序数组的长度如果是空的或者1个元素时是不需要排序的，即arr.length<2
        //2 不断减少要排序数组的规模
        int[] nums = new int[]{2, 4, 3, 6, 9, 7, 8};
        quickSort2(nums, 0, nums.length - 1);
        Assert.assertArrayEquals(nums, new int[]{2, 3, 4, 6, 7, 8, 9});

        int[] nums2 = new int[]{2, 4, 3, 3, 9, 1, 5};
        quickSort2(nums2, 0, nums.length - 1);
        Assert.assertArrayEquals(nums2, new int[]{1, 2, 3, 3, 4, 5, 9});

        int[] nums3 = new int[]{3, 1, 4, 2, 5, 6};
        quickSort2(nums3, 0, nums3.length - 1);
        Assert.assertArrayEquals(nums3, new int[]{1, 2, 3, 4, 5, 6});
    }

    /**
     * 比如拿{3,1,4,2,5,6}做分析，https://www.bilibili.com/video/BV1at411T75o?from=search&seid=11248481625031341663
     * <p>
     * 这个跟上面的几乎完全一样，只不过注释更多一些
     *
     * @param nums
     * @param start
     * @param end
     */
    private void quickSort2(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int i = start, j = end;//左右两个指针
        int pivot = nums[start];
        while (i < j) {//0 接下来就是交替移动j,i
            while (i < j && nums[j] >= pivot) {//1 首先移动j，（其实可以考虑一下如果先移动i的话就记不住pivot的位置了）；再判断i<j是因为下一次循环可能导致不符合循环条件
                j--;
            }
            if (i < j) {//因为上一步的j--可能会打破i<j的条件，这时候j移动到了一个<pivot的值上，所以要把i的坑填平，这时候j就成了一个坑
                nums[i] = nums[j];
                i++;//填完坑之后，需要把i后移，然后就可以执行第2部分了
            }
            while (i < j && nums[i] < pivot) {//2 下一步就是移动i
                i++;
            }
            if (i < j) {//这时候i移动到了一个<pivot的位置上
                nums[j] = nums[i];
                j--;//既然j所在的坑被填平了，所以j继续往前移
            }
        }
        nums[i] = pivot;//这个时候需要拿pivot填平缺省值
        quickSort(nums, start, i - 1);
        quickSort(nums, i + 1, end);
    }
}
