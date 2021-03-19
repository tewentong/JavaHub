
/*
    Map.Entry接口
        虽然已经清楚了Map集合的基本操作形式，但是依然需要有一个核心的问题要解决，
            Map集合里面是如何进行数据存储的？
            对于List而言（LinkedList子类）依靠的是链表的形式实现的数据存储，
                那么在进行数据存储的时候，一定要将数据保存在一个Node节点之中，虽然在HashMap里面也可以见到Node类型定义
                通过源代码的定义可以发现，HashMap类中的Node内部类本身实现了Map.Entry接口
                static class Node<K, V> implements Map.Entry<K, V> {}
                所以可以得出结论：所有的key和value的数据都被封装在Map.Entry接口之中，此接口定义如下：
                    public static interface Map.Entry<K, V>
                并且在这个内部接口里面提供有两个重要的操作方法：
                    获取key: public K getKey();
                    获取value: public V getValue();
            在JDK1.9以前的开发版本之中，使用者都不会考虑去创建Map.Entry的对象，
                实际上在正常的开发过程之中，使用者也不需要关心Map.Entry对象创建，
                可是从JDK1.9之后，Map接口里面追加有一个新的方法：
                创建Map.Entry对象： public static <K, V> Map.Entry<K, V> entry(K k, V v);
        范例：创建Map.Entry对象
            import java.util.Map;
            public class EntryInnerInterfaceDemo {
                public static void main(String[] args) {
                    Map.Entry<String, Integer> entry = Map.entry("one", 1);
                    System.out.println("获取Key: " + entry.getKey());
                    System.out.println("获取Value: " + entry.getValue());
                    System.out.println(entry.getClass().getName()); //观察使用的子类
                }
            }
            程序输出：
                获取Key: one
                获取Value: 1
                java.util.KeyValueHolder
            通过分析可以发现在整个的Map集合里面，Map.Entry的主要作用就是作为一个Key和Value的包装类型使用
                而在大部分情况下进行数据存储的时候都会将key和value包装为一个Map.Entry对象进行使用
*/
import java.util.Map;

public class EntryInnerInterfaceDemo {
    public static void main(String[] args) {
        Map.Entry<String, Integer> entry = Map.entry("one", 1);
        System.out.println("获取Key: " + entry.getKey());
        System.out.println("获取Value: " + entry.getValue());
        System.out.println(entry.getClass().getName()); // 观察使用的子类
    }
}