package algorithm;

import java.util.Stack;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2018/1/13 上午9:26
 * Blog: coderdaily.net
 */
public class Stack_01 {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack);

        Integer a = stack.pop();
        System.out.println(stack);

        Integer b = stack.peek();
        System.out.println(stack);
    }
}
