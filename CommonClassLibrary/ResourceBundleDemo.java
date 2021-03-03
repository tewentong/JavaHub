/*
    现在已经准备好了资源文件，那么随后就需要进行资源文件的读取操作，而读取资源文件主要依靠java.util.ResourceBundle类
    此类定义如下：
        public abstract class ResourceBundle extends Object
    ResourceBundle是一个抽象类，如果说现在想要进行此类对象的实例化可以直接使用该类中提供的一个static方法完成
        获取ResourceBundle类对象：public static final ResourceBundle getBundle(String baseName);
            |- baseName:描述的是资源文件的名称，但是没有后缀(Java.CommonClassLibrary.Messages)
        根据key读取资源内容：public final String getString(String key);
    范例：使用ResourceBundle类读取内容
        ResourceBundle resource = ResourceBundle.getBundle("Messages"); //如果资源没有放在包里面，则直接编写资源名称即可
        String val = resource.getString("info");
        System.out.println(val);
    在进行资源读取的时候数据的key一定要存在，如果不存在则会出现如下异常信息
        java.util.MissingResourceException: Can't find resource for bundle java.util.PropertyResourceBundle, key infos
*/
import java.util.ResourceBundle;
public class ResourceBundleDemo {
    public static void main(String[] args) throws Exception {
        ResourceBundle resource = ResourceBundle.getBundle("Messages");
        String val = resource.getString("info");
        System.out.println(val);
    }
}