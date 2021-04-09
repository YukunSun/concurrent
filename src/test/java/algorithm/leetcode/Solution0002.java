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
}
