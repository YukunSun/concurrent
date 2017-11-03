package disruptordemo;

import com.lmax.disruptor.EventHandler;

/**
 * Description:消费端
 *
 * @author SunYukun
 * @date 2017/8/9
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("消费端处理了：" + longEvent.getValue());
    }
}
