package algorithm.util;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2020/2/22 13:19
 * Blog: coderdaily.net
 * <p>
 * 单链表
 */
public class SingleLinkedList {
    public Integer value;
    public SingleLinkedList next;

    public SingleLinkedList(int value) {
        this.value = value;
    }

    /**
     * 添加到头结点比添加到结尾更方便
     */
    public SingleLinkedList addHead(Integer value) {
        if (value == null) {
            return this;
        }
        SingleLinkedList head = this;
        SingleLinkedList cur = new SingleLinkedList(value);
        //core
        cur.next = head;
        head = cur;

        return head;
    }

    /**
     * 在结尾添加结点
     */
    public SingleLinkedList addTail(Integer value) {
        if (value == null) {
            return this;
        }
        //指针 p 划到最后端才能添加
        SingleLinkedList p = this;
        //core
        while (p.next != null) {
            p = p.next;
        }
        SingleLinkedList cur = new SingleLinkedList(value);
        p.next = cur;
        cur.next = null;

        return this;
    }

    /**
     * 打印链表
     */
    public void printSingleLinkedList() {
        if (this == null) {
            System.out.println("list = []");
        }
        SingleLinkedList p = this;
        while (p != null) {
            System.out.print(p.value + " -> ");
            p = p.next;
        }
    }
}
