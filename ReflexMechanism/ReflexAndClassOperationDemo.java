/*
    Java反射编程
        反射与类操作
            在反射机制的处理过程之中不仅仅只是一个实例化对象的处理操作，更多的情况下还有类的组成结构操作
            任何一个类的基本组成结构：父类（父接口）、包、属性、方法（构造方法、普通方法）
            获取类的基本信息：
                一个类的基本信息主要包括类所在的包名称、父类的定义、父接口的定义
            范例：定义一个程序类
                interface IMessageService {
                    public void send();
                }

                interface IChannelService {
                    public boolean connect();
                }

                abstract class AbstractBase {

                }

                class Person0312 extends AbstractBase implements IMessageService, IChannelService {
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

                public class ReflexAndClassOperationDemo {
                    public static void main(String[] args) throws Exception {
                        Class<?> cls = Person0312.class; // 获取指定类的Class对象
                        System.out.println(cls);
                        
                    }
                }
                
            如果此时想要获得类的一些基础信息则可以通过Class类中的如下方法：
                获取包名称：public Package getPackage();
                获取继承父类：public Class<? super T> getSuperclass();
                获取实现父接口：public Class<?>[] getInterfaces();

            范例：获得包名称
                Class<?> cls = Person0312.class; // 获取指定类的Class对象
                Package pack = cls.getPackage(); // 获取指定类的包定义
                System.out.println(pack.getName());
            范例：获取父类信息
                Class<?> cls = Person0312.class; // 获取指定类的Class对象
                Class<?> parent = cls.getSuperclass();
                System.out.println(parent.getName());   //AbstractBase
                System.out.println(parent.getSuperclass().getName());   //java.lang.Object
                System.out.println(parent.getSuperclass().getSuperclass().getName());   //java.lang.NullPointerException
            范例：获取父接口
                Class<?> cls = Person0312.class; // 获取指定类的Class对象
                Class<?> clazz[] = cls.getInterfaces();
                for (Class<?> temp : clazz) {
                    System.out.println(temp.getName());
                }

        当获取了一个类的Class对象之后就意味着这个对象可以获取类之中的一切继承结构信息
*/
interface IMessageService {
    public void send();
}

interface IChannelService {
    public boolean connect();
}

abstract class AbstractBase {

}

class Person0312 extends AbstractBase implements IMessageService, IChannelService {
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

public class ReflexAndClassOperationDemo {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Person0312.class; // 获取指定类的Class对象
        Class<?> clazz[] = cls.getInterfaces();
        for (Class<?> temp : clazz) {
            System.out.println(temp.getName());
        }
    }
}