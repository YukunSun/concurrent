package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/24 18:32
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/merge-sorted-array/solution/he-bing-liang-ge-you-xu-shu-zu-by-leetco-rrb0/
 */
public class Solution0088 {
    @Test
    public void case1() {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        merge(nums1, 3, nums2, nums2.length);
        Assert.assertArrayEquals(nums1, new int[]{1, 2, 2, 3, 5, 6});
    }

    @Test
    public void case2() {
        int[] nums3 = new int[]{1};
        int[] nums4 = new int[]{};
        merge(nums3, 1, nums4, nums4.length);
        Assert.assertArrayEquals(nums3, new int[]{1});
    }

    @Test
    public void case3() {
        int[] nums3 = new int[]{1, 2, 4, 5, 6, 0};
        int[] nums4 = new int[]{3};
        merge(nums3, 5, nums4, nums4.length);
        Assert.assertArrayEquals(nums3, new int[]{1, 2, 3, 4, 5, 6});
    }

    /**
     * 双指针，太特么绕了，不太好
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0;
        if (nums1 == null || nums1.length == 0) {
            nums1 = nums2;
            return;
        }
        if (nums2 == null || nums2.length == 0) {
            return;
        }
        while (i < m || j < n) {
            int newLength = m + j;
            if (!(i <= newLength && j < n)) {//太tm绕了
                return;
            }
            while (nums1[i] < nums2[j] && i < newLength) {
                i++;
            }

            for (int k = newLength; k > i; k--) {//copy array's element which is after i
                nums1[k] = nums1[k - 1];
            }
            nums1[i] = nums2[j];
            j++;
            i++;
        }
    }


    @Test
    public void mergeTest2() {

    }

    /**
     * 如果从后往前先确定最大的岂不是比较好判断？这样就不用往后复制数组了
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {

    }


    @Test
    public void mergeTest3() {
        //nums2全部复制到nums1里，然后再排序。这个操作有点骚...，略
    }
}
