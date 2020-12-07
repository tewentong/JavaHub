interface IChannel2 {//定义接口
    public void send(); //发送消息
    abstract class AbstractMessage {
        public abstract String getContent();
    }
}

class ChannelImpl2 implements IChannel2 {
    public void send() {
        AbstractMessage msg = new MessageImple();
        System.out.println(msg.getContent());
        System.out.println("哈哈哈哈和，我又写出来了！");
    }
    class MessageImple extends AbstractMessage{
        public String getContent() {
            return "www.mldn.cn";
        }
    }
}

public class InnerAbstractClassDemo {
    public static void main(String[] args) {
        IChannel2 channel = new ChannelImpl2();
        channel.send();
    }
}