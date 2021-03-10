
/*
    Java IO编程
    打印流操作：如果现在要想通过程序实现内容的输出，核心的本质一定依靠OutputStream类完成，
        但是OutputStream类有一个最大的缺点，这个类的数据输出操作功能有限
        public void write(byte[] b) throws IOException; 所有的数据一定要转为字节数组后才可以输出
        于是假设说现在你的项目里可能输出的是long、double、Date，在这样的情况下，就必须将这些数据变为字节的形式来处理
        这样的处理一定是非常麻烦的，所以在开发之中最初的时候为了解决此类的重复操作，往往会由开发者自行定义一些功能类以简化我们的输出过程

    范例：打印流的设计思想
        import java.io.File;
        import java.io.IOException;
        import java.io.OutputStream;
        import java.io.FileOutputStream;
        class PrintUtil implements AutoCloseable { // 实现一些常用的数据输出
            private OutputStream output; // 不管你现在如何进行输出，核心使用的就是OutputStream
            public PrintUtil(OutputStream output) { // 由外部来决定输出的位置
                this.output = output;
            }
            @Override
            public void close() throws Exception {
                this.output.close();
            }
            public void println(long num) {
                this.println(String.valueOf(num));
            }
            public void print(long num) {
                this.print(String.valueOf(num));
            }
            public void print(String str) { // 输出字符串
                try {
                    this.output.write(str.getBytes()); // 输出
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void println(String str) {
                this.print(str + "\r\n");
            }
        }

        public class PrintStreamDemo {
            public static void main(String[] args) throws Exception {
                File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt");
                PrintUtil pu = new PrintUtil(new FileOutputStream(file));
                pu.println("姓名：小强子");
                pu.print("年龄：");
                pu.println(78);
                pu.close();
            }
        }
        在整个的操作过程之中，打印流的设计思想的本质在于：
            提高已有类的功能，例如：OutputStream是唯一可以实现输出的操作标准类，所以应该以其为核心根本，
            但是这个类输出的操作功能有限，所以不方便输出各个数据类型，那么，就为它作出了一层包装
            所以，此时所采用的设计思想就是“装饰设计模式”
        但是，既然所有的开发者都已经发现了原始的OutputStream功能的不足，设计者也一定可以发现，
            所以，为了解决输出问题，在java.io包里面提供有打印流：PrintStream、PrintWriter
                public class PrintStream extends FilterOutputStream implements Appendable、Closeable
                    构造方法：public PrintStream(OutputStream out);
                public class PrintWriter extends Writer;
                    构造方法：public PrintWriter(OutputStream out);
                    构造方法：public PrintWriter(Writer out);

    范例：下面使用PrintWriter来实现数据的输出操作(数据输出)
        import java.io.File;
        import java.io.PrintWriter;
        import java.io.FileOutputStream;

        public class PrintStreamDemo {
            public static void main(String[] args) throws Exception {
                File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt");
                PrintWriter pu = new PrintWriter(new FileOutputStream(file));
                pu.println("姓名：小小强子");
                pu.print("年龄：");
                pu.println(78);
                pu.close();
            }
        }
        
        从JDK1.5开始PrintWriter类里面追加有格式化输出的操作支持：
            public PrintWriter printf(String format, Object... args);
    范例：PrintWriter格式化输出
        import java.io.File;
        import java.io.PrintWriter;
        import java.io.FileOutputStream;

        public class PrintStreamDemo {
            public static void main(String[] args) throws Exception {
                File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt");
                PrintWriter pu = new PrintWriter(new FileOutputStream(file));
                String name = "小强子子";
                int age = 78;
                double salary = 72823.23423424;
                pu.printf("姓名： %s、年龄： %d、收入： %9.2f", name, age, salary);
                pu.close();
            }
        }

    比起直接使用OutputStream类，那么直接使用PrintWriter、PrintStream类的处理操作会更加的简单
    以后只要是程序进行内容输出的时候，全部使用打印流
*/
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class PrintStreamDemo {
    public static void main(String[] args) throws Exception {
        File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt");
        PrintWriter pu = new PrintWriter(new FileOutputStream(file));
        String name = "小强子子";
        int age = 78;
        double salary = 72823.23423424;
        pu.printf("姓名： %s、年龄： %d、收入： %9.2f", name, age, salary);
        pu.close();
    }
}