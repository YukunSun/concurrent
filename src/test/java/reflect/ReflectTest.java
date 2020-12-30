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
        Class c3 = Thread.class;
//        1 get methods
        Method[] methods = c3.getDeclaredMethods();
        for (Method method : methods) {
            //可以获取到private方法
            System.out.println(method);
        }
        //获取方法对象
        Method methodRun = c3.getMethod("run", null);
        Assert.assertEquals("public void java.lang.Thread.run()", methodRun.toString());
        //获取返回值、参数
        Assert.assertEquals("void", methodRun.getReturnType().toString());
        Assert.assertEquals(0, methodRun.getParameterCount());
        //通过method对象调用方法
        try {
            Object returnValue = methodRun.invoke(new Thread(), null);//只是举例而已,如果是一个静态方法调用的话则可以用null代替指定对象作为invoke()的参数
            Assert.assertEquals(null, returnValue);
        } catch (Exception e) {
            e.printStackTrace();
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
            //获取字段类型
            Field interrupted = c3.getField("MAX_PRIORITY");
            Assert.assertEquals(int.class, interrupted.getType());
            //通过反射修改字段值
            Thread newObj = new Thread();
            Object obj = interrupted.get(newObj);
//            interrupted.set(obj, 10000);//Can not set static final int field java.lang.Thread.MAX_PRIORITY to java.lang.Integer，要不是 final 的就改成功了，懒得换case了
        } catch (Exception e) {
            e.printStackTrace();
        }

//        10 annotations
        for (Annotation annotation : Runnable.class.getAnnotations()) {
            System.out.println(annotation);
        }
    }
}
