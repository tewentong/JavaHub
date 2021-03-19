
/*
    Collections工具类
        Collections是java提供的一组集合数据的操作工具类，也就是说利用它可以实现各个集合的操作
        范例：使用Collections操作List集合
            import java.util.ArrayList;
            import java.util.List;
            import java.util.Collections;
            public class CollectionsToolsDemo {
                public static void main(String[] args) throws Exception {
                    List<String> all = new ArrayList<String>();
                    Collections.addAll(all, "Hello", "World", "MLDN");
                    System.out.println(all);
                }
            }

        范例：数据的反转
            import java.util.ArrayList;
            import java.util.List;
            import java.util.Collections;
            public class CollectionsToolsDemo {
                public static void main(String[] args) throws Exception {
                    List<String> all = new ArrayList<String>();
                    Collections.addAll(all, "Hello", "World", "MLDN");
                    Collections.reverse(all);
                    System.out.println(all);
                }
            }

        范例：使用二分查找
            import java.util.ArrayList;
            import java.util.List;
            import java.util.Collections;
            public class CollectionsToolsDemo {
                public static void main(String[] args) throws Exception {
                    List<String> all = new ArrayList<String>();
                    Collections.addAll(all, "Hello", "World", "MLDN");
                    Collections.sort(all); // 先进行排序处理
                    System.out.println(all);
                    System.out.println(Collections.binarySearch(all, "MLDN"));
                }
            }

        大部分情况下对于集合的使用可能没有这么多的复杂要求，
            更多的情况下就是利用集合保存数据
            要么进行输出要么进行查询
    
        面试题：请解释Collection与Collections的区别
            Collection是集合接口，允许保存单值对象
            Collections是集合操作的工具类
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class CollectionsToolsDemo {
    public static void main(String[] args) throws Exception {
        List<String> all = new ArrayList<String>();
        Collections.addAll(all, "Hello", "World", "MLDN");
        Collections.sort(all); // 先进行排序处理
        System.out.println(all);
        System.out.println(Collections.binarySearch(all, "MLDN"));
    }
}