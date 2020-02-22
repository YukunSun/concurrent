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
    @Before
    public void constuctSingleLinkedList() {
        SingleLinkedList linkedList = new SingleLinkedList(23).addHead(6).addHead(15);
        linkedList.printSingleLinkedList();
    }


    @Test
    public void insert() {
    }
}
