import java.lang.Runnable;
class MyThread120803 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
public class NamingThreadDemo {
    public static void main(String[] args) {
        MyThread120803 mt = new MyThread120803();
        new Thread(mt, "线程A").start();    //设置了线程的名字
        new Thread(mt).start();            //未设置线程的名字
        new Thread(mt).start();            //未设置线程的名字
        new Thread(mt).start();            //未设置线程的名字
        new Thread(mt).start();            //未设置线程的名字
        new Thread(mt).start();            //未设置线程的名字
        new Thread(mt, "线程B").start();    //设置了线程的名字
    }
}

//当开发者为线程设置名字的时候就使用设置的名字，而如果没有设置名字，则会自动生成不重复的名字
//这种自动的属性命名主要是依靠了static属性完成
//在Thread类中定义有如下操作
    /*
        private static int threadInitNumber;
        private static synchronized int nextThreadNum() {
            return threadInitNumber++;
        }
    */