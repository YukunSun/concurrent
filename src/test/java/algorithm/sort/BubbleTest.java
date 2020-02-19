package algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2020/2/18 22:55
 * Blog: coderdaily.net
 */
public class BubbleTest {
    /**
     * 核心逻辑：
     * <p>
     * 1 两层循环：一个指针控制排好序的部分，另一个控制待排的部分（待排部分的两个指针可以用其中之一推断出来）
     * 2 排序时需要交换元素
     */
    @Test
    public void bubbleTest() {
        int[] expect = {1, 3, 5, 7};
        Assert.assertArrayEquals(expect, bubbleSortAsc(new int[]{5, 7, 3, 1}));
        Assert.assertArrayEquals(expect, bubbleSortAsc(new int[]{3, 5, 1, 7}));
    }

    public static int[] bubbleSortAsc(int[] arr) {
        int j = arr.length - 1;
        while (j >= 1) {
            for (int i = 0; i + 1 <= j; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
            j--;
        }
        return arr;
    }

    static void swap(int[] arr, int index, int index2) {
        int tmp;
        tmp = arr[index];
        arr[index] = arr[index2];
        arr[index2] = tmp;
    }
}
