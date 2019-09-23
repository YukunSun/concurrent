package future;

/**
 * @author: sun.yukun@foxmail.com
 * Time: 2019/9/23 20:18
 * Blog: coderdaily.net
 */
public class Client {
    public Data request(final String queryStr) {
        final FutureData futureData = new FutureData();
        new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }.start();
        return futureData;
    }

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        Data data = client.request("ping");//这里将立即返回数据
        System.out.println("请求完毕，立即返回");

        Thread.sleep(5 * 1000);//模拟其他业务逻辑

        System.out.println("真实的数据：" + data.getResult());//待真实数据差不多构造完毕时，才去获取数据
    }
}
