package algorithm.sort;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2020/2/19 09:11
 * Blog: coderdaily.net
 */
public class Util {

    public static void swap(int[] arr, int index, int index2) {
        int tmp;
        tmp = arr[index];
        arr[index] = arr[index2];
        arr[index2] = tmp;
    }
}
