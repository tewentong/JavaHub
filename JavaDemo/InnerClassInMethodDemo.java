//内部类可以在任意的结构中进行定义这就包括了： 类中、方法中、代码块中，但是在方法中定义较多
class Outer1128 {
    private String msg = "www.mldn.cn";
    public void fun(long time) { //final long time
        //final String info = "我很好";
        class Inner {   //内部类
            public void print() {
                System.out.println(Outer1128.this.msg);
                System.out.println(time);
            }
        }
        new Inner().print();  //方法中直接实例化内部类对象
    }
}
//此时fun()方法内部提供有Inner内部类的定义，并且发现内部类可以直接访问外部类中的私有属性，也可以访问方法中的参数
//但是对于方法中的参数直接访问是从JDK1.8开始支持的
//JDK1.8以前内部类要访问方法中的参数需要加关键字 final （代码中有注释，要注意）

public class InnerClassInMethodDemo {
    public static void main(String[] args) {
        new Outer1128().fun(12121243434L);
    }
}