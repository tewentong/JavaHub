/*
    编写程序，实现国际化应用，从命令行输入国家的代号，例如，1表示中国，2表示美国，然后根据输入代号的不同调用不同的资源文件显示信息
    本程序的实现肯定要通过Locale类的对象来指定区域，随后利用ResourceBundle类加载资源文件信息
    而对于数据的输入可以继续使用初始化参数的形式完成

    1.定义中文的资源文件：CommonClassLibrary.Message.Messages_zh_CN.properties
    2.定义英文的资源文件：CommonClassLibrary.Message.Messages_en_US.properties
    3.定义程序类进行加载控制
*/
import java.util.Locale;
import java.util.ResourceBundle;
class MessageUtil0307 {
    public static final int CHINA = 1;
    public static final int USA = 2;
    private static final String KEY = "info";
    private static final String BASENAME = "CommonClassLibrary.Message.Messages";  //Message.Messages 可以运行出结果
    public String getMessage(int num) {
        Locale loc = this.getLocale(num);
        if (loc == null) {
            return "nothing";
        }else {
            return ResourceBundle.getBundle(BASENAME, loc).getString(KEY);
        }
    }
    private Locale getLocale(int num) {
        switch(num) {
            case CHINA : return new Locale("zh", "CN");
            case USA : return new Locale("en", "US");
            default: return null;
        }
    }
}
public class CountryCode {
    public static void main(String [] args) {
        if (args.length != 1) { //没有得到输入参数
            System.out.println("程序执行错误，没有设置区域代码，正确格式：java CountryCode 选择项");
            System.exit(1);
        }
        int choose = Integer.parseInt(args[0]);
        System.out.println(new MessageUtil0307().getMessage(choose));
    }
}