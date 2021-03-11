package algorithm;

import java.util.Arrays;

/**
 * Author: sunyukun.china@gmail.com
 * Time: 2017/1/9 23:37
 * Blog: coderdaily.net
 * <p>
 * from https://zh.wikipedia.org/wiki/%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F#Java
 */
public class QuickSort {
    int[] arr;

    private void swap(int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private void quickSortRecursive(int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = arr[end];
        int i = start, j = end - 1;
        while (i < j) {
            while (arr[i] < pivot && i < j) {
                i++;
            }
            while (arr[j] >= pivot && i < j) {
                j--;
            }
            swap(i, j);
        }
        if (arr[i] > arr[end]) {
            swap(i, end);
        } else {
            i++;
        }
        quickSortRecursive(start, i - 1);
        quickSortRecursive(i + 1, end);
    }

    public void sort(int[] arrin) {
        arr = arrin;
        quickSortRecursive(0, arr.length - 1);
    }

    public void sort2(int[] arrin) {
        arr = arrin;
        quickSortRecursive2(0, arr.length - 1);
    }

    private void quickSortRecursive2(int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = arr[start];//pivot,即 arr[start] 是数组的第一个坑
        int i = start, j = end;
        while (i < j) {//终止条件
            while (i < j && arr[j] >= pivot) {//大于 pivot 的都放到右边
                j--;
            }
            if (i < j) {
                arr[i++] = arr[j];//如果arr[j]<pivot,就把这个坑给 arr[i]，arr[j]也就空出了一个坑
            }
            while (i < j && arr[i] < pivot) {//小于 pivot 的都放在左边
                i++;
            }
            if (i < j) {//如果 arr[i]>pivot，就把 arr[i]这个坑填平 arr[j]，arr[j]就空出来了
                arr[j--] = arr[i];
            }
        }
        arr[i] = pivot;//最后总会空出来一个坑，就用 pivot 回填
        quickSortRecursive2(start, i - 1);
        quickSortRecursive2(i + 1, end);
    }

    public static void main(String[] args) {
//        int[] arr = {1, 2, 5, 7, 8, 11, 12, 13};
        int[] arr = {1, 2, 5, 7, 8, 11, 4, 10};
//        int[] arr = {11, 9, 5, 3, 8, 2, 4, 10};
        QuickSort quickSort = new QuickSort();
//        quickSort.sort(arr);
        quickSort.sort2(arr);
//        for (int i : arr) {
//            System.out.println(i);
//        }
        Arrays.stream(arr).forEach(System.out::println);
    }
}
