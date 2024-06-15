package 单例模式;

/**
 * 首先思考需要满足的点：
 * 1 延迟加载
 * 2 不能被 create、不能被赋值
 * 3 缓存
 * 4 线程安全：https://cloud.tencent.com/developer/article/2071666
 */
public class Singleton {
    //private: 不想被创建
    private Singleton() {
    }


    //private：懒加载，只想暴露 单例模式.Singleton.getInstance，要不然都可以访问了，其他的同理
    //final: 有没有都行（语义上可能保证不被继承）
    //static: 直接通过类创建，而不用通过实例（因为单例模式.Singleton.SingletonHolder.INSTANCE必须是 static，所以这里也得必须是 static 了）
    private static final class SingletonHolder {
        //final: 不能被赋值，见 prove1；而且只能初始化1次
        //static:如果不是 static，单例模式.Singleton.getInstance中就得通过创建实例来访问（通过实例来访问自然要创建新的对象）
        private static final Singleton INSTANCE = new Singleton();
    }

    //staic: 不是 static 的话，就得通过创建实例来访问
    //final: 能使用 final，但没必要
    //线程安全：虚拟机会保证一个类的()方法在多线程环境中被正确地加锁、同步，如果多个线程同时去初始化一个类，那么只会有一个线程去执行这个类的()方法，其他线程都需要阻塞等待，直到活动线程执行()方法完毕。
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        /**
         * prove1：要是 单例模式.Singleton.SingletonHolder#INSTANCE 不被 final 修饰，下面的SingletonHolder.INSTANCE = new Singleton();就能编译通过，得到的两个实力就是两个实例
         */
        Singleton singleton = Singleton.getInstance();
        System.out.println("singleton = " + singleton);
        //Singleton.SingletonHolder.INSTANCE = new Singleton();
        System.out.println("Singleton.getInstance() = " + Singleton.getInstance());

    }
}