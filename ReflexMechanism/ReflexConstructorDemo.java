
/*
    反射调用
        获取构造方法：在一个类之中除了有继承的关系之外最为重要的操作就是类中的结构处理了，
                而类中的结构首先需要观察的就是构造方法的使用问题，之前通过反射实例化对象的时候就已经接触到了构造方法的问题了
                实例化方法替代：clazz.getDeclaredConstructor().newInstance()
            所有类的构造方法的获取都可以直接通过Class类来完成，该类中定义有如下的几个方法：
                获取所有构造方法：public Constructor<?>[] getDeclaredConstructors() throws SecurityException;
                获取指定构造方法：public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)
                                                            throws NoSuchMethodException, SecurityException
                获取所有构造方法：public Constructor<?>[] getConstructors() throws SecurityException
                获取指定构造方法：public Constructor<T> getConstructor(Class<?>... parameterTypes) 
                                                            throws NoSuchMethodException, SecurityException
        范例：修改Person类的定义
            现在父类和子类都拥有构造方法（无参、有参）
            import java.lang.reflect.Constructor;
            interface IMessageService0313 {
                public void send();
            }

            interface IChannelService0313 {
                public boolean connect();
            }

            abstract class AbstractBase0313 {
                public AbstractBase0313() {
                }

                public AbstractBase0313(String msg) {
                }
            }

            class Person0313 extends AbstractBase0313 implements IMessageService0313, IChannelService0313 {
                public Person0313() {
                }

                public Person0313(String name, int age) {
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
        范例：获取构造
            public class ReflexConstructorDemo {
                public static void main(String[] args) throws Exception {
                    Class<?> cls = Person0313.class; // 获取指定的Class对象
                    Constructor<?>[] constructors = cls.getDeclaredConstructors(); // 获取全部构造
                    for (Constructor<?> cons : constructors) {
                        System.out.println(cons);
                    }
                }
            }

            //cls.getDeclaredConstructors();
            程序输出：
                public Person0313()
                public Person0313(java.lang.String,int)
            //cls.getConstructors();
            程序输出：
                public Person0313()
                public Person0313(java.lang.String,int)
            此时获取的是类之中的全部构造方法，但是也可以获取一个指定参数的构造
            例如：现在的Person类之中提供有两个构造方法：
                class Person0313 extends AbstractBase0313 implements IMessageService0313, IChannelService0313 {
                    private String name;
                    private int age;
                    public Person0313() {
                    }

                    public Person0313(String name, int age) {
                        this.name = name;
                        this.age = age;
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
                此时程序打算调用Person类之中的有参构造方法进行Person类对象的实例化处理
                这个时候就必须指明要调用的构造，而后通过Constructor类中提供的实例化方法操作：
                    public T newInstance(Object... initargs)
                                throws InstantiationException,
                                        IllegalAccessException,
                                        IllegalArgumentException,
                                        InvocationTargetException
        范例：调用指定构造实例化对象
            public class ReflexConstructorDemo {
                public static void main(String[] args) throws Exception {
                    Class<?> cls = Person0313.class; // 获取指定的Class对象
                    Constructor<?> constructor = cls.getConstructor(String.class, int.class);
                    Object obj = constructor.newInstance("小强", 10); // 实例化对象
                    System.out.println(obj);
                }
            }
            程序输出：
                姓名： 小强、年龄： 10

        虽然程序本身允许开发者调用由参构造处理，但是如果从实际的开发来讲，所有使用反射的类中最好提供有无参构造
            因为这样的实例化可以达到统一性
        
*/
import java.lang.reflect.Constructor;

interface IMessageService0313 {
    public void send();
}

interface IChannelService0313 {
    public boolean connect();
}

abstract class AbstractBase0313 {
    public AbstractBase0313() {
    }

    public AbstractBase0313(String msg) {
    }
}

class Person0313 extends AbstractBase0313 implements IMessageService0313, IChannelService0313 {
    private String name;
    private int age;

    public Person0313() {
    }

    public Person0313(String name, int age) {
        this.name = name;
        this.age = age;
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

public class ReflexConstructorDemo {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Person0313.class; // 获取指定的Class对象
        Constructor<?> constructor = cls.getConstructor(String.class, int.class);
        Object obj = constructor.newInstance("小强", 10); // 实例化对象
        System.out.println(obj);
    }
}