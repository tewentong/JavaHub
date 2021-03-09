/*
    字节输出流：OutputStream
    字节的数据是以byte类型为主实现的操作，在进行字节内容的输出的时候可以使用OutputStream类来完成，这个类的基本定义如下：
        pulic abstract class OutputStream extends Object implements Closeable,Flushable;
        首先可以发现这个类实现了两个接口，于是基本的对应关系如下：
            Closeable:
                public interface Closeable extends AutoCloseable {
                    public void close() throws IOException;
                }
            Flushable:
                public interface Flushable {
                    public void flush() throws IOException;
                }
        OutputStream类定义的是一个公共的输出操作标准，而在这个标准里面一共定义有三个内容输出的方法
            1.public abstract void write(int b) throws IOException; //普通，输出单个字节数据
            2.public void write(byte[] b) throws IOExcepion;    //普通，输出一组字节数据
            3.public void write(byte[] b, int off, int len) throws IOException; //普通，输出部分字节数据
        但是需要注意的一个核心问题在于：OutputStream类毕竟是一个抽象类，
            而这个抽象类如果要想获得实例化对象，按照传统的认识应该通过子类对象的向上转型完成
            如果现在要进行的是文件处理操作，则可以使用FileOutputStream子类
        因为最终都需要发生向上转型的处理关系，所以对于此时的FileOutputStream子类核心的关注点就可以放在构造方法上
            【覆盖】构造方法：public FileOutputStream(File file) throws FilNotFoundException
            【追加】构造方法：public FileOutputStream(File file, boolean append) throws FileNotFoundException
    范例：使用OutputStream类实现内容的输出
        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.OutputStream;
        public class OutputStreamDemo {
            public static void main(String [] args) throws Exception {
                File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3" + File.separator + 
                    "test" + File.separator + "mldn.txt");//1.指定了要操作文件的路径
                if (!file.getParentFile().exists()) {    //文件不存在
                    file.getParentFile().mkdirs();       //创建父目录
                }
                OutputStream output = new FileOutputStream(file);   //2.通过子类实例化
                String str = "www.mldn.cn";     //要输出的文件内容
                output.write(str.getBytes());   //3.将字符串变为字节数组
                output.close(); //4.关闭资源
            }
        }
        本程序是使用了最为标准的形式实现了输出的操作处理，
        并且在整体的处理之中，只是创建了文件的父路径，但是并没有创建文件
        而我们在执行后会发现文件可以自动帮助用户创建
    
    另外需要注意的是：OutputStream子类也属于AutoCloseable接口子类，所以对于close()方法也可以简化使用
    范例：自动关闭处理
        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.OutputStream;
        import java.io.IOException;
        public class OutputStreamDemo {
            public static void main(String [] args) throws Exception {
                File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3" + File.separator + 
                    "test" + File.separator + "mldn.txt");//1.指定了要操作文件的路径
                if (!file.getParentFile().exists()) {    //文件不存在
                    file.getParentFile().mkdirs();       //创建父目录
                }
                try (OutputStream output = new FileOutputStream(file)) {
                    String str = "www.mldn.cn"; //要输出的文件内容
                    output.write(str.getBytes());   //将字符串变为字节数足并输出
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        是否使用自动的关闭处理取决你项目的整体结构，另外还需要提醒大家的是：
            整个的程序里面最终是输出了一组的字节数据，但是千万不要忘记了OutputStream类之中定义的输出方法一共有三个
*/
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;
public class OutputStreamDemo {
    public static void main(String [] args) throws Exception {
        File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3" + File.separator + 
            "test" + File.separator + "mldn.txt");//1.指定了要操作文件的路径
        if (!file.getParentFile().exists()) {    //文件不存在
            file.getParentFile().mkdirs();       //创建父目录
        }
        try (OutputStream output = new FileOutputStream(file, true)) {
            String str = "www.mldn.cn\r\n"; //要输出的文件内容
            output.write(str.getBytes());   //将字符串变为字节数足并输出
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}