package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/5/19 22:10
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class Solution0003 {
    @Test
    public void longestTest() {
        Assert.assertEquals(lengthOfLongestSubstring("pwwkew"), 3);
    }

    /**
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
