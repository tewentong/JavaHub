//在多线程的开发过程中最为著名的案例：生产者与消费者操作
/*
    生产者负责信息内容的生产
    每当生产者生产完成一项完整的信息之后消费者要从这里面取走信息
    如果生产者没有生产好，消费者要等待生产者生产完成；如果消费者没有消费信息，则生产者要等待消费者处理完成后再继续生产

*/

//可以将生产者和消费者定义为两个独立的线程类对象，但是对于现在生产的数据，可以使用如下的组装
/*
    数据一： title = 王建、content = 大帅哥
    数据二： title = 小高、content = 小朋友
*/
//既然生产者和消费者是两个独立的线程，那么这两个独立的线程之间就需要有一个数据的保存集中点
//那么可以定义Message类来实现数据的保存

import java.lang.Runnable;
class Producer121001 implements Runnable {
    private Message msg;
    public Producer121001(Message msg) {
        this.msg = msg;
    }
    @Override 
    public void run() {
        for (int x = 0; x < 100; x ++) {
            if (x % 2 == 0) {
                this.msg.setTitle("wang jian");
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.msg.setContent("handsome boy");
            }else {
                this.msg.setTitle("小高");
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.msg.setContent("小朋友");
            }
        }
    }
}
class Consumer121001 implements Runnable {
    private Message msg;
    public Consumer121001(Message msg) {
        this.msg = msg;
    }
    @Override
    public void run() {
        for (int x = 0; x < 100; x ++) {
            try {
                Thread.sleep(100);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.msg.getTitle() + " - " + this.msg.getContent());
        }
    }
}
class Message {
    private String title;
    private String content;
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTitle() {
        return this.title;
    }
    public String getContent() {
        return this.content;
    }
}


public class ProducerAndConsumerDemo {
    public static void main(String [] args) {
        Message msg = new Message();
        new Thread(new Producer121001(msg)).start();    //启动生产者线程
        new Thread(new Consumer121001(msg)).start();    //启动消费者线程
    }
}
/*
    通过整个代码的执行，此时你会发现两个问题：
        问题一： 数据不同步（数据错乱）
        问题二： 按道理应该是生产一个取走一个，但是发现有了重复生产和重复取出的问题
*/