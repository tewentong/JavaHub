//设计四个线程对象，两个线程执行减操作，两个线程执行加操作
import java.lang.Runnable;
class AddThread implements Runnable {
    private Resource resource;
    public AddThread(Resource resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        for(int x = 0; x < 50; x++) {
            try {
                this.resource.add();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
class SubThread implements Runnable {
    private Resource resource;
    public SubThread(Resource resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        for (int x = 0; x < 50; x++) {
            try {
                this.resource.sub();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
class Resource {    //定义一个操作的资源
    private int num = 0;    //这是要进行加减操作的数据
    private boolean flag = true;    //加减的切换
    //flag == true  表示可以进行加法操作、但是无法进行减法操作
    //flag == false 表示可以进行减法操作、但是无法进行加法操作
    public synchronized void add() throws Exception {    //执行加法操作
        if (this.flag == false) {    //现在需要执行的是减法操作、加法要等待
            super.wait();
        }
        try {
            Thread.sleep(100);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.num ++;
        System.out.println("【加法操作 - " + Thread.currentThread().getName() + "】num = " + this.num);
        this.flag = false;  //加法操作执行完毕，可以进行减法运算了
        super.notifyAll();  //唤醒全部等待线程
    }
    public synchronized void sub() throws Exception {    //执行减法操作
        if (this.flag == true) {    //现在需要执行的是加法操作，减法等待
            super.wait();
        }
        try {
            Thread.sleep(200);
        }catch (InterruptedException e) {
            e.printStackTrace();
        } 
        this.num --;
        System.out.println("【减法操作 - " + Thread.currentThread().getName() + "】num = " + this.num);
        this.flag = true;   //减法操作执行完毕，可以进行加法运算了
        super.notifyAll();  //唤醒全部等待线程
    }
}
public class ThreadAdditionAndSubtractionDemo {
    public static void main(String [] args) {
        Resource res = new Resource();
        AddThread at = new AddThread(res);
        SubThread st = new SubThread(res);
        new Thread(at, "加法线程 - A").start();
        new Thread(st, "减法线程 - X").start();
        new Thread(at, "加法线程 - B").start();
        new Thread(st, "减法线程 - Y").start();
    }
}

//这一个题目是一个经典的多线程的开发操作，这个程序里面一定要考虑的核心本质是：
//加一个、减一个，整体的计算结果应该在：0、-1、1之间循环出现