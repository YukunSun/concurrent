package algorithm.leetcode;

import org.junit.Before;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2020/2/23 13:19
 * Blog: coderdaily.net
 * <p>
 * https://leetcode-cn.com/problems/add-two-numbers/
 */
public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = new ListNode(0);
        int high = 0;
        ListNode result = p;
        //如果不加 high !=0 有可能会出现l1,l2只有一个结点时,会丢掉最低位
        while (l1 != null || l2 != null || high != 0) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;

            //核心逻辑
            int sum = v1 + v2 + high;
            int low = sum % 10;
            high = sum / 10;
            //需要有一个指针不断移动
            ListNode cur = new ListNode(low);
            p.next = cur;
            p = p.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (high > 0) {
            result.next = new ListNode(high);
        }
        return result.next;
    }

    ListNode l1;
    ListNode l2;

//    @Before
//    public void before() {
//        l1 = new ListNode(2).addTail(4).addTail(3);
//        l1.printSingleLinkedList();
//
//        l2 = new ListNode(5).addTail(6).addTail(4);
//        l2.printSingleLinkedList();
//    }

    @Before
    public void before2() {
        l1 = new ListNode(5);
        l1.printSingleLinkedList();

        l2 = new ListNode(5);
        l2.printSingleLinkedList();
    }

    @Test
    public void addTwoNumbersTest() {
        ListNode l3 = addTwoNumbers(l1, l2);
        l3.printSingleLinkedList();
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    //调试用
    public void printSingleLinkedList() {
        if (this == null) {
            System.out.println("list = []");
        }
        ListNode p = this;
        while (p != null) {
            System.out.print(p.val + " -> ");
            p = p.next;
        }
    }

    /**
     * 在结尾添加结点
     */
    public ListNode addTail(Integer value) {
        if (value == null) {
            return this;
        }
        //指针 p 划到最后端才能添加
        ListNode p = this;
        //core
        while (p.next != null) {
            p = p.next;
        }
        ListNode cur = new ListNode(value);
        p.next = cur;
        cur.next = null;

        return this;
    }
}