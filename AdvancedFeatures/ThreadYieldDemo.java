//线程的礼让指的是先将资源让出去，让别的线程先执行
//线程的礼让可以使用Thread类中提供的方法：
//礼让： public static void yield();
//礼让执行的时候每一次调用yield()方法都只会礼让一次当前的资源
public class ThreadYieldDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int x = 0 ; x < 100; x ++) {
                if (x % 3 == 0) {
                    Thread.yield(); //线程礼让
                    System.out.println("###玩耍的线程礼让执行###");
                }
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行、 x = " + x);
            }
        }, "玩耍的线程");
        thread.start();

        for(int x = 0 ; x < 100; x++) {
            try {
                Thread.sleep(100);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【霸道的main线程】number = " + x);
        }
    }
}