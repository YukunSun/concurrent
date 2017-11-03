package algorithm;

/**
 * Author: sun.yukun@foxmail.com
 * Time: 2017/10/30 下午7:43
 * Blog: coderdaily.net
 * <p>
 * 在一个先升后降的数组中找最大值。
 */
public class MaxNum {
    int getMax(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        int mid = 0;
        while (start < end) {
            mid = (start + end) / 2;
            if ((arr[mid] > arr[mid + 1]) && (arr[mid] < arr[mid - 1])) {
                end = mid;
            }
            if ((arr[mid] < arr[mid + 1]) && (arr[mid] > arr[mid - 1])) {
                start = mid;
            }
            if ((arr[mid] >= arr[mid - 1]) && (arr[mid] >= arr[mid + 1])) {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 4, 3, 2, 1, 0};
        MaxNum maxNum = new MaxNum();
        int index = maxNum.getMax(arr);
        System.out.println(arr[index]);
    }
}
