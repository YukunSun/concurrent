package algorithm.util;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2020/2/22 13:19
 * Blog: coderdaily.net
 * <p>
 * 单链表
 */
public class ListNode {
    public Integer value;
    public ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

    /**
     * 添加到头结点比添加到结尾更方便
     */
    public ListNode addHead(Integer value) {
        if (value == null) {
            return this;
        }
        ListNode head = this;
        ListNode cur = new ListNode(value);
        //core
        cur.next = head;
        head = cur;

        return head;
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

    /**
     * 删除头结点
     */
    public ListNode delHead() {
        if (this == null) {
            return this;
        }
        ListNode head = this;
        head = this.next;
        return head;
    }

    /**
     * 删除最后一个结点
     *
     * @return
     */
    public ListNode delTail() {
        if (this == null) {
            return this;
        }
        ListNode head = this;
        ListNode p = head;
        ListNode p2 = p.next;
        while (p2.next != null) {
            p = p.next;
            p2 = p2.next;
        }
        p.next = null;
        return head;
    }

    /**
     * 打印链表
     */
    public void printSingleLinkedList() {
        if (this == null) {
            System.out.println("list = []");
        }
        ListNode p = this;
        while (p != null) {
            System.out.print(p.value + " -> ");
            p = p.next;
        }
    }
}
