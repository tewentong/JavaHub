
/*
    自定义Annotation
        现在已经清除了Annotation的获取，以及Annotation的运行策略，但是最为关键性的因素是，
        如何可以实现自定义的Annotation呢？
        为此，在java里面提供有新的语法，使用“@interface”来定义Annotation
    范例：自定义Annotation
        import java.lang.annotation.Retention;
        import java.lang.annotation.RetentionPolicy;
        import java.lang.reflect.InvocationTargetException;
        import java.lang.reflect.Method;

        @Retention(RetentionPolicy.RUNTIME) // 定义Annotation的运行策略
        @interface DefaultAnnotation { // 自定义的Annotation
            public String title(); // 获取数据，

            public String url() default "www.mldn.cn"; // 获取数据，默认值
        }

        class Message0316 {
            @DefaultAnnotation(title = "MLDN")
            public void send(String msg) {
                System.out.println("【消息发送】" + msg);
            }
        }

        public class CustomAnnotationDemo {
            public static void main(String[] args) throws Exception {
                Method method = Message0316.class.getMethod("send", String.class);
                DefaultAnnotation anno = method.getAnnotation(DefaultAnnotation.class); // 获取指定的Annotation
                System.out.println(anno.title()); // 直接调用Annotation中的方法
                System.out.println(anno.url()); // 直接调用Annotation中的方法
                System.out.println("---------------------------");
                String msg = anno.title() + "(" + anno.url() + ")"; // 消息内容
                method.invoke(Message0316.class.getDeclaredConstructor().newInstance(), msg);
            }
        }
        程序输出：
            MLDN
            www.mldn.cn
            ---------------------------
            【消息发送】MLDN(www.mldn.cn)

        使用Annotation之后的最大特点就是可以结合反射机制实现程序的处理
*/
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME) // 定义Annotation的运行策略
@interface DefaultAnnotation { // 自定义的Annotation
    public String title(); // 获取数据，

    public String url() default "www.mldn.cn"; // 获取数据，默认值
}

class Message0316 {
    @DefaultAnnotation(title = "MLDN")
    public void send(String msg) {
        System.out.println("【消息发送】" + msg);
    }
}

public class CustomAnnotationDemo {
    public static void main(String[] args) throws Exception {
        Method method = Message0316.class.getMethod("send", String.class);
        DefaultAnnotation anno = method.getAnnotation(DefaultAnnotation.class); // 获取指定的Annotation
        System.out.println(anno.title()); // 直接调用Annotation中的方法
        System.out.println(anno.url()); // 直接调用Annotation中的方法
        System.out.println("---------------------------");
        String msg = anno.title() + "(" + anno.url() + ")"; // 消息内容
        method.invoke(Message0316.class.getDeclaredConstructor().newInstance(), msg);
    }
}