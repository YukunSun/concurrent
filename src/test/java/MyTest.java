import org.junit.Test;
import someconceptions.CallableTest;
import someconceptions.RunnableTest;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Author: sunyukun.china@gmail.com
 * Time: 2016/11/28 23:32
 * Blog: coderdaily.net
 */
public class MyTest {
    //Executor方便了管理Thread对象，无须显式地管理生命周期
    @Test
    public void testExecutor(){
        //ExecutorService是具有生命周期的Executor
//        ExecutorService exec = Executors.newCachedThreadPool();

        //一次性预先执行代价高昂的线程分配
//        ExecutorService exec = Executors.newFixedThreadPool(5);

        //确保只有唯一的线程在运行
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            exec.execute(new RunnableTest());
        }
        exec.shutdown();
    }

    //Runnable是执行工作的独立任务，但它不返回值，所以可以用Callable接口代替Runnable在结束时返回值。
    @Test
    public void testCallable(){
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        for (int i = 0; i < 5; i++) {
            //必须使用ExecutorService.submit去调用call()
            results.add(exec.submit(new CallableTest(i)));
        }
        for (Future<String> f :
                results) {
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                exec.shutdown();
            }
        }
    }




}
