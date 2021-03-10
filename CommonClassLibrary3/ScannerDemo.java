
/*
    Java IO编程
        Scanner类：java.util.Scanner是从JDK1.5之后追加的一个程序类
            其主要的目的是为了解决输入流的访问问题的，可以理解为BufferedReader类的替代功能类
            在Scanner类里面有如下几种核心的操作方法：
                构造方法：public Scanner(InputStream source);
                判断是否有数据：public boolean hasNext();
                取出数据：public String next();
                设置分割符：public Scanner useDelimiter(String pattern);
        范例：使用Scanner实现键盘数据输入
            import java.util.Scanner;
            public class ScannerDemo {
                public static void main(String[] args) throws Exception {
                    Scanner scan = new Scanner(System.in);
                    System.out.print("请输入年龄： ");
                    if (scan.hasNextInt()) { // 判断是否有整数输入
                        int age = scan.nextInt(); // 直接获取数字
                        System.out.println("您的年龄： " + age);
                    } else {
                        System.out.println("咋看不懂人话呢？输入的是年龄！");
                    }
                    scan.close();
                }
            }
            此时可以明显的感受到，Scanner的处理会更加的简单
        
        范例：输入一个字符串
            import java.util.Scanner;
            public class ScannerDemo {
                public static void main(String[] args) throws Exception {
                    Scanner scan = new Scanner(System.in);
                    System.out.print("请输入： ");
                    if (scan.hasNext()) {
                        String msg = scan.next();
                        System.out.println("输入信息为： " + msg);
                    }
                    scan.close();
                }
            }

            使用Scanner输入数据还有一个最大的特点是可以直接利用正则进行验证判断
        范例：输入一个人的生日（yyyy-MM-dd）
            import java.util.Scanner;
            import java.text.SimpleDateFormat;
            public class ScannerDemo {
                public static void main(String[] args) throws Exception {
                    Scanner scan = new Scanner(System.in);
                    System.out.print("请输入您的生日： ");
                    if (scan.hasNext("\\d{4}-\\d{2}-\\d{2}")) {
                        String str = scan.next("\\d{4}-\\d{2}-\\d{2}");
                        System.out.println("输入信息为： " + new SimpleDateFormat("yyyy-MM-dd").parse(str));
                    }
                    scan.close();
                }
            }
            现在可以发现Scanner的整体设计要好于BufferedReader,而且要比直接使用InputStream类读取要方便
            例如，如果现在要读取一个文本文件中的所有内容信息，
                如果采用的是InputStream类，那么就必须依靠内存输出流进行临时数据的保存，随后还需要判断读取的内容是否是换行
        范例：使用Scanner读取
            import java.util.Scanner;
            import java.io.File;
            public class ScannerDemo {
                public static void main(String[] args) throws Exception {
                    Scanner scan = new Scanner(
                            new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt"));
                    scan.useDelimiter("\n"); // 设置读取分割符
                    while (scan.hasNext()) {
                        System.out.println(scan.next());
                    }
                    scan.close();
                }
            }

        在以后的开发之中，如果程序需要输出数据一定使用打印流，输入数据一定使用Scanner或者BufferedReader
*/
import java.util.Scanner;
import java.io.File;

public class ScannerDemo {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(
                new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt"));
        scan.useDelimiter("\n"); // 设置读取分割符
        while (scan.hasNext()) {
            System.out.println(scan.next());
        }
        scan.close();
    }
}