/*
    对象克隆处理
        所谓对象的克隆，指的就是对象的复制，而且属于全新的复制
        即： 使用已有对象内容创建一个新的对象
        如果需要进行对象的克隆，就要使用到Object类中提供的Clone()方法
            对象那个克隆： protected Object clone() throws CloneNotSupportedException;
        
        所有的类都会继承Object父类，所以所有的类都一定会有clone()方法，但是并不是所有的类都希望被克隆
        所以要想实现对象克隆，那么对象所在的类需要实现一个Cloneable接口
            Cloneable接口并没有任何的方法提供，是因为它描述的是一种能力

    如果在开发之中不是非常特别的需求下，很少会出现对象克隆的需求
*/
import java.lang.Cloneable;
class Member121203 implements Cloneable {
    private String name;
    private int age;
    public Member121203(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
        return "【" + super.toString() + "】name = " + this.name + "、age = " + this.age;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();   //调用父类中提供的clone()方法
    }
}
public class ObjectCloneDemo {
    public static void main(String [] args) throws CloneNotSupportedException {
        Member121203 memberA = new Member121203("linqiang", 30);
        Member121203 memberB = (Member121203)memberA.clone();
        System.out.println(memberA);
        System.out.println(memberB);
        /*
            输出结果
            【Member121203@4dcbadb4】name = linqiang、age = 30
            【Member121203@4e515669】name = linqiang、age = 30
        */
    }
}