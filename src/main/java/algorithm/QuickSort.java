package algorithm;

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
        if (arr[i] >= arr[end]) {
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

    public static void main(String[] args) {
//        int[] arr = {1, 2, 5, 7, 8, 11, 12, 13};
//        int[] arr = {1, 2, 5, 7, 8, 11, 4, 10};
        int[] arr = {11, 9, 5, 3, 8, 2, 4, 10};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
