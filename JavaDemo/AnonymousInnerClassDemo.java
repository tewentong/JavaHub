//匿名内部类是一种简化的内部类的处理形式，其主要是在抽象类和接口的子类上使用
//匿名内部类对象仅仅使用一次,没必要定义为单独的类
interface IMessage {
    public void send(String str);
}

/*
class MessageImpl implements IMessage {
    public void send(String str) {
        System.out.println(str);
    }
}
*/

public class AnonymousInnerClassDemo {
    public static void main(String[] args) {
        IMessage msg = new IMessage() { //匿名内部类
            public void send(String str) {
                System.out.println(str);
            }
        };
        msg.send("www.mldn.cn~~~~");
    }    
}