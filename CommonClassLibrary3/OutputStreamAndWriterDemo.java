/*
    字节流与字符流的区别
    现在已经清除字节流与字符流的基本操作，但是对于这两类流依然是存在有区别的，重点来分析以下输出的处理操作
    在使用OutputStream和Writer输出的最后发现都使用了clese()方法进行了关闭处理
    在使用OutputStream类输出的时候如果现在没有使用close()方法关闭输出流，发现内容依然可以实现正常的输出
    在使用Writer的时候没有使用close()方法关闭输出流，那么这时内容将无法进行输出，因为Writer使用到了缓冲区
        当使用close()方法的时候实际上会出现有强制刷新缓冲区的情况，所以这个时候会将内容进行输出，如果没有关闭，那么将无法进行输出操作
        所以此时要想在不关闭的情况下将全部内容输出，可以使用flush()方法强制清空
    范例：使用Writer并强制性清空
        import java.io.File;
        import java.io.Writer;
        import java.io.FileWriter;
        public class ByteAndCharacterStreamDemo {
            public static void main(String [] args) throws Exception {
                File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" +
                                    File.separator + "mldn.txt");
                if (!file.getParentFile().exists()) {    
                    file.getParentFile().mkdirs();
                }
                Writer out = new FileWriter(file);
                String str = "www.mldn.cn123";
                out.write(str);
                out.flush();    //强制刷新
            }
        }

    字节流操作没有使用到缓冲区，而字符流操作使用到缓冲区了
    此外，使用缓冲区的字符流更加适合中文数据的处理，所以在日后的开发之中，如果涉及到包含有中文信息的输出一般都会使用字符流进行处理
    但是，从另外一方面讲，字节流与字符流的基本处理形式是相似的，
    由于IO在很多情况下都是进行数据的传输使用（二进制），所以本次的讲解将以字节流为主
*/

import java.io.File;
import java.io.Writer;
import java.io.FileWriter;
public class OutputStreamAndWriterDemo {
    public static void main(String [] args) throws Exception {
        File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" +
                            File.separator + "mldn.txt");
        if (!file.getParentFile().exists()) {    
            file.getParentFile().mkdirs();
        }
        Writer out = new FileWriter(file);
        String str = "www.mldn.cn123";
        out.write(str);
        out.flush();    //强制刷新
    }
}