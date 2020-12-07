//为了便于使用，利用静态方法做匿名内部类的实现
//返回一个匿名内部类对象
//这段代码很重要。要好好研究。
interface IMessage1128 {
    public void send(String str);
    public static IMessage1128 getInstance() { 
        return new IMessage1128() { //匿名内部类操作
            public void send(String str) {
                System.out.println(str);
            }
        };
    }
}

public class StaticAnonymousInnerClassDemo {
    public static void main(String[] args) {
        IMessage1128.getInstance().send("www.mldn.cn!!!");
    }
}