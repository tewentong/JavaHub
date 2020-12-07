//内部类本身也是一个类，大部分情况下被外部类包裹，但是外部依然可以实例化内部类对象，格式如下：
//外部类.内部类 内部类对象 = new 外部类().new 内部类()
//内部类与外部类之间可以直接进行私有成员的访问，这样一来如果内部类如果提供有实例化对象了，一定要先保证外部类已经先实例化了

class Outer2 {
    private String msg = "www.mldn.com";
    class Inner {   // 在Outer类的内部定义了Inner类 
        public void print() {
            System.out.println(Outer2.this.msg); //Outer类中的属性
        }
    }
}

public class NewInnerObjectDemo {
    public static void main(String[] args) {
        Outer.Inner in = new Outer().new Inner();
        in.print();
    }
}