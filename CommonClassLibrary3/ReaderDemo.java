/*
    字符输入流：Reader
    Reader是实现字符输入流的一种类型，其本身属于一个抽象类，这个类的定义如下：
        public abstract class Reader extends Object implements Readable, Closeable;
    Reader类里面并没有像Writer类一样提供有整个字符串的输入处理操作，只能够利用字符数组实现接收
        接收数据：public int read(char[] cbuf) throws IOException;
    范例：实现数据读取
        import java.io.File;
        import java.io.FileReader;
        import java.io.Reader;
        public class ReaderDemo {
            public static void main(String [] args) throws Exception {
                File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test"
                                    + File.separator + "mldn.txt");
                if (file.exists()) {    //文件存在则进行读取
                    Reader in = new FileReader(file);
                    char data [] = new char[1024];
                    int len = in.read(data);
                    System.out.println("读取内容： " + new String(data, 0 ,len));
                    in.close();
                }
            }
        }
        字符流读取的时候，只能够按照数组的形式来实现处理操作
*/
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
public class ReaderDemo {
    public static void main(String [] args) throws Exception {
        File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test"
                            + File.separator + "mldn.txt");
        if (file.exists()) {    //文件存在则进行读取
            Reader in = new FileReader(file);
            char data [] = new char[1024];
            int len = in.read(data);
            System.out.println("读取内容： " + new String(data, 0 ,len));
            in.close();
        }
    }
}