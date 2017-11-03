package 并发类容器;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Description:
 *
 * @author SunYukun
 * @date 2017/8/6
 */
public class ConcurrentLinkedQueue_01 {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> clq = new ConcurrentLinkedQueue<Integer>();
        clq.offer(1);
        clq.offer(2);
        clq.offer(3);
        clq.offer(4);
        clq.add(5);


        System.out.println(clq.remove());
        System.out.println(clq.poll());
        System.out.println(clq.element());
        System.out.println(clq.peek());
    }
}
