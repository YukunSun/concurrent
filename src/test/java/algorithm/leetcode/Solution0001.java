package algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/5/10 22:09
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/two-sum/
 */
public class Solution0001 {
    @Test
    public void twoSumTest() {
        int[] arr = new int[]{2, 7, 11, 15};
        int[] result = twoSum(arr, 444);
        System.out.println(Arrays.toString(result));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int another = target - cur;
            if (map.get(another) != null) {//如果获取到了，意味着可以直接返回了
                return new int[]{i, map.get(another)};
            } else {
                map.put(cur, i);//如果没有，那就先加进去，等着下一个
            }
        }
        return new int[]{-1, -1};
    }
}
