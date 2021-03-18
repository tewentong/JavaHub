
/*
    LinkedHashMap
        HashMap虽然是Map集合最为常用的一个子类，但是其本身所保存的数据都是无序的（有序与否对Map没有影响）
            如果希望Map集合之中保存的数据的顺序为其增加顺序，则就可以更换子类为LinkedHashMap（基于链表实现的）
            LinkedHashMap类的定义形式：
                public class LinkedHashMap<K,V> extends HashMap<K,V> implements Map<K,V>
                既然是链表保存，所以一般在使用LinkedHashMap类的时候往往数据量都不要特别大，因为会造成时间复杂度攀升
                通过继承结构可以发现LinkedHashMap是HashMap子类
        范例：使用LinkedHashMap
            import java.util.LinkedHashMap;
            import java.util.Map;

            public class LinkedHashMapDemo {
                public static void main(String[] args) throws Exception {
                    Map<String, Integer> map = new LinkedHashMap<String, Integer>();
                    map.put("one", 1);
                    map.put("two", 2);
                    map.put("one", 101); // key重复
                    map.put(null, 0); // key为null
                    map.put("zero", null); // value为null
                    System.out.println(map);
                }
            }
            程序输出：
                {one=101, two=2, null=0, zero=null}
            通过此时的程序执行可以发现当使用了LinkedHashMap进行存储之后所有数据的保存顺序为添加顺序
*/
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemo {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("one", 101); // key重复
        map.put(null, 0); // key为null
        map.put("zero", null); // value为null
        System.out.println(map);
    }
}