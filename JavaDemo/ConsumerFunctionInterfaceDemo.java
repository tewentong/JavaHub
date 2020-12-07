//消费型函数式接口：只能够进行数据的处理操作，而没有任何的返回
/*
    @FunctionalInterface
    public interface Consumer<T> {
        public void accept(T t);
    }
*/
import java.util.function.*;
public class ConsumerFunctionInterfaceDemo {
    public static void main(String[] args) {
        Consumer<String> con = System.out :: println;
        con.accept("www.mldn.cn");
    }
}