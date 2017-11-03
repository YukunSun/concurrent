package socketDemo;


import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Author: sunyukun.china@gmail.com
 * Time: 2016/12/27 23:50
 * Blog: coderdaily.net
 *
 * NioSocket Demo --Server端模拟
 */
public class NioServer {
    public static void main(String[] args) throws Exception {
        //1.创建channel，监听端口
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(9999));
        //2.设置成非阻塞
        server.configureBlocking(false);
        //3.注册选择器
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        //4.创建处理器
        NioHandler handler = new NioHandler(1024);
        while (true) {
            //5.等待请求，每次等待3s,超过3s后继续向下运行；
            //如果传入0或者不传参数，将一直阻塞；
            if (selector.select(3000) == 0) {
                System.out.println("请求超时........");
                continue;
            }
            System.out.println("处理ing........");
            //selector调用selectedKeys方法返回SelectionKey的集合，SelectionKey保存了当前请求的Channel和Selector
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                try {
                    //5.1 接收到请求时...
                    if (key.isAcceptable()) {
                        handler.handleAccept(key);
                    }
                    //5.2 读数据
                    if (key.isReadable()) {
                        handler.handleRead(key);
                    }
                } catch (Exception e) {
                    keyIterator.remove();
                    continue;
                }
                //6.处理完后，从待处理的SelectionKey中移除所有key
                keyIterator.remove();
            }


        }


    }

}