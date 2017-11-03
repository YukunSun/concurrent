package disruptordemo;

import com.lmax.disruptor.EventFactory;

/**
 * Description:
 *
 * @author SunYukun
 * @date 2017/8/9
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    public LongEvent newInstance() {
        return new LongEvent();
    }
}
