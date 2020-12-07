//内部类还有一些更有意思的结构
//如果现在定义了一个接口，那么可以JDK1.8在内部类追加static方法，即不受到实例化对象的控制
//接口内部进行接口实现
interface IChannel3 {//定义接口
    public void send(); //发送消息
    class ChannelImpl3 implements IChannel3 {
        public void send() {
            System.out.println("www.mldn.cn");
        }
    }
    public static IChannel3 getInstance() {
        return new ChannelImpl3();
    }
}
public class InnerAbstractClassDemo2 {
    public static void main(String[] args) {
        IChannel3 channel = IChannel3.getInstance();
        channel.send();
    }
}