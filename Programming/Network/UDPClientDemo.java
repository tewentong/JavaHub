
/*
    UDP客户端，实现不可靠的连接传输
*/
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