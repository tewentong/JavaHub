/*
    反射与单例设计模式
        单例设计模式的核心本质在于：
            类内部的构造方法私有化，在类的内部产生类的实例化对象之后，通过static方法获取实例化对象进行类中的结构调用
        单例设计模式一共有两类：懒汉式、俄汉式
            饿汉式的单例是不在本次讨论范围之内的，主要来讨论懒汉式的单例设计模式
        范例：观察懒汉式单例设计模式的问题
            1.单线程情况下：
                class Singleton0312 {
                    private static Singleton0312 instance = null;
                    private Singleton0312() {
                    }
                    public static Singleton0312 getInstance() {
                        if (instance == null) {
                            instance = new Singleton0312();
                        }
                        return instance;
                    }
                    public void print() {
                        System.out.println("www.mldn.cn");
                    }
                }

                public class ReflexAndSingletonDemo {
                    public static void main(String[] args) throws Exception {
                        Singleton0312 sinA = Singleton0312.getInstance();
                        sinA.print();
                    }
                }

            2.多线程情况下：
                class Singleton0312 {
                    private static Singleton0312 instance = null;

                    private Singleton0312() {
                        System.out.println("【" + Thread.currentThread().getName() + "】*******实例化Singleton类对象************");
                    }

                    public static Singleton0312 getInstance() {
                        if (instance == null) {
                            instance = new Singleton0312();
                        }
                        return instance;
                    }

                    public void print() {
                        System.out.println("www.mldn.cn");
                    }
                }

                public class ReflexAndSingletonDemo {
                    public static void main(String[] args) throws Exception {
                        for (int x = 0; x < 3; x++) {
                            new Thread(() -> {
                                Singleton0312.getInstance().print();
                            }, "单例消费端-" + x).start();
                        }
                    }
                }
                程序执行结果：
                    【单例消费端-1】*******实例化Singleton类对象************
                    【单例消费端-2】*******实例化Singleton类对象************
                    www.mldn.cn
                    【单例消费端-0】*******实例化Singleton类对象************
                    www.mldn.cn
                    www.mldn.cn

                单例设计模式的最大特点是在整体的运行过程之中只允许产生一个实例化对象，
                    这个时候会发现，当有了若干线程之后，当前的程序就可以产生多个实例化对象了，那么此时就不是单例设计模式了
                此时问题造成的关键在于代码本身出现了不同步的情况，而要想解决的关键核心就在于需要进行同步处理，同步自然想到synchronized关键字
            3.使用synchronized关键字进行同步以后：
                class Singleton0312 {
                    private static Singleton0312 instance = null;

                    private Singleton0312() {
                        System.out.println("【" + Thread.currentThread().getName() + "】*******实例化Singleton类对象************");
                    }

                    public static synchronized Singleton0312 getInstance() {
                        if (instance == null) {
                            instance = new Singleton0312();
                        }
                        return instance;
                    }

                    public void print() {
                        System.out.println("www.mldn.cn");
                    }
                }

                public class ReflexAndSingletonDemo {
                    public static void main(String[] args) throws Exception {
                        for (int x = 0; x < 3; x++) {
                            new Thread(() -> {
                                Singleton0312.getInstance().print();
                            }, "单例消费端-" + x).start();
                        }
                    }
                }
                程序运行结果：
                    【单例消费端-0】*******实例化Singleton类对象************
                    www.mldn.cn
                    www.mldn.cn
                    www.mldn.cn
                
                这个时候的确进行了同步处理，但是这个同步的代价有些大，因为效率会低
                    因为整体代码里面实际上只有一块代码需要同步处理：instance对象的实例化处理部分
                        if (instance == null) {
                            instance = new Singleton0312();
                        }
                    在这样的情况下我们会发现，同步加的有点草率了
            4范例：修改getInstance()方法进行同步处理
                class Singleton0312 {
                    private static volatile Singleton0312 instance = null;

                    private Singleton0312() {
                        System.out.println("【" + Thread.currentThread().getName() + "】*******实例化Singleton类对象************");
                    }

                    public static Singleton0312 getInstance() {
                        if (instance == null) {
                            synchronized (Singleton0312.class) {
                                if (instance == null) {
                                    instance = new Singleton0312();
                                }
                            }
                        }
                        return instance;
                    }

                    public void print() {
                        System.out.println("www.mldn.cn");
                    }
                }

                public class ReflexAndSingletonDemo {
                    public static void main(String[] args) throws Exception {
                        for (int x = 0; x < 3; x++) {
                            new Thread(() -> {
                                Singleton0312.getInstance().print();
                            }, "单例消费端-" + x).start();
                        }
                    }
                }
                程序运行结果：
                    【单例消费端-0】*******实例化Singleton类对象************
                    www.mldn.cn
                    www.mldn.cn
                    www.mldn.cn
                我们修改了两个地方：
                    private static volatile Singleton0312 instance = null;

                    public static Singleton0312 getInstance() {
                        if (instance == null) {
                            synchronized (Singleton0312.class) {
                                if (instance == null) {
                                    instance = new Singleton0312();
                                }
                            }
                        }
                        return instance;
                    }

    面试题：请编写单例设计模式
        在Java中哪里用到单例设计模式了？Runtime类、Spring框架
        懒汉式单例设计模式的问题？
                
*/
class Singleton0312 {
    private static volatile Singleton0312 instance = null;

    private Singleton0312() {
        System.out.println("【" + Thread.currentThread().getName() + "】*******实例化Singleton类对象************");
    }

    public static Singleton0312 getInstance() {
        if (instance == null) {
            synchronized (Singleton0312.class) {
                if (instance == null) {
                    instance = new Singleton0312();
                }
            }
        }
        return instance;
    }

    public void print() {
        System.out.println("www.mldn.cn");
    }
}

public class ReflexAndSingletonDemo {
    public static void main(String[] args) throws Exception {
        for (int x = 0; x < 3; x++) {
            new Thread(() -> {
                Singleton0312.getInstance().print();
            }, "单例消费端-" + x).start();
        }
    }
}