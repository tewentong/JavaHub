/*
    在多线程的定义之中，volatile关键字主要在属性定义上使用，表示此属性为直接数据操作，而不进行副本的拷贝处理
    这样的话，在一些书上就将其错误地理解为同步属性了

    在正常进行变量处理的时候往往会经历如下的几个步骤：
        1. 获取变量原有的数据 内容副本
        2. 利用副本 为变量进行数学计算
        3. 将计算后的变量保存到原始空间之中
    而如果一个属性上追加了volatile关键字，表示的就是不使用副本，而是直接操作原始变量
    相当于节约了： 拷贝副本、重新保存的步骤
    即可以更快地实现变量的修改处理
*/

import java.lang.Runnable;
class MyThread121001 implements Runnable {
    private volatile int ticket = 5; //添加volatile关键字以后，所有操作不是通过拷贝的副本完成
    //而是直接操作内存中的原始数据
    @Override
    public void run() {
       synchronized (this) {    //添加了volatile关键字以后，该同步还得同步
        while (ticket > 0 ) {
            try {
                Thread.sleep(100);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "卖票处理、ticket = " + this.ticket --);
        }
       }
    }
}
public class VolatileDemo {
    public static void main(String [] args) {
        MyThread121001 mt = new MyThread121001();
        new Thread(mt, "票贩子A").start();
        new Thread(mt, "票贩子B").start();
        new Thread(mt, "票贩子C").start();
    }
}

/*
    面试题： 请解释volatile和synchronized的区别
        volatile主要在属性上使用，synchronized主要在代码块和方法上使用
        volatile无法描述同步的处理，它只是一种直接内存的处理，避免了副本的拷贝
        synchronized是实现同步的
*/