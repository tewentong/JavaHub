/*
    AutoCloseable主要是用于日后进行资源开发的处理上，以实现资源的自动关闭（释放资源）
        例如： 在以后进行文件、网络以及数据库开发的过程之中，由于服务器的资源有限，所以使用之后一定要关闭资源，这样才可以被更多的使用者所使用

    下面为了更好的说明资源的问题，将通过一个消息的发送处理来完成：
*/
interface IMessage121201 {    //实现消息的处理机制
    public void send(); //消息发送
}
class NetMessage121201 implements IMessage121201 { //实现消息的处理机制
    private String msg;
    public NetMessage121201(String msg) {
        this.msg = msg;
    }
    public boolean open() {    //获取资源连接
        System.out.println("【OPEN】获取消息发送连接资源。");
        return true;
    }
    @Override
    public void send() {
        if (this.open()) {      //是否打开了连接
            System.out.println("【***发送消息***】" + this.msg);
        }
    }
    public void close() {
        System.out.println("【CLOSE】关闭消息发送通道。");
    }
}
public class AutoCloseableInterfaceDemo {
    public static void main(String [] args) {
        NetMessage121201 nm = new NetMessage121201("www.mldn.cn");  //定义要发送的处理
        nm.send();      //发送消息
        nm.close();     //关闭连接
    }
}