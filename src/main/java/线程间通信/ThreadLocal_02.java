package 线程间通信;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author SunYukun
 * @date 2017/7/8
 * <p>
 * 使用ThreadLocal改写，其就相当于一个容器
 */
public class ThreadLocal_02 {
    static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>();

    static class ParseDate implements Runnable {
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (t1.get() == null) {
                    t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                Date date = t1.get().parse("2018-02-07 19:29:" + i % 60);
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            service.execute(new ParseDate(i));
        }
    }
}
