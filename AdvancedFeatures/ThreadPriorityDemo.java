/*
    线程优先级
    从理论上来讲线程的优先级越高越有可能先执行（越有可能先抢占到资源）
    在Thread类里面，针对优先级的操作提供有如下的两个处理方法：
        设置优先级： public final void setPriority(int newPriority);
        获取优先级： public final int getPriority();
    在进行优先级的定义的时候都是通过int型的数字来完成的，而对于此数字的选择在Thread类里面就定义有三个常量：
        最高优先级： public static final int MAX_PRIORITY;      //10
        中等优先级： public static final int NORM_PRIORITY;     //5
        最低优先级： public static final int MIN_PRIORITY;      //1

*/
//优先级高的有可能先执行，但并不一定先执行

import java.lang.Runnable;
public class ThreadPriorityDemo {
    public static void main(String[] args) {
        Runnable run = () -> {
            for (int x = 0; x < 10; x++) {
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行。");
            }
        };
        Thread threadA = new Thread(run, "线程对象A");
        Thread threadB = new Thread(run, "线程对象B");
        Thread threadC = new Thread(run, "线程对象C");
        threadA.setPriority(Thread.MIN_PRIORITY);
        threadB.setPriority(Thread.MIN_PRIORITY);
        threadC.setPriority(Thread.MAX_PRIORITY);
        threadA.start();
        threadB.start();
        threadC.start();
    }
}