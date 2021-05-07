package algorithm.leetcode;

import algorithm.util.ListNode;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/5/7 09:46
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class Solution0019 {
    static ListNode list = new ListNode(1);
    static ListNode list2 = new ListNode(1);

    @BeforeClass
    public static void beforeClass() throws Exception {
        list2.addTail(2);
        list.addTail(3);
//        list2.addTail(4);
        list.addTail(5);
        System.out.println("original list:");
        list.printSingleLinkedList();
        System.out.println("original list2:");
        list2.printSingleLinkedList();
    }

    @Test
    public void removeNthFromEndTest() {
        ListNode result = removeNthFromEnd(list, 1);
        result.printSingleLinkedList();

        ListNode result2 = removeNthFromEnd(list2, 1);
//        Assert.assertEquals(null, result2);
        result2.printSingleLinkedList();
    }

    /**
     * 两遍遍历
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode result = head;
        ListNode p1 = head;
        int size = 0;
        ListNode tmp = head;
        while (tmp != null) {
            tmp = tmp.next;
            size += 1;
        }
        int position = size - n - 1;
        for (int i = 0; i < position; i++) {
            p1 = p1.next;
        }
        if (position < 0) {//这里的case：比如只有1个节点或2个节点，然后删除最后一个
            return result.next;
        } else {
            p1.next = p1.next.next;
        }
        return result;
    }
}
