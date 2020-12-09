/*
    线程的同步与死锁
        在多线程的处理之中，可以利用Runnable描述多个线程操作的资源，而Thread描述每一个线程对象，于是当多个线程访问同一资源的时候
        如果处理不当，就会产生数据的错误操作

*/

//同步问题的引出，将创建若干个线程对象实现卖票的处理操作

import java.lang.Runnable;
class MyThread1209 implements Runnable {
    private int ticket = 10; //总票数为10
    public void run() {
        while (true) {
            if (this.ticket > 0) {
                try {
                    Thread.sleep(100);  //模拟网络延迟
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "卖票， ticket = " + this.ticket --);
            }else {
                System.out.println("****票已买光****");
                break;
            }
        }
    }
}
public class ThreadSynchronizationAndDeadlockDemo {
    public static void main(String[] args) {
        //此时的程序将创建三个线程对象，并且这三个线程对象将进行10张票的出售
        MyThread1209 mt = new MyThread1209();
        new Thread(mt, "票贩子A").start();
        new Thread(mt, "票贩子B").start();
        new Thread(mt, "票贩子C").start();
        //此时的程序进行卖票处理的时候并未出现任何问题，其实这是假象
        //模拟卖票中的延迟操作
        //追加了延迟操作以后，出现了票数为负数的情况，而实际上这个问题一直都存在
        //票贩子B卖票， ticket = -1
        //本例最大的问题就是线程数据不同步
    }
}