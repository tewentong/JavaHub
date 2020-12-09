/*
    死锁： 死锁是在进行多线程同步的处理之中有可能产生的一种问题
        所谓的死锁是指若干个线程彼此互相等待的状态
    
    若干个线程访问同一资源时一定要进行同步处理，而过多的同步一定会造成死锁
    死锁是同步引起的
*/
import java.lang.Runnable;
class Jian {
    public synchronized void say(Qiang xq) {
        System.out.println("jian说： 路是我的，要过给钱");
        xq.get();
    }
    public synchronized void get() {
        System.out.println("jian说： 拿到钱了，可以买饭吃，你过吧");
    }
}
class Qiang {
    public synchronized void say(Jian jj) {
        System.out.println("qiang说： 让我先跑，我再给你钱");
        jj.get();
    }
    public synchronized void get() {
        System.out.println("qiang说： 逃过一劫，可以继续送快餐了");
    }
}

public class ThreadDeadlockDemo implements Runnable{
    private Jian jj = new Jian();
    private Qiang xq = new Qiang();
    @Override
    public void run() {
        jj.say(xq);
    }
    public ThreadDeadlockDemo() {
        new Thread(this).start();
        xq.say(jj);
    }
    public static void main(String [] args) {
        new ThreadDeadlockDemo();
    }
}
//现在死锁造成的主要原因是： 彼此都在互相等待着，等待着对方先让出资源
//现在只要删除任何一方get()方法的synchronized关键字，即可消除死锁

//死锁是开发中出现的一种不确定的状态，有的时候如果代码处理不当则会不定期出现死锁，这是属于正常开发中的调试问题