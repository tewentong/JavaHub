//lamda表达式较为简洁，避免了面向对象复杂繁琐的处理
//实现要求：SAM (SingleAbstractMethod)，只有一个抽象方法
//以IMessage1129为例，这个接口里发现只提供有一个抽象方法send()，除此之外没有其他抽象方法定义
//称为函数式接口
//只有函数式接口才可以被Lambda表达式所使用

@FunctionalInterface //函数式接口
interface IMessage1129{
    public void send(String str);
}

public class LambdaDemo {
    public static void main(String[] args) {
        IMessage1129 msg = (str) -> {
            System.out.println("发送消息： " + str);
        };
        msg.send("www.mldn.com");
    }    
}

/*
    对于Lambda表达式而言，提供有如下几种格式
        方法没有参数： () -> {};
        方法有参数： (参数, 参数) -> {};
        如果现在只有一行语句返回： (参数, 参数) -> 语句;
*/