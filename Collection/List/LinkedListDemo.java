
/*
    LinkList子类
        在List接口里面还有另外一个比较常用的子类，LinkedList，这个类通过名称就已经可以发现其特点了：基于链表的实现
        那么首先来观察一下LinkedList子类的定义：
            public class LinkedList<E> extends AbstractSequentialList<E> implements
                List<E>, Deque<E>, Cloneable, Serializable
        范例:使用LinkedList实现集合操作
            import java.util.ArrayList;
            import java.util.LinkedList;
            import java.util.List;

            public class LinkedListDemo {
                public static void main(String[] args) throws Exception {
                    List<String> all = new LinkedList<String>(); // 为List父接口进行实例化
                    all.add("Hello");
                    all.add("Hello");
                    all.add("World");
                    all.add("MLDN");
                    all.forEach(System.out::println);
                }
            }
            如果说现在只是观察程序的功能你会发现和ArrayList使用是完全一样的，但是其内部的实现机制是完全不同的
                首先LinkedList构造方法里面并没有提供有像ArrayList那样的初始化大小的方法，
                    而只是提供有无参的构造处理：public LinkedList();
                随后观察add()方法的具体实现：
                    public boolean add(E e) {
                        LinkLast(e);
                        return true;
                    }
                    在之前编写自定义链表的时候，是判断了传入数据是否为null,如果为null则不进行保存，
                    但是在LinkedList里面并没有做这样的处理，而是所有的数据都可以保存
                    而后此方法调用了LinkedLast()方法，//在最后一个节点之后追加
                    void linkLast(E e) {
                        final Node<E> l = last;
                        final Node<E> newNode = new Node<>(1, e, null);
                        last = newNode;
                        if (l == null)
                            first = newNode;
                        else 
                            l.next = newNode;
                        size ++;
                        modCount ++;
                    }
                    在LinkedList类里面保存的数据都是利用Node节点进行的封装处理
                    同时为了提高程序执行的性能，每一次都会保存上一个追加的节点（最后一个节点）
                    就可以在增加数据的时候避免递归处理
                    在增加数据的时候要进行数据保存个数的追加
            通过一系列的分析之后就会发现，LinkedList封装的就是一个链表实现
        
        面试题：请问ArrayList与LinkedList有什么区别？
            ArrayList是数组实现的集合操作，而LinkedList是链表实现的集合操作
            在使用List集合中的get()方法根据索引获取数据时，
                ArrayList的时间复杂度为O(1),而LinkedList时间复杂度为O(n)
            ArrayList在使用的时候默认的初始化对象数组的大小长度为10，如果空间不足则会采用2倍的形式进行容量的扩充
                如果保存大数据量的时候有可能会造成垃圾的产生以及性能的下降
                但是这个时候可以使用LinkedList子类保存
*/
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {
    public static void main(String[] args) throws Exception {
        List<String> all = new LinkedList<String>(); // 为List父接口进行实例化
        all.add("Hello");
        all.add("Hello");
        all.add("World");
        all.add("MLDN");
        all.add(null);
        System.out.println(all.get(4)); // 根据索引取数据
        all.forEach(System.out::println);
    }
}
