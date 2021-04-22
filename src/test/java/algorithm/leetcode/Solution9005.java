package algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/22 22:46
 * Blog: bengle.me
 * <p>
 * 找两个字符串s1,s2的最大公共子串
 */
public class Solution9005 {
    @Test
    public void findCommonPrefixTest() {
        Assert.assertEquals(findCommonPrefix("ab", "ab"), "ab");
        Assert.assertEquals(findCommonPrefix("ab", "abc"), "ab");
        Assert.assertEquals(findCommonPrefix("acb", "abc"), "a");
        Assert.assertEquals(findCommonPrefix("acb", "d"), "");
        Assert.assertEquals(findCommonPrefix("bababdefabcd", "abc"), "abc");
    }

    private String findCommonPrefix(String s1, String s2) {
        //1 假设s1.length>s2.length，这不是核心
        if (s1.length() < s2.length()) {
            String tmp = s1;
            s1 = s2;
            s2 = tmp;
        }
        //2 j 的长度即是最后的公共长度
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int j = 0;
        boolean neverMatch = true;
        for (int i = 0; i < s1.length(); ) {
            if (j >= arr2.length - 1) {//终止条件
                break;
            }
            if (arr1[i] == arr2[j]) {
                i++;
                j++;
                neverMatch = false;//j一直指向0，给人的感觉是总有一个匹配，其实并非是这样，需要一个bool来记录是否匹配过
            } else {
                i++;
                j = 0;
            }
        }
        return neverMatch ? "" : s2.substring(0, j + 1);
    }
}
