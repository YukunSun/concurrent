package reflect;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;

/**
 * @author sunyk
 **/
public class ReflectionWithArray {

    /**
     * 1 通过反射访问数组
     */
    @Test
    public void arrayCreate() {
        //创建数组
        int[] array = (int[]) Array.newInstance(int.class, 3);
        Assert.assertEquals(3, array.length);
        Assert.assertEquals(int.class, array.getClass().getComponentType());//检查成员类型

        //访问数组
        Array.set(array, 0, 100);
        Array.set(array, 1, 200);
        Array.set(array, 2, 100);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    /**
     * 所以，一但获得了Class对象，就可以创建数组了...
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void getClassOfArray() throws ClassNotFoundException {
        //如果不通过反射的话你可以这样来获取数组的Class对象：
        Class s = String[].class;
        Assert.assertEquals(true, s.isArray());

        //如果使用Class.forName()方法来获取Class对象则不是那么简单。比如你可以像这样来获得一个原生数据类型（primitive）int数组的Class对象：
        Class intArray = Class.forName("[I");//(boolean, byte, char, short, int, long, float, and double)
        Assert.assertEquals(int.class, intArray.getComponentType());
        Assert.assertEquals(char.class, Class.forName("[C").getComponentType());
        Assert.assertEquals(short.class, Class.forName("[S").getComponentType());
        Assert.assertEquals(double.class, Class.forName("[D").getComponentType());
        Assert.assertEquals(float.class, Class.forName("[F").getComponentType());
        Assert.assertEquals(byte.class, Class.forName("[B").getComponentType());
//        Assert.assertEquals(boolean.class, Class.forName("？？").getComponentType());//fixme boolean.class 用什么？

//        对于普通对象类型的数组有一点细微的不同：
//        注意‘[L’的右边是类名，类名的右边是一个‘;’符号。这个的含义是一个指定类型的数组。
        Class stringArrayClass = Class.forName("[Ljava.lang.String;");
        //需要注意的是，你不能通过Class.forName()方法获取一个原生数据类型的Class对象。下面这两个例子都会报ClassNotFoundException：
//        Class intClass1 = Class.forName("I");
//        Class intClass2 = Class.forName("int");
        Class intClass2 = int.class;
    }
}
