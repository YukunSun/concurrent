package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/5/26 16:06
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/solution/mian-shi-ti-42-lian-xu-zi-shu-zu-de-zui-da-he-do-2/
 */
public class Solution8000 {
    @Test
    public void maxSubArrayTest() {
        Assert.assertEquals(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}), 6);
    }

    /**
     * 说人话就是求和的最大值，非常讨厌出这类题还没有文字描述。
     * <p>
     * 动态规划三大步骤：
     * <p>
     * 寻找dp[i]或者dp[i][j]的意义，此题中dp[i]的意义就是前面i+1个数的和，从0开始
     * 寻找公式dp[i] = nums[i] + dp[i-1]，dp[i-1]应该大于0，不然就越加越小
     * 寻找初始值，很明显，dp[0]没有前面的元素，所以dp[0]=nums[0]
     * <p>
     * 作者：da-yu-co
     * 链接：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/solution/di-yi-ge-dong-tai-gui-hua-by-da-yu-co-jt9i/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param arr
     * @return
     */
    public int maxSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int i = 1;
        int max = 0;
        while (i < arr.length) {
            arr[i] += Math.max(arr[i - 1], 0);
            max = Math.max(max, arr[i]);//max不断的临时存储到其他地方了，只需要一步步移动指针就行了
            i++;
        }
        return max;
    }
}
