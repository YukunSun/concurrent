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
    public void reflectDemo() throws ClassNotFoundException {
        //get methods
        Method[] methods = ReflectTest.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

//       get Class
        Class c = ReflectTest.class;
        Assert.assertEquals(true, c instanceof Class);

        Class c2 = Class.forName("reflect.ReflectTest");
        Assert.assertEquals(c, c2);
//        class name
        Assert.assertEquals("reflect.ReflectTest", c.getName());
        Assert.assertEquals("ReflectTest", c.getSimpleName());
//        modifier
        Assert.assertEquals(true, Modifier.isPublic(c.getModifiers()));
        Assert.assertEquals(false, Modifier.isAbstract(c.getModifiers()));
//        package
        Assert.assertEquals("reflect", c.getPackageName());
//        super class
        Assert.assertEquals(Object.class, c.getSuperclass());
//        implemented interfaces
        Class c3 = Thread.class;
        for (Class itf : c3.getInterfaces()) {
            System.out.println(itf);
        }
//        constructor
        for (Constructor constructor : c3.getConstructors()) {
            System.out.println(constructor);
        }
//        fields
        for (Field f : c3.getFields()) {
            System.out.println(f);
        }
//        annotations
        for (Annotation annotation : Runnable.class.getAnnotations()) {
            System.out.println(annotation);
        }
    }
}
