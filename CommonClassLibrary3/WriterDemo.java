/*
    字符输出流：Writer
    使用OutputStream字节输出流进行数据输出的时候使用的都是字节类型的数据，而很多的情况下发现字符串的输出是比较方便的
    所以对于java.io包而言，在JDK1.1的时候又推出了字符输出流：Writer
    这个类的定义如下：
        public abstract class Writer extends Object implements Appendable, Closeable, Flushable;
    在Writer类里面提供有许多的输出操作方法，重点来看两个：
        输出字符数组：public void write(char[] cbuf) throws IOException;
        输出字符串：public void write(String str) throws IOException;
    范例：使用Writer输出
        import java.io.File;
        import java.io.Writer;
        import java.io.FileWriter;
        public class WriterDemo {
            public static void main(String [] args) throws Exception{
                File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" 
                                    + File.separator + "mldn.txt");
                if (file.getParentFile().exists()) {    
                    file.getParentFile().mkdirs();  //父目录必须存在
                }
                Writer out = new FileWriter(file);
                String str = "www.mldn.cn";
                out.write(str);
                out.close();
            }
        }
        使用Writer输出的最大优势在于可以直接利用字符串完成
        Writer是字符流，字符处理的优势在于中文数据
*/
import java.io.File;
import java.io.Writer;
import java.io.FileWriter;
public class WriterDemo {
    public static void main(String [] args) throws Exception{
        File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" 
                            + File.separator + "mldn.txt");
        if (file.getParentFile().exists()) {    
            file.getParentFile().mkdirs();  //父目录必须存在
        }
        Writer out = new FileWriter(file, true);
        String str = "www.mldn.cn\r\n";
        out.write(str);
        out.append("Chinese people great!");    //追加输出内容
        out.close();
    }
}