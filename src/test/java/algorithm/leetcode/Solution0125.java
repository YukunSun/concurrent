package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/26 23:06
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/valid-palindrome/
 */
public class Solution0125 {
    @Test
    public void palindromeTest() {
        Assert.assertEquals(isPalindrome(".,"), true);//md，忽略这个., 忘了这事了
        Assert.assertEquals(isPalindrome("A man, a plan, a canal: Panama"), true);
        Assert.assertEquals(isPalindrome("race a car"), false);
    }

    private boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        char[] arr = s.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            while (i <= j && !Character.isLetterOrDigit(arr[i])) {
                i++;
            }
            while (i <= j && !Character.isLetterOrDigit(arr[j])) {
                j--;
            }
            if (i <= j) {
                if (Character.toLowerCase(arr[i]) != Character.toLowerCase(arr[j])) {
                    return false;
                }
                i++;
                j--;
            }
        }
        return true;
    }
}
