
/*
    Vector子类：
        Vector是一个原始古老的程序类，这个类在JDK1.0的时候就提供的
        而后到了JDK1.2的时候由于许多的开发者已经习惯于使用Vector，并且许多的系统类也是基于Vector实现的
        考虑到其使用的广泛性，所以类集框架将其保存了下来，并且让其实现了一个List的接口
        观察Vector的定义结构：
            public class Vector<E> extends AbstractList<E> implements
                List<E>, RandomAccess, Cloneable, Serializable
            继承结构与ArrayList是相同的，这个类继承结构如下
        范例：Vector使用
            import java.util.List;
            import java.util.Vector;

            public class VectorDemo {
                public static void main(String[] args) throws Exception {
                    List<String> all = new Vector<String>(); // 为List父接口进行实例化
                    all.add("Hello");
                    all.add("Hello");
                    all.add("World");
                    all.add("MLDN");
                    all.forEach(System.out::println);
                }
            }
            下面可以进一步地观察Vector类的实现：
                public Vector() {
                    this(10);
                }
                public Vector(int initialCapacity) {
                    this(initialCapacity, 0);
                }
                public Vector(int initialCapacity, int capacityIncrement) {
                    super();
                    if (initialCapacity < 0)
                        throw new IllegalArgumentException("Illegal Capacity :
                            + initialCapacity");
                    this.elementData = new Object[initialCapacity];
                    this.capacityIncrement = capacityIncrement;
                }
                Vector类如果使用的是无参构造方法，则一定会默认开辟10个长度的数组
                而后，其余的实现操作与ArrayList是相同的
            
                public synchronized boolean add(E e) {
                    modCount ++;
                    add(e, elementData, elementCount);
                    return true;
                }
                通过源代码的分析可以发现Vector类之中的操作方法采用的都是synchronized同步处理，
                而ArrayList并没有进行同步处理，
                所以Vector类之中的方法在多线程访问的时候属于线程安全的，但是性能不如ArrayList高
*/
import java.util.List;
import java.util.Vector;

public class VectorDemo {
    public static void main(String[] args) throws Exception {
        List<String> all = new Vector<String>(); // 为List父接口进行实例化
        all.add("Hello");
        all.add("Hello");
        all.add("World");
        all.add("MLDN");
        all.forEach(System.out::println);
    }
}