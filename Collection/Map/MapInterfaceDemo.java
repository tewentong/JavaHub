
/*
    Java类集框架
        Map集合接口
            在之前已经学习了Collection接口以及其对应的子接口，可以发现在Collection接口之中
                所保存的数据全部都只是单个对象，但是在数据结构里面除了可以进行单个的数据保存之外，
                实际上也可以进行二元偶对象的保存（key=value）的形式来存储
                而存取二元偶对象的核心意义在于可以通过key获取对应的value
                在开发之中：Collection集合保存数据的目的是为了输出，Map集合保存数据的目的是为了进行key的查找
            Map接口简介：
                Map接口是进行二元偶对象保存的最大父接口
                该接口定义如下：public interface Map<K,V>
                该接口为一个独立的父接口，并且在进行接口对象实例化的时候需要设置Key与Value的类型
                    也就是说在整体操作的时候需要保存两个内容
                在Map接口里面定义有许多的操作方法，但是需要记住以下的核心操作方法：
                    public V put(K key, V value);   普通    向集合之中保存数据
                    public V get(Object key);   普通    根据key查询数据
                    public Set<Map,Entry<K,V>> entrySet();  普通    将Map集合转为Set集合
                    public boolean containsKey(Object key)  普通    查询指定的key是否存在
                    public Set<K> keySet(); 将Map集合中的key转为Set集合
                    public V remove(Object key);    普通    根据key删除掉指定的数据
                从JDK1.9之后Map接口里面也扩充了一些静态方法供用户使用
            范例：观察Map集合的特点
                import java.util.Map;

                public class MapInterfaceDemo {
                    public static void main(String[] args) throws Exception {
                        Map<String, Integer> map = Map.of("one", 1, "two", 2);  //JDK1.9之后
                        System.out.println(map);
                    }
                }
                在Map集合之中数据的保存就是按照“key=value”的形式存储的，并且使用of()方法操作的时候里面的数据是不允许重复的
                    如果重复则会出现“IllegalArgumentException”异常，
                    如果设置的内容为null，则会出现"NullPointerException"异常
                对于现在见到的of()方法严格意义上来讲并不是Map集合的标准用法，因为正常的开发之中需要通过Map集合的子类来进行接口对象的实例化
                    而常用的子类：HashMap、Hashtable、TreeMap、LinkedHashMap 
*/
import java.util.Map;

public class MapInterfaceDemo {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> map = Map.of("one", 1, "two", 2); // JDK1.9之后
        System.out.println(map);
    }
}