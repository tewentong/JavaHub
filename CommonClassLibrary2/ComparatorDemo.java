/*
    Comparator属于一种挽救的比较器支持，其主要的目的是解决一些没有使用Comparable排序的对象数组排序操作
    范例：现在程序项目已经开发完成了，并且由于先期的设计并没有去考虑到所谓的比较器
         后来经过了若干版本的更新迭代之后发现需要对Person类进行排序处理，但是又不能够去修改Person类（无法实现Comparable接口）
         所以这个时候就需要一种挽救的形式来实现比较，
         在Arrays里面，排序有另外一种实现：
            基于Comparator的排序处理：public static <T> void sort(T[] a, Comparator<? super T> c);
    在java.util.Comparator里面最初只定义有一个排序的compare()方法：public int compare(T o1, T o2);
        但是后来持续发展又出现了许多static方法
    范例：定义排序规则类
        class PersonComparator implements Comparator<Person030503> {
            @Override
            public int compare(Person030503 p1, Person030503 p2) {
                return p1.getAge() - p2.getAge();
            }
        }
    
    对于这种排序的操作如果不是必须的情况下强烈不建议使用Comparator，最好以Comparable为主

    面试题：请解释Comparable与Comparator的区别
        java.lang.Comparable是在类定义的时候实现的父接口，主要用于定义排序规则，里面只有一个compareTo()方法
        java.util.Comparator是挽救的比较器操作，需要设置单独的比较器规则类实现排序，里面有compare()方法
*/
import java.util.Arrays;
import java.util.Comparator;
class Person030503 {
    private String name;
    private int age;
    public Person030503(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
        return "【Person类对象】姓名： " +this.name + "、年龄： " + this.age + "\n";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
class PersonComparator implements Comparator<Person030503> {
    @Override
    public int compare(Person030503 p1, Person030503 p2) {
        return p1.getAge() - p2.getAge();
    }
}
public class ComparatorDemo {
    public static void main(String[] args) throws Exception {
        Person030503 data [] = new Person030503 [] {
            new Person030503("小强-A", 80),
            new Person030503("小强-B", 50),
            new Person030503("小强-C", 100)
        };
        Arrays.sort(data,new PersonComparator());
        System.out.println(Arrays.toString(data));
    }
}