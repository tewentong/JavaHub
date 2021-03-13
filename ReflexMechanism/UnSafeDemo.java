
/*
    UnSafe工具类
        反射是Java的第一大特点，一旦打开了反射的大门就可以有更加丰富的类设计形式
        除了JVM本身支持的反射的处理之外，在Java里面也提供有一个sun.misc.UnSafe类（不安全的操作），
            这个类的主要特点是可以利用反射来获取对象，并直接使用底层的C++来代替JVM执行
            即可以绕过JVM的相关的对象的管理机制，如果你一旦使用了UnSafe类，
                那么你的项目之中将无法继续使用JVM的内存管理机制以及垃圾回收处理
        但是如果要想使用UnSafe类首先就要确认一下这个类之中定义的构造方法与常量问题：
            构造方法：private Unsafe() {}
            私有常量：private static final Unsafe theUnsafe = new Unsafe();
            但是需要注意的是，在这个UnSafe类里面并没有提供static方法
                即不能够通过类似于传统的单例设计模式之中提供的样式来进行操作
                如果要想获得这个类的对象，就必须利用反射机制来完成。
        范例：利用反射机制获取Unsafe对象
            import java.lang.reflect.Field;
            import sun.misc.Unsafe;

            public class UnSafeDemo {
                public static void main(String[] args) throws Exception {
                    Field field = Unsafe.class.getDeclaredField("theUnsafe");
                    field.setAccessible(true); // 解除封装处理
                    Unsafe unsafeObject = (Unsafe) field.get(null); // static属性不需要传递实例化对象
                }
            }
            在传统的开发之中，一个程序类必须要通过实例化对象后才可以调用类中的普通方法，尤其是以单例设计模式为例
        范例：单例设计模式
            class Singleton0313 {
                private static final Singleton0313 INSTANCE = new Singleton0313();

                private Singleton0313() {
                    System.out.println("***********Singleton类构造****************");
                }

                public static Singleton0313 getInstance() {
                    return INSTANCE;
                }

                public void print() {
                    System.out.println("www.mldn.cn");
                }
            }

            public class UnSafeDemo {
                public static void main(String[] args) throws Exception {
                    Singleton0313.getInstance().print();
                }
            }
            程序输出：
                ***********Singleton类构造****************
                www.mldn.cn
            ----------------------------------------------------
        范例：使用Unsafe类绕过实例化对象的管理
            import java.lang.reflect.Field;
            import sun.misc.Unsafe;

            class Singleton0313 {
                private Singleton0313() {
                    System.out.println("***********Singleton类构造****************");
                }
                public void print() {
                    System.out.println("www.mldn.cn");
                }
            }

            public class UnSafeDemo {
                public static void main(String[] args) throws Exception {
                    Field field = Unsafe.class.getDeclaredField("theUnsafe");
                    field.setAccessible(true); // 解除封装处理
                    Unsafe unsafeObject = (Unsafe) field.get(null); // static属性不需要传递实例化对象
                    // 利用Unsafe类绕过了JVM的管理机制，可以在没有实例化对象的情况下获取一个Singleton类实例化对象
                    Singleton0313 instance = (Singleton0313) unsafeObject.allocateInstance(Singleton0313.class);
                    instance.print();
                }
            }
            程序输出：
                www.mldn.cn

        Unsafe只能够说为我们的开发提供了一些更加方便的处理机制，但是这种操作由于不受JVM的管理，
            所以如果不是必须情况下不建议使用，而讲解这个类主要的目的是帮助大家巩固对于反射的理解，
            同时也帮助大家在笔试的时候如果有人问到你单例设计模式的情况下
            也可以追加一个Unsafe以加深你对这一个概念的理解

*/
import java.lang.reflect.Field;
import sun.misc.Unsafe;

class Singleton0313 {

    private Singleton0313() {
        System.out.println("***********Singleton类构造****************");
    }

    public void print() {
        System.out.println("www.mldn.cn");
    }
}

public class UnSafeDemo {
    public static void main(String[] args) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true); // 解除封装处理
        Unsafe unsafeObject = (Unsafe) field.get(null); // static属性不需要传递实例化对象
        // 利用Unsafe类绕过了JVM的管理机制，可以在没有实例化对象的情况下获取一个Singleton类实例化对象
        Singleton0313 instance = (Singleton0313) unsafeObject.allocateInstance(Singleton0313.class);
        instance.print();
    }
}