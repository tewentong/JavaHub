/*
    通过分析可以发现如果要实现对象的比较肯定需要比较器来指定比较的规则，而比较的规则就通过Comparable来实现
    对于Comparable而言，需要清楚其基本的定义结构：
        public interface Comparable<T> {
            *
            * 实现对象的比较处理操作
            * @param o 要比较的对象
            * @return  当前数据比传入的对象小返回负数，如果大于返回正数、如果等于返回0
            *
            public int compareTo(T o);
        }

    范例：实现自定义对象数组排序操作
        import java.util.Arrays;
        import java.lang.Comparable;
        class Person030502 implements Comparable<Person030502>{
            private String name;
            private int age;
            public Person030502 (String name, int age) {
                this.name = name;
                this.age = age;
            }
            @Override
            public int compareTo(Person030502 per) {
                return this.age - per.age;
            }
            //无参构造、setter、getter省略
            public String toString() {
                return "【Person类对象】姓名： " + this.name + "、年龄：" + this.age + "\n";
            }
        }
        public class ComparableDemo2 {
            public static void main(String [] args) throws Exception{
                Person030502 data [] = new Person030502 [] {
                    new Person030502("小强-A", 80),
                    new Person030502("小强-B", 50),
                    new Person030502("小强-C", 100)};
                Arrays.sort(data);  //进行对象数组的排序
                System.out.println(Arrays.toString(data));
            }
        }
    排序里面只需要有一个compareTo()方法进行排序规则的定义，而后整个java系统里面就可以为其实现排序处理了
*/
import java.util.Arrays;
import java.lang.Comparable;
/*
    public interface Comparable<T> {
        *
        * 实现对象的比较处理操作
        * @param o 要比较的对象
        * @return  当前数据比传入的对象小返回负数，如果大于返回正数、如果等于返回0
        *
        public int compareTo(T o);
    }
*/
class Person030502 implements Comparable<Person030502>{
    private String name;
    private int age;
    public Person030502 (String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public int compareTo(Person030502 per) {
        return this.age - per.age;
    }
    //无参构造、setter、getter省略
    public String toString() {
        return "【Person类对象】姓名： " + this.name + "、年龄：" + this.age + "\n";
    }
}
public class ComparableDemo2 {
    public static void main(String [] args) throws Exception{
        Person030502 data [] = new Person030502 [] {
            new Person030502("小强-A", 80),
            new Person030502("小强-B", 50),
            new Person030502("小强-C", 100)};
        Arrays.sort(data);  //进行对象数组的排序
        System.out.println(Arrays.toString(data));
    }
}