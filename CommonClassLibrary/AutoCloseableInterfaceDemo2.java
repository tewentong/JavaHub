/*
    此时有位设计师说了： 既然所有的资源处理完成之后都要进行关闭操作，那么能否实现一种自动关闭的功能呢？
    在此要求下，推出了AutoCloseable的访问接口，此接口是在JDK1.7的时候提供的，并且此接口只提供有一个方法：
        关闭方法： public void close() throws Exception;

    要想实现自动关闭处理，除了要使用AutoCloseable之外，还需要结合 异常处理语句 可以正常调用

    在以后的章节之中会接触到资源的自动关闭，往往都会iu使用AutoCloseable接口
*/
interface IMessage121202 extends AutoCloseable {    //实现消息的处理机制
    public void send(); //消息发送
}
class NetMessage121202 implements IMessage121202 { //实现消息的处理机制
    private String msg;
    public NetMessage121202(String msg) {
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
    public void close() throws Exception {
        System.out.println("【CLOSE】关闭消息发送通道。");
    }
}
public class AutoCloseableInterfaceDemo2 {
    public static void main(String [] args) throws Exception {
        /*  
            现在的操作并未完成自动关闭：
            NetMessage121202 nm = new NetMessage121202("www.mldn.cn");  //定义要发送的处理
            nm.send();      //发送消息
        */
        
        /*
            //结合异常处理完成自动关闭操作
            try (IMessage121202 msg = new NetMessage121202("www.mldn.cn");  ){
                msg.send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        */
        //结合异常处理完成自动关闭操作
        try (IMessage121202 msg = new NetMessage121202("www.mldn.cn");  ){
            msg.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}