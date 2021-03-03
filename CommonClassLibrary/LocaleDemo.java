/*  
    国际化的程序实现
    所谓国际化的程序实现指的是同一个程序代码可以根据不同的国家实现不同的语言描述，但是程序处理的核心业务是相同的

    国际化问题简介：
        现在假设有一款世界都认可的企业管理平台，那么这个企业的老板决定将这个产品推广到全世界各个大型上市公司
        于是，这些公司可能来自于：中国、美国、德国
        那么在这样的情况下，首先要考虑的问题是什么呢？
            通过分析之后可以发现，如果想要实现国际化的程序开发，那么要结局的问题就在于以下两点
                1.如何定义保存文字的文件信息
                2.如何根据不同的区域语言的编码读取指定的资源信息

    如果想要实现国际化，那么首先需要解决的就是不同国家用户的区域和语言的编码问题
    而java.util包中提供有一个专门描述区域和语言编码的类：Locale
    而后主要使用Locale类中的两个构造方法进行实例化
        构造方法：public Locale(String language);
        构造方法：public Locale(String language, String country);
    此时需要的是国家和语言的代码，而中文的代码：zh_CN、美国英语的代码：en_US
    对于这些区域和语言的编码最简单的获得方式就是可以通过IE浏览器完成
        范例：实例化Locale类对象
            Locale loc = new Locale("zh", "CN");    //中文环境
            System.out.println(loc);
    如果说现在想要自动获得当前的运行环境，那么现在就可以利用Locale类本身默认的方式进行实例化
        读取本地默认环境：public static Locale getDefault();
            Locale loc = Locale.getDefault();   //获取默认环境
            System.out.println(loc);
    在世纪的开发之中，很多人可能并不关心国家和语言的编码，所以为了简化开发，Locale类也将世界上比较知名国家的编码设置为了常量
            Locale loc = Locale.CHINA;
            System.out.println(loc);
        使用常量的优势在于可以避免一些区域编码信息的繁琐
*/      
import java.util.Locale;

public class LocaleDemo {
    public static void main(String[] args) throws Exception {
        /*
        Locale loc = new Locale("zh", "CN");    //中文环境
        System.out.println(loc);
        */
        /*
        Locale loc = Locale.getDefault();   //获取默认环境
        System.out.println(loc);
        */
        Locale loc = Locale.CHINA;
        System.out.println(loc);
    }
}