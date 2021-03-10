
/*
    Java IO编程
    System类对IO的支持：
        System类是一个系统类，而且是一个从头到尾一直都在使用的系统类，而在这个系统类之中实际上听过有三个常量：
            标准输出(显示器)：public static final PrintStream out;
            错误输出：public static final PrintStream err;
            标准输入(键盘)：public static final InputStream in;
        范例：观察输出
            public class SystemIODemo {
                public static void main(String[] args) throws Exception {
                    try {
                        Integer.parseInt("a"); // java.lang.NumberFormatException: For input string: "a"
                    } catch (Exception e) {
                        System.out.println(e); // java.lang.NumberFormatException: For input string: "a"
                        System.err.println(e); // java.lang.NumberFormatException: For input string: "a"
                    }
                }
            }
            System.out和System.err都是同一种类型的，如果现在使用的是Eclipse则在使用System.err输出的时候会使用红色字体
                而System.out会使用黑色字体
            最早设置两个输出的操作是有目的的，System.out是输出那些希望用户可以看见的信息，
                而System.err是输出那些不希望用户可以看见的信息
            如果有需要，也可以修改输出的位置：
                修改out的输出位置：public static void setOut(PrintStream out);
                修改err的输出位置：public static void setErr(PrintStream err);
        范例：修改System.err的位置
            import java.io.File;
            import java.io.PrintStream;
            import java.io.FileOutputStream;

            public class SystemIODemo {
                public static void main(String[] args) throws Exception {
                    System.setErr(new PrintStream(new FileOutputStream(
                            new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt"))));
                    try {
                        Integer.parseInt("a");
                    } catch (Exception e) {
                        System.out.println(e); // 控制台输出： java.lang.NumberFormatException: For input string: "a"
                        System.err.println(e); // 输出到指定路径的文件里了
                    }
                }
            }

            在System类里面还提供有一个int常量，而这个常量对应的是标准输入设备键盘的输入处理
                可以实现键盘数据输入
        范例：实现键盘输入
            import java.io.InputStream;
            public class SystemIODemo {
                public static void main(String[] args) throws Exception {
                    InputStream input = System.in; // 此时的输入流为键盘输入
                    System.out.print("请输入信息：");
                    byte data[] = new byte[1024];
                    int len = input.read(data);
                    System.out.println("输入内容为： " + new String(data, 0, len));
                }
            }
            但是，这样的键盘输入处理本身是有缺陷的：如果你现在的长度不足，那么只能够接收部分数据，
                所以这个输入就有可能需要进行重复的输入流数据接收，
            而且在接收的时候还有可能会牵扯到输入中文的情况，如果对于中文的处理不当，则也有可能造成乱码问题
*/
import java.io.InputStream;

public class SystemIODemo {
    public static void main(String[] args) throws Exception {
        InputStream input = System.in; // 此时的输入流为键盘输入
        System.out.print("请输入信息：");
        byte data[] = new byte[1024];
        int len = input.read(data);
        System.out.println("输入内容为： " + new String(data, 0, len));
    }
}