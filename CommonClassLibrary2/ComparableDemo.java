
/*
    比较器：进行大小关系的确定判断
    分析比较器存在的意义：
        如果要进行数组操作，肯定要使用java.util.Arrays的操作类完成，这个类里面提供有绝大部分的数组操作支持
        同时在这个类里面还提供有一种对象数组的排序支持：public static void sort(Object[] a);
        范例：实现对象数组的排序
            Integer data[] = new Integer[] { 10, 9, 5, 2, 20 }; // 对象数组
            Arrays.sort(data); // 进行对象数组的排序
            System.out.println(Arrays.toString(data));
        同样，如果现在给定的是一个String型的对象数组，那么也是可以进行排序处理的
        范例：String的对象数组排序
            String data [] = new String[] {"X", "B", "A", "E", "G"};
            Arrays.sort(data);
            System.out.println(Arrays.toString(data));
        java.lang.Integer与java.lang.String两个类都是由系统提供的程序类，那么如果说现在有一个自定义的类需要实现排序处理呢？
        范例：采用自定义类型进行排序
            任意的一个类默认情况下是使用系统内部的类实现数组排序或比较需求的，
            是因为没有明确的指定出到底该如何进行比较的定义（没有比较规则）
            那么这个时候在Java里面为了统一比较规则的定义，所以提供有比较器的接口：Comparable
*/
import java.util.Arrays;
class  Person0305{
    private String name;
    private int age;
    public Person0305(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //无参构造、setter、getter略
    @Override
    public String toString() {
        return "【Person类对象】姓名： " + this.name + "、年龄： " + this.age + "\n";
    }
}
public class ComparableDemo {
    public static void main(String[] args) throws Exception {
        /*
        Integer data[] = new Integer[] { 10, 9, 5, 2, 20 }; // 对象数组
        Arrays.sort(data); // 进行对象数组的排序
        System.out.println(Arrays.toString(data));
        */
        /*
        String data [] = new String[] {"X", "B", "A", "E", "G"};
        Arrays.sort(data);
        System.out.println(Arrays.toString(data));
        */
        Person0305 data [] = new Person0305 [] {
            new Person0305("小强-A", 80),
            new Person0305("小强-B", 50),
            new Person0305("小强-C",100)
        };
        Arrays.sort(data);
        System.out.println(Arrays.toString(data));
        /*
            运行时异常：
            Exception in thread "main" java.lang.ClassCastException: Person0305 cannot be cast to java.lang.Comparable
                at java.util.ComparableTimSort.countRunAndMakeAscending(ComparableTimSort.java:320)
                at java.util.ComparableTimSort.sort(ComparableTimSort.java:188)
                at java.util.Arrays.sort(Arrays.java:1246)
                at ComparatorDemo.main(ComparatorDemo.java:51)
        */
    }
}