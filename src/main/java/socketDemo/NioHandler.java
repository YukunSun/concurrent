package socketDemo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Author: sunyukun.china@gmail.com
 * Time: 2016/12/28 0:11
 * Blog: coderdaily.net
 */
public class NioHandler {
    private int bufferSize = 1024;
    private String localCharset = "UTF-8";

    public NioHandler() {
    }

    public NioHandler(int bufferSize) {
        this(bufferSize, null);
    }

    public NioHandler(String localCharset) {
        this(-1, localCharset);
    }

    public NioHandler(int bufferSize, String localCharset) {
        if (bufferSize > 0)
            this.bufferSize = bufferSize;
        if (localCharset != null)
            this.localCharset = localCharset;
    }

    protected void handleAccept(SelectionKey key) throws IOException {
        SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
    }

    protected void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        //获取buffer并重置
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        //没有读到内容则关闭
        if (channel.read(buffer) == -1) {
            channel.close();
        } else {
            //将buffer转换为读状态
            buffer.flip();
            //将接收到的值，编码
            String received = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
            System.out.println("from client-----" + received);

            String send = "i am server";
            buffer = ByteBuffer.wrap(send.getBytes(localCharset));

            channel.write(buffer);
            channel.close();
        }
    }
}
