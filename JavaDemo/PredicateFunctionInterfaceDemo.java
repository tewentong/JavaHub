//断言型函数式接口: 进行判断处理
/*
    @FunctionalInterface
    public interface Predicate<T> {
        public boolean test(T t);
    }
*/

import java.util.function.*;
public class PredicateFunctionInterfaceDemo {
    public static void main(String[] args) {
        Predicate<String> pre = "mldn" :: equalsIgnoreCase;
        System.out.println(pre.test("MLDN"));
    }
}