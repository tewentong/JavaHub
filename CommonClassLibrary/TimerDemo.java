/*
    定时器的主要操作是进行定时任务的处理
    在Java中提供有定时任务的支持，但是这种任务的处理只是实现了一种间隔触发的操作
    如果想要实现定时的处理操作主要需要有一个定时操作的主体类，以及一个定时任务的控制
    可以使用两个类实现：
        java.util.TimerTask类：实现定时任务处理
        java.util.Timer类：进行任务的启动，启动的方法：
            任务启动：public void schedule(TimerTask task, long delay);//延迟单位为毫秒
            间隔触发：public void scheduleAtFixedRate(TimerTask task, long delay, long period);
    范例：实现定时任务处理
        import java.util.Timer;
        import java.util.TimerTask;
        class MyTask extends TimerTask {    //任务主体
            @Override
            public void run() { //多线程的处理方法
                System.out.println(Thread.currentThread().getName() + "、定时任务执行，当前时间" + System.currentTimeMillis());
            }
        }
        public class TimerDemo {
            public static void main(String[] args) throws Exception {
                Timer timer = new Timer();  //定时任务
                //定义间隔任务，100毫秒后开始执行，每秒执行1次
                timer.scheduleAtFixedRate(new MyTask(), 100, 1000);
            }
        }
        这种定时是由JDK最原始的方式提供的支持，但是实际上开发之中利用此类方式进行的定时处理实现的代码会非常的复杂
*/
import java.util.Timer;
import java.util.TimerTask;
class MyTask extends TimerTask {    //任务主体
    @Override
    public void run() { //多线程的处理方法
        System.out.println(Thread.currentThread().getName() + "、定时任务执行，当前时间" + System.currentTimeMillis());
    }
}
public class TimerDemo {
    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();  //定时任务
        //定义间隔任务，100毫秒后开始执行，每秒执行1次
        timer.scheduleAtFixedRate(new MyTask(), 100, 1000);
    }
}