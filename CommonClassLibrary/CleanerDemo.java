/*
    对于回收释放
    从JDK1.9开始建议开发者使用AutoCloseable或者使用java.lang.ref.Cleaner类进行回收处理
    Cleaner也支持AutoCloseable处理

    在新一代的清除回收处理的过程之中，更多的情况下考虑的是多线程的使用
        即： 为了防止有可能造成的延迟处理，许多对象回收前的处理都是单独通过一个线程完成的
*/

/*
    import java.lang.Runnable;
    import java.lang.ref.Cleaner;
    class Member121202 implements Runnable {
        public member121202() {
            System.out.println("【构造】在一个雷电交加的夜晚，linqiang诞生了。");
        }
        @Override
        public void run() { //执行清除的时候执行的是此操作
            System.out.println("【回收】最终你一定要死的");
        }
    }
    class Member121202Cleaning implements AutoCloseable{    //实现清除的处理
        private static final Cleaner cleaner = Cleaner.create();    //创建一个清除处理
        private Member121202 member;
        private Cleaner.cleanable cleanable;
        public Member121202Cleaning() {
            this.member = new Member121202();
            this.cleanable = this.cleaner.register(this, this.member);   //注册要使用的对象
        }
        @Override
        public void close() throws Exception {
            this.cleanable.clean(); //启动多线程
        }
    }
    public class CleanerDemo {
        public static void main(String [] args) {
            try (Member121202Cleaning mc = new Member121202Cleaning()) {
                //中间可以执行一些相关的代码
            }catch (Exception e) {}
        }
    }

*/  