package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/25 21:51
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 */
public class Solution0167 {
    @Test
    public void onTest() {
        Assert.assertArrayEquals(twoSum(new int[]{2, 7, 11, 15}, 9), new int[]{1, 2});
    }

    /**
     * 先用hash表存起来所有元素，然后再获取，空：O(logN)
     *
     * @param numbers
     * @param target
     * @return
     */
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

    @Test
    public void onlgnTest() {
        Assert.assertArrayEquals(twoSum2(new int[]{2, 7, 11, 15}, 9), new int[]{1, 2});
        Assert.assertArrayEquals(twoSum2(new int[]{5, 25, 75}, 100), new int[]{2, 3});
    }

    /**
     * 二分查找
     *
     * @param numbers
     * @param target
     * @return
     */
    private int[] twoSum2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int one = numbers[i];
            int another = target - one;

            int p1 = i + 1, p2 = numbers.length - 1;//因为是从前往后确定的，所以一旦确定一个数，那么另外一个数肯定在右面
            while (p1 <= p2) {
                int mid = (p1 + p2) / 2;
                if (another == numbers[mid]) {//
                    return new int[]{i + 1, mid + 1};
                } else if (another > numbers[mid]) {
                    p1 = mid + 1;//p1往前移动1位
                } else {
                    p2 = mid - 1;//p2需要往后移动1位
                }
            }
        }
        return new int[]{-1, -1};
    }

    @Test
    public void o1Test() {
        Assert.assertArrayEquals(twoSum3(new int[]{2, 7, 11, 15}, 9), new int[]{1, 2});
        Assert.assertArrayEquals(twoSum3(new int[]{5, 25, 75}, 100), new int[]{2, 3});
    }

    /**
     * 双指针移动，这个最容易理解
     *
     * @param numbers
     * @param target
     * @return
     */
    private int[] twoSum3(int[] numbers, int target) {
        int p1 = 0, p2 = numbers.length - 1;
        while (p1 <= p2) {
            int sum = numbers[p1] + numbers[p2];
            if (sum == target) {
                return new int[]{p1 + 1, p2 + 1};
            } else if (sum < target) {
                p1++;//意味着当前太小了，增加1位试试
            } else {
                p2--;//意味着当前太大了，减少1位试试
            }
        }
        return new int[]{-1, -1};
    }
}
