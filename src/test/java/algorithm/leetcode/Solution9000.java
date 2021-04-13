package algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/13 22:12
 * Blog: bengle.me
 * <p>
 * 一个数组的值先从小到大递增后从大到小递减，从小到大对其排序
 */
public class Solution9000 {
    @Test
    public void on() {
//        int[] arr = {1, 2, 5, 4, 3};
        int[] arr = {1, 2, 5, 7, 9, 8, 6, 4, 3};
        int[] result = sort(arr);
        System.out.println(Arrays.toString(result));
    }

    /**
     * 新增一个数组来存储结果
     *
     * @param arr
     * @return
     */
    public int[] sort(int[] arr) {
        int i = 0, j = arr.length - 1;
        int[] result = new int[arr.length];
        int k = 0;
        while (i <= j) {
            if (arr[i] > arr[j]) {
                result[k++] = arr[j--];
            } else {
                result[k++] = arr[i++];
            }
        }
        return result;
    }
}
