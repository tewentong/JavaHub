/*
    2. 利用同步方法解决： 只需要在方法定义上使用synchronized关键字即可

    日后学习Java类库的时候会发现，系统中许多的类上使用的同步处理采用的都是同步方法
    千万要记住，同步会造成性能下降
*/

import java.lang.Runnable;
class MyThread120903 implements Runnable {
    private int ticket = 10;
    @Override
    public void run() {
        while(this.sale()) {
            
        }
    }
    public synchronized boolean sale() {    //同步方法
        if (this.ticket > 0) {
            try {
                Thread.sleep(100);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "卖票， ticket = " + this.ticket --);
            return true;
        }else {
            System.out.println("****票已买光****");
            return false;
        }
    }
}
public class ThreadSynchronizationMethodDemo {
    public static void main(String[] args) throws Exception {
        MyThread120903 mt = new MyThread120903();
        new Thread(mt, "票贩子A").start();
        new Thread(mt, "票贩子A").start();
        new Thread(mt, "票贩子A").start();
    }
}