
/*
    transient关键字
        默认情况下当执行了对象序列化的时候会将类中的全部属性的内容进行全部的序列化操作
            但是很多情况下有一些属性可能并不需要序列化的处理，这个时候在属性定义上就可以使用transient关键字
        private transient String name
            在进行序列化处理的时候name属性的内容是不会被保存下来的
            换言之，读取的数据name将是其对应数据类型的默认值“null”
        如果假设类之中有一些需要计算保存的属性内容，往往是不需要被序列化的，这个时候就可以使用transient
            但是，在开发之中大部分需要被序列化的类往往都是简单java类，所以这一个关键字的出现频率并不高
*/
import java.io.Serializable;

@SuppressWarnings("serial")
class Person031103 implements Serializable {
    private transient String name;
    private int age;

    public Person031103(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // setter、getter方法略
    @Override
    public String toString() {
        return "姓名： " + this.name + "年龄： " + this.age;
    }
}

public class TrasientDemo {

}