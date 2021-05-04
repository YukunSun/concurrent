package algorithm.leetcode;

import algorithm.util.ListNode;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/5/3 20:44
 * Blog: bengle.me
 * <p>
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/
 */
public class Solution0024 {

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
    public void swapPairsTest() {
        ListNode result = swapPairs(list);
        result.printSingleLinkedList();
    }

    /**
     * 法一：临时存储头指针用于返回，然后逐个遍历交换
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode third = swapPairs(head.next.next);//这个递归是真绕啊！
        ListNode second = head.next;
        head.next = third;
        second.next = head;
        return second;
    }
}
