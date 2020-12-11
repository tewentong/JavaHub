/*
    实现一个竞拍抢答程序：
    要求设置三个抢答者（三个线程），而后同时发出抢答指令，抢答成功者给出成功提示，未抢答成功者给出失败提示

    对于这个多线程的操作，由于里面牵扯到数据的返回问题，那么现在最好使用Callable
*/
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
class MyThread121101 implements Callable<String> {
    private boolean flag = false;   //抢答处理
    @Override
    public String call() throws Exception {
        synchronized(this) {    //数据同步
            if (this.flag == false) {   //抢答成功
                this.flag = true;
                return Thread.currentThread().getName() + " 抢答成功！";
            }else {
                return Thread.currentThread().getName() + " 抢答失败！";
            }
        }
    }
}

public class CompetitionForAnswersDemo {
    public static void main(String [] args) throws Exception {
        MyThread121101 mt = new MyThread121101();
        FutureTask<String> taskA = new FutureTask<String>(mt);
        FutureTask<String> taskB = new FutureTask<String>(mt);
        FutureTask<String> taskC = new FutureTask<String>(mt);
        new Thread(taskA, "竞赛者A").start();
        new Thread(taskB, "竞赛者B").start();
        new Thread(taskC, "竞赛者C").start();
        System.out.println(taskA.get());
        System.out.println(taskB.get());
        System.out.println(taskC.get());
    }
}