//内部类存在的意义就是 内部类可以轻松访问外部类的私有成员
//同时，外部类也可以访问内部类的私有属性

//内部类与外部类之间的私有操作的访问就不再需要通过setter、getter以及其他的间接方式完成，可以直接进行处理操作

class Outer {  
    private String msg = "www.mldn.cn"; //私有成员属性
    public void fun() { //普通方法
        Inner in = new Inner(); //实例化内部类对象
        in.print(); //调用内部类方法
        System.out.println(in.info); //外部类访问内部类的私有属性
    }
    class Inner {   //在Outer类的内部定义了Inner类
        private String info = "今天天气不好，该收衣服啦！"; //内部类的私有属性
        public void print() {
            System.out.println(Outer.this.msg); //Outer类中的属性
        }
    }
}
public class InnerClassDemo {
    public static void main(String[] args) {
        Outer out = new Outer(); //实例化外部类对象
        out.fun(); //调用外部类中的方法
    }
}