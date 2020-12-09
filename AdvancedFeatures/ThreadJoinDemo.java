//线程强制执行指的是当满足于某些条件之后，某一个线程对象将可以独占资源，直到该线程的程序执行结束
//在进行线程强制执行的时候一定要获取强制执行线程对象之后才可以执行join()调用

public class ThreadJoinDemo {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread(); //获得主线程
        //只有获得主线程，才能通过其他的线程强制执行

        //现在这种情况，主线程和子线程都在交替执行
        Thread thread = new Thread(() -> {
            for (int x = 0; x < 100; x++) {
                if (x == 3) {   //现在霸道的线程来了
                    try {
                        mainThread.join();  //霸道的线程要先执行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "执行、 x = " + x);
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "玩耍的线程");
        thread.start();

        for (int x = 0; x < 100; x++){
            try {
                Thread.sleep(100);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【霸道的main线程】number = " + x);
        }

        //现在我希望主线程独占执行，就可以利用Thread类中的方法
        //强制执行： public final void join() throws InterruptedException;
    }
}