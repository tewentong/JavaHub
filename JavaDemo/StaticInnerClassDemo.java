//如果在内部类上使用了static定义，那么这个内部类就变为了“外部类”
//static定义的都是独立于类的结构, 所以该结构就相当于是一个独立的程序类了
//需要注意的是，static定义的不管是类还是方法，只能访问static成员，所以static定义的内部类只能访问外部类中的static属性或方法
class Outer3 {
    private static final String MSG = "www.mldn.cn"; //定义了全局常量
    static class Inner {
        public void print() {
            System.out.println(Outer3.MSG);
        }
    }
}

//此时Inner类是一个独立的类，此时要想实例化Inner类对象，只需要根据
// 外部类.内部类
//的结构实例化对象即可
//格式如下：
// 外部类.内部类 内部类对象 = new 外部类.内部类()
public class StaticInnerClassDemo {
    public static void main(String[] args) {
        Outer3.Inner in = new Outer3.Inner();
        in.print();
    }
}