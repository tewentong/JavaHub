
/*
    Java反射编程
        反射与Annotation
            从JDK1.5之后Java开发提供了Annotation的技术支持，这种技术为项目的编写带来的新的模型，
            而后经过十多年的发展，Annotation技术得到了非常广泛的应用，并且在所有的项目开发之中都会存在
        获取Annotation信息：
            在进行类或方法定义的时候都可以使用一系列的Annotation进行声明，于是如果要想获取这些Annotation的信息，
            那么就可以直接通过反射来完成，
            在java.lang.reflect里面有一个AccessibleObject类，在本类中提供有获取Annotation类的方法：
                获取全部Annotation:public Annotation[] getAnnotations();
                获取指定Annotation:public <T extends Annotation> T getAnnotation(Class<T> annotationClass);
        范例：定义一个接口，并且在接口上使用Annotation
            import java.lang.annotation.Annotation;
            import java.io.Serializable;
            import java.lang.reflect.Method;

            @FunctionalInterface
            @Deprecated(since = "1.0")
            interface IMessage0316 { // 有两个Annotation
                public void send(String msg);
            }

            @SuppressWarning("serial") // 无法在程序执行的时候获取
            class MessageImpl0316 implements IMessage0316, Serializable {
                @Override   // 无法在程序执行的时候获取
                public void send(String msg) {
                    System.out.println("【消息发送】" + msg);
                }
            }

            public class ReflectAndAnnotationDemo {
                public static void main(String[] args) throws Exception {
                    { // 获取接口上的Annotation信息
                        Annotation annotations[] = IMessage0316.class.getAnnotations(); // 获取接口上的全部的Annotation
                        for (Annotation temp : annotations) {
                            System.out.println(temp);
                        }
                    }
                    System.out.println("------------------------------");
                    { // 获取MessageImpl子类上的Annotation
                        Annotation annotations[] = MessageImpl0316.class.getAnnotations();
                        for (Annotation temp : annotations) {
                            System.out.println(temp);
                        }
                    }
                    System.out.println("--------------------------------");
                    { // 获取MessageImpl0316.toString()方法上的Annotation
                        Method method = MessageImpl0316.class.getDeclaredMethod("send", String.class);
                        Annotation annotations[] = method.getAnnotations(); // 获取接口上的全部Annotation
                        for (Annotation temp : annotations) {
                            System.out.println(temp);
                        }
                    }
                }
            }
            程序输出：
                @java.lang.FunctionalInterface()
                @java.lang.Deprecated()
                ------------------------------
                --------------------------------
            不同的Annotation有它的存在的范围
                下面对比两个Annotation
                @FunctionalInterface(运行时):
                    @Documented
                    @Retention(RetentionPolicy.RUNTIME)
                    @Target(ElementType.TYPE)
                    public @interface FunctionalInterface{}
                @SuppressWarnings(源代码):
                    @Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE,MODULE})
                    @Retention(RetentionPolicy.SOURCE)
                    public @interface SuppressWarnings
                现在发现“@FunctionalInterface”是在运行时生效的Annotation，所以当我们程序执行时可以获取此Annotation
                而“@SuppressWarnings”是在源代码编写的时候有效
                而在RetentionPolicy枚举类中还有一个class的定义，指的是在类定义的时候生效
*/
import java.lang.annotation.Annotation;
import java.io.Serializable;
import java.lang.reflect.Method;

@FunctionalInterface
@Deprecated(since = "1.0")
interface IMessage0316 { // 有两个Annotation
    public void send(String msg);
}

@SuppressWarning("serial") // 无法在程序执行的时候获取
class MessageImpl0316 implements IMessage0316, Serializable {
    @Override // 无法在程序执行的时候获取
    public void send(String msg) {
        System.out.println("【消息发送】" + msg);
    }
}

public class ReflectAndAnnotationDemo {
    public static void main(String[] args) throws Exception {
        { // 获取接口上的Annotation信息
            Annotation annotations[] = IMessage0316.class.getAnnotations(); // 获取接口上的全部的Annotation
            for (Annotation temp : annotations) {
                System.out.println(temp);
            }
        }
        System.out.println("------------------------------");
        { // 获取MessageImpl子类上的Annotation
            Annotation annotations[] = MessageImpl0316.class.getAnnotations();
            for (Annotation temp : annotations) {
                System.out.println(temp);
            }
        }
        System.out.println("--------------------------------");
        { // 获取MessageImpl0316.toString()方法上的Annotation
            Method method = MessageImpl0316.class.getDeclaredMethod("send", String.class);
            Annotation annotations[] = method.getAnnotations(); // 获取接口上的全部Annotation
            for (Annotation temp : annotations) {
                System.out.println(temp);
            }
        }
    }
}