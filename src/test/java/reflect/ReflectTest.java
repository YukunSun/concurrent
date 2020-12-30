package reflect;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author sunyk
 **/
public class ReflectTest {
    @Test
    public void reflectDemo() throws ClassNotFoundException, NoSuchMethodException {
//        1 get methods
        Method[] methods = ReflectTest.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

//       2 get Class
        Class c = ReflectTest.class;
        Assert.assertEquals(true, c instanceof Class);

        Class c2 = Class.forName("reflect.ReflectTest");
        Assert.assertEquals(c, c2);
//        3 class name
        Assert.assertEquals("reflect.ReflectTest", c.getName());
        Assert.assertEquals("ReflectTest", c.getSimpleName());
//        4 modifier
        Assert.assertEquals(true, Modifier.isPublic(c.getModifiers()));
        Assert.assertEquals(false, Modifier.isAbstract(c.getModifiers()));
//        5 package
        Assert.assertEquals("reflect", c.getPackageName());
//        6 super class
        Assert.assertEquals(Object.class, c.getSuperclass());
//        7 implemented interfaces
        Class c3 = Thread.class;
        for (Class itf : c3.getInterfaces()) {
            System.out.println(itf);
        }
//        8 constructor
        for (Constructor constructor : c3.getConstructors()) {
            System.out.println(constructor);
        }
        Constructor constructor = c3.getConstructor(new Class[]{Runnable.class, String.class});
        Assert.assertEquals("public java.lang.Thread(java.lang.Runnable,java.lang.String)", constructor.toString());
//        利用Constructor对象实例化一个类
        System.out.println(constructor.getParameterTypes());
        try {
            Thread thread = (Thread) constructor.newInstance(new Thread(), "11");
            System.out.println(thread);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        9 fields
        for (Field f : c3.getFields()) {
            //获取不到 private 字段
            System.out.println(f);
        }
        try {
            Field interrupted = c3.getField("MAX_PRIORITY");
            Assert.assertEquals(int.class, interrupted.getType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
//        10 annotations
        for (Annotation annotation : Runnable.class.getAnnotations()) {
            System.out.println(annotation);
        }
    }
}
