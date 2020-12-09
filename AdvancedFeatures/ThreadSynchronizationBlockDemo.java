//解决同步问题的关键是锁，指的是当某一个线程执行操作的时候，其他线程外面等待
//在程序之中实现锁的功能，就可以使用synchronized关键字来实现，利用此关键字就可以定义同步方法和同步代码块
//在同步代码块里的代码只允许一个线程操作执行
/*
    1. 利用同步代码块进行处理：
        synchronized(同步对象) {
            同步代码操作；
        }
        //一般要进行同步对象处理的时候可以采用当前对象this进行同步
*/
import java.lang.Runnable;
class MyThread120902 implements Runnable {
    private int ticket = 10;    //总票数为10
    @Override
    public void run() {
        while(true) {
            synchronized(this) {
                if (this.ticket > 0) {
                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票， ticket = " + this.ticket --);
                }else {
                    System.out.println("****票已经买光了****");
                    break;
                } 
            }
        }
    }
}
public class ThreadSynchronizationBlockDemo {
    public static void main(String[] args) {
        MyThread120902 mt = new MyThread120902();
        new Thread(mt, "票贩子A").start();
        new Thread(mt, "票贩子B").start();
        new Thread(mt, "票贩子C").start();
    }
}
//加入同步处理之后，程序的整体的性能下降了。同步实际上会造成性能的降低。