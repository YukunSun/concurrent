package algorithm.algorithm4th;

import java.util.ArrayList;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2019-06-20 17:12
 * Blog: coderdaily.net
 * <p>
 * 通过一个短字符串分隔另外长一个字符串，返回分隔的内容
 */
public class PatternMatch_SplitStringArrayByAnotherStringArray {
    static String srcString = "321245871200124";
    static String target = "12";

    /**
     * @param srcString
     * @param target
     * @return
     */
    public static ArrayList<String> getResult(String srcString, String target) {
        ArrayList<String> result = new ArrayList<String>();
        char[] srcArr = srcString.toCharArray();
        char[] targetArr = target.toCharArray();

        int srcLength = srcArr.length;
        int targetLenth = targetArr.length;
        int k = 0;
        for (int i = 0; i <= srcLength - targetLenth; i++) {
            int j;
            for (j = 0; j < targetLenth; j++) {
                //代替 i 移动
                char charSrc = srcArr[i + j];
                char charTar = targetArr[j];
                if (charSrc != charTar) {
                    break;
                }
            }
            if (j == targetLenth) {
                String string = srcString.substring(k, i);
                i = i + targetLenth;
                k = i;
                result.add(string);
            }
            //最后一部分字符串不能丢
            if (k > srcLength - j) {
                result.add(srcString.substring(k, srcLength));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //期望返回：[32,4587,00,4]
        ArrayList<String> result = getResult(srcString, target);
        System.out.println(result);

        System.out.println(getResult("121afffaaa12", "12"));
    }
}
