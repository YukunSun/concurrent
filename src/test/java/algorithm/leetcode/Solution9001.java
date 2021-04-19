package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/13 23:11
 * Blog: bengle.me
 * <p>
 * 一个升序的数组，返回某个值的最大/小的index比如[0,3]
 */
public class Solution9001 {
    @Test
    public void findMaxAndMinIndexOfSomeElementTest() {
        int[] arr = {1, 2, 2, 3, 3, 3, 3, 4, 4, 6, 7, 7, 7, 8};
        Assert.assertArrayEquals(find(arr, 1), new int[]{0, 0});
        Assert.assertArrayEquals(find(arr, 2), new int[]{1, 2});
        Assert.assertArrayEquals(find(arr, 3), new int[]{3, 6});
        Assert.assertArrayEquals(find(arr, 8), new int[]{13, 13});
        Assert.assertArrayEquals(find(arr, 9), new int[]{-1, -1});
    }

    public int[] find(int[] arr, int k) {
        if (arr == null) {
            return new int[]{-1, -1};
        }
        int i = 0, j = 0;
        boolean hasFound = false;
        for (; j < arr.length; j++) {
            if (hasFound && arr[j] > k) {//及时终止，因为没必要继续遍历了
                break;
            }
            if (arr[j] == k) {
                hasFound = true;
                continue;
            } else {
                i++;
            }
        }
        if (!hasFound) {
            return new int[]{-1, -1};
        }
        return new int[]{i, j - 1};//i正好是下标，j正好是上标+1
    }
}
