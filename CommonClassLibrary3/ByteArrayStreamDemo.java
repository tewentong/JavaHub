/*
    Java IO编程
    内存操作流
        在之前使用的全部都是文件操作流，文件操作流的特点是：
            程序利用InputStream读取内容，而后程序利用OutputStream向文件输出内容，所有的操作都是以文件为终端的
        假设说现在需要实现IO操作，可是又不希望产生文件（临时文件），则就可以用内存为终端进行处理
        Java里面提供有两类的内存操作流：
            字节内存操作流：ByteArrayOutputStream、ByteArrayInputStream
            字符内存操作流：CharArrayWriter、CharArrayReader
        下面以ByteArrayOutputStream和ByteArrayInputStream为主进行内存的使用分析，首先来分析各自的构造方法：
            ByteArrayInputSteam构造方法：  public ByteArrayInputStream(byte[] buf);
            ByteArrayOutputStream构造方法：public ByteArrayOutputStream();
        在ByteArrayOutputStream类里面有一个重要方法，这个方法可以获取全部保存在内存流中的数据信息
            获取数据：public byte[] toByteArray();
            使用字符串的形式来获取：public String toString();

    范例：利用内存流实现一个小写字母转大写字母的操作
        import java.io.InputStream;
        import java.io.ByteArrayInputStream;
        import java.io.OutputStream;
        import java.io.ByteArrayOutputStream;
        import java.lang.Character;
        public class ByteArrayStreamDemo {
            public static void main(String[] args) throws Exception {
                String str = "www.mldn.cn";
                InputStream input = new ByteArrayInputStream(str.getBytes()); // 将数据保存到内存流
                OutputStream output = new ByteArrayOutputStream(); // 读取内存中的数据
                int data = 0;
                while ((data = input.read()) != -1) { // 每次读取一个字节
                    output.write(Character.toUpperCase(data)); // 保存数据
                }
                System.out.println(output);
                input.close();
                output.close();
            }
        }
        //现在是以字符串的形式返回

    范例：利用内存流实现一个小写字母转大写字母的操作
        如果现在不希望只是以字符串的形式返回，因为可能存放的是其他二进制的数据，
        那么此时就可以利用ByteArrayOutputStream子类的扩展功能获取全部字节数据
        import java.io.InputStream;
        import java.io.ByteArrayInputStream;
        import java.io.OutputStream;
        import java.io.ByteArrayOutputStream;
        import java.lang.Character;
        public class ByteArrayStreamDemo {
            public static void main(String[] args) throws Exception {
                String str = "www.mldn.cn";
                InputStream input = new ByteArrayInputStream(str.getBytes()); // 将数据保存到内存流
                // 必须使用子类来调用子类自己的扩展方法
                ByteArrayOutputStream output = new ByteArrayOutputStream(); // 读取内存中的数据
                int data = 0;
                while ((data = input.read()) != -1) { // 每次读取一个字节
                    output.write(Character.toUpperCase(data)); // 保存数据
                }
                byte result[] = output.toByteArray(); // 获取全部数据
                System.out.println(new String(result)); // 自己处理数据字节数据
                input.close();
                output.close();
            }
        }

    在最初的时候可以利用ByteArrayOutputStream实现大规模文本文件的读取，但现在使用已经比较少了
*/

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.lang.Character;

public class ByteArrayStreamDemo {
    public static void main(String[] args) throws Exception {
        String str = "www.mldn.cn";
        InputStream input = new ByteArrayInputStream(str.getBytes()); // 将数据保存到内存流
        // 必须使用子类来调用子类自己的扩展方法
        ByteArrayOutputStream output = new ByteArrayOutputStream(); // 读取内存中的数据
        int data = 0;
        while ((data = input.read()) != -1) { // 每次读取一个字节
            output.write(Character.toUpperCase(data)); // 保存数据
        }
        byte result[] = output.toByteArray(); // 获取全部数据
        System.out.println(new String(result)); // 自己处理数据字节数据
        input.close();
        output.close();
    }
}