package socketDemo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Author: sunyukun.china@gmail.com
 * Time: 2016/12/28 23:32
 * Blog: coderdaily.net
 */
public class HttpHandler implements Runnable {
    private int bufferSize = 1024;
    private String localCharset = "UTF-8";
    private SelectionKey key;

    private String rn = "\r\n";

    public HttpHandler(SelectionKey key) {
        this.key = key;
    }

    public void run() {
        try {
            //接收到连接请求时
            if (key.isAcceptable()) {
                System.out.println("dddddddddd");
                handleAccept();
            }
            //读数据
            if (key.isReadable()) {
                handleRead();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleRead() throws IOException {
        SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();
        channel.configureBlocking(false);
        channel.register(key.selector(),SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
    }

    private void handleAccept() throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        //获取buffer并重置
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        if (sc.read(buffer)==-1){
            sc.close();
        }else {
            //接收请求数据
            buffer.flip();
            String receive = Charset.forName(localCharset).newDecoder().decode(buffer).toString();

            //1.打印请求报文头
            String[] requestMsg = receive.split("\r\n");
            for (String s :  requestMsg) {
                //若遇到空行说明打印完毕
                if (null == s || "".equals(s)) {
                    break;
                }
            }

            //2.打印首行
            String[] firstLine = requestMsg[0].split(" ");
            System.out.println();
            System.out.println("Method:\t"+firstLine[0]);
            System.out.println("url:\t"+firstLine[1]);
            System.out.println("HTTP version:\t"+firstLine[2]);

            //3.返回客户端
            StringBuilder sendStr = new StringBuilder();
            sendStr.append("HTTP/1.1 200 OK"+rn);
            sendStr.append("Content-Type:text/html;charset="+localCharset+rn);
            sendStr.append(rn);//最后一行加上空行

            sendStr.append("<html><head><title>hello world</title></head><body>");
            sendStr.append("the msg is-------<br/>");
            for (String s :
                    requestMsg) {
                sendStr.append(s + "<br/>");
            }
            sendStr.append("</body></html>");
            buffer = ByteBuffer.wrap(sendStr.toString().getBytes(localCharset));

            sc.write(buffer);
            System.out.println(buffer);
            sc.close();
        }
    }
}
