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
 * 会报错，因为SimpleDateFormat.parse()是线程不安全的
 */
public class ThreadLocal_01 {
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");

    static class ParseDate implements Runnable {
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                Date date = format.parse("2018-02-07 19:29:" + i % 60);
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
