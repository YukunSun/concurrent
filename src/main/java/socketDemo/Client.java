package socketDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Author: sunyukun.china@gmail.com
 * Time: 2016/12/26 23:02
 * Blog: coderdaily.net
 *
 * Socket Demo --Client端模拟
 */
public class Client {
    public static void main(String[] args) {
        try {
            //1.连接
            Socket socket  = new Socket("127.0.0.1",9999);
            //2.读写数据
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //3.发送数据
            writer.println("i am client...");
            writer.flush();
            //4.接收
            String line = reader.readLine();
            System.out.println("from server----"+line);
            //5.关闭资源

            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
