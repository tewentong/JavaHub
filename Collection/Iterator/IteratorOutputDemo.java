
/*
    Java类集框架
        集合输出：
            集合输出实际上从JDK1.8开始就在Iterable接口之中提供有一个forEach()的方法，
                但是这种方法的输出并不是传统意义上的集合输出形式，并且也很难在实际的开发之中出现
                对于集合操作而言，一共定义有四种输出形式：
                    Iterator迭代输出、ListIterator双向迭代输出、Enumeration枚举输出、forEach输出
        Iterator输出：
            通过Collection接口的继承关系可以发现，从JDK1.5开始其多继承了一个Iterable父接口，
            并且在这个接口里面定义有Iterator()方法，通过此方法可以获取Iterator接口对象(在JDK1.5之前，此方法直接定义在Collection接口之中)
                获取Iterator接口对象：public Iterator<T> iterator()
            在Iterator接口里面定义有如下的方法:
                public boolean hasNext()    普通    判断是否有数据
                public E next() 普通    取出当前数据
                public default void remove  普通    删除
            在之前使用的java.util.Scanner类就是Iterator接口的子类
        
        范例：使用Iterator输出
            import java.util.Iterator;
            import java.util.Set;

            public class IteratorOutputDemo {
                public static void main(String[] args) {
                    Set<String> all = Set.of("Hello", "World", "MLDN"); // JDK1.9之后才可以
                    Iterator<String> iter = all.iterator(); // 实例化Iterator接口对象
                    while (iter.hasNext()) {
                        String str = iter.next();
                        System.out.println(str);
                    }
                }
            }
            
            但是对于Iterator接口中的remove()方法的使用需要特别注意以下（如果不是必须不要使用）
            实际上在Collection接口里面定义有数据的删除操作方法
            但是在进行迭代输出的过程中如果你使用了Collection中的remove()法那个法会导致迭代失败
        范例：采用Collection集合中的remove()方法删除
            import java.util.Iterator;
            import java.util.Set;
            import java.util.HashSet;

            public class IteratorOutputDemo {
                public static void main(String[] args) throws Exception {
                    Set<String> all = new HashSet<String>();
                    all.add("Hello");
                    all.add("World");
                    all.add("MLDN");
                    Iterator<String> iter = all.iterator(); // 实例化Iterator接口对象
                    while (iter.hasNext()) {
                        String str = iter.next();
                        if ("World".equals(str)) {
                            all.remove("World"); // Collection集合方法
                        } else {
                            System.out.println(str);
                        }
                    }
                }
            }
            程序结果：并发修改失败
               Exception in thread "main" java.util.ConcurrentModificationException 
               此时无法进行数据的删除处理操作，那么此时就只能够利用Iterator接口中的remove()方法删除
        范例：使用Iterator接口中的remove()删除
            import java.util.Iterator;
            import java.util.Set;
            import java.util.HashSet;

            public class IteratorOutputDemo {
                public static void main(String[] args) throws Exception {
                    Set<String> all = new HashSet<String>();
                    all.add("Hello");
                    all.add("World");
                    all.add("MLDN");
                    Iterator<String> iter = all.iterator(); // 实例化Iterator接口对象
                    while (iter.hasNext()) {
                        String str = iter.next();
                        if ("World".equals(str)) {
                            iter.remove(); // 删除当前数据
                        } else {
                            System.out.println(str);
                        }
                    }
                    System.out.println("*** " + all);
                }
            }
            程序输出：
                Hello
                MLDN
                *** [Hello, MLDN]
            此时程序执行之后没有出现任何的错误，并且可以成功的删除原始集合中的数据

        面试题：请解释Collection.remove()与Iterator.remove()的区别？
            在进行迭代输出的时候如果使用了Collection.remove()则会造成并发更新的异常，导致程序删除出错
            此时只能使用Iterator接口中的Iterator.remove()方法实现正常的删除处理
*/
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class IteratorOutputDemo {
    public static void main(String[] args) throws Exception {
        Set<String> all = new HashSet<String>();
        all.add("Hello");
        all.add("World");
        all.add("MLDN");
        Iterator<String> iter = all.iterator(); // 实例化Iterator接口对象
        while (iter.hasNext()) {
            String str = iter.next();
            if ("World".equals(str)) {
                iter.remove(); // 删除当前数据
            } else {
                System.out.println(str);
            }
        }
        System.out.println("*** " + all);
    }
}