package algorithm;

import java.util.ArrayList;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2019-06-20 17:54
 * Blog: coderdaily.net
 * <p>
 * 删除数组中的指定元素并将元素前移
 */
public class DelElementsOfArray {

    public static ArrayList<Integer> getResult(ArrayList<Integer> arrayList, Integer target) {
        int space = -1;
        for (int i = 0; i < arrayList.size(); i++) {
            if (target.equals(arrayList.get(i))) {
                for (int j = i; j < arrayList.size(); j++) {
                    if (j == arrayList.size() - 1) {
                        arrayList.set(j, space);
                    } else {
                        arrayList.set(j, arrayList.get(j + 1));
                    }
                }
            }
        }
        return arrayList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(7);
        list.add(3);
        list.add(4);

        System.out.println(getResult(list, 3));
    }
}
