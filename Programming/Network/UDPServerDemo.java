
/*
    数据包发送与接收
        之前所见到的都属于TCP程序开发范畴，TCP程序最的特点是可靠的网络连接
        网络程序开发之中还存在UDP程序，基于数据报的网络编程实现
        如果想要实现UDP程序的话需要两个类：
            DatagramPacket(数据内容)、DatagramSocket(网络发送与接收)
        数据报就好比发送的短消息一样，客户端是否接收到与发送者无关
    范例：实现UDP客户端
        import java.net.DatagramSocket;
        import java.net.DatagramPacket;
        public class UDPClientDemo {
            public static void main(String[] args) throws Exception { // 接收数据信息
                DatagramSocket client = new DatagramSocket(9999); // 连接到9999端口
                byte data[] = new byte[1024]; // 接收消息
                DatagramPacket packet = new DatagramPacket(data, data.length); // 接收数据的对象
                System.out.println("客户端等待接收发送的消息........");
                client.receive(packet); // 接收消息，所有的消息都在data的字节数组之中
                System.out.println("接收到的消息内容为： " + new String(data, 0, packet.getLength()));
                client.close();
            }
        }
    范例：实现UDP服务端
        import java.net.DatagramSocket;
        import java.net.DatagramPacket;
        import java.net.InetAddress;
        public class UDPServerDemo {
            public static void main(String[] args) throws Exception {
                DatagramSocket server = new DatagramSocket(9000); // 9000是监听端口
                String str = "www.mldn.cn"; // 要发送的消息的内容
                DatagramPacket packet = new DatagramPacket(str.getBytes(), 0, str.length(), InetAddress.getByName("localhost"),
                        9999); // 发送数据，发送到9999端口上
                server.send(packet); // 发送消息
                System.out.println("消息发送完毕........");
                server.close();
            }
        }
*/
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class UDPServerDemo {
    public static void main(String[] args) throws Exception {
        DatagramSocket server = new DatagramSocket(9000); // 9000是监听端口
        String str = "www.mldn.cn"; // 要发送的消息的内容
        DatagramPacket packet = new DatagramPacket(str.getBytes(), 0, str.length(), InetAddress.getByName("localhost"),
                9999); // 发送数据，发送到9999端口上
        server.send(packet); // 发送消息
        System.out.println("消息发送完毕........");
        server.close();
    }
}