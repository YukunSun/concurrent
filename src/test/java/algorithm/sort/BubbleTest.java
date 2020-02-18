package algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2020/2/18 22:55
 * Blog: coderdaily.net
 */
public class BubbleTest {
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
