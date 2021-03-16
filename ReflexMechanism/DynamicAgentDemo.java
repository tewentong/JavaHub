
/*
    动态代理设计模式
        通过静态代理设计模式的缺陷可以发现，最好的做法是为所有功能一致的业务操作接口提供有统一的代理处理操作
            而这就可以通过动态代理机制来实现，但是在动态代理机制里面需要考虑到如下几点问题：
            1.不管是动态代理类还是静态代理类都一定要接收真实业务实现子类对象
            2.由于动态代理类不再与某一个具体的接口进行捆绑，所以应该可以动态获取类的接口信息
            
            在动态代理实现的操作之中，首先需要关注的就是一个InvocationHandler接口，这个接口规定了代理方法的执行
                interface InvocatationHandler {
                    **
                     * 代理方法调用，代理主题类里面执行的方法最终都是此方法
                     * 
                     * @param proxy  要代理的对象
                     * @param method 要执行的接口方法名称
                     * @param args   传递的参数
                     * @return 某一个方法的返回值
                     * @throws Throwable 方法调用时出现的错误继续向上抛出
                     *
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
                }
            在进行动态代理设计的时候对于动态对象的创建是由JVM底层完成的，此时主要依靠的是java.lang.reflect.Proxy程序类
                而这个程序类之中只提供有一个核心方法——代理对象：
                    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interface, InvocationHandler h);
                        ClassLoader loader：获取当前真实主题类的ClassLoader
                        Class<?>[] interface：代理是围绕接口进行的，所以一定要获取真实主题类的接口信息
                        InvocationHandler h：代理处理的方法
        
        范例：实现动态代理机制
           
        如果你认真观察系统中提供的Proxy.newProxyInstance()方法你会发现该方法会使用大量的JVM底层机制来进行代理对象的动态创建
        所有的代理类是复合所有相关功能需求的操作功能类，它不再代表具体的接口，这样在处理的时候就必须依赖于类加载器与接口进行代理对象的伪造
*/
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface IMessage031502 {
    public void send();
}

class MessageReal031502 implements IMessage031502 {
    @Override
    public void send() {
        System.out.println("【发送消息】www.mldn.cn");
    }
}

class MLDNProxy implements InvocationHandler {
    private Object target; // 保存真实业务对象

    /**
     * 进行真实业务对象与代理业务对象之间的绑定处理
     * 
     * @param target 真实业务对象
     * @return Proxy生成的代理业务对象
     */
    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public boolean connect() {
        System.out.println("【消息代理】进行消息发送通道的连接");
        return true;
    }

    public void close() {
        System.out.println("【消息代理】关闭消息通道");
    }

    @Override
    public Object invoke(Object pro, Method method, Object[] args) throws Throwable {
        System.out.println("******【执行方法】" + method);
        Object returnData = null;
        if (this.connect()) {
            returnData = method.invoke(this.target, args);
            this.close();
        }
        return returnData;
    }
}

public class DynamicAgentDemo {
    public static void main(String[] args) throws Exception {
        IMessage031502 msg = (IMessage031502) new MLDNProxy().bind(new MessageReal031502());
        msg.send();
    }
}