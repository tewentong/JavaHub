
/*
    ArrayList保存自定义类
        通过之前的分析已经清楚了ArrayList子类的实现原理以及List核心操作，但是在测试的时候使用的是
            系统提供的String类，这是一个设计非常完善的类，而对于类集而言也可以实现自定义类对象的保存
        范例：实现自定义类对象的保存
            import java.util.ArrayList;
            import java.util.List;

            class Person0317 {
                private String name;
                private int age;

                public Person0317(String name, int age) {
                    this.name = name;
                    this.age = age;
                }

                // setter、getter全略
                public String toString() {
                    return "姓名： " + this.name + "、 年龄： " + this.age;
                }
            }

            public class ArrayListSaveCustomClassDemo {
                public static void main(String[] args) throws Exception {
                    List<Person0317> all = new ArrayList<Person0317>();
                    all.add(new Person0317("张三", 30));
                    all.add(new Person0317("李四", 16));
                    all.add(new Person0317("小强", 78));
                    System.out.println(all.contains(new Person0317("小强", 78))); // false
                    all.forEach(System.out::println); // 方法引用代替了消费型的接口
                    System.out.println("--------------------------------");
                    all.remove(new Person0317("小强", 78));
                    all.forEach(System.out::println); // 方法引用代替了消费型的接口
                }
            }
            程序输出：
                false
                姓名： 张三、 年龄： 30
                姓名： 李四、 年龄： 16
                姓名： 小强、 年龄： 78
                --------------------------------
                姓名： 张三、 年龄： 30
                姓名： 李四、 年龄： 16
                姓名： 小强、 年龄： 78
            在使用List保存自定义对象的时候如果需要使用到contains()、remove()方法进行查询与删除处理的时候
                一定要保证类之中已经成功复写了equals()方法
        范例：复写euqls()方法之后的自定义类对象的保存
            import java.util.ArrayList;
            import java.util.List;

            class Person0317 {
                private String name;
                private int age;

                public Person0317(String name, int age) {
                    this.name = name;
                    this.age = age;
                }

                @Override
                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null) {
                        return false;
                    }
                    if (!(obj instanceof Person0317)) {
                        return false;
                    }
                    Person0317 per = (Person0317) obj;
                    return this.name.equals(per.name) && this.age == per.age;
                }

                // setter、getter全略
                public String toString() {
                    return "姓名： " + this.name + "、 年龄： " + this.age;
                }
            }

            public class ArrayListSaveCustomClassDemo {
                public static void main(String[] args) throws Exception {
                    List<Person0317> all = new ArrayList<Person0317>();
                    all.add(new Person0317("张三", 30));
                    all.add(new Person0317("李四", 16));
                    all.add(new Person0317("小强", 78));
                    System.out.println(all.contains(new Person0317("小强", 78))); // false
                    all.forEach(System.out::println); // 方法引用代替了消费型的接口
                    System.out.println("--------------------------------");
                    all.remove(new Person0317("小强", 78));
                    all.forEach(System.out::println); // 方法引用代替了消费型的接口
                }
            }
            程序输出：
                true
                姓名： 张三、 年龄： 30
                姓名： 李四、 年龄： 16
                姓名： 小强、 年龄： 78
                --------------------------------
                姓名： 张三、 年龄： 30
                姓名： 李四、 年龄： 16 
                
*/
import java.util.ArrayList;
import java.util.List;

class Person0317 {
    private String name;
    private int age;

    public Person0317(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Person0317)) {
            return false;
        }
        Person0317 per = (Person0317) obj;
        return this.name.equals(per.name) && this.age == per.age;
    }

    // setter、getter全略
    public String toString() {
        return "姓名： " + this.name + "、 年龄： " + this.age;
    }
}

public class ArrayListSaveCustomClassDemo {
    public static void main(String[] args) throws Exception {
        List<Person0317> all = new ArrayList<Person0317>();
        all.add(new Person0317("张三", 30));
        all.add(new Person0317("李四", 16));
        all.add(new Person0317("小强", 78));
        System.out.println(all.contains(new Person0317("小强", 78))); // false
        all.forEach(System.out::println); // 方法引用代替了消费型的接口
        System.out.println("--------------------------------");
        all.remove(new Person0317("小强", 78));
        all.forEach(System.out::println); // 方法引用代替了消费型的接口
    }
}