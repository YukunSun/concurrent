package socketDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author: sunyukun.china@gmail.com
 * Time: 2016/12/26 23:02
 * Blog: coderdaily.net
 *
 * Socket Demo --Server端模拟
 */
public class Server {
    public static void main(String[] args) {
        try {
            //1.监听8080
            ServerSocket serverSocket = new ServerSocket(9999);
            //2.等待请求
            Socket socket = serverSocket.accept();
            //3.接收后使用socket进行通信，创建BufferedReader用于读数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = reader.readLine();
            System.out.println("client ---------" + line);
            //4.创建PrintWriter用于发送数据
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("i am server");
            writer.flush();
            //5.关闭资源
            writer.close();
            reader.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
