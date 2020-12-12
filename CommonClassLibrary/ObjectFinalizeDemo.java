/*
    Cleaner是在JDK1.9之后提供的对象的清理操作，其主要的功能是进行finalize()方法的替代
    C++中有两种特殊的函数： 构造函数、析构函数（对象的手工回收）
    在Java操作里面所有的垃圾空间都是通过GC自动回收的，所以在很多情况下是不需要使用这种析构函数的，也正因为如此，Java没有提供这方面的支持
    但是Java本身依然提供了给用户收尾的操作，每一个实例化对象在回收之前，至少给他一个喘息的机会
        最初实现的对象收尾处理的方式是Object类中提供的finalize()方法
            @Deprecated (since="9")
            protected void finalize() throws Throwable;     //该方法最大的特征是抛出了Throwable的异常类型
            //Throwable 异常类型分为两个子类型： Error、 Exception
        
        从JDK1.9开始，这一操作已经不建议使用了，而对于回收释放。从JDK1.9开始建议开发者使用AutoCloseable或者使用java.lang.ref.Cleaner类进行回收处理
        Cleaner也支持AutoCloseable处理
*/
class Member {
    public Member() {
        System.out.println("【构造】在一个雷电交加的夜晚，linqiang诞生了");
    }
    @Override
    protected void finalize() throws Throwable {
        System.out.println("【回收】最终你一定要死的");
        throw new Exception("我还要再活五百年...");
    }
}
public class ObjectFinalizeDemo  {
    public static void main(String [] args) {
        //观察传统回收
        Member mem = new Member();  //诞生
        mem = null; //成为垃圾，准备回收
        System.gc();
        System.out.println("太阳照常升起、地球照样转动。");
        /*
            最后的输出如下

            【构造】在一个雷电交加的夜晚，linqiang诞生了
            太阳照常升起、地球照样转动。
            【回收】最终你一定要死的
        */
    }
}