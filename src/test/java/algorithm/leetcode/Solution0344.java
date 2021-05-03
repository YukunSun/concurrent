package algorithm.leetcode;

import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/5/3 16:50
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/reverse-string/submissions/
 */
public class Solution0344 {

    @Test
    public void reverseStringTest() {
        char[] arr = new char[]{'h', 'e', 'l', 'l', 'o'};
        System.out.println(arr);
        reverseString(arr);
        System.out.println(arr);
    }

    /**
     * //法1：两个首尾指针
     * //法2：stack
     * //法3：递归：太反直觉了
     *
     * @param s
     */
    public void reverseString(char[] s) {
        if (s == null || s.length < 2) {
            return;
        }
        helper(0, s);
    }

    private void helper(int index, char[] s) {
        int length = s.length;
        if (index >= length / 2) {
            return;
        }
        int p = length - index - 1;//后面的指针也得移动
        char tmp = s[index];
        s[index] = s[p];
        s[p] = tmp;
        helper(++index, s);//注意这里是++i，而不是i++
    }
}
