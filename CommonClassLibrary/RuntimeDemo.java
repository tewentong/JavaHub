/*
    Runtime描述的是运行时的状态，也就是说在JVM之中，Runtime类是唯一一个与JVM运行状态有关的类，并且默认提供有一个该类的实例化对象
    由于在每一个JVM进程里面只允许提供有一个Runtime类的对象，所以这个类的构造方法被默认私有化了： private Runtime() {}
        即该类使用的是单例设计模式，并且单例设计模式一定会提供有一个static方法获取本类实例

    由于Runtime类属于单例设计模式，如果想要获取实例化对象，就要依靠类中的getRuntime()方法完成：
        获取实例化对象： public static Runtime getRuntime();
    通过Runtime类中的availableProcessors()方法可以获取本机的CPU的内核数（决定了并发访问量的最佳状态）

    Runtime类中其他的重要操作方法：
        获取最大可用内存空间：   public long maxMemory();       //默认的配置为本机系统内存的四分之一
        获取可用内存空间：      public long totalMemory();      //默认的配置为本机系统内存的六十四分之一
        获取空闲内存空间：      public long freeMemory();       
        手工进行GC处理：       public void gc();

    面试题： 请问什么是GC？如何处理？
        GC(Garbage Colletor)垃圾收集器，是可以由系统自动调用的垃圾释放功能，或者使用Runtime类中的gc()手工调用
*/
public class RuntimeDemo {
    public static void main(String [] args) {
        Runtime run = Runtime.getRuntime(); //获取实例化对象
        System.out.println(run.availableProcessors());  //输出 12 （因为我是12核心CPU）

        //观察内存状态
        System.out.println("【1】MAX_MEMEORY: " + run.maxMemory());         
        System.out.println("【1】TOTAL_MEMEORY: " + run.totalMemory());
        System.out.println("【1】FREE_MEMEORY: " + run.freeMemory());
        System.out.println();

        //产生大量的垃圾空间
        String str = "";
        for (int x = 0; x < 30000; x ++) {
            str += "x";
        }
        System.out.println("【2】MAX_MEMEORY: " + run.maxMemory());         
        System.out.println("【2】TOTAL_MEMEORY: " + run.totalMemory());
        System.out.println("【2】FREE_MEMEORY: " + run.freeMemory());
        System.out.println();

        //手动清理垃圾
        run.gc();
        System.out.println("【3】MAX_MEMEORY: " + run.maxMemory());         
        System.out.println("【3】TOTAL_MEMEORY: " + run.totalMemory());
        System.out.println("【3】FREE_MEMEORY: " + run.freeMemory());
    }
}