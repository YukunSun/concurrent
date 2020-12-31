package reflect;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author sunyk
 **/
public class ReflectionWithGenericTest {
    /**
     * 0 通过输出内容可以观察到：只剩占位符了，相关的参数信息却没有......
     */
    @Test
    public void test2() {
        List<Frob> list = new ArrayList<>();
        Map<Frob, Fnorkle> map = new HashMap<>();
        Quark<Fnorkle> quark = new Quark<>();
        Paricle<Long, Double> paricle = new Paricle<>();
        //查看类型参数
        System.out.println("Arrays.toString(list.getClass().getTypeParameters()) = " + Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println("Arrays.toString(map.getClass().getTypeParameters()) = " + Arrays.toString(map.getClass().getTypeParameters()));
        System.out.println("Arrays.toString(quark.getClass().getTypeParameters()) = " + Arrays.toString(quark.getClass().getTypeParameters()));
        System.out.println("Arrays.toString(paricle.getClass().getTypeParameters()) = " + Arrays.toString(paricle.getClass().getTypeParameters()));
        //output:
        // Arrays.toString(list.getClass().getTypeParameters()) = [E]
        //Arrays.toString(map.getClass().getTypeParameters()) = [K, V]
        //Arrays.toString(quark.getClass().getTypeParameters()) = [Q]
        //Arrays.toString(paricle.getClass().getTypeParameters()) = [POSITION, MOMENTUM]
    }

    /**
     * 1 获取字段类型
     */
    @Test
    public void fieldType() throws NoSuchFieldException {
        Field field = Foo.class.getField("field");
        Type genericFieldType = field.getGenericType();
        if (genericFieldType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) genericFieldType;
            for (Type fieldType : type.getActualTypeArguments()) {
                Assert.assertEquals("String", fieldType.toString());
            }
        }
    }

    /**
     * 2 获取返回值类型
     */
    @Test
    public void returnType() throws NoSuchMethodException {
        Method method = Foo.class.getMethod("doSth", null);
        Type returnType = method.getGenericReturnType();
        if (returnType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments = type.getActualTypeArguments();
            for (Type typeArgument : typeArguments) {
                Assert.assertEquals("String", typeArgument.toString());
            }
        }
    }

    /**
     * 3 获取参数类型
     */
    @Test
    public void paramType() throws NoSuchMethodException {
        Method method = Foo.class.getMethod("doSth", List.class);

        Type[] genericParameterTypes = method.getGenericParameterTypes();

        for (Type genericParameterType : genericParameterTypes) {
            if (genericParameterType instanceof ParameterizedType) {
                ParameterizedType aType = (ParameterizedType) genericParameterType;
                Type[] parameterArgTypes = aType.getActualTypeArguments();
                for (Type parameterArgType : parameterArgTypes) {
                    Assert.assertEquals("String", parameterArgType.toString());
                }
            }
        }
    }
}

class Frob {
}

class Fnorkle {
}

class Quark<Q> {
    public List<Q> field = new ArrayList<>();

    public <Q> List<Q> doSth(List<Q> param) {
        return param;
    }
}

class Paricle<POSITION, MOMENTUM> {
}

class Foo<String> {
    public List<String> field = new ArrayList<>();

    public List<String> doSth() {
        return null;
    }

    public List<String> doSth(List<String> param) {
        return param;
    }
}
