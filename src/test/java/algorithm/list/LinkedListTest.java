package algorithm.list;

import algorithm.util.SingleLinkedList;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2020/2/22 13:20
 * Blog: coderdaily.net
 */
public class LinkedListTest {
    SingleLinkedList linkedList;

    @Before
    public void constuctSingleLinkedList() {
        linkedList = new SingleLinkedList(23).addHead(6).addHead(15);
    }

    /**
     * 构造链表
     */
    @Test
    public void init() {
        linkedList.printSingleLinkedList();
    }

    @Test
    public void addTailTest() {
        linkedList.addTail(3);
        linkedList.printSingleLinkedList();
    }

    /**
     * 在指定位置插入结点
     * <p>
     * 比如插入9变成：15 -> 6 -> 23 ==》15 6 9 23
     */
    @Test
    public void addNoteAtSomePosition() {
        SingleLinkedList p = linkedList;
        while (p.value != 6) {
            p = p.next;
        }
        SingleLinkedList cur = new SingleLinkedList(9);
        cur.next = p.next;
        p.next = cur;
        linkedList.printSingleLinkedList();
    }

    @Test
    public void delHead() {
        SingleLinkedList deleted = linkedList.delHead();
        deleted.printSingleLinkedList();
    }
}
