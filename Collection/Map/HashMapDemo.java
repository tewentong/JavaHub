/*
    HashMap子类
        HashMap是Map接口之中最为常见的子类，该类的主要特点是无序存储，先来观察HashMap子类的定义形式
            public class HashMap<K,V> extends AbstractMap<K,V> implements
                            Map<K,V>, Clonable, Serializable
            该类的定义继承形式符合之前的集合定义形式，依然提供有抽象类并且依然需要重复实现Map接口
        范例：观察Map集合的使用
            import java.util.HashMap;
            import java.util.Map;

            public class HashMapDemo {
                public static void main(String[] args) throws Exception {
                    Map<String, Integer> map = new HashMap<String, Integer>();
                    map.put("one", 1);
                    map.put("two", 2);
                    map.put("one", 101); // key重复
                    map.put(null, 0); // key为null
                    map.put("zero", null); // value为null
                    System.out.println(map);
                }
            }
            ————————————————————————————————————————————————————————
            import java.util.HashMap;
            import java.util.Map;

            public class HashMapDemo {
                public static void main(String[] args) throws Exception {
                    Map<String, Integer> map = new HashMap<String, Integer>();
                    map.put("one", 1);
                    map.put("two", 2);
                    map.put("one", 101); // key重复
                    map.put(null, 0); // key为null
                    map.put("zero", null); // value为null
                    System.out.println(map);
                    System.out.println(map.get("one")); // key存在
                    System.out.println(map.get(null)); // key存在
                    System.out.println(map.get("ten")); // key不存在
                }
            }
            以上的操作形式为Map集合使用的最标准的处理形式，
            通过代码可以发现，通过HashMap实例化的Map接口里面可以针对于key或value保存null的数据
                同时也可以发现即便保存数据的key重复，那么也不会出现错误，而是出现内容的替换
            但是对于Map接口中提供的put()方法本身是提供有返回值的，
                那么这个返回值指的是在重复key的情况下返回旧的value
        范例：观察put()方法
            import java.util.HashMap;
            import java.util.Map;

            public class HashMapDemo {
                public static void main(String[] args) throws Exception {
                    Map<String, Integer> map = new HashMap<String, Integer>();
                    System.out.println(map.put("one", 1)); // key不重复，返回null
                    System.out.println(map.put("one", 101)); // key重复，返回旧数据
                }
            }
            在设置了相同的key的内容的时候put()方法会返回原始的数据内容

            清楚了HashMap的基本功能之后下面就需要来研究以下HashMap之中给出的源代码
                HashMap之中肯定需要存储大量的数据，那么对于数据的存储
                无参构造：
                    public HashMap() {
                        this.loadFactor = DEFAULT_LOAD_FACTOR;  
                    }
                    当时用无参构造的时候会出现有一个loadFactor属性，并且该属性默认的内容为0.75
                        static final float DEFAULT_LOAD_FACTOR = 0.75f

                    public V put(K key, V value) {
                        return putVal(hash(key), key, value, false, true);
                    }
                    在使用put()方法进行数据保存的时候会调用一个putVal()方法，
                        同时会将key进行hash处理（生成一个hash码）
                        而对于putVal()方法里面会发现依然提供有一个Node节点类进行数据的保存
                        而在使用putVal()方法操作的过程之中会调用有一个resize()方法可以进行容量的扩充
        面试题：
            在进行HasnMap的put()操作的时候，如何实现容量扩充的？
                static final int DEFAAULT_INITIAL_CAPACITY = 1 << 4   //aka 16
                在HashMap类里面提供有一个“DEFAULT_INITIAL_CAPACITY”常量，作为初始化的容量配置，这个常量的默认大小为16个元素
                    也就是说默认可以保存的最大内容是16
                当保存的内容的容量超过了阈值（DEFAULT_LOAD_FACTOR=0.75f）,相当于“容量*阈值=12”
                    保存12个元素的时候就会进行容量的扩充
                在进行扩充的时候HashMap采用的是成倍的扩充模式，即：每一次都扩充两倍的容量
                
*/

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        System.out.println(map.put("one", 1)); // key不重复，返回null
        System.out.println(map.put("one", 101)); // key重复，返回旧数据
    }
}