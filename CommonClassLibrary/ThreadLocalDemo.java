/*
    ThreadLocal类：解决核心资源与多线程并发访问的处理情况
        开发中解决资源的引用传递安全问题的解决方案
    范例：一个简单的发送消息的例子
        class Channel { //消息的发送通道
            private static Message030402 message;
            private Channel() {}
            public static void setMessage(Message030402 m) {
                message = m;
            }
            public static void send() { //发送消息
                System.out.println("【消息发送】： " + message.getInfo());
            }
        }
        class Message030402 { //要发送的消息体
            private String info;
            public void setInfo(String info) {
                this.info = info;
            }
            public String getInfo() {
                return info;
            }
        }
        public class ThreadLocalDemo {
            public static void main(String[] args) throws Exception {
                Message030402 msg = new Message030402();    //实例化消息主体
                msg.setInfo("www.mldn.cn"); //设置要发送的内容
                Channel.setMessage(msg);    //设置要发送的消息
                Channel.send(); //发送消息
            }    
        }
        对于当前的程序实际上采用的是一种单线程的模式来进行处理的
        那么如果在多线程的状态下能否实现完全一致的操作效果呢？
        为此，我们将启动三个线程进行处理

    范例：多线程的影响
        class Channel { //消息的发送通道
            private static Message030402 message;
            private Channel() {}
            public static void setMessage(Message030402 m) {
                message = m;
            }
            public static void send() { //发送消息
                System.out.println("【" + Thread.currentThread().getName() + "、消息发送】： " + message.getInfo());
            }
        }
        class Message030402 { //要发送的消息体
            private String info;
            public void setInfo(String info) {
                this.info = info;
            }
            public String getInfo() {
                return info;
            }
        }
        public class ThreadLocalDemo {
            public static void main(String[] args) throws Exception {
            new Thread(() -> {
                Message030402 msg = new Message030402();    //实例化消息主体
                msg.setInfo("第一个线程的消息"); //设置要发送的内容
                Channel.setMessage(msg);    //设置要发送的消息
                Channel.send(); //发送消息
            },"消息发送者A").start();

            new Thread(() -> {
                Message030402 msg = new Message030402();    //实例化消息主体
                msg.setInfo("第二个线程的消息"); //设置要发送的内容
                Channel.setMessage(msg);    //设置要发送的消息
                Channel.send(); //发送消息
            },"消息发送者B").start();

            new Thread(() -> {
                Message030402 msg = new Message030402();    //实例化消息主体
                msg.setInfo("第三个线程的消息"); //设置要发送的内容
                Channel.setMessage(msg);    //设置要发送的消息
                Channel.send(); //发送消息
            },"消息发送者C").start();
            }    
        }
        因为并发执行，消息之间产生了影响：产生了数据的覆盖，因为Message030402是static的
            【消息发送者A、消息发送】： 第二个线程的消息
            【消息发送者C、消息发送】： 第三个线程的消息
            【消息发送者B、消息发送】： 第二个线程的消息
    
    在保持Channel(所有发送的通道)核心结构不改变的情况下，需要考虑到每个线程的独立操作问题
    在这样的情况下，对于Channel类而言，除了要保留有发送的消息之外，还应该多存放每一个线程的标记（当前线程）
    这时，就可以通过ThreadLocal类来存放数据
    在ThreadLocal类里面提供有如下的操作方法：
        1.构造方法：public ThreadLocal();
        2.设置数据：public void set(T value);
        3.取出数据：public T get();
        4.删除数据：public void remove();
    范例：解决线程同步问题
        import java.lang.ThreadLocal;
        class Channel { //消息的发送通道
            private static final ThreadLocal<Message030402> THREADLOCAL = new ThreadLocal<Message030402>();
            private Channel() {}
            public static void setMessage(Message030402 m) {
                THREADLOCAL.set(m); //向ThreadLocal中保存数据 
            }
            public static void send() { //发送消息
                System.out.println("【" + Thread.currentThread().getName() + "、消息发送】： " + THREADLOCAL.get().getInfo());
            }
        }
        class Message030402 { //要发送的消息体
            private String info;
            public void setInfo(String info) {
                this.info = info;
            }
            public String getInfo() {
                return info;
            }
        }
        public class ThreadLocalDemo {
            public static void main(String[] args) throws Exception {
            new Thread(() -> {
                Message030402 msg = new Message030402();    //实例化消息主体
                msg.setInfo("第一个线程的消息"); //设置要发送的内容
                Channel.setMessage(msg);    //设置要发送的消息
                Channel.send(); //发送消息
            },"消息发送者A").start();

            new Thread(() -> {
                Message030402 msg = new Message030402();    //实例化消息主体
                msg.setInfo("第二个线程的消息"); //设置要发送的内容
                Channel.setMessage(msg);    //设置要发送的消息
                Channel.send(); //发送消息
            },"消息发送者B").start();

            new Thread(() -> {
                Message030402 msg = new Message030402();    //实例化消息主体
                msg.setInfo("第三个线程的消息"); //设置要发送的内容
                Channel.setMessage(msg);    //设置要发送的消息
                Channel.send(); //发送消息
            },"消息发送者C").start();
            }    
        }
        每个线程通过ThreadLocal只允许保存一个数据
*/
import java.lang.ThreadLocal;
class Channel { //消息的发送通道
    private static final ThreadLocal<Message030402> THREADLOCAL = new ThreadLocal<Message030402>();
    private Channel() {}
    public static void setMessage(Message030402 m) {
        THREADLOCAL.set(m); //向ThreadLocal中保存数据 
    }
    public static void send() { //发送消息
        System.out.println("【" + Thread.currentThread().getName() + "、消息发送】： " + THREADLOCAL.get().getInfo());
    }
}
class Message030402 { //要发送的消息体
    private String info;
    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }
}
public class ThreadLocalDemo {
    public static void main(String[] args) throws Exception {
       new Thread(() -> {
        Message030402 msg = new Message030402();    //实例化消息主体
        msg.setInfo("第一个线程的消息"); //设置要发送的内容
        Channel.setMessage(msg);    //设置要发送的消息
        Channel.send(); //发送消息
       },"消息发送者A").start();

       new Thread(() -> {
        Message030402 msg = new Message030402();    //实例化消息主体
        msg.setInfo("第二个线程的消息"); //设置要发送的内容
        Channel.setMessage(msg);    //设置要发送的消息
        Channel.send(); //发送消息
       },"消息发送者B").start();

       new Thread(() -> {
        Message030402 msg = new Message030402();    //实例化消息主体
        msg.setInfo("第三个线程的消息"); //设置要发送的内容
        Channel.setMessage(msg);    //设置要发送的消息
        Channel.send(); //发送消息
       },"消息发送者C").start();
    }    
}