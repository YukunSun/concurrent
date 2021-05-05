package algorithm.leetcode;

import algorithm.util.ListNode;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/8 10:40
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class Solution0206 {
    static ListNode list = new ListNode(1);

    @BeforeClass
    public static void beforeClass() throws Exception {
        list.addTail(2);
        list.addTail(3);
        list.addTail(4);
        list.addTail(5);
        System.out.println("original list:");
        list.printSingleLinkedList();
    }

    @Test
    public void o1() {
        System.out.println("result:");
        ListNode result = reverseList(list);
        result.printSingleLinkedList();
    }

    /**
     * 迭代方式
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        //1 边界
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    @Test
    public void on() {
        System.out.println("result:");
        ListNode result = reverseList2(list);
        result.printSingleLinkedList();
    }

    /**
     * 递归方式
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode second = head.next;//second 正好是尾节点的前一个节点，因为如果是尾节点的话是到不了这里的（直接返回了）
        ListNode reverse = reverseList2(second);
        second.next = head;
        head.next = null;
        return reverse;
    }
}
