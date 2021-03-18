
/*
    Hashtable子类
        HashTable类是从JDK1.0的时候提供的，与Vector,Enumeration属于最早的一批动态数组的实现类
            后来为了让其保存下来，让其多实现了一个Map接口，Hashtable类的定义结构如下：
            public class Hashtable<K,V> extends Dictionary<K,V> implements 
                                Map<K，V>， Cloneable, Serializable
        范例：观察Hashtable子类的使用
            import java.util.Hashtable;
            import java.util.Map;

            public class HashtableDemo {
                public static void main(String[] args) throws Exception {
                    Map<String, Integer> map = new Hashtable<String, Integer>();
                    map.put("one", 1);
                    map.put("two", 2);
                    map.put("one", 101); // key重复
                    map.put(null, 0); // key为null
                    map.put("zero", null); // value为null
                    System.out.println(map);
                }
            }
            程序输出：
                Exception in thread "main" java.lang.NullPointerException
*/
import java.util.Hashtable;
import java.util.Map;

public class HashtableDemo {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> map = new Hashtable<String, Integer>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("one", 101); // key重复
        System.out.println(map);
    }
}