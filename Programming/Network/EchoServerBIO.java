
/*
    多线程与网络编程：
        尽管现在已经实现了一个标准的网络程序开发，但是在整个开发过程之中本程序存在有严重的性能缺陷，
            因为该服务器只能为一个线程提供Echo服务
            如果说现在的服务器需要有多人进行连接访问的时候那么其他的使用者将无法连接（等待连接）
        所以现在就可以发现单线程的服务器开发本身就是一种不合理的做法，那么此时最好的解决方案：
            将每一个连接到服务器上的客户端都通过一个线程对象来进行处理，即：
            服务器上启动多个线程，每一个线程单独为每一个客户端实现Echo支持
            
    范例：BIO模型
        import java.io.IOException;
        import java.io.PrintStream;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.Scanner;

        public class EchoServerBIO {
            private static class ClientThread implements Runnable {
                private Socket client = null; // 描述每一个不同的客户端
                private Scanner scan = null;
                private PrintStream out = null;
                private boolean flag = true; // 循环标记

                public ClientThread(Socket client) throws Exception {
                    this.client = client;
                    this.scan = new Scanner(client.getInputStream()); // 客户端输入流
                    this.scan.useDelimiter("\n"); // 设置分割符
                    this.out = new PrintStream(client.getOutputStream()); // 客户端输出流
                }

                @Override
                public void run() {
                    while (this.flag) {
                        if (scan.hasNext()) { // 现在有数据发送
                            String val = scan.next().trim(); // 接收发送的数据
                            if ("byebye".equalsIgnoreCase(val)) {
                                out.println("ByeByeBye......");
                                this.flag = false;
                            } else {
                                out.println("【ECHO】" + val);
                                out.flush(); // 强制刷新缓冲区
                            }
                        }
                    }
                    try {
                        scan.close();
                        out.close();
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            public static void main(String[] args) throws Exception {
                ServerSocket server = new ServerSocket(9999); // 设置服务器端的监听端口
                System.out.println("等待客户端连接...............");
                boolean flag = true; // 循环标记
                while (flag) {
                    Socket client = server.accept(); // 有客户端连接
                    new Thread(new ClientThread(client)).start();
                }
                server.close();
            }
        }       
        
        如果你在这类的代码里面再追加一些集合的数据控制，实际上就可以实现一个80年代的聊天室了
*/
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServerBIO {
    private static class ClientThread implements Runnable {
        private Socket client = null; // 描述每一个不同的客户端
        private Scanner scan = null;
        private PrintStream out = null;
        private boolean flag = true; // 循环标记

        public ClientThread(Socket client) throws Exception {
            this.client = client;
            this.scan = new Scanner(client.getInputStream()); // 客户端输入流
            this.scan.useDelimiter("\n"); // 设置分割符
            this.out = new PrintStream(client.getOutputStream()); // 客户端输出流
        }

        @Override
        public void run() {
            while (this.flag) {
                if (scan.hasNext()) { // 现在有数据发送
                    String val = scan.next().trim(); // 接收发送的数据
                    if ("byebye".equalsIgnoreCase(val)) {
                        out.println("ByeByeBye......");
                        this.flag = false;
                    } else {
                        out.println("【ECHO】" + val);
                        out.flush(); // 强制刷新缓冲区
                    }
                }
            }
            try {
                scan.close();
                out.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9999); // 设置服务器端的监听端口
        System.out.println("等待客户端连接...............");
        boolean flag = true; // 循环标记
        while (flag) {
            Socket client = server.accept(); // 有客户端连接
            new Thread(new ClientThread(client)).start();
        }
        server.close();
    }
}