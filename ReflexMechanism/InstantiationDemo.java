/*
    Java反射编程
    Class类对象的三种实例化模式
        反射之中所有的核心操作都是通过Class类对象展开的，可以说Class类是反射操作的根源所在，
            但是这个类如果要想获取它的实例化对象，可以采用三种方式完成
        java.lang.Class类的定义：
            public final Class<T> extends Object implements Serializable, GenericDeclaration, Type, AnnotatedElement
            从JDK1.5开始Class类在定义的时候可以使用泛型进行标记，这样的用法主要是希望可以避免所谓的向下转型
        下面通过具体的操作讲解三种实例化形式：
            1.【Object类支持】Object类可以根据实例化对象获取Class对象：public final Class<?>getClass();
                这种方式有一个不是缺点的缺点：如果现在只是想获得Class类对象，则必须产生指定类对象以后才可以获得
                class Person0312 {  //采用自定义的程序类
                }
                public class InstantiationDemo {
                    public static void main(String [] args) throws Exception {
                        Person0312 per = new Person0312();  //已经存在有指定类的实例化对象
                        Class cls = per.getClass();         // 获取类的完整名称
                        System.out.println(cls.getName());
                        Class<? extends Person0312> cls1 = per.getClass();   
                        System.out.println(cls1.getName());
                        Class<?> cls2 = per.getClass();
                        System.out.println(cls2.getName());
                    }    
                }
            2.【JVM直接支持】采用“类.class”的形式实例化
                特点：如果要采用此种模式，则必须导入程序所对应的开发包
                class Person0312 { // 采用自定义的程序类
                }
                public class InstantiationDemo {
                    public static void main(String[] args) throws Exception {
                        Class<? extends Person0312> cls = Person0312.class;
                        System.out.println(cls.getName()); // 获取的是类的完整名称
                    }
                }
            3.【Class类支持】在Class类里面提供有一个static方法：
                加载类：public static Class<?>forName(String className) throws ClassNotFoundException;
                
                public class InstantiationDemo {
                    public static void main(String[] args) throws Exception {
                        Class<?> cls = Class.forName("Person.Person0312");
                        System.out.println(cls.getName()); // Person.Person0312
                    }
                }
                这种模式最大的特点是可以直接采用字符串的形式定义要使用的类型，并且程序中不需要编写任何的import语句
                如果此时要使用的程序类不存在，则会抛出异常“java.lang.ClassNotFoundException”异常
*/

public class InstantiationDemo {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("Person.Person0312");
        System.out.println(cls.getName()); // Person.Person0312
    }
}