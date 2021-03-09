/*
    JAVA IO编程
    转换流
    所谓的转换流指的是可以实现字节流与字符流操作的功能转换，例如：
        进行输出的时候，OutputStream需要将内容变为字节数组后才可以输出
        而Writer可以直接输出字符串
        这一点是很方便的，所以很多人就认为需要提供一种转换的机制，来实现不同流类型的转换操作
        为此，在java.io包里面提供有两个类：InputStreamReader、OutputStreamWriter
            public class OutputStreamWriter extends Writer
                构造方法：public OutputStreamWriter(OutputStream out)
            public class InputStreamReader extends Reader
                构造方法：public InputStreamReader(InputStream in)
        通过类的继承结构与构造方法可以发现，所谓的转换处理就是将接收到的字节流对象通过向上转型变为字符流对象
    范例：观察转换
        import java.io.File;
        import java.io.OutputStream;
        import java.io.FileOutputStream;
        import java.io.Writer;
        import java.io.OutputStreamWriter;
        public class StreamChangerDemo {
            public static void main(String [] args) throws Exception {
                File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" +
                                    File.separator + "mldn.txt");
                if (file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();  //父目录必须存在
                }
                OutputStream output = new FileOutputStream(file);
                Writer out = new OutputStreamWriter(output);    //字节流变为字符流
                out.write("www.mldn.cn Changer");   //直接输出字符串，字符流适合处理中文
                out.close();
            }
        }
    讲解转换流的主要目的基本上不是为了让开发者去记住它，而是指导有这样一种功能，但同时更多的是需要进行结构的分析处理
        通过之前的一系列的字节流与字符流的分析之后你会发现：
        OutputStream类有FileOutputStream直接子类
        InputStream类有FileInputStream直接子类
        但是观察一下FileWriter、FileReader类的继承关系
            public class FileWriter extends OutputStreamWriter;

            public class FileReader extends InputStreamReader;

    实际上所谓的缓存都是指的是程序中间的一道处理缓冲区（适宜处理中文的原因）
        java字符用Unicode编码，16进制编码，如果编码不统一的时候，容易出现乱码
*/
import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.Writer;
import java.io.OutputStreamWriter;
public class StreamChangerDemo {
    public static void main(String [] args) throws Exception {
        File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" +
                            File.separator + "mldn.txt");
        if (file.getParentFile().exists()) {
            file.getParentFile().mkdirs();  //父目录必须存在
        }
        OutputStream output = new FileOutputStream(file);
        Writer out = new OutputStreamWriter(output);    //字节流变为字符流
        out.write("www.mldn.cn Changer");   //直接输出字符串，字符流适合处理中文
        out.close();
    }
}