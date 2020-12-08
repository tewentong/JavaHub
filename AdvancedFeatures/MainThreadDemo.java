import java.lang.Runnable;
class MyThread120804 implements Runnable{
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
public class MainThreadDemo {
    public static void main(String[] args) {
        MyThread120804 mt = new MyThread120804();
        new Thread(mt, "线程对象").start();
        mt.run();   //对象直接调用run()
    }
}

//通过此代码可以发现当使用了 mt.run() 直接在主方法中调用线程类对象中的run()方法，所获得的线程对象的名字为main
//所以可以得出一个结论：主方法也是一个线程（主线程）
//那么现在问题来了，所有的线程都是在进程上的划分，那么进程在哪里？
//每当使用java命令执行程序的时候就表示启动了一个JVM的进程，一台电脑可以同时启动若干个JVM进程，所以每一个JVM进程都会有各自的线程

//开发之中，主线程可以创建若干个子线程，创建子线程的目的是：可以将一些比较复杂的逻辑或者比较耗时的逻辑交由子线程处理
//主线程负责整体流程，子线程负责耗时操作