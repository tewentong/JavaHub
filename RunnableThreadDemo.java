//虽然可以通过Thread类的继承来实现多线程的定义，但是在Java程序里面对于继承永远都是有单继承的局限

import java.lang.Runnable;//只能导包处理，自己写Runnable接口将无法被识别
//@FunctionalInterface    //从JDK1.8之后引入了Lambda表达式之后就变为了函数式接口
//interface Runnable{
//    public void run();
//}

class MyThread1207 implements Runnable {
    private String title;
    public MyThread1207 (String title) {
        this.title = title;
    }
    @Override
    public void run() { //线程的主体方法
        for(int x = 0; x < 10; x++) {
            System.out.println(this.title + "运行， X = " + x);
        }
    }
}
public class RunnableThreadDemo {
    public static void main(String[] args) {
        //但是此时由于不再继承Thread父类，那么此时MyThread120702类中也不再有start()方法
        //可是如果不使用Thread.start()方法是无法进行多线程启动的
        //那么现在需要去观察Thread类提供的构造方法了
        //public Thread(Runnable target);

        //启动多线程
        /*
        Thread threadA = new Thread(new MyThread1207("线程A"));
        Thread threadB = new Thread(new MyThread1207("线程B"));
        Thread threadC = new Thread(new MyThread1207("线程C"));
        threadA.start(); //启动多线程
        threadB.start(); //启动多线程
        threadC.start(); //启动多线程
        */

        //Lambda表达式完成
        /*
        for (int x = 0; x < 3; x ++) {
            String title = "线程对象-" + x;
            Runnable run = () -> {
                for (int y = 0; y < 10; y++) {
                    System.out.println(title + "运行、y = " + y);
                }
            };
            new Thread(run).start();
        }
        */
        //变体：把Runnable对象接受的代码直接放到Thread(Runnable target)中
        for (int x = 0; x < 3; x ++) {
            String title = "线程对象-" + x;
            new Thread(() -> {
                for (int y = 0; y < 10; y++) {
                    System.out.println(title + "运行、y = " + y);
                }
            }).start();
        }

    }
}