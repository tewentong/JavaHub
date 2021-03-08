/*
    Java IO编程
    File文件操作类

    在Java语言里面提供有对于文件操作系统的支持，而这个支持就在java.io.File类中进行了定义
    也就是在整个的java.io包里面，File类是唯一一个与文件本身操作（创建、删除、重命名等等）有关的类
    而如果想要进行File类的操作，我们必须要提供有完整的路径，而后才可以调用相应的方法进行处理

    File类的基本使用
        打开JDK文档可以发现，File类是Comparable接口的子类，所以File类的对象是可以进行排序处理的
        而在进行File类处理的时候，需要为其设置访问路径，对于路径的配置主要通过File类的构造方法处理
            构造方法：public File(String pathname); //设置要操作完整路径
            构造方法：public File(File parent, String child);   //设置父路径与子目录
        如果现在想要进行文件的基本操作，可以使用如下的反法：
            创建新的文件：public boolean createNewFile() throws IOException;
            判断文件是否存在：public boolean exists();
            删除文件：public boolean exists();
    范例：使用File类创建一个文件
        import java.io.File;
        public class FileDemo {
            public static void main(String [] args) throws Exception {
                File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/mldn.txt");
                if (file.exists()) {
                    file.delete();
                } else {
                    System.out.println(file.createNewFile());
                }
            }
        }
*/
import java.io.File;
public class FileDemo {
    public static void main(String [] args) throws Exception {
        File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/mldn.txt");
        if (file.exists()) {
            file.delete();
        } else {
            System.out.println(file.createNewFile());
        }
    }
}