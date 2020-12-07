@FunctionalInterface    //函数式接口
//P描述的是参数

//特定类方法的函数式引用

interface IFunction1129_1<P> {
    public int compare(P p1, P p2);
} 

public class SpecifiedClassMethodReferenceDemo {
    public static void main(String[] args) {
        IFunction1129_1<String> fun = String :: compareTo;
        System.out.println(fun.compare("Hello", "hi"));
    }    
}