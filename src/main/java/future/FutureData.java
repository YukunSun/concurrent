package future;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2019/9/23 20:18
 * Blog: coderdaily.net
 * <p>
 * 它是真正数据 RealData 的代理，封装了获取 RealData 的过程
 */
public class FutureData implements Data {

    protected RealData realData = null;
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();//如果 RealData 准备好，则通知 setResult
    }

    @Override
    public String getResult() {
        while (!isReady) {
            try {
                wait();//一直等待，直到收到通知
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getResult();
    }
}
