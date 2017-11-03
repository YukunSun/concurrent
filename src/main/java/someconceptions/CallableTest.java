package someconceptions;

import java.util.concurrent.Callable;

/**
 * Author: sunyukun.china@gmail.com
 * Time: 2016/11/29 0:15
 * Blog: coderdaily.net
 */
public class CallableTest implements Callable<String> {
    private int id;

    public CallableTest(int id) {
        this.id = id;
    }

    public String call() throws Exception {
        return "the result---" + id;
    }
}
