
/*
    Java IO编程
    BufferedReader类：BufferedReader类提供的是一个缓冲字符输入流的概念
        利用BufferedReader类可以很好的解决输入流数据的读取问题
        BufferedReader类是在最初的时候提供的最完善的数据输入的处理（JDK1.5之前，JDK1.5之后出了一个功能更强大的类代替此类）
        BufferedReader类提供有一个重要的方法：
            读取一行数据：public String readLine() throws IOException;
    范例：利用BufferedReader类实现键盘输入数据的标准化定义
        import java.io.BufferedReader;
        import java.io.InputStreamReader;

        public class BufferedReaderDemo {
            public static void main(String[] args) throws Exception {
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("请输入信息： ");
                String msg = input.readLine(); // 接收输入信息
                System.out.println("输入内容为： " + msg);
            }
        }
        在以后的开发之中会经常遇到输入数据的情况，而所有输入数据的类型都是通过String描述的
            那么这样就方便了接收者进行各种处理
    范例：接收整型输入并且验证
        import java.io.BufferedReader;
        import java.io.InputStreamReader;

        public class BufferedReaderDemo {
            public static void main(String[] args) throws Exception {
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("请输入您的年龄： ");
                String msg = input.readLine(); // 接收输入信息
                if (msg.matches("\\d{1,3}")) { // 是否由数字所组成
                    int age = Integer.parseInt(msg);
                    System.out.println("年龄为： " + age);
                } else {
                    System.out.println("请确保您看懂了我的提示，不要随意输入，伤不起！");
                }
            }
        }
        对于现代的Java开发，由键盘输入数据的情况并不多了，但是作为一些基础的逻辑训练还是可以使用键盘输入数据的
            而键盘输入数据的标准做法（JDK1.5之前）就是上面的实现操作
        实际开发之中所有输入的数据全部都是字符串，这样可以方便用户验证与进行字符串的复杂处理
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BufferedReaderDemo {
    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入您的年龄： ");
        String msg = input.readLine(); // 接收输入信息
        if (msg.matches("\\d{1,3}")) { // 是否由数字所组成
            int age = Integer.parseInt(msg);
            System.out.println("年龄为： " + age);
        } else {
            System.out.println("请确保您看懂了我的提示，不要随意输入，伤不起！");
        }
    }
}