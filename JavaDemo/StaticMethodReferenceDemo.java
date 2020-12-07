@FunctionalInterface //函数式接口
//P描述的是参数、R描述的是返回值
interface IFunction<P, R> { //泛型编程
    public R change(P p);
}

public class StaticMethodReferenceDemo {
    public static void main(String[] args) {
        IFunction<Integer, String> fun = String :: valueOf; //valueOf是String类的static方法
        String str = fun.change(100);
        System.out.println(str.length());
    }    
}