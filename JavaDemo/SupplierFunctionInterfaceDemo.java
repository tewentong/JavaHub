//供给型函数式接口：没有接受参数，但有返回值
/*
    @FunctionalInterface
    public interface Supplier<T> {
        public T get();
    }
 */
import java.util.function.*;
public class SupplierFunctionInterfaceDemo {
    public static void main(String[] args) {
        Supplier<String> sup = "www.MLDNJAVA.cn" :: toLowerCase;
        System.out.println(sup.get());
    }    
}