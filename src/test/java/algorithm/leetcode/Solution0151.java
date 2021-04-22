package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/22 19:22
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 * https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/
 */
public class Solution0151 {
    @Test
    public void o1() {
        Assert.assertEquals(reverseWords("the sky is blue"), "blue is sky the");
        Assert.assertEquals(reverseWords("  hello world!  "), "world! hello");
        Assert.assertEquals(reverseWords("a good   example"), "example good a");
        Assert.assertEquals(reverseWords("  Bob    Loves  Alice   "), "Alice Loves Bob");
        Assert.assertEquals(reverseWords("Alice does not even like bob"), "bob like even not does Alice");
    }

    public String reverseWords(String s) {
        if (s == null) {
            return s;
        }
        s = s.trim();
        String[] arr = s.trim().split("\\s+");//用正则区分
        int i = 0, j = arr.length - 1;
        while (i < j) {
            String tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
        return String.join(" ", arr);
    }

    @Test
    public void on() {
        Assert.assertEquals(reverseWords2("the sky is blue"), "blue is sky the");
        Assert.assertEquals(reverseWords2("  hello world!  "), "world! hello");
        Assert.assertEquals(reverseWords2("a good   example"), "example good a");
        Assert.assertEquals(reverseWords2("  Bob    Loves  Alice   "), "Alice Loves Bob");
        Assert.assertEquals(reverseWords2("Alice does not even like bob"), "bob like even not does Alice");
    }

    /**
     * use stack
     *
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        if (s == null) {
            return null;
        }
        Stack stack = new Stack();
        String[] arr = s.trim().split("\\s+");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != " ") {
                stack.push(arr[i]);
            } else {
                if (stack.peek() != " ") {
                    stack.push(arr[i]);
                }
            }
        }
        String result = "";
        while (!stack.isEmpty()) {
            result += stack.pop() + " ";
        }
        return result.trim();
    }
}
