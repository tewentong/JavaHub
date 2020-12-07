interface IChannel {    //定义接口
    public void send(IMessage msg); //发送消息
    interface IMessage {    //内部接口
        public String getContent(); //获取消息内容
    }
}
class ChannelImpl implements IChannel {
    public void send(IMessage msg){
        System.out.println("发送消息： " + msg.getContent());
        System.out.println("哈哈哈哈，老子终于发送成功了！");
    }
    class MessageImpl implements IMessage {
        public String getContent(){
            return "www.mldn.com";
        }
    }
}
public class InnerInterfaceDemo {
    public static void main(String[] args) {
        IChannel channel = new ChannelImpl();
        channel.send(((ChannelImpl)channel).new MessageImpl());
    }
}