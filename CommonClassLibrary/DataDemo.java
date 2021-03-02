/*
    简单Java类的设计主要来自于数据表的结构
    数据表的结构的常用的类型：数字、字符串、日期
    java.util.Date类，此类直接实例化就可以获取当前的日期时间
        Date date = new Date();
        System.out.println(date);

    观察Date类中的构造方法：
    public Date() {
        this(System.currentTimeMillis());
    }
    public Date(long date) {
        fastTime = date;
    }
    通过对以上的源代码分析可以得出一个结论：Date类中只是对long数据的一种包装。
    所以Date类中以一定提供有日期与long数据类型之间转换的方法：
        将long转为Date：public Date(long date);
        将date转为long：public long getTime();
    范例：观察Date与long之间的转换：
        Date date = new Date();
        long current = date.getTime();
        current += 864000 * 1000;  //10天的秒数（原本是按照毫秒计算）
        System.out.println(new Date(current));  //long转为Date
    long之中可以保存毫秒的数据集，这样的话方便程序处理。
*/
import java.util.Date;
public class DataDemo {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);

        long current = date.getTime();
        current += 864000 * 1000;  //10天的秒数（原本是按照毫秒计算）
        System.out.println(new Date(current));  //long转为Date
    }    
}