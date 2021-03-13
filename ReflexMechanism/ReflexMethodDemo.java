
/*
    获取方法：
        在进行反射处理的时候也可以通过反射来获取类之中的全部方法，但是需要提醒的，如果要想通过反射调用这些方法
            必须要有一个前提条件：类之中要提供有实例化对象
        在Class类里面提供有如下的操作可以获取方法对象：
            获取全部方法：public Method[] getMethods() throws SecutityException
            获取指定方法：public Method getMethod(String name, Class<?>... parameterTypes)
                                        throws NoSuchMethodException, SecurityException
            获取本类全部方法：public Method[] getDeclaredMethods() throws SecurityException
            获取本类指定方法：public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
                                        throws NoSuchMethodException, SecurityException
        范例：获取全部方法
            public class ReflexMethodDemo {
                public static void main(String[] args) throws Exception {
                    Class<?> cls = Person031302.class; // 获取指定类的Class对象
                    { // 获取全部方法(包括父类中的方法)
                        Method methods[] = cls.getMethods();
                        for (Method met : methods) {
                            System.out.println(met);
                        }
                    }
                    System.out.println("-----------------------------------------");
                    { // 获取本类方法
                        Method methods[] = cls.getDeclaredMethods();
                        for (Method met : methods) {
                            System.out.println(met);
                        }
                    }
                }
            }
            程序输出：
                public void Person031302.send()
                public java.lang.String Person031302.toString()
                public boolean Person031302.connect()
                public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
                public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
                public final void java.lang.Object.wait() throws java.lang.InterruptedException
                public boolean java.lang.Object.equals(java.lang.Object)
                public native int java.lang.Object.hashCode()
                public final native java.lang.Class java.lang.Object.getClass()
                public final native void java.lang.Object.notify()
                public final native void java.lang.Object.notifyAll()
                -----------------------------------------
                public void Person031302.send()
                public java.lang.String Person031302.toString()
                public boolean Person031302.connect()
                但是需要注意的是，这个时候的方法信息的获取是依靠Method类提供的toString()方法完成的
                    很多时候也可以由用户自己来拼凑方法信息的展示形式：
            
            范例：自定义方法信息显示
                public class ReflexMethodDemo {
                    public static void main(String[] args) throws Exception {
                        Class<?> cls = Person031302.class; // 获取指定类的Class对象
                        { // 获取全部方法(包括父类中的方法)
                            Method methods[] = cls.getMethods();
                            for (Method met : methods) {
                                int mod = met.getModifiers(); // 修饰符
                                System.out.print(Modifier.toString(mod) + " ");
                                System.out.print(met.getReturnType().getName() + " "); // 返回值类型
                                System.out.print(met.getName() + "("); // 方法名
                                Class<?> params[] = met.getParameterTypes(); // 获取参数类型
                                for (int x = 0; x < params.length; x++) {
                                    System.out.print(params[x].getName() + " " + "arg-" + x);
                                    if (x < params.length - 1) {
                                        System.out.print(",");
                                    }
                                }
                                System.out.print(")");
                                Class<?> exp[] = met.getExceptionTypes();
                                if (exp.length > 0) {
                                    System.out.print(" throws ");
                                }
                                for (int x = 0; x < exp.length; x++) {
                                    System.out.print(exp[x].getName());
                                    if (x < exp.length - 1) {
                                        System.out.print(", ");
                                    }
                                }
                                System.out.println(); // 换行
                            }
                        }
                    }
                }
                这种代码你只需要清楚可以根据反射获取方法的结构即可，不需要做过多的深入了解，
                    但是在Method类里面，有一个致命的重要方法：反射调用类中的方法
                        public Object invoke(Object obj, Object... args) 
                                        throws IllegalAccessException,
                                                IllegalArgumentException,
                                                InvocationTargetException
                    在Person类里面为name属性追加有setter、getter方法
                        class Person031302 extends AbstractBase031302 implements IMessageService031302, IChannelService031302 {
                            private String name;
                            private int age;
                            public Person031302() {
                            }
                            public Person031302(String name, int age) {
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
                    随后需要通过反射机制来实现Person类之中的setter、getter方法的调用处理

        范例：在不导入指定类开发包的情况下实现属性的配置
            public class ReflexMethodDemo {
                public static void main(String[] args) throws Exception {
                    Class<?> cls = Class.forName("Person031302"); // 获取指定类的Class对象
                    String value = "小强子"; // 要设置的属性内容
                    // 1.任何情况下如果要想保存类中的属性或者调用类中的方法都必须保证存在有实例化对象，现在既然不允许导入包，那么就反射实例化
                    Object obj = cls.getDeclaredConstructor().newInstance(); // 调用无参构造实例化
                    // 2.如果要想进行方法的调用，那么一定要获取方法的名称
                    String setMethodName = "setName"; // 方法名称
                    Method setMethod = cls.getDeclaredMethod(setMethodName, String.class); // 获取指定的方法
                    setMethod.invoke(obj, value); // 等价于： Person对象.setName(value);
                    String getMethodName = "getName";
                    Method getMethod = cls.getDeclaredMethod(getMethodName); // getter没有参数
                    System.out.println(getMethod.invoke(obj)); // 等价于：Person对象.getName()
                }
            }
        利用此类操作整体的形式上不会任何的明确的类对象产生，一切都是依靠反射机制处理的，这样的处理避免了与某一个类的耦合问题

*/
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

interface IMessageService031302 {
    public void send();
}

interface IChannelService031302 {
    public boolean connect();
}

abstract class AbstractBase031302 {
    public AbstractBase031302() {
    }

    public AbstractBase031302(String msg) {
    }
}

class Person031302 extends AbstractBase031302 implements IMessageService031302, IChannelService031302 {
    private String name;
    private int age;

    public Person031302() {
    }

    public Person031302(String name, int age) {
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

public class ReflexMethodDemo {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("Person031302"); // 获取指定类的Class对象
        String value = "小强子"; // 要设置的属性内容
        // 1.任何情况下如果要想保存类中的属性或者调用类中的方法都必须保证存在有实例化对象，现在既然不允许导入包，那么就反射实例化
        Object obj = cls.getDeclaredConstructor().newInstance(); // 调用无参构造实例化
        // 2.如果要想进行方法的调用，那么一定要获取方法的名称
        String setMethodName = "setName"; // 方法名称
        Method setMethod = cls.getDeclaredMethod(setMethodName, String.class); // 获取指定的方法
        setMethod.invoke(obj, value); // 等价于： Person对象.setName(value);
        String getMethodName = "getName";
        Method getMethod = cls.getDeclaredMethod(getMethodName); // getter没有参数
        System.out.println(getMethod.invoke(obj)); // 等价于：Person对象.getName()
    }
}
