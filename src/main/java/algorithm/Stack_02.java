package algorithm;

import java.util.Stack;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2018/1/13 上午9:48
 * Blog: coderdaily.net
 * <p>
 * 给定一个栈s1的乱序元素，一个空栈s2和一个变量t，将s1的内容依次压入s2.
 */
public class Stack_02 {
    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<Integer>();
        s1.push(3);
        s1.push(2);
        s1.push(1);
        s1.push(5);
        s1.push(4);
        System.out.println("s1 :" + s1);

        Stack<Integer> s2 = new Stack<Integer>();
        Integer temp = null;

        while (!s1.isEmpty()) {
            if (s2.isEmpty() || s1.peek() >= s2.peek()) {
                s2.push(s1.pop());
            } else {
                temp = s1.pop();
                while (!s2.isEmpty() && (temp < s2.peek())) {
                    s1.push(s2.pop());

                }
                s2.push(temp);
            }
        }
        System.out.println(s2);
    }
}
