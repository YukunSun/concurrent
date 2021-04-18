package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/18 18:38
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
public class Solution0026 {
    @Test
    public void removeDuplicatesTest() {
        Assert.assertEquals(removeDuplicates(new int[]{1, 2, 2, 3, 4}), 4);
        Assert.assertEquals(removeDuplicates(new int[]{1, 1}), 1);
        Assert.assertEquals(removeDuplicates(new int[]{1, 1, 2}), 2);
        Assert.assertEquals(removeDuplicates(new int[]{1, 1, 2, 3, 4, 5,}), 5);
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
//        if (nums.length == 2 && nums[0] == nums[1]) {//其实这种case完全没必要考虑
//            return 1;
//        }
        int i = 0, j = 0;//j是快指针，i是慢指针
        for (; j < nums.length; j++) {
//            if (nums[j] == nums[i]) {
//                //skip
//            } else {
//                i++;
//            }
            //这一段代码可以优化为：
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];//光求出来一个size肯定不行啊，还得复制元素呢
            }
        }
        return i + 1;
    }
}
