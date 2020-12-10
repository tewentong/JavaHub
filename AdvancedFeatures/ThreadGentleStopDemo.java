//优雅的线程停止
/*
    在多线程的操作之中如果要启动多线程肯定使用的是Thread类中的start()方法
    而如果需要对多线程进行停止处理，Thread类原本提供有stop()方法，但是对于这些方法，从JDK1.2版本开始就已经将其废除了，而且一直到现在也不再建议出现在你的代码之中
    而除了stop()之外还有几个方法也被禁用了：
        停止多线程： public void stop();
        销毁多线程： public void destroy();
        挂起线程：  public final void suspend(); //暂停执行
        恢复挂起的线程执行： public final void resume();
    废除这些方法是因为，这些方法有可能导致线程的死锁，所以从JDK1.2开始就都不建议使用了

    现在如果想要实现线程的停止就要通过一种柔和的方式进行
*/
public class ThreadGentleStopDemo {
    public static boolean flag = true;
    public static void main(String [] args) {
        new Thread(() -> {
            long num = 0;
            while (flag) {
                try {
                    Thread.sleep(50);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在运行、num = " + num ++);
            }
        }, "执行线程").start();
        
        try {
            Thread.sleep(200);
        }catch (InterruptedException e) {
            e.printStackTrace();
        } 

        //万一现在有其他的线程去控制这个flag的内容，那么这个时候对于线程的停止也不是说停就立刻停止的
        //而是会在执行中判断flag的内容来确定是否要停止程序
        flag = false;
    }
}