package algorithm;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2019-06-20 16:49
 * Blog: coderdaily.net
 * <p>
 * 用两个栈实现一个队列：如果每次往栈中再放置一个元素，意味着 s2 得把所有的元素写回 s1 ，这是什么屁玩意儿题目，没意义
 */
public class ImplementQueueWithStack {
    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        Queue<Integer> queue = new PriorityQueue<Integer>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);

        //s1，用作入栈
        while (!queue.isEmpty()) {
            s1.push(queue.poll());
        }

        //s2 用作出栈
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }

        System.out.println(s2);
    }
}
