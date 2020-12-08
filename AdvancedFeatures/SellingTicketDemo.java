import java.lang.Runnable;

//多个线程访问同一资源
class MyThread1208 implements Runnable {    //线程的主体类
    private int ticket = 5;
    @Override
    public void run() {
        for(int x = 0; x < 100; x ++){
            if(this.ticket > 0)
                System.out.println("卖票， ticket = " + this.ticket --);
        }
    }
}

public class SellingTicketDemo {
    public static void main(String[] args) {    //三个线程访问同一资源
        MyThread1208 mt = new MyThread1208();
        new Thread(mt).start(); //第一个线程启动
        new Thread(mt).start(); //第二个线程启动
        new Thread(mt).start(); //第三个线程启动
    }    
}