package algorithm.leetcode;

import algorithm.util.ListNode;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/8 11:34
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/
 */
public class Solution0021 {
    static ListNode list = new ListNode(1);
    static ListNode list2 = new ListNode(1);

    @BeforeClass
    public static void beforeClass() throws Exception {
        list2.addTail(2);
        list.addTail(3);
        list2.addTail(4);
        list.addTail(5);
        System.out.println("original list:");
        list.printSingleLinkedList();
        System.out.println("original list2:");
        list2.printSingleLinkedList();
    }

    @Test
    public void oN() {
        ListNode result = mergeTwoLists(list, list2);
        result.printSingleLinkedList();
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;

        while (p1 != null || p2 != null) {
            if (p1 == null) {
                p.next = p2;
                p = p.next;
                p2 = p2.next;
                continue;
            }
            if (p2 == null) {
                p.next = p1;
                p = p.next;
                p1 = p1.next;
                continue;
            }
            if (p1.value <= p2.value) {
                p.next = p1;
                p = p.next;
                p1 = p1.next;
            } else {
                p.next = p2;
                p = p.next;
                p2 = p2.next;
            }
        }
        return dummy.next;
    }
}
