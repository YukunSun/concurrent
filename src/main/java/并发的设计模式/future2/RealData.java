package 并发的设计模式.future2;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2019/9/23 21:14
 * Blog: coderdaily.net
 */
public class RealData implements Callable<String> {
    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para + new Random().nextInt(20);
    }
}
