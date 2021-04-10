package algorithm.leetcode;

import algorithm.util.ListNode;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/10 23:10
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/odd-even-linked-list/solution/
 */
public class Solution0328 {
    static ListNode list = new ListNode(1);

    @BeforeClass
    public static void beforeClass() throws Exception {
        list.addTail(2);
        list.addTail(3);
        list.addTail(4);
        list.addTail(5);
        list.addTail(6);
        list.addTail(7);
        list.addTail(8);
        System.out.println("original list:");
        list.printSingleLinkedList();
    }

    @Test
    public void oddEvent() {
        ListNode result = oddEvenList(list);
        result.printSingleLinkedList();
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode head1 = head;
        ListNode head2 = head.next;
        ListNode p1 = head1;
        ListNode p2 = head2;
        while (p1.next != null && p2.next != null) {
            p1.next = p2.next;
            p1 = p2.next;
            p2.next = p1.next;
            p2 = p1.next;
        }
        p1.next = head2;
        return head1;
    }
}
