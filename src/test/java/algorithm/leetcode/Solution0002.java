package algorithm.leetcode;

import algorithm.util.ListNode;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/8 12:02
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/add-two-numbers/solution/
 */
public class Solution0002 {
    static ListNode list = new ListNode(2);
    static ListNode list2 = new ListNode(5);

    @BeforeClass
    public static void beforeClass() throws Exception {
        list.addTail(4);
        list.addTail(9);

        list2.addTail(6);
        list2.addTail(4);
        list2.addTail(9);

        System.out.println("original list:");
        list.printSingleLinkedList();
        System.out.println("original list2:");
        list2.printSingleLinkedList();
    }

    @Test
    public void oN() {
        ListNode result = addTwoNumbers(list, list2);
        result.printSingleLinkedList();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode p1 = l1;
        ListNode p2 = l2;
        int tmp = 0;//用于存储想加的临时值
        while (p1 != null || p2 != null) {
            //第一个三目运算符需要加括号...好坑
            tmp += (p1 == null ? 0 : p1.value) + (p2 == null ? 0 : p2.value);
            p.next = new ListNode(tmp % 10);//当前值
            if (p1 != null) {
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }
            tmp = tmp / 10;//进位
            p = p.next;
        }
        if (tmp > 0) {
            p.next = new ListNode(1);
        }
        return dummy.next;
    }

    @Test
    public void debug() {
        int tmp = 0;
        int a = 0;
        int b = 1;
        tmp += a == 0 ? 0 : 1 + b == 0 ? 0 : 1;//tmp=0
//        tmp += (a == 0 ? 0 : 1) + b == 0 ? 0 : 1;//tmp=1
        System.out.println("tmp = " + tmp);
    }

    @Test
    public void oN2() {
        ListNode result = addTwoNumbers2(list, list2);
        result.printSingleLinkedList();
    }

    /**
     * 同1，只不过更好理解
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode p1 = l1;
        ListNode p2 = l2;
        int carry = 0;//用于存储想加的临时值

        while (p1 != null || p2 != null) {
            int sum = (p1 == null ? 0 : p1.value) + (p2 == null ? 0 : p2.value);
            p.next = new ListNode((sum + carry) % 10);//算余数，(sum + carry)这个是重点
            carry = (sum + carry) / 10;//算进位，(sum + carry)也是重点
            if (p1 != null) {
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }
            p = p.next;
        }
        if (carry > 0) {
            p.next = new ListNode(1);
        }
        return dummy.next;
    }
}