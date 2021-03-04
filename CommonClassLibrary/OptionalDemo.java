/*
    Optional类主要进行null的相关处理，在以前进行程序开发的时候，如果为了防止程序之中出现空指向异常
    往往可以追加有null的验证
    范例：传统的引用传递问题
        interface IMessage0304 {
            public String getContent();
        }
        class MessageImpl implements IMessage0304 {
            @Override
            public String getContent() {
                return "www.mldn.cn";
            }
        }
        class MessageUtil {
            private MessageUtil() {}
            public static IMessage0304 getMessage() {
                return null;
            }
            public static void useMessage(IMessage0304 msg) {
                if (msg != null) {
                    System.out.println(msg.getContent());   //有可能因为出现null，而导致空指向异常
                }
            }
        }
        public class OptionalDemo {
            public static void main(String[] args) throws Exception {
                MessageUtil.useMessage(MessageUtil.getMessage());
            }
        }

    在引用接收的一方往往都是被动的进行判断，所以为了解决这种被动的处理操作
    在Java类中提供有一个Optional类，这个类可以实现空的处理操作
    在这个类里面提供有如下的操作方法：
        1.返回空数据：public static <T> Optional<T> empty();
        2.获取数据： public T get();
        3.保存数据，但是不允许出现null: public static <T> Optional<T> of(T value);
            如果在保存数据的时候存在着null，则会抛出NullPointerException异常
        4.保存数据，允许为空：public static <T> Optional<T> ofNullable(T value);
        5.空的时候返回其他数据：public T orElse(T other);
    范例：修改程序，按正规的结构完成：
        import java.util.Optional;
        interface IMessage0304 {
            public String getContent();
        }
        class MessageImpl implements IMessage0304 {
            @Override
            public String getContent(){
                return "www.mldn.cn";
            }
        }
        class MessageUtil {
            private MessageUtil() {}
            public static Optional<IMessage0304> getMessage() {
                return Optional.of(new MessageImpl());  //有对象
            }
            public static void useMessage(IMessage0304 msg) {
                if (msg != null) {
                    System.out.println(msg.getContent());
                }
            }
        }
        public class OptionalDemo {
            public static void main(String[] args) throws Exception {
                IMessage0304 temp = MessageUtil.getMessage().get();
                MessageUtil.useMessage(temp);
            }
        }
    
    在所有的引用数据类型的操作处理之中，null是一个重要的技术问题，所以JDK1.8后提供的这个新的类对于null的处理很有帮助
    同时也是在日后项目开发过程之中使用次数很多的一个程序类
*/

/*
interface IMessage0304 {
public String getContent();
}
class MessageImpl implements IMessage0304 {
@Override
public String getContent() {
    return "www.mldn.cn";
}
}
class MessageUtil {
private MessageUtil() {}
public static IMessage0304 getMessage() {
    return null;
}
public static void useMessage(IMessage0304 msg) {
    if (msg != null) {
        System.out.println(msg.getContent());   //有可能因为出现null，而导致空指向异常
    }
}
}
public class OptionalDemo {
public static void main(String[] args) throws Exception {
    MessageUtil.useMessage(MessageUtil.getMessage());
}
}
*/
import java.util.Optional;
interface IMessage0304 {
    public String getContent();
}
class MessageImpl implements IMessage0304 {
    @Override
    public String getContent(){
        return "www.mldn.cn";
    }
}
class MessageUtil {
    private MessageUtil() {}
    public static Optional<IMessage0304> getMessage() {
        return Optional.of(new MessageImpl());  //有对象
    }
    public static void useMessage(IMessage0304 msg) {
        if (msg != null) {
            System.out.println(msg.getContent());
        }
    }
}
public class OptionalDemo {
    public static void main(String[] args) throws Exception {
        IMessage0304 temp = MessageUtil.getMessage().get();
        MessageUtil.useMessage(temp);
    }
}
//如果说现在保存的数据是null，就会在保存处出现异常
/*
    Exception in thread "main" java.lang.NullPointerException
        at java.util.Objects.requireNonNull(Objects.java:203)
        at java.util.Optional.<init>(Optional.java:96)
        at java.util.Optional.of(Optional.java:108)
        at MessageUtil.getMessage(OptionalDemo.java:111)
        at OptionalDemo.main(OptionalDemo.java:121)
*/