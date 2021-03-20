
/*
    Echo服务器端编程
*/
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintStream;

public class EchoServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9999); // 设置服务器端的监听端口
        System.out.println("等待客户端连接...............");
        Socket client = server.accept(); // 有客户端连接
        // 1.首先需要先接收客户端发送来的信息,而后才可以将信息处理之后发送回客户端
        Scanner scan = new Scanner(client.getInputStream()); // 客户端输入流
        scan.useDelimiter("\n"); // 设置分割符
        PrintStream out = new PrintStream(client.getOutputStream()); // 客户端输出流
        boolean flag = true; // 设置循环标记
        while (flag) {
            if (scan.hasNext()) { // 现在有数据发送
                String val = scan.next().trim(); // 接收发送的数据
                if ("byebye".equalsIgnoreCase(val)) {
                    out.println("ByeByeBye......");
                    flag = false;
                } else {
                    out.println("【ECHO】" + val);
                    out.flush(); // 强制刷新缓冲区
                }
            }
        }
        scan.close();
        out.close();
        client.close();
        server.close();
    }
}