
/*
    Java类集简介：
        List集合接口
            List是Collection子接口，其最大的特点是允许保存有重复元素数据，该接口的定义如下：
                public interface List<E> extends Collection<E>
                需要清楚的是List子接口对于Collection接口进行了方法扩充
            List中方法：
                public E get(int index) 普通 获取指定索引上的数据
                public E set(int index, E element) 普通 修改指定索引数据
                public ListIterator<E> listIterator() 普通 返回ListIterator接口对象
            但是List本身依然属于一个接口，那么对于接口要想使用则一定要使用子类来完成定义
                在List子接口中有三个常用子类：ArrayList、Vector、LinkedList

            从JDK1.9开始List子接口里面追加有一些static方法，以方便用户的处理
                范例：观察Lsit中的静态方法
                    import java.util.List;

                    public class ListInterfaceDemo {
                        public static void main(String[] args) throws Exception {
                            List<String> all = List.of("Hello", "World", "你好", "MLDN", "饿了么"); // 这是JDK1.9提供的支持
                            System.out.println(all);

                            Object result [] = all.toArray();
                            for (Object temp : result) {
                                System.out.println(temp + "、");
                            }
                        }
                    }
                    这些操作方法并不是List传统用法，是在新版本之后添加的新功能
*/
import java.util.List;

public class ListInterfaceDemo {
    public static void main(String[] args) throws Exception {
        List<String> all = List.of("Hello", "World", "你好", "MLDN", "饿了么"); // 这是JDK1.9提供的支持
        System.out.println(all);

        Object result[] = all.toArray();
        for (Object temp : result) {
            System.out.println(temp + "、");
        }
    }
}