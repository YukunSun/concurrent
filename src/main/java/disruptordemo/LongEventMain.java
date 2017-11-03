package disruptordemo;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author SunYukun
 * @date 2017/8/9
 */
public class LongEventMain {
    public static void main(String[] args) {
        //    创建缓冲区
        ExecutorService executorService = Executors.newCachedThreadPool();
        LongEventFactory factory = new LongEventFactory();
    /*ringBuffer的大小，必须是2的指数倍*/
        int ringBufferSize = 1024 * 1024;

//        RingBuffer<LongEvent> ringBuffer;
//        private LongEventProducer producer = new LongEventProducer(ringBuffer);

        /**
         *
         */
        Disruptor<LongEvent> eventDisruptor = new Disruptor<LongEvent>(factory, ringBufferSize, executorService, ProducerType.SINGLE, new YieldingWaitStrategy());
//        连接消费事件
        eventDisruptor.handleEventsWith(new LongEventHandler());
//        启动
        eventDisruptor.start();

//        事件发布是一个两阶段提交的过程
//        发布事件
        RingBuffer<LongEvent> ringBuffer = eventDisruptor.getRingBuffer();
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 0; i < 100; i++) {
            byteBuffer.putLong(0, i);
            producer.OnData(byteBuffer);
        }
    }
}
