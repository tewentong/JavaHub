//构造器方法引用示例代码
class Person {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String toString() {
        return "姓名： " + this.name + "、 年龄： " + this.age;
    }
}

@FunctionalInterface    //函数式接口
//R代表返回值
interface IFunction1129_2<R> {
    public R creater(String s, int a);
}

public class ConstructorMethodReferenceDemo {
    public static void main(String[] args) {
        IFunction1129_2<Person> fun = Person :: new;
        System.out.println(fun.creater("张三", 20));
    }
}