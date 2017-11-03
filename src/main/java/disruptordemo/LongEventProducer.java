package disruptordemo;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @author SunYukun
 * @date 2017/8/9
 */
public class LongEventProducer {
    //    环状缓冲区
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * 自己定义的一个方法
     *
     * @param byteBuffer
     */
    public void OnData(ByteBuffer byteBuffer) {
//        得到ringBuffer的下一个的事件槽
        long sequence = ringBuffer.next();
        try {
//        用一个索引取出空的事件，用于填充。
            LongEvent event = ringBuffer.get(sequence);
//        获取要通过事件传递的业务数据
            event.setValue(byteBuffer.getLong(0));
        } finally {
//            必须写，只有发布了，才能被处理
            ringBuffer.publish(sequence);
        }


    }
}
