
/*
    序列化与反序列化
        有了序列化的支持类以后如果要想实现序列化与反序列化的操作,则就可以利用以下两个类完成
            序列化:ObjectOutputStream
                类定义:public class ObejctOutputStream extends OutputStream implements ObjectOutput,ObjectStreamConstants
                构造方法:public ObjectOutputStream(OutputStream out) throws IOException
                操作方法:public final void writeObject(Object obj) throws IOException
            反序列化:ObjectInputStream
                类定义:public class ObjectInputStream extends InputStream implements ObjectInput,ObjectStreamConstants
                构造方法:public ObjectInputStream(InputStream in) throws IOException
                操作方法:public final Obejct readObject() throws IOException,ClassNotFoundException
        范例:实现序列化与反序列化
            import java.io.File;
            import java.io.Serializable;
            import java.io.ObjectOutputStream;
            import java.io.FileOutputStream;
            import java.io.ObjectInputStream;
            import java.io.FileInputStream;
            @SuppressWarnings("serial")
            class Person031102 implements Serializable {
                private String name;
                private int age;
                public Person031102(String name, int age) {
                    this.name = name;
                    this.age = age;
                }
                // setter、getter方法略
                @Override
                public String toString() {
                    return "姓名： " + this.name + "年龄： " + this.age;
                }
            }
            public class ObjectStreamDemo {
                private static final File SAVE_FILE = new File(
                        "/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.person");
                public static void main(String[] args) throws Exception {
                    // saveObject(new Person031102("小喷嚏", 78));
                    System.out.println(loadObject()); // 姓名： 小喷嚏年龄： 78
                }
                public static void saveObject(Object obj) throws Exception {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE));
                    oos.writeObject(obj); // 序列化
                    oos.close();
                }
                public static Object loadObject() throws Exception {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE));
                    Object obj = ois.readObject(); // 反序列化
                    ois.close();
                    return obj;
                }
            }
            在Java中的对象序列化与反序列化必须使用内部提供的对象操作流，因为这里面牵扯到二进制数据的格式
                所以不能够自定义处理，另外如果要想实现一组对象的序列化，则可以使用对象数组完成
            在很多的实际的项目开发过程之中，开发者很少能够见到ObjectOutputStream、ObjectInputStream类的直接操作，
                因为会有一些容器帮助开发者自动实现
*/
import java.io.File;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

@SuppressWarnings("serial")
class Person031102 implements Serializable {
    private String name;
    private int age;

    public Person031102(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // setter、getter方法略
    @Override
    public String toString() {
        return "姓名： " + this.name + "年龄： " + this.age;
    }
}

public class ObjectStreamDemo {
    private static final File SAVE_FILE = new File(
            "/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.person");

    public static void main(String[] args) throws Exception {
        // saveObject(new Person031102("小喷嚏", 78));
        System.out.println(loadObject()); // 姓名： 小喷嚏年龄： 78
    }

    public static void saveObject(Object obj) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE));
        oos.writeObject(obj); // 序列化
        oos.close();
    }

    public static Object loadObject() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE));
        Object obj = ois.readObject(); // 反序列化
        ois.close();
        return obj;
    }
}