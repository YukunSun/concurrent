package algorithm.algorithm4th;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2019-06-20 19:29
 * Blog: coderdaily.net
 * <p>
 * 暴力查找子串
 */
public class PatternMatch {
    static String text = "1234742487523";
    static String pattern = "23";

    public static int search(String pattern, String text) {
        int patLength = pattern.length();
        int txtLength = text.length();

        for (int i = 0; i < txtLength - patLength; i++) {
            int j;
            for (j = 0; j < patLength; j++) {
                //利用text.charAt(i + j) 代替指针 i 的移动
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == patLength) {
                return i;
            }
        }
        return txtLength;
    }

    public static void main(String[] args) {
        System.out.println(search(pattern, text));
    }
}
