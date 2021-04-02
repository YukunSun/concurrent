package i2021.algorithm;

import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/2 21:18
 * Blog: bengle.me
 */
public class LinkedListTest {

    @Test
    public void addTwoNumOnDoubleLinkedList() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(7);
//        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(9);

        ListNode result = addTwoNumbers(l1, l2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode p3 = result;
        ListNode p1 = l1;
        ListNode p2 = l2;
        int tmp = 0;

        while (p1 != null || p2 != null) {
            tmp += (p1 == null ? 0 : p1.val) + (p2 == null ? 0 : p2.val);
            result.next = new ListNode(tmp % 10);
            if (p1 != null) {
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }
            result = result.next;
            tmp /= 10;
        }
        if (tmp != 0) {
            result.next = new ListNode(tmp);
        }
        return p3.next;
    }

    class ListNode {
        public ListNode next;
        public int val;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
