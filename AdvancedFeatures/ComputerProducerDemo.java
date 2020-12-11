//多线程案例分析二
/*
    设计一个生产电脑和搬运电脑类，要求生产出一台电脑就搬走一台电脑
    如果没有电脑生产出来，则搬运工就要等待新的电脑生产出来
    如果生产出的电脑没有搬走，则要等到电脑搬走以后再生产，并统计出生产的电脑数量

    本程序之中就是标准的生产者和消费者的模型
*/
import java.lang.Runnable;
class Producer121101 implements Runnable{
    private Resource121101 resource;
    public Producer121101(Resource121101 resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        for(int x = 0; x < 50; x ++) {
            try {
                this.resource.make();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
class Consumer121101 implements Runnable {
    private Resource121101 resource;
    public Consumer121101(Resource121101 resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        for(int x = 0; x < 50; x ++) {
            try {
                this.resource.get();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
class Computer {
    private static int count = 0;   //表示生产的个数
    private String name;
    private double price;
    public Computer(String name, double price) {
        this.name = name;
        this.price = price;
        count ++;
    }
    public String toString() {
        return "【第" + count + "台电脑】" + "电脑名字" + this.name + "、价值" + this.price;
    }
} 
class Resource121101 {
    private Computer computer;
    public synchronized void make() throws Exception {
        if (this.computer != null) {    //已经生产过了
            super.wait();
        }
        try {
            Thread.sleep(100);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.computer = new Computer("MLDN", 1.1);
        System.out.println("生产电脑" + this.computer); //生产电脑
        super.notifyAll();
    }
    public synchronized void get() throws Exception {
        if (this.computer == null) {    //没有生产过计算机
            super.wait();
        }
        try {
            Thread.sleep(10);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("取走电脑" + this.computer);  //取走电脑
        this.computer = null;   //已经取走了
        super.notifyAll();
    }
}
public class ComputerProducerDemo {
    public static void main(String [] args) {
        Resource121101 res = new Resource121101();
        new Thread(new Producer121101(res)).start();
        new Thread(new Consumer121101(res)).start();
    }
}