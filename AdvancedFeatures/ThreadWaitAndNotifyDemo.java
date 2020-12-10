//线程等待与唤醒
/*
    如果说现在想要解决生产者与消费者的问题，最好的解决方案就是使用等待与唤醒机制
        此机制主要依靠Object类中提供的方法进行处理
            等待机制：
                死等： public final void wait() throws InterruptedException;
                设置等待时间： public final void wait(long timeout) throws InterruptedException;
                设置等待时间： public final void wait(long timeout, int nanos) throws InterruptedException;
            唤醒第一个等待线程： public final void notify();
            唤醒全部等待线程： public final void notifyAll();

    如果此时有若干个等待线程的话，那么notify()表示的是唤醒第一个等待的，而其他的线程继续等待
    notifyAll()表示会唤醒所有等待的线程，哪个线程的优先级别高就有可能先执行
*/

//对于当前问题的解决应该通过Message类完成处理
import java.lang.Runnable;
class Message121003 {
    private String title;
    private String content;
    private boolean flag;   //表示生产或消费的形式
    //flag == true  允许生产，不允许消费
    //flag == false 允许消费，不允许生产
    public synchronized void set(String title, String content) {
        if(this.flag == false) {    //无法进行生产，应该等待被消费
            try {
                super.wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.title = title;
        try {
            Thread.sleep(100);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.content = content;
        this.flag = false;  //生产完毕，可以消费了
        super.notify(); //唤醒等待的线程
    }
    public synchronized String get() {
        if(this.flag == true) { //尚未生产，需要等待
            try {
                super.wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(100);
        }catch (InterruptedException e) {
            e.printStackTrace();
        } 
        
        try{
            return this.title + "   ---   " + this.content;
        }finally {  //不管如何，都要执行
            this.flag = true;   //消费结束，你们继续生产
            super.notify();     //唤醒等待线程
        }
    }
}
class Producer121003 implements Runnable {
    private Message121003 msg;
    public Producer121003(Message121003 msg) {
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
class Consumer121003 implements Runnable {
    private Message121003 msg;
    public Consumer121003(Message121003 msg) {
        this.msg = msg;
    }
    @Override
    public void run() {
        for(int x = 0 ; x < 100; x ++) {
            System.out.println(this.msg.get());
        }
    }
}
public class ThreadWaitAndNotifyDemo {
    public static void main(String [] args) {
        Message121003 msg = new Message121003();
        new Thread(new Producer121003(msg)).start();
        new Thread(new Consumer121003(msg)).start();
    }
}

//这种处理形式就是在进行多线程开发过程之中最原始的处理方案，整个的等待、同步、唤醒机制都由开发者自行通过原生代码实现控制