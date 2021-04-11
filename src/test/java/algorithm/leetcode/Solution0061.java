package algorithm.leetcode;

import algorithm.util.ListNode;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/11 22:39
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/rotate-list/
 */
public class Solution0061 {
    static ListNode list = new ListNode(0);
    static ListNode list2 = new ListNode(1);

    @BeforeClass
    public static void beforeClass() throws Exception {
        list.addTail(1);
        list.addTail(2);
        System.out.println("original list:");
        list.printSingleLinkedList();

        list2.addTail(2);
        list2.addTail(3);
        list2.addTail(4);
        list2.addTail(5);
        System.out.println("original list:");
        list2.printSingleLinkedList();
    }

    @Test
    public void o1() {
        ListNode result = rotateRight(list, 4);
        result.printSingleLinkedList();

        ListNode result2 = rotateRight(list2, 2);
        result2.printSingleLinkedList();
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        int i = 1;
        for (; ; i++) {
            if (p.next == null) {
                p.next = head;//强制成环，方便遍历,p的位置正好是尾节点
                break;
            }
            p = p.next;
        }
        //而i的值正好是list.size
        for (int j = 0; j < i - k % i; j++) {
            p = p.next;
        }
        //不能直接打印，因为还是个环呢，需要再目的节点的前一个节点上切断环
        ListNode next = p.next;
        p.next = null;
        return next;
    }
}
