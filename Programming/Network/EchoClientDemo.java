
/*
    TCP程序的基本实现：
        TCP的程序开发是网络程序的最基本的开发模型，其核心的特点是使用两个类实现数据的交互处理：
            1.ServerSocket(服务器端)、2.Socket(客户端)
                ServerSocket的主要目的是设置服务器的监听端口，而Socket需要指明要连接的服务器地址与端口
                下面实现一个最简单的数据处理操作，即：Eoch程序实现
        范例：实现服务器端的定义
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

        范例：实现客户端编程
            import java.net.Socket;
            import java.io.BufferedReader;
            import java.io.InputStreamReader;
            import java.util.Scanner;
            import java.io.PrintStream;

            public class EchoClientDemo {
                private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

                public static void main(String[] args) throws Exception {
                    Socket client = new Socket("localhost", 9999); // 定义服务端的连接信息
                    // 现在的客户端需要有输入与输出的操作支持，所以依然要准备出Scanner与PrintStream
                    Scanner scan = new Scanner(client.getInputStream()); // 接收服务器端的输入内容
                    scan.useDelimiter("\n");
                    PrintStream out = new PrintStream(client.getOutputStream()); // 向服务器端发送内容
                    boolean flag = true;
                    while (flag) {
                        String input = getString("请输入要发送的内容： ").trim();
                        out.println(input); // 加换行
                        if (scan.hasNext()) { // 服务器端有回应了
                            System.out.println(scan.next());
                        }
                        if ("byebye".equalsIgnoreCase(input)) {
                            flag = false;
                        }
                    }
                    scan.close();
                    out.close();
                    client.close();
                }

                public static String getString(String prompt) throws Exception {
                    System.out.println(prompt);
                    String str = KEYBOARD_INPUT.readLine();
                    return str;
                }
            }
        
        如果此时需要对程序进行测试，最好的方法是直接使用telnet命令完成
            在服务器端开启的情况下，利用telnet指令输入
                open localhost 9999
*/
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.PrintStream;

public class EchoClientDemo {
    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        Socket client = new Socket("localhost", 9999); // 定义服务端的连接信息
        // 现在的客户端需要有输入与输出的操作支持，所以依然要准备出Scanner与PrintStream
        Scanner scan = new Scanner(client.getInputStream()); // 接收服务器端的输入内容
        scan.useDelimiter("\n");
        PrintStream out = new PrintStream(client.getOutputStream()); // 向服务器端发送内容
        boolean flag = true;
        while (flag) {
            String input = getString("请输入要发送的内容： ").trim();
            out.println(input); // 加换行
            if (scan.hasNext()) { // 服务器端有回应了
                System.out.println(scan.next());
            }
            if ("byebye".equalsIgnoreCase(input)) {
                flag = false;
            }
        }
        scan.close();
        out.close();
        client.close();
    }

    public static String getString(String prompt) throws Exception {
        System.out.println(prompt);
        String str = KEYBOARD_INPUT.readLine();
        return str;
    }
}