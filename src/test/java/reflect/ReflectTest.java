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
            //Class.getField(String name)和Class.getFields()只会返回公有的变量，无法获取私有变量,要想获取私有变量你可以调用Class.getDeclaredField(String name)方法或者Class.getDeclaredFields()方法
            System.out.println(f);
        }
        for (Field f : c3.getDeclaredFields()) {//获取private field演示
            System.out.println(f);
        }

        try {
            //获取字段类型
//            Field threadStatus = c3.getField("threadStatus");
            Field threadStatus = c3.getDeclaredField("daemon");
//            Assert.assertEquals(int.class, threadStatus.getType());
            //通过反射修改字段值
            Thread newObj = new Thread();
            threadStatus.setAccessible(true);//同样的，注意Method.setAcessible(true)这行代码，通过调用setAccessible()方法会关闭指定类的Method实例的反射访问检查，这行代码执行之后不论是私有的、受保护的以及包访问的作用域，你都可以在任何地方访问，即使你不在他的访问权限作用域之内。但是你如果你用一般代码来访问这些不在你权限作用域之内的代码依然是不可以的，在编译的时候就会报错。
            Object obj = threadStatus.get(newObj);
//            threadStatus.set(obj, false);
            threadStatus.setBoolean(obj, false);//java.lang.IllegalArgumentException: Can not set boolean field java.lang.Thread.daemon to java.lang.Boolean
        } catch (Exception e) {
            e.printStackTrace();
        }

//        10 annotations
        for (Annotation annotation : Runnable.class.getAnnotations()) {
            System.out.println(annotation);
        }
    }
}
