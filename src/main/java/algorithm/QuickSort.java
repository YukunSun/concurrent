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

    private void quick_sort_recursive(int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = arr[end];
        int left = start, right = end - 1;
        while (left < right) {
            while (arr[left] < mid && left < right) {
                left++;
            }
            while (arr[right] >= mid && left < right) {
                right--;
            }
            swap(left, right);
        }
        if (arr[left] >= arr[end]) {
            swap(left, end);
        } else {
            left++;
        }
        quick_sort_recursive(start, left - 1);
        quick_sort_recursive(left + 1, end);
    }

    public void sort(int[] arrin) {
        arr = arrin;
        quick_sort_recursive(0, arr.length - 1);

    }

    public static void main(String[] args) {
//        int[] arr = {1, 2, 5, 7, 8, 11, 12, 13};
        int[] arr = {1, 2, 5, 7, 8, 11, 4, 10};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
