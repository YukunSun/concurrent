package 并发的设计模式.future;

import java.util.Random;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2019/9/23 20:18
 * Blog: coderdaily.net
 * <p>
 * 真正的数据，可能比较耗时
 */
public class RealData implements Data {
    protected final String result;

    public RealData(String param) {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        param = param + new Random().nextInt(20);
        this.result = param;
    }

    @Override
    public String getResult() {
        return result;
    }
}
