//生产者与消费者中解决数据同步问题
/*
    如果想要解决数据同步，最简单的做法是使用sychronized关键字定义同步代码块或同步方法
    于是这个时候对同步的处理直接就可以在Message类中完成

 */
import java.lang.Runnable;
class Message121002 {
    private String title;
    private String content;
    public synchronized void set(String title , String content) {
        this.title = title ;
        try {
            Thread.sleep(100);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.content = content;
    }
    public synchronized String get() {
        try {
            Thread.sleep(100);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.title + "  --- " + this.content;
    }
}
class Producer121002 implements Runnable {
    private Message121002 msg;
    public Producer121002(Message121002 msg) {
        this.msg = msg;
    }
    @Override
    public void run() {
        for (int x = 0; x < 100; x ++) {
            if (x % 2 == 0) {
                this.msg.set("wang jian", "hand someboy");
            }else {
                this.msg.set("小高", "小朋友");
            }
        }
    }
}
class Consumer121002 implements Runnable {
    private Message121002 msg;
    public Consumer121002(Message121002 msg) {
        this.msg = msg;
    }
    @Override
    public void run() {
        for(int x = 0 ; x < 100; x ++) {
            System.out.println(this.msg.get());
        }
    }
}

public class DataSynchronizationDemo {
    public static void main(String [] args) {
        /*
            在进行同步处理的时候肯定需要同步的处理对象，那么此时肯定要将同步操作交由Message类处理是最合适的
            我们发现目前数据可以正常保持一致了，但是重复操作问题依然存在
        */
        Message121002 msg = new Message121002();
        new Thread(new Producer121002(msg)).start();
        new Thread(new Consumer121002(msg)).start();
    }
}