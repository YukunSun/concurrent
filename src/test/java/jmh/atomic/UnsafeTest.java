package jmh.atomic;

import org.junit.Assert;
import org.junit.Test;
import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author sunyk
 **/
public class UnsafeTest {

    @Test
    public void theUnsafe() throws NoSuchFieldException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        System.out.println(field);

        System.out.println(getUnsafe());
    }

    /**
     * Unsafe 1: 绕过类构造函数完成对象创建
     */
    @Test
    public void createInstance() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Assert.assertEquals(10, new Example().getI());

        //create by reflect
//        Example.class.newInstance();
        Example e = Example.class.getDeclaredConstructor().newInstance();
        Assert.assertEquals(10, e.getI());

        //create by Unsafe
        Example e2 = (Example) getUnsafe().allocateInstance(Example.class);
        Assert.assertEquals(0, e2.getI());
    }


    /**
     * Unsafe 2: 直接修改内存
     */
    @Test
    public void updateMemory() throws NoSuchFieldException {
        Example example = new Example();
        Assert.assertEquals(10, example.getI());

//        Unsafe unsafe = Unsafe.getUnsafe();
        Unsafe unsafe = getUnsafe();
        Field field = example.getClass().getDeclaredField("i");
        //可以直接获取该字段的内存偏移量，并修改其值
        unsafe.putInt(example, unsafe.objectFieldOffset(field), 20);

        Assert.assertEquals(20, example.getI());
    }

    /**
     * Unsafe 3：类的加载
     */
    @Test
    public void classLoad() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        byte[] content = loadClassFile();
        Class c = getUnsafe().defineAnonymousClass(Example.class, content, null);
        Object result = c.getMethod("getI").invoke(c.getDeclaredConstructor().newInstance(), null);
        Assert.assertEquals(10, result);
    }

    private static byte[] loadClassFile() throws IOException {
        File file = new File("/Users/sunyk/Documents/ideaworks/concurrent/src/test/java/jmh/atomic/Example.class");
        try (FileInputStream input = new FileInputStream(file)) {
            byte[] content = new byte[(int) file.length()];
            input.read(content);
            return content;
        }
    }

    /**
     * 通过反射机制获取 Unsafe 对象
     *
     * @return
     */
    private static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Exception e) {
            throw new RuntimeException("can't initial the unsafe instance.", e);
        }
    }
}

class Example {
    private int i;

    public Example() {
        i = 10;
    }

    public int getI() {
        return i;
    }
}
