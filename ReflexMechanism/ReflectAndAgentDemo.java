/*  
    Java反射编程
    反射与代理设计模式
        代理设计模式是在程序开发之中使用最多的设计模式，代理设计模式的核心是：有真实业务实现类，与代理业务实现类
            并且代理类要完成比真实业务更多的处理操作
        传统代理设计模式的弊端：
            所有的代理设计模式如果按照设计要求来讲，必须是基于接口的设计，也就是说需要首先定义出核心接口的组成
            下面模拟一个消息发送的代理操作结构
            范例：传统代理设计
                interface IMessage0315 { // 传统代理设计必须有接口
                    public void send(); // 业务方法
                }
                class MessageReal implements IMessage0315 {
                    @Override
                    public void send() {
                        System.out.println("【发送消息】www.mldn.cn");
                    }
                }
                class MessageProxy implements IMessage0315 { // 代理类
                    private IMessage0315 message; // 代理对象，一定是业务接口实例
                    public MessageProxy(IMessage0315 message) {
                        this.message = message;
                    }
                    public boolean connect() {
                        System.out.println("【消息代理】进行消息发送通道的连接");
                        return true;
                    }
                    public void close() {
                        System.out.println("【消息代理】关闭消息通道");
                    }
                    @Override
                    public void send() {
                        if (this.connect()) {
                            this.message.send(); // 消息的发送处理
                            this.close();
                        }
                    }
                }
                public class ReflectAndAgentDemo {
                    public static void main(String[] args) throws Exception {
                        IMessage0315 msg = new MessageProxy(new MessageReal());
                        msg.send();
                    }
                }
                以上的操作代码是一个最为标准的代理设计，但是如果要进一步的去思考会发现，
                    客户端的接口与具体的子类产生耦合问题
                    所以这样的操作如果从实际的开发来讲最好再引入工厂设计模式进行代理对象的获取
                
                以上的代理设计模式为静态代理设计，这种静态代理设计的特点在于：
                    一个代理类只为一个接口服务
                    那么说如果现在准备出了3000个业务接口，那么按照此种做法就意味着需要编写3000个代理类，
                    并且这3000个代理类的操作形式类似
                    现在需要解决的问题在于：如何可以让一个代理类满足于所有的业务接口操作要求
                    
        
*/
interface IMessage0315 { // 传统代理设计必须有接口
    public void send(); // 业务方法
}

class MessageReal implements IMessage0315 {
    @Override
    public void send() {
        System.out.println("【发送消息】www.mldn.cn");
    }
}

class MessageProxy implements IMessage0315 { // 代理类
    private IMessage0315 message; // 代理对象，一定是业务接口实例

    public MessageProxy(IMessage0315 message) {
        this.message = message;
    }

    public boolean connect() {
        System.out.println("【消息代理】进行消息发送通道的连接");
        return true;
    }

    public void close() {
        System.out.println("【消息代理】关闭消息通道");
    }

    @Override
    public void send() {
        if (this.connect()) {
            this.message.send(); // 消息的发送处理
            this.close();
        }
    }
}

public class ReflectAndAgentDemo {
    public static void main(String[] args) throws Exception {
        IMessage0315 msg = new MessageProxy(new MessageReal());
        msg.send();
    }
}