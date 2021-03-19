/*
    关于KEY的定义：
        在使用Map集合的时候可以发现对于Key和Value的类型实际上都可以由使用者任意决定
            那么也就意味着现在可以用自定义的类来进行Key类型的设置
            public put(K key, V value) {
                return putVal(hash(key), key, value, false, true);
            }
            在进行数据的保存的时候发现会自动使用传入的key的数据生成一个hash码，也就是说存储的时候有hash数值
            
            public V get(Object key) {
                Node<K, V> e;
                return (e = getNode(hash(key), key)) == null ? null : e.value;
            }
            根据key获取数据的时候依然要将传入的key通过hash()的方法来获取其对应的hash码
                那么也就证明在查询的过程之中首先要利用hashCode()来进行数据查询
                当使用getNode()方法进行查询的时候，还需要使用到equals()方法

        对于自定义key类型所在的类中一定要复写hashCode()与equals()方法，否则无法查找
        范例：使用自定义类作为key类型
            import java.util.HashMap;
            import java.util.Map;
            import java.util.Set;

            class Person0319 {
                private String name;
                private int age;

                public Person0319(String name, int age) {
                    this.name = name;
                    this.age = age;
                }

                @Override
                public int hashCode() {
                    final int prime = 31;
                    int result = 1;
                    result = prime * result + age;
                    result = prime * result + ((name == null) ? 0 : name.hashCode());
                    return result;
                }

                @Override
                public boolean equals(Object obj) {
                    if (this == obj)
                        return true;
                    if (obj == null)
                        return false;
                    if (getClass() != obj.getClass())
                        return false;
                    Person0319 other = (Person0319) obj;
                    if (age != other.age)
                        return false;
                    if (name == null) {
                        if (other.name != null)
                            return false;
                    } else if (!name.equals(other.name))
                        return false;
                    return true;
                }
            }

            public class CustomKeyOfMapDemo {
                public static void main(String[] args) throws Exception {
                    Map<Person0319, String> map = new HashMap<Person0319, String>();
                    map.put(new Person0319("小强", 78), "林弱"); // 使用自定义类作为key
                    System.out.println(map.get(new Person0319("小强", 78))); // 通过key找到value
                }
            }
            程序输出：
                林弱

            虽然允许你使用自定义的类作为key的类型，但是也需要注意一点
                在实际的开发之中对于Map集合的Key常用的类型就是：String、Long、Integer
                尽量使用系统类
    
        面试题：如果在进行HashMap进行数据操作的时候出现了Hash冲突（Hash码相同），HashMap是如何解决的？
            当出现了Hash冲突之后为了保证程序的正常执行，会在冲突的位置上将所有Hash冲突的内容转为链表保存
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Person0319 {
    private String name;
    private int age;

    public Person0319(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person0319 other = (Person0319) obj;
        if (age != other.age)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}

public class CustomKeyOfMapDemo {
    public static void main(String[] args) throws Exception {
        Map<Person0319, String> map = new HashMap<Person0319, String>();
        map.put(new Person0319("小强", 78), "林弱"); // 使用自定义类作为key
        System.out.println(map.get(new Person0319("小强", 78))); // 通过key找到value

    }
}