
/*
    获取成员
        类结构之中的最后一个核心的组成就是成员(Field),大部分情况下都会将其成为成员属性，对于成员信息的获取也是通过Class类完成的
        在这个类中提供有如下两组操作方法：
            获取本类全部成员：public Field[] getDeclaredFields() throws SecurityException；
            获取本类指定成员：public Filed getDeclaredField(String name) throws NoSuchFieldException, SecurityException
            获取父类全部成员：public Field[] getFields() throws SecurityException
            获取父类指定成员：public Field getField(String name) throws NoSuchFieldException, SecurityException
        范例：修改要操作的父类的结构
            abstract class AbstractBase031303 {
                protected static final String BASE = "www.mldn.cn";
                private String info = "Hello MLDN";
                public AbstractBase031303() {}
                public AbstractBase031303(String msg) {}
            }
            interface IMessageService031303 {
                public void send();
            }
            interface IChannelService031303 {
                public static final String NAME = "mldnjava";
                public boolean connect();
            }
            class Person031303 extends AbstractBase031303 implements IMessageService031303, IChannelService031303 {
                private String name;
                private int age;

                public Person031303() {
                }

                public Person031303(String name, int age) {
                    this.name = name;
                    this.age = age;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getName() {
                    return this.name;
                }

                public void setAge(int age) {
                    this.age = age;
                }

                public int getAge() {
                    return this.age;
                }

                @Override
                public String toString() {
                    return "姓名： " + this.name + "、年龄： " + this.age;
                }

                @Override
                public boolean connect() {
                    return true;
                }

                @Override
                public void send() {
                    if (this.connect()) {
                        System.out.println("【信息发送】www.mldn.cn");
                    }
                }
            }

        范例：获取类中的成员
            public class ReflexFieldDemo {
                public static void main(String[] args) throws Exception {
                    Class<?> cls = Class.forName("Person031303"); // 获取指定类的Class对象
                    { // 获取父类之中的公共成员信息
                        Field fields[] = cls.getFields(); // 获取成员
                        for (Field fie : fields) {
                            System.out.println(fie);
                        }
                    }
                    System.out.println("-------------------------------------");
                    { // 获取子类中定义的成员
                        Field fields[] = cls.getDeclaredFields();
                        for (Field fie : fields) {
                            System.out.println(fie);
                        }
                    }
                }
            }
            程序输出：
                public static final java.lang.String IChannelService031303.NAME
                -------------------------------------
                private java.lang.String Person031303.name
                private int Person031303.age
            但是在Field类里面最重要的操作形式不是获取全部的成员，而是如下的三个方法
            1.设置属性内容：public void set(Object obj, Object value) 
                    throws IllegalArgumentException, IllegalAccessException
            2.获取属性内容：public Object get(Object obj) 
                    throws IllegalArgumentException, IllegalAccessException
            3.解除封装：public void setAccessible(boolean flag);
            所有的成员都是在对象实例化之后进行空间分配的，所以此时一定要先有实例化对象之后才可以进行成员的操作
        范例：直接调用Person类中的name私有成员
            public class ReflexFieldDemo {
                public static void main(String[] args) throws Exception {
                    Class<?> cls = Class.forName("Person031303"); // 获取指定类的Class对象
                    Object obj = cls.getConstructor().newInstance(); // 实例化对象（分配成员空间）
                    Field nameField = cls.getDeclaredField("name"); // 获取成员对象
                    nameField.setAccessible(true); // 没有封装了
                    nameField.set(obj, "番茄强"); // 等价于 Person对象.name = "番茄强";
                    System.out.println(nameField.get(obj));
                }
            }
            通过一系列的分析可以发现，类之中的构造、方法、成员属性都可以通过反射实现调用，但是对于成员的反射调用很少直接处理
                大部分操作都应该通过setter、getter来处理，所以说以上的代码只能说是反射的一个特色，但是不具有实际的使用能力
            而对于Field类在实际开发之中只有一个方法最为常用：
                获取成员类型：public Class<?>getType();
        范例：获取Person类中的name成员类型
            public class ReflexFieldDemo {
                public static void main(String[] args) throws Exception {
                    Class<?> cls = Class.forName("Person031303"); // 获取指定类的Class对象
                    Field nameField = cls.getDeclaredField("name"); // 获取成员对象
                    System.out.println(nameField.getType().getName()); // 获取完整类名称 包.类
                    System.out.println(nameField.getType().getSimpleName()); // 获取类名称
                }
            }
            程序输出：
                java.lang.String
                String              

*/
import java.lang.reflect.Field;

abstract class AbstractBase031303 {
    protected static final String BASE = "www.mldn.cn";
    private String info = "Hello MLDN";

    public AbstractBase031303() {
    }

    public AbstractBase031303(String msg) {
    }
}

interface IMessageService031303 {
    public void send();
}

interface IChannelService031303 {
    public static final String NAME = "mldnjava";

    public boolean connect();
}

class Person031303 extends AbstractBase031303 implements IMessageService031303, IChannelService031303 {
    private String name;
    private int age;

    public Person031303() {
    }

    public Person031303(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return "姓名： " + this.name + "、年龄： " + this.age;
    }

    @Override
    public boolean connect() {
        return true;
    }

    @Override
    public void send() {
        if (this.connect()) {
            System.out.println("【信息发送】www.mldn.cn");
        }
    }
}

public class ReflexFieldDemo {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("Person031303"); // 获取指定类的Class对象
        Field nameField = cls.getDeclaredField("name"); // 获取成员对象
        System.out.println(nameField.getType().getName()); // 获取完整类名称 包.类
        System.out.println(nameField.getType().getSimpleName()); // 获取类名称
    }
}