import java.util.HashMap;
import java.util.Map;

import jdk.internal.cmm.SystemResourcePressureImpl;

/*
    ThreadLocal
        Thread -- 人类
        Runnable -- 任务类

        1.ThreadLocalAPI
            ThreadLocal类只有三个方法：
                void set(T value): 保存值
                T get(): 获取值
                void remove(): 移除值

        2.ThreadLocal的内部是Map
            ThreadLocal内部其实是个Map来保存数据。
            虽然在使用ThreadLocal时只给出了值，没有给出键，其实它内部使用了当前线程做键。
*/
//看看ThreadLocal内部的实现
//把ThreadLocal理解成银行的保险箱
class TL<T> {
    private Map<ThreadLocal, T> map = new HashMap<Thread, T>();

    public void set(T data) {
        // 使用当前线程做key
        map.put(Thread.currentThread(), data);
    }

    public T get() {
        // 使用当前线程做key
        return map.get(Thread.currentThread());
    }

    public void remove() {
        map.remove(Thread.currentThread());
    }
}

public class ThreadLocalDemo0331 {
    public void threadLocalString() {
        ThreadLocal<String> tl = new ThreadLocal<String>();
        tl.set("Hello"); // 存
        System.out.println(tl.get()); // 取
        tl.remove(); // 删

        new Thread() {
            public void run() {
                System.out.println("内部类： " + tl.get()); // 输出为 内部类： null
            }
        }.start();
    }

    public void threadLocalString2() {
        ThreadLocal<String> tl = new ThreadLocal<String>();

        new Thread() {
            public void run() {
                tl.set("Hello");
                System.out.println("内部类： " + tl.get()); // 输出为 内部类： null
            }
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("外部输出： " + tl.get()); // 取
    }

    public void customThreadLocal() {
        Map<Thread, String> map = new HashMap<Thread, String>();
        map.put(Thread.currentThread(), "Hello");
        System.out.println("线程为： " + Thread.currentThread().getName() + "线程，输出是：" + map.get(Thread.currentThread()));

        new Thread() {
            public void run() {
                System.out.println("内部线程是： " + Thread.currentThread().getName());
                System.out.println("内部线程想要输出： " + map.get(Thread.currentThread()));
            }
        }.start();
    }

    public static void main(String[] args) {
        // new ThreadLocalDemo0331().threadLocalString();
        System.out.println("----------------------------------------");
        // new ThreadLocalDemo0331().threadLocalString2();
        System.out.println("----------------------------------------");
        new ThreadLocalDemo0331().customThreadLocal();
    }
}

// 用法
// ThreadLocal通常用在一个类的成员上
// 多个线程访问它时，都有自己的副本，互不干扰
// Spring中Connection放到了ThreadLocal中
class User0331 {
    private ThreadLocal<String> usernameTl = new ThreadLocal<String>();
}