/*
    字节输入流：InputStream
    与OutputStream类对应的一个流就是字节输入流，InputStream类主要实现的就是字节数据读取，该类定义如下：
        public abstract class InputStream extends Object implements Closeable;
        在InputStream类里面定义有如下的几个核心方法：
            1.public abstract int read() throws IOException;    //普通，读取单个字节数据
                //如果现在已经读取到底，返回-1，表示文件已经读取完成了
            2.public int read(byte [] b) throws IOException;    //普通，读取一组字节数据
                //返回的是读取的个数，如果数据已经读取到底，返回-1
            3.public int read(byte [] b, int off, int len) throws IOException;  //普通，读取一组字节数据的部分内容
        InputStream类属于一个抽象类，这时应该依靠它的子类来实例化对象，如果要从文件读取，一定使用FileInputStream子类
            于子类而言，我们只关心父类对象实例化
            构造方法：public FileInputStream(File file)throws FileNotFoundException;
        范例：读取数据
            import java.io.File;
            import java.io.FileInputStream;
            import java.io.InputStream;
            public class InputStreamDemo {
                public static void main(String [] args) throws Exception {
                    File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt");
                    InputStream input = new FileInputStream(file);
                    byte data [] = new byte [1024]; //开辟一个缓冲区读取数据
                    int len = input.read(data);   //读取数据，数据全部保存在字节数组之中
                    System.out.println("【" + new String(data, 0 ,len) + "】");
                    input.close();
                }
            }
        
        对于字节输入流里面最为麻烦的问题就在于：使用read()方法读取的时候，只能够以字节数组为主进行接受
        特别需要注意的是，从JDK1.9开始在InputStream类里面增加了一个新的方法：
            public byte[] readAllBytes() throws IOException;
            
            范例：readAllBytes();
                import java.io.File;
                import java.io.FileInputStream;
                import java.io.InputStream;
                public class InputStreamDemo {
                    public static void main(String [] args) throws Exception {
                        File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt");
                        InputStream input = new FileInputStream(file);
                        byte data [] = input.readAllBytes();    //读取全部数据
                        System.out.println("【" + new String(data) + "】");
                        input.close();
                    }
                }
            如果你现在要读取的内容很大很大的时候，这种读取方式会直接搞死你的程序
*/
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
public class InputStreamDemo {
    public static void main(String [] args) throws Exception {
        File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt");
        InputStream input = new FileInputStream(file);
        byte data [] = input.readAllBytes();    //读取全部数据
        System.out.println("【" + new String(data) + "】");
        input.close();
    }
}