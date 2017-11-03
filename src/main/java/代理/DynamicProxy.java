package 代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description:可以把invoke翻译成借助.
 * <p>
 * 1.JDK动态代理只能代理有接口的类,并且只能代理接口方法,不能代理一般的类中的方法
 * 2.提供了一个使用InvocationHandler作为参数的构造方法。在代理类中做一层包装,业务逻辑在invoke方法中实现。
 * 3.重写了Object类的equals、hashCode、toString，它们都只是简单的调用了InvocationHandler的invoke方法，即可以对其进行特殊的操作，也就是说JDK的动态代理还可以代理上述三个方法。
 * 4.在invoke方法中我们甚至可以不用Method.invoke方法调用实现类就返回。这种方式常常用在RPC框架中,在invoke方法中发起通信调用远端的接口等。
 *
 * @author SunYukun
 * @date 2017/7/18
 */
public class DynamicProxy implements InvocationHandler {
    private Class<?> target;
    private Object real;

    public DynamicProxy(Class<?> target) {
        this.target = target;
    }

    public static void main(String[] args) {
        Moveable m = (Moveable) new DynamicProxy(Moveable.class).bind(new Cart());
        m.move();
    }

    //自定义的一个方法，实际执行类bind
    public Object bind(Object real) {
        this.real = real;
        //利用JDK提供的Proxy实现动态代理
        return Proxy.newProxyInstance(target.getClassLoader(), new Class[]{target}, this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start......");
//        实际执行的方法
        Object invoke = method.invoke(real, args);
        System.out.println("end......");
        return invoke;
    }
}
