package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/5/20 11:56
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class Solution0020 {
    @Test
    public void isValidTest() {
        Assert.assertEquals(isValid("(])"), false);
        Assert.assertEquals(isValid("]"), false);
        Assert.assertEquals(isValid("(]"), false);
        Assert.assertEquals(isValid("()"), true);
        Assert.assertEquals(isValid("()[]{}"), true);
        Assert.assertEquals(isValid("([)]"), false);
        Assert.assertEquals(isValid("([)]"), false);
        Assert.assertEquals(isValid("{[]}"), true);
    }

    /**
     * 就是纯粹的堆逻辑，让人抓不到重点，这种题很烦，写不好还以为你啥都不会呢...
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char cur = arr[i];
            if (cur == '(' || cur == '[' || cur == '{') {
                stack.push(cur);
            } else if (cur == ')' && (stack.isEmpty() || stack.peek() != '(')) {//注意：EmptyStackException
                return false;
            } else if (cur == ']' && (stack.isEmpty() || stack.peek() != '[')) {
                return false;
            } else if (cur == '}' && (stack.isEmpty() || stack.peek() != '{')) {
                return false;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
