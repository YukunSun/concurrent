package 基础类;

/**
 * Author: sun.yukun@foxmail.com
 * Time: 2017/10/27 下午10:30
 * Blog: coderdaily.net
 */
public class String_01 {
    public static void main(String[] args) {
        String str = "abcd";
        System.out.println(str.replace('d', 'D'));
        System.out.println(str);

        String str1 = "hello world";
        String str2 = new String("hello world");
        String str3 = "hello world";
        String str4 = new String("hello world");
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str2 == str4);
    }
}
