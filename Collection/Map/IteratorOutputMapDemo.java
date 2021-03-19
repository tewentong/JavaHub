
/*
    使用Iterator输出Map集合
        对于集合的输出而言，最标准的做法就是利用Iterator接口来完成，
            但是需要明确一点的是在Map集合里面并没有一个方法可以直接返回Iterator接口对象
            所以这种情况下就必须分析不直接提供Iterator接口实例化的原因
            下面对Collection与Map集合的存储结构进行一个比较处理
                发现在Map集合里面保存的实际上是一组Map.Entry接口对象（里面包装的是Key与Value）
                    整体而言，Map实现的依然是单值的保存
                    Map集合里面提供有一个方法: public Set<Map.Entry<K, V>>entrySet()
                    将全部的Map集合转为Set集合
        经过分析可以发现，如果要想使用Iterator实现Map集合的输出，则必须按照如下步骤
            利用Map接口中提供的entrySet()方法将Map集合转为Set集合
            利用Set接口中的iterator()方法将Set集合转为Iterator接口实例
            利用Iterator进行迭代输出获取每一组的Map.Entry对象，随后通过getKey()与getValue()获取数据
        范例：利用Iterator输出Map集合
            import java.util.Map;
            import java.util.HashMap;
            import java.util.Set;
            import java.util.Iterator;
            public class IteratorOutputMapDemo {
                public static void main(String[] args) throws Exception {
                    Map<String, Integer> map = new HashMap<String, Integer>();
                    map.put("one", 1);
                    map.put("two", 2);
                    map.entrySet();
                    Set<Map.Entry<String, Integer>> set = map.entrySet(); // 将Map集合变为Set集合
                    Iterator<Map.Entry<String, Integer>> iter = set.iterator();
                    while (iter.hasNext()) {
                        Map.Entry<String, Integer> me = iter.next();
                        System.out.println(me.getKey() + " = " + me.getValue());
                    }
                }
            }
            虽然Map集合本身有迭代输出的支持，但是从实际的开发来讲，
                Map集合最主要的用法在于实现数据的key查找操作
                另外需要提醒的是，如果现在不使用Iterator而使用foreach语法输出则也需要将Map集合转为Set集合
        范例：使用foreach输出Map集合
            import java.util.Map;
            import java.util.HashMap;
            import java.util.Set;
            import java.util.Iterator;

            public class IteratorOutputMapDemo {
                public static void main(String[] args) throws Exception {
                    Map<String, Integer> map = new HashMap<String, Integer>();
                    map.put("one", 1);
                    map.put("two", 2);
                    map.entrySet();
                    Set<Map.Entry<String, Integer>> set = map.entrySet(); // 将Map集合变为Set集合
                    for (Map.Entry<String, Integer> entry : set) {
                        System.out.println(entry.getKey() + " = " + entry.getValue());
                    }
                }
            }
            由于Map迭代输出的情况相对较少，所以对于此类的语法应该深入理解以下，并且灵活掌握
*/
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class IteratorOutputMapDemo {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("one", 1);
        map.put("two", 2);
        map.entrySet();
        Set<Map.Entry<String, Integer>> set = map.entrySet(); // 将Map集合变为Set集合
        for (Map.Entry<String, Integer> entry : set) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}