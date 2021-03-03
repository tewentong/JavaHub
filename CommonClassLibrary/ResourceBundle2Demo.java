/*
    现在国际化程序的实现前期准备已经全部完成了，也就是依靠资源文件、Locale、ResourceBundle类就可以实现国际化的处理操作
    下面进行国际化的程序实现(核心关键：读取资源信息)
        1.在CLASSPPATH下建立：Java.CommonClassLibrary.Message.Messages_zh_CN.properties 
            info=欢迎您的访问！
        2.在CLASSPPATH下建立：Java.CommonClassLibrary.Message.Messages_en_US.properties
            info=Welcome!
        3.现在加上没有默认的区域的资源文件，一共定义了三个资源
            Messages_zh_CN.properties
            Messages_en_US.properties
            Messages.properties
        4.如果有需要也可以修改当前的Locale环境，则可以使用ResourceBundle类中的如下方法：
            获取ResourceBundle: ResourceBundle getBundle(String baseName, Locale locale);

        通过程序进行指定区域的资源信息加载
        ResourceBundle resource = ResourceBundle.getBundle("Message.Messages");
        String val = resource.getString("info");
        System.out.println(val);    //Welcome to my JavaHub!
        此时在利用ResourceBundle类读取资源的时候并没有设置一个明确的Locale对象，但是发现本地默认Locale起作用了

        资源文件读取顺序： 读取指定区域的资源文件 > 默认的本地资源 > 公共的资源（没有区域设置的）
*/
import java.util.Locale;
import java.util.ResourceBundle;
public class ResourceBundle2Demo {
    public static void main(String[] args) {
        Locale loc = Locale.CHINA;
        ResourceBundle resource = ResourceBundle.getBundle("Message.Messages", loc);
        String val = resource.getString("info");
        System.out.println(val);
    }
}