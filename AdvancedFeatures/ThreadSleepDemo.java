/*
    如果说现在希望某个线程可以暂缓执行一次,就可以使用休眠处理，在Thread类中定义的休眠方法如下
        休眠： public static void sleep(long millis) throws InterruptedException;
        休眠： public static void sleep(long millis, int nanos) throws InterruptedException;
    在休眠的时候有可能产生中断异常“InterruptedException”
    休眠的主要特点是可以自动实现线程的唤醒，以继续进行后续的处理
    但是要注意，如果你现在有多个线程对象，休眠也是有先后顺序的
*/
public class ThreadSleepDemo {
    public static void main(String[] args) throws Exception{
        new Thread(() -> {
            for(int x = 0; x < 10; x++){
                System.out.println(Thread.currentThread().getName() + ", x = " + x);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程对象").start();
    }
}