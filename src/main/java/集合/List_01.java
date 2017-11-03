package 集合;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * Author: sun.yukun@foxmail.com
 * Time: 2017/10/29 下午1:48
 * Blog: coderdaily.net
 * <p>
 * 把年龄>3的用户删除
 */
public class List_01 {
    static class User {
        public Integer age;

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public User(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<User> list = new ArrayList<User>();
        list.add(new User(1));
        list.add(new User(2));
        list.add(new User(3));
        list.add(new User(4));
        list.add(new User(5));
//        for i 错误
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getAge() > 3) {
//                list.remove(i);
//            }
//        }
//        printList(list);

////       for i 正确
//        for (int i = list.size() - 1; i >= 0; i--) {
//            if (list.get(i).getAge() > 3) {
//                list.remove(i);
//            }
//        }
//        printList(list);


////        for each 错误
//        for (User u : list) {
//            if (u.getAge() > 3) {
//                list.remove(u);
//            }
//        }
//        printList(list);

////        for each 正确,复制了新的数组
//        CopyOnWriteArrayList<User> list1 = new CopyOnWriteArrayList<User>(list);
//        for (User u : list1) {
//            if (u.getAge() > 3) {
//                list.remove(u);
//            }
//        }
//        printList(list);

////iterator 错误
//        Iterator<User> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            User u = iterator.next();
//            if (u.getAge() > 3) {
//                list.remove(u);
//            }
//        }
//        printList(list);

        //iterator 正确
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()) {
            User u = iterator.next();
            if (u.getAge() > 3) {
                iterator.remove();
            }
        }
        printList(list);
    }

    private static void printList(List<User> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
