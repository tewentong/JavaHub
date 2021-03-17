
/*
    ArrayList子类
        ArrayList是List子接口使用最多的一个子类，但是这个子类在使用的时候也是有前提要求的
            所以本次来对这个类的相关定义及源代码组成进行分析，在Java里面ArrayList的定义如下：
            public class ArrayList<E> extends AbstractList<E> implements
                List<E>, RandomAccess, Cloneable, Serializable
        范例：使用ArrayList实例化List父接口：
            import java.util.ArrayList;
            import java.util.List;

            public class ArrayListDemo {
                public static void main(String[] args) {
                    List<String> all = new ArrayList<String>(); // 为List父接口进行实例化
                    all.add("Hello");
                    all.add("Hello");
                    all.add("World");
                    all.add("MLDN");
                    System.out.println(all);
                }
            }
            程序输出：
                [Hello, Hello, World, MLDN]
            通过本程序可以发现List存储的一个特征：
                1.保存的顺序就是其存储顺序
                2.List集合里面允许存在有重复数据
            在以上的程序里面虽然实现了集合的输出，但是这种输出的操作是直接利用了每一个类提供的toString()方法实现的
            为了方便地进行输出处理，在JDK1.8之后Iterable父接口之中定义有一个forEach()方法，方法定义如下：
                输出支持：default void froEach(Consumer<? super T>action);
        范例：利用forEach()方法输出    //不是标准输出
            import java.util.ArrayList;
            import java.util.List;

            public class ArrayListDemo {
                public static void main(String[] args) {
                    List<String> all = new ArrayList<String>(); // 为List父接口进行实例化
                    all.add("Hello");
                    all.add("Hello");
                    all.add("World");
                    all.add("MLDN");
                    all.forEach((str) -> {
                        System.out.println(str + "、");
                    });
                }
            }
            需要注意的是：此种输出并不是在正常开发情况下要考虑的操作形式
        
        范例：观察List集合的其他操作方法：
            import java.util.ArrayList;
            import java.util.List;

            public class ArrayListDemo {
                public static void main(String[] args) {
                    List<String> all = new ArrayList<String>(); // 为List父接口进行实例化
                    System.out.println("集合是否为空？" + all.isEmpty() + "、集合元素个数：" + all.size());
                    all.add("Hello");
                    all.add("Hello"); // 重复数据
                    all.add("World");
                    all.add("MLDN");
                    all.remove("Hello"); // 删除元素
                    System.out.println("集合是否为空？" + all.isEmpty() + "、集合元素个数：" + all.size());
                    all.forEach((str) -> {
                        System.out.println(str + "、");
                    });
                }
            }
            如果以方法的功能为例，那么ArrayList里面操作支持与之前编写的链表形式是非常相似的，但是它并不是使用链表来实现的
            通过类名称实际上就已经可以清楚的发现了，ArrayList应该封装的是一个数组
            ArrayList构造： public ArrayList();
                public ArrayList() {
                    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
                }

                private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {}
            ArrayList构造：public ArrayList(int initialCapacity);
                public ArrayList(int initialCapacity) {
                    if (initialCapacity > 0) {
                        this.elementData = new Object[initialCapacity];
                    } else if (initialCapacity == 0) {
                        this.elementData = EMPTY_ELEMENTDATA;
                    } else {
                        throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
                    }
                }

                transient Object[] elementData; //不能被序列化的对象数组
            通过有参构造方法可以发现，在ArrayList里面所包含的数据实际上就是一个对象数组
                如果现在在进行数据追加的时候发现ArrayList集合里面保存的对象数组的长度不够的时候：
                    那么会进行新的数组开辟，同时将原始的旧数组内容拷贝到新数组之中
                    而后，数组的开辟操作：
                        int oldCapacity = elementData.length;
                        int newCapacity = oldCapacity + (oldCapacity >> 1);
                        if (newCapacity - minCapacity <= 0) {
                            if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                                return Math.max(DEFAULT_CAPACITY, minCapacity)
                            if (minCapacity < 0)    //overflow
                                throw new OutOfMemoryError();
                            return minCapacity;
                        }
                        return (newCapacity - MAX_ARRAY_SIZE <= 0) ? newCapacity : hugeCapacity(minCapacity);
                    如果在实例化ArrayList类对象的时候并没有传递初始化的长度，则默认情况下会使用一个空数组
                        但是，如果在进行数据增加的时候发现数组容量不够了
                        则会判断当前的增长的容量与默认的容量的大小，使用较大的一个数值进行新的数组开辟
                        所以可以得出一个结论：
                            JDK1.9以后，ArrayList默认的构造只会使用默认的空数组，使用的时候才会开辟数组
                                默认的开辟长度为10
                            JDK1.9之前，ArrayList默认的构造实际上就会开辟大小为10的数组
                    当ArrayList之中保存的容量不足的时候会采用成倍的方式进行增长，原始的长度为10，那么下次的怎长就是20，依次翻倍
                        在使用ArrayList子类的时候一定要估算出你的数据量有多少，如果超过了10个，那么使用由参构造方法进行创建
                        以避免垃圾数组的空间产生
*/
import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
        List<String> all = new ArrayList<String>(); // 为List父接口进行实例化
        System.out.println("集合是否为空？" + all.isEmpty() + "、集合元素个数：" + all.size());
        all.add("Hello");
        all.add("Hello"); // 重复数据
        all.add("World");
        all.add("MLDN");
        all.remove("Hello"); // 删除元素
        System.out.println("集合是否为空？" + all.isEmpty() + "、集合元素个数：" + all.size());
        all.forEach((str) -> {
            System.out.println(str + "、");
        });
    }
}