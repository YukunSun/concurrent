package i2021.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2021/4/2 21:20
 * Blog: bengle.me
 */
public class ArrayListTest {
    @Test
    public void convertStringToNumber() {
        String value = "12345";
        char[] array = value.toCharArray();
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            //point:char转int
            sum += (value.charAt(i) - '0') * Math.pow(10, array.length - i - 1);
        }
        System.out.println("sum = " + sum);
    }

    /**
     * subList可能出现的问题
     */
    @Test
    public void subList() {
        ArrayList<Integer> list = new ArrayList(16);
        IntStream.range(1, 10).forEach(i -> {
            list.add(i);
        });
        System.out.println("list = " + list);
        List<Integer> subList = list.subList(0, 3);
        //1.取了subList之后，如果添加或移除会报:java.util.ConcurrentModificationException
        list.add(1);
//        list.remove(1);
        System.out.println("subList = " + subList);
        //2.修改subList不会导致报错，但会影响原列表的元素
        subList.add(-1);
        subList.remove(0);//rm by index
        subList.remove(Integer.valueOf(0));
        System.out.println("list(after subList was modified) = " + list);
    }
}
