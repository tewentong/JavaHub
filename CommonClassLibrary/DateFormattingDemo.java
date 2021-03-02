/*
    虽然Date类可以获取当前的日期时间，但是默认的情况下Date类输出的时间结构并不能被国人习惯
    现在需要对显示的格式进行格式化处理，为了格式化日期
        在java.text包中提供有SimpleDateFormat程序类，该类是DateFormat的子类，该类中提供有如下的方法：
        ·【DateFormat继承】将日期格式化：public final String format(Date date);
        ·【DateFormat继承】将字符串转为日期：public Date parse(String source) throws ParseException;
        ·构造方法：public SimpleDateFormat(String pattern);
            日期格式： 年(yyyy)、月(MM)、日(dd)、时(HH)、分(mm)、秒(ss)、毫秒(SSS)；    //注意大小写

    范例：格式化日期显示
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String str = sdf.format(date);
        System.out.println(str);
    
    除了可以将日期格式化为字符串之后，也可以实现字符串与日期之间的转换处理
    范例：将日期转为Date
        String birthday = "1846-11-11 11:11:11.111";    //字符串的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = sdf.parse(birthday);
        System.out.println(date);

    如果在进行字符串定义的时候，所使用的日期时间数字超过了指定的合理范围，则会自动进行进位处理
    
    范例：数字格式化
        double money = 21221433231.23;  
        String str = NumberFormat.getInstance().format(money);
        System.out.println(str);
*/

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
public class DateFormattingDemo {
    public static void main(String[] args) throws Exception {
        /*
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String str = sdf.format(date);
        System.out.println(str);
        */
        /*
        String birthday = "1846-11-11 11:11:11.111";    //字符串的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = sdf.parse(birthday);
        System.out.println(date);
        */
        double money = 21221433231.23;  
        String str = NumberFormat.getInstance().format(money);
        System.out.println(str);
    }
}

/*
    通过到现在的学习发现，String字符串可以向所有的类型转换，基本类型、日期类型
    可以将字符串视为一个万能的类型
*/