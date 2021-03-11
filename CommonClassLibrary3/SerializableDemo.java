
/*
    Java IO编程
    对象序列化：
        几乎只要是Java开发，就一定会有序列化的概念，而正是因为序列化的概念逐步发展，慢慢也有了更多的序列化的标准
        序列化的概念：所谓的对象序列化指的是将内存中保存的对象以二进制数据流的形式进行处理，可以实现对象的保存或者是网络传输
        然而并不是所有的对象都可以被序列化，在Java里面有一个强制性的要求： 
            如果要序列化对象，那么对象所在的类一定要实现java.io.Serializable父接口，作为序列化的标记
                这个接口并没有任何的方法，因为它描述的是一种类的能力
        范例：定义一个可以被序列化的类
            import java.io.Serializable;
            class Person0311 implements Serializable { // Person类可以被序列化
                // 可以压制警告 @SuppressWarnings("serial")
                private static final long serialVersionUID = 1L; // 序列化的版本编号,为了在不同虚拟机环境下可以使用
                private String name;
                private int age;
                public Person0311(String name, int age) {
                    this.name = name;
                    this.age = age;
                }
                // setter、getter略
                @Override
                public String toString() {
                    return "姓名： " + this.name + "、年龄： " + this.age;
                }
            }
        此时,Person类产生的每一个对象都可以实现二进制的数据传输,属于可以被序列化的程序类
*/
import java.io.Serializable;

class Person0311 implements Serializable { // Person类可以被序列化
    // 可以压制警告 @SuppressWarnings("serial")
    private static final long serialVersionUID = 1L; // 序列化的版本编号,为了在不同虚拟机环境下可以使用
    private String name;
    private int age;

    public Person0311(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // setter、getter略
    @Override
    public String toString() {
        return "姓名： " + this.name + "、年龄： " + this.age;
    }
}

public class SerializableDemo {
    public static void main(String[] args) throws Exception {

    }
}