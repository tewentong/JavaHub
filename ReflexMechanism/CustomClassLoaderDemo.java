
/*
    自定义类加载器
        清楚了类加载器的功能之后就可以根据自身的需要来实现自定义的类加载器
        但是千万要记住一点：自定义的类加载器加载的顺序是在所有系统类加载器的最后
        系统类中的类加载器都是根据CLASSPATH路径进行类加载的，而如果有了自定义类的加载器，就可以由开发者任意指派类的加载位置
        1.随意编写一个程序类，并且将这个类保存在磁盘上
            class Message031502 {
                public void send() {
                    System.out.println("www.mldn.cn");
                }
            }
        2.将此类直接拷贝到D盘上进行编译处理，并且不打包
            ctrl c + v
            javac Message031502.class
            此时并未进行打包处理，所以这个类无法通过CLASSPATH正常加载
        3.自定义一个类加载器，并且继承自ClassLoader类
            在ClassLoader类里面为用户提供有一个字节转换为类结构的方法:
                定义类：protected final Class<?> define(String name, byte[] b, int off, int len) throws ClassFormatError
        范例：自定义类加载器的控制操作
            class MLDNClassLoader extends ClassLoader {
                private static final String MESSAGE_CLASS_PATH = "D:" + File.separator + "Message031502.class";

                **
                 * 进行指定类的加载
                 * 
                 * @param className 类的完整名称“包.类”
                 * @return 返回一个指定类的Class对象
                 * @throws Exception 如果类文件不存在，则无法加载
                 *
                public Class<?> loadData(String className) throws Exception {
                    byte data[] = this.loadClassData(); // 读取二进制数据文件
                    if (data != null) { // 读取到了
                        return super.defineClass(className, data, 0, data.length);
                    }
                    return null;
                }

                private byte[] loadClassData() throws Exception { // 通过文件进行类的加载
                    InputStream input = null;
                    ByteArrayOutputStream bos = null; // 将数据加载到内存之中
                    byte data[] = null;
                    try {
                        bos = new ByteArrayOutputStream(); // 实例化内存流
                        input = new FileInputStream(new File(MESSAGE_CLASS_PATH)); // 文件流加载
                        input.transferTo(bos); // 读取数据
                        data = bos.toByteArray(); // 将所有读取到的字节数据直接取出
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (input != null) {
                            input.close();
                        }
                        if (bos != null) {
                            bos.close();
                        }
                    }
                    return data;
                }
            }
        4.编写测试类，实现类加载控制
            public class CustomClassLoaderDemo {
                public static void main(String[] args) throws Exception {
                    MLDNClassLoader classLoader = new MLDNClassLoader(); // 实例化自定义类的加载器
                    Class<?> cls = classLoader.loadData("cn.mlcn.demo.Message031502"); // 进行类的加载
                    System.out.println(cls);
                    Object obj = cls.getDeclaredConstructor().newInstance();
                    Method method = cls.getDeclaredMethod("send");
                    method.invoke(obj);
                }
            }

    如果在以后结合到网络程序开发的话，就可以通过一个远程的服务器来确定类的功能

        如果说你现在自定义了一个类，这个类的名字为：java.lang.String，并且利用了自定义的类加载进行加在处理，
        这个类将不会被加载，java之中针对于类加载器提供有双亲加载机制
        如果现在要加载的程序类是由系统提供的类则会由系统类进行加载
        如果开发者定义的类与系统类名称相同，那么为了保证系统的安全性绝对不会加载

*/
import java.io.File;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.lang.reflect.Method;

class Message031502 {
    public void send() {
        System.out.println("www.mldn.cn");
    }
}

class MLDNClassLoader extends ClassLoader {
    private static final String MESSAGE_CLASS_PATH = "D:" + File.separator + "Message031502.class";

    /**
     * 进行指定类的加载
     * 
     * @param className 类的完整名称“包.类”
     * @return 返回一个指定类的Class对象
     * @throws Exception 如果类文件不存在，则无法加载
     */
    public Class<?> loadData(String className) throws Exception {
        byte data[] = this.loadClassData(); // 读取二进制数据文件
        if (data != null) { // 读取到了
            return super.defineClass(className, data, 0, data.length);
        }
        return null;
    }

    private byte[] loadClassData() throws Exception { // 通过文件进行类的加载
        InputStream input = null;
        ByteArrayOutputStream bos = null; // 将数据加载到内存之中
        byte data[] = null;
        try {
            bos = new ByteArrayOutputStream(); // 实例化内存流
            input = new FileInputStream(new File(MESSAGE_CLASS_PATH)); // 文件流加载
            input.transferTo(bos); // 读取数据
            data = bos.toByteArray(); // 将所有读取到的字节数据直接取出
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
        return data;
    }
}

public class CustomClassLoaderDemo {
    public static void main(String[] args) throws Exception {
        MLDNClassLoader classLoader = new MLDNClassLoader(); // 实例化自定义类的加载器
        Class<?> cls = classLoader.loadData("cn.mlcn.demo.Message031502"); // 进行类的加载
        System.out.println(cls);
        Object obj = cls.getDeclaredConstructor().newInstance();
        Method method = cls.getDeclaredMethod("send");
        method.invoke(obj);
    }
}