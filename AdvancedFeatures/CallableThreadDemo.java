//Runnable接口的缺点： 当线程执行完毕以后，我们无法获取一个返回值
import java.util.concurrent.Callable;
/*
    @FunctionalInterface
    public interface Callable<V> {
        public V call() throws Exception;
    }
    //可以发现，Callable定义的时候可以设置一个泛型，此泛型的类型就是返回数据的类型，好处是避免向下转型带来的安全隐患
*/

import java.util.concurrent.FutureTask;

class MyThread120802 implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int x = 0; x < 10; x ++) {
            System.out.println("***********线程执行*********" + x );
        }
        return "线程执行完毕";  //返回值
    }
}

public class CallableThreadDemo {
    public static void main(String[] args) throws Exception {
        FutureTask<String> task = new FutureTask<>(new MyThread120802());
        new Thread(task).start();
        System.out.println("【线程返回数据】: " + task.get());
    }
}

/*
    Runnable与Callable的区别？
    Runnable是JDK1.0时代提出的多线程的实现接口，而Callable是在JDK1.5时代提出的
    java.lang.Runnable接口之中只提供有一个run()方法，并且没有返回值
    java.util.concurrent.Callale接口提供有call()方法，可以提供有返回值

*/