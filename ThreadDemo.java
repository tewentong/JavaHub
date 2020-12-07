class MyThread extends Thread { //线程的主体类
    private String title;
    public MyThread(String title) {
        this.title = title;
    }
    @Override 
    public void run() { //线程的主体方法
    //多线程要执行的功能都要在run()方法中进行定义
    //说明： 正常情况下，调用一个类中的方法一定要产生该类的实例化对象，但是run()方法是不能够被直接调用的，因为这里涉及到操作系统的资源调度问题
    //所以要想启动多线程，必须使用start()方法
        for(int x = 0; x < 10; x++) {
            System.out.println(this.title + "运行， X = " + x);
        }
    }
}

public class ThreadDemo {
    public static void main(String [] args) {
        new MyThread("线程A").start();
        new MyThread("线程B").start();
        new MyThread("线程C").start();
        //虽然调用了start()方法，但是执行的是run()方法，且所有线程的对象都是交替执行的，执行顺序不可控制
        //为什么多线程的启动不直接使用run()方法而必须使用Thread类中start()方法呢？
        
        //任何情况下，只要定义了多线程，多线程的启动永远只有一种方案： Thread类中的start()方法
    }
}

/*
   public synchronized void start() {
       
        if (threadStatus != 0)
            throw new IllegalThreadStateException();

        
        group.add(this);

        boolean started = false;
        try {
            start0();
            started = true;
        } finally {
            try {
                if (!started) {
                    group.threadStartFailed(this);
                }
            } catch (Throwable ignore) {
                
        }
    }

    private native void start0(); 
    
    native: Java程序执行的过程之中。考虑到对于不同层次开发者的需求，所以其支持有本地的操作系统函数调用，而这项技术被称为JNI(Java Native Interface)技术
    但是Java开发过程之中并不推荐这样使用，利用这项技术可以使用一些操作系统提供的底层函数进行一些特殊的处理
    而在Thread类里提供的start0()就表示需要将此方法依赖于不同的操作系统实现
    start0()的实现由JVM中间作匹配得来
*/