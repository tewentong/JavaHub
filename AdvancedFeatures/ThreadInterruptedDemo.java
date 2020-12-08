//线程中断
/*
    之前发现在线程休眠里面提供有一个中断异常，实际上就证明线程的休眠是可以被打断的，而这种打断肯定是由其他线程完成的
    在Thread类中提供有中断执行的方法：
        判断线程是否被中断： public boolean isInterrupted();
        中断线程执行：      public void interrupt();
*/

public class ThreadInterruptedDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("***在疯狂了72个小时之后需要休息补充精力");
            try {
                Thread.sleep(10000);    //准备休眠10秒
                System.out.println("***睡足了，可以出去继续玩儿了");
            }catch (InterruptedException e) {
                System.out.println("敢打扰我睡觉，老子宰了你～");
            }
        });

        thread.start(); //开始睡觉
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(!thread.isInterrupted()) {   //该线程是否已经中断
            System.out.println("我偷偷地打扰一下你的睡眠");
            thread.interrupt();   //中断执行
        }
    }
}