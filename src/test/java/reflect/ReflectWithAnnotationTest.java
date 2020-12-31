package reflect;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author sunyk
 **/
public class ReflectWithAnnotationTest {
    /**
     * 定义一个几乎无所不包的注解以供测试
     */
    @Retention(value = RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface MyAnnotation {
        String name();

        String value() default "foo";
    }

    @MyAnnotation(value = "1", name = "2")
    public class TheClass {
        @MyAnnotation(name = "3", value = "5")
        String field;

        @MyAnnotation(name = "4")
        String doSth() {
            return "sth";
        }

        String doSth(@MyAnnotation(name = "bar") String param) {
            return "sth";
        }
    }

    /**
     * 1 类注解
     */
    @Test
    public void classAnn() {
        Class c = TheClass.class;
        MyAnnotation myAnnotation = (MyAnnotation) c.getAnnotation(MyAnnotation.class);
        Assert.assertEquals("2", myAnnotation.name());
        Assert.assertEquals("1", myAnnotation.value());
    }

    /**
     * 2 方法注解
     */
    @Test
    public void methodAnn() throws NoSuchMethodException {
        Class c = TheClass.class;
        //获取方法对象
        Method method = c.getDeclaredMethod("doSth");
        MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
        Assert.assertEquals("4", myAnnotation.name());
        Assert.assertEquals("foo", myAnnotation.value());
    }

    /**
     * 3 参数注解
     */
    @Test
    public void paramAnn() throws NoSuchMethodException {
        Class c = TheClass.class;
        //获取方法对象
        Method method = c.getDeclaredMethod("doSth");
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (Annotation[] annotations : parameterAnnotations) {
            for (Annotation annotation : annotations) {
                MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
                Assert.assertEquals("bar", myAnnotation.name());
                Assert.assertEquals("foo", myAnnotation.value());
            }
        }
    }


    /**
     * 4 变量注解
     */
    @Test
    public void fieldAnn() throws NoSuchFieldException {
        Class c = TheClass.class;
        //获取字段对象
        Field field = c.getDeclaredField("field");
        MyAnnotation myAnnotation = field.getAnnotation(MyAnnotation.class);
        Assert.assertEquals("3", myAnnotation.name());
        Assert.assertEquals("5", myAnnotation.value());
    }
}
