/*
    @FunctionalInterface
    public interface Function<T, R> {
        public R apply(T t);
    }
*/
import java.util.function.*;
public class FunctionalFunctionInterfaceDemo {
    public static void main(String[] args) {
        Function<String, Boolean> fun = "**Hello" :: startsWith;
        System.out.println(fun.apply("**"));
    }
}