//后台守护线程
/*
    如果假设现在有一个人，且这个人有一个保镖，那么这个保镖一定是在这个人活着的时候进行守护，如果这个人已经死了，保镖就没用了
    所以在多线程里可以进行守护线程的定义：
        也就是说如果现在主线程的程序或者其他的线程还在执行的时候，那么守护线程将一直存在，并且运行在后台状态
    在Thread类里面提供有如下的守护线程的方法：
        设置为守护线程： public final void setDaemon(boolean on);
        判断是否为守护线程： public final boolean isDaemon();
*/
public class BackgroundDaemonDemo {
    public static void main(String [] args) {
        Thread userThread = new Thread(() -> {
            for (int x = 0; x < 10; x ++) {
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在运行、x = " + x);
            }
        }, "用户线程"); //完成核心的业务
        Thread daemonThread = new Thread(() -> {
            for (int x = 0; x <Integer.MAX_VALUE; x ++) {
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在运行、x = " + x);
            }
        }, "守护线程");
        daemonThread.setDaemon(true);   //设置为守护线程
        userThread.start();
        daemonThread.start();
    }
}

/*
    可以发现守护线程围绕在用户线程的周围，如果程序执行完毕，守护线程也就消失了
    JVM中最大的守护线程就GC线程
    程序执行中GC线程会一直存在，如果程序执行完毕，GC线程也将消失
*/