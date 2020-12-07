//使用static 定义内部接口，是因为这些接口是一组相关的操作，有了外部接口之后可以更加明确地描述出这些接口的主要功能
interface IMessageWarp {    //消息包装
    static interface IMessage1128 {
        public String getContent();
    }
    static interface IChannel1128 {
        public boolean connect();   //消息的发送通道是否建立
    }
    public static void send(IMessage1128 msg, IChannel1128 channel) {    //消息发送
        if(channel.connect()) {
            System.out.println(msg.getContent());
        }else {
            System.out.println("消息通道无法建立，消息发送失败");
        }
    }
}
class DefaultMessage implements IMessageWarp.IMessage1128 {
    public String getContent() {
        return "www.mldn.com";
    }
}
class NetChannel implements IMessageWarp.IChannel1128 {
    public boolean connect() {
        return true;
    }
}
public class StaticInnerInterfaceDemo {
    public static void main(String[] args) {
        IMessageWarp.send(new DefaultMessage(), new NetChannel());
    }
}