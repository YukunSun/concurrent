package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/20 22:37
 * Blog: bengle.me
 * <p>
 * 1.只扫描一趟
 * 2.原地排序
 * https://leetcode-cn.com/problems/sort-colors/solution/
 */
public class Solution0075 {
    private int[] nums = new int[]{2, 0, 2, 1, 1, 0};
    private int[] nums2 = new int[]{2, 0, 2, 2, 1, 1, 2, 0, 1, 1, 0};
    private int[] nums3 = new int[]{2, 1, 0};

    //真特么的绕
    @Test
    public void sortColorTest() {
        sortColors2(nums);
        Assert.assertArrayEquals(nums, new int[]{0, 0, 1, 1, 2, 2});

        sortColors2(nums2);
        Assert.assertArrayEquals(nums2, new int[]{0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2});

        sortColors2(nums3);
        Assert.assertArrayEquals(nums3, new int[]{0, 1, 2});
    }

    /**
     * 其他方法：（不过有点不符合题意）
     * 1.直接排序
     * 2.直接遍历然后赋值
     * 3.略
     */
    @Test
    public void sortColorTest2() {
        sortColors2(nums);
        Assert.assertArrayEquals(nums, new int[]{0, 0, 1, 1, 2, 2});

        sortColors2(nums2);
        Assert.assertArrayEquals(nums2, new int[]{0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2});

        sortColors2(nums3);
        Assert.assertArrayEquals(nums3, new int[]{0, 1, 2});
    }

    /**
     * 主要的思想是：使用两个指针p1,p2将整个数组分成三部分，第一部分放0，第三部分放2，那么第二部分自然都是1。
     * 1次遍历,https://leetcode-cn.com/problems/sort-colors/solution/yan-se-fen-lei-by-leetcode-solution/
     *
     * @param nums
     * @see algorithm.leetcode.Solution9003#quickSort2(int[], int, int)
     */
    private void sortColors(int[] nums) {
        if (nums == null) {
            return;
        }
        int p1 = 0, p2 = nums.length - 1;//p1,p2两个指针将整个数组分成3个区域
        for (int i = 0; i < p2; i++) {
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            }
        }
    }


    /**
     * 遍历两次
     *
     * @param nums
     */
    private void sortColors2(int[] nums) {
        if (nums == null) {
            return;
        }
        int p1 = 0;
        for (int i = 0; i < nums.length; i++) {//p1最后指向的就是非0开始的位置，遍历结束之后前面也就是都是0了
            if (nums[i] == 0) {
                int tmp = nums[p1];
                nums[p1] = nums[i];
                nums[i] = tmp;
                p1++;
            }
        }
        for (int i = p1; i < nums.length; i++) {
            if (nums[i] == 1) {
                int tmp = nums[p1];
                nums[p1] = nums[i];
                nums[i] = tmp;
                p1++;
            }
        }
    }
}
