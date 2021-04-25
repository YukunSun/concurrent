package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/25 21:51
 * Blog: bengle.me
 */
public class Solution0167 {
    @Test
    public void onTest() {
        Assert.assertArrayEquals(twoSum(new int[]{2, 7, 11, 15}, 9), new int[]{1, 2});
    }

    private int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> hash = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            hash.put(numbers[i], i + 1);
        }
        for (int i = 0; i < numbers.length; i++) {
            int another = target - numbers[i];
            if (hash.get(another) != null) {
                return new int[]{i + 1, hash.get(another)};
            }
        }
        return new int[]{-1, -1};
    }



}
