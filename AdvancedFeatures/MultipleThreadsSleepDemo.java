//产生多个线程对象进行休眠处理
import java.lang.Runnable;

public class MultipleThreadsSleepDemo {
    public static void main(String[] args) {
        Runnable run = () -> {
            for(int x = 0; x < 10; x++) {
                System.out.println(Thread.currentThread().getName() + "、x = " + x);
                try {
                    Thread.sleep(1000);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int num = 0; num < 5; num ++) {    //产生五个线程对象，且五个线程对象执行相同的方法体
            new Thread(run, "线程对象 - " + num).start();
        }
    }
}
//此时，从程序执行的感觉来看好像是若干个线程一起进行了休眠，又一起进行了自动唤醒
//实际上有差别
//实际上进行了适当的延迟操作，即有先有后