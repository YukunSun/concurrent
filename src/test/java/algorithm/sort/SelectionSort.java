package algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2020/2/19 08:59
 * Blog: coderdaily.net
 */
public class SelectionSort {

    /**
     * 核心逻辑：
     * <p>
     * 1 第一层循环负责第一趟遍历，*选择*一个最小值
     * 2 第二层循环负责把剩下的重复第一遍逻辑
     */
    @Test
    public void selectionTest() {
        int[] expect = {1, 3, 5, 7};
        Assert.assertArrayEquals(expect, selectionSortAsc(new int[]{5, 7, 3, 1}));
        Assert.assertArrayEquals(expect, selectionSortAsc(new int[]{3, 5, 1, 7}));
        Assert.assertArrayEquals(expect, selectionSortAsc(new int[]{7, 5, 3, 1}));
    }

    public static int[] selectionSortAsc(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    Util.swap(arr, i, j);
                }
            }
        }
        return arr;
    }
}
