
/*
    foreach输出
        除了使用迭代接口实现输出之外，从JDK1.5开始加强型for循环也可以实现集合的输出了
            这种输出的形式与数组的输出操作形式类似
        范例：使用foreach输出
            import java.util.ArrayList;
            import java.util.List;
            public class ForeachDemo {
                public static void main(String[] args) throws Exception {
                    List<String> all = new ArrayList<String>();
                    all.add("Hello");
                    all.add("World");
                    all.add("MLDN");
                    for (String str : all) {
                        System.out.print(str + "、");
                    }
                }
            }
            这种输出最初出现的时候很多人并不建议使用，因为标准的集合操作还是应该以Iterator为主，
            但是毕竟JDK1.5也已经推出十多年了，很多的语法也开始被大部分人所习惯
*/
import java.util.ArrayList;
import java.util.List;

public class ForeachDemo {
    public static void main(String[] args) throws Exception {
        List<String> all = new ArrayList<String>();
        all.add("Hello");
        all.add("World");
        all.add("MLDN");
        for (String str : all) {
            System.out.print(str + "、");
        }
    }
}