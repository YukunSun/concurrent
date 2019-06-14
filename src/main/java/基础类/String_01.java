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

    /*链接：https://www.nowcoder.com/questionTerminal/17068b08ab1c4c50a012397ec9a272a6?toCommentId=421749
    来源：牛客网

    String str1 = "hello";
 
    这里的str1指的是方法区中的字符串常量池中的“hello”，编译时期就知道的； 
 
    String str2 = "he" + new String("llo");
 
    这里的str2必须在运行时才知道str2是什么，所以它是指向的是堆里定义的字符串“hello”，所以这两个引用是不一样的。
 
    如果用str1.equal(str2)，那么返回的是true；因为String类重写了equals()方法。
 
    编译器没那么智能,它不知道"he" + new String("llo")的内容是什么,所以才不敢贸然把"hello"这个对象的引用赋给str2.
 
    如果语句改为:"he"+"llo"这样就是true了。
*/
        String str5 = "hello";
        String str6 = "he" + new String("llo");
        System.out.println(str5 == str6);//false

        String str7 = new String("hello");
        String str8 = new String("hello");
        System.out.println(str7 == str8);
    }
}
