package socketDemo;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Author: sunyukun.china@gmail.com
 * Time: 2016/12/28 23:13
 * Blog: coderdaily.net
 *
 * 模拟http请求
 */
public class HttpServer {
    public static void main(String[] args) throws Exception{
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(9999));

        ssc.configureBlocking(false);
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            if (selector.select(3000)==0){
                System.out.println("waiting....");
                continue;
            }

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                //启动新线程处理SelectionKey
                new Thread(new HttpHandler(key));
                //处理完后移除
                iterator.remove();
            }
        }
    }
}
