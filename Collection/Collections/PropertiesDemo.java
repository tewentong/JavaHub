
/*
    Java类集框架
        Properties属性操作
            在之前讲解国际化程序的时候讲解过资源文件（*.properties）
            那么这类文件的存储结构是按照“key=value”的形式存储的
            而这种结构的保存形式与Map集合很相似，但是唯一的区别在于其所保存的内容只能是字符串
            所以为了可以方便地描述属性的定义
            在java.util包里面提供有Properties类型，此类是Hashtable的子类
                public class Properties extends Hashtable<Object, Object>;
                可以发现在继承Hashtable的时候为Hashtable中定义的泛型为Object
                Properties是不需要操作泛型的，因为它可以操作的类型只能是String类型
                在Properties之中如果要想实现属性的操作可以采用如下的方法：
                    public Object setProperty(String key, String value) 普通 设置属性
                    public String getProperty(String key)   普通    取得属性，key不存在，返回null
                    public String getProperty(String key, String defaultValue)  普通    取得属性，不存在返回默认值
                    public void store(OutputStream out, String comments) throws IOException 输出属性内容
                    public void load(InputStream inStream) throws IOException   普通    通过输入流读取属性内容
        范例：观察属性的设置和取得
            import java.util.Properties;
            public class PropertiesDemo {
                public static void main(String[] args) throws Exception {
                    Properties prop = new Properties();
                    // 设置内容只允许是字符串
                    prop.setProperty("mldn", "www.mldn.cn");
                    prop.setProperty("mldnjava", "www.mldnjava.cn");
                    System.out.println(prop.getProperty("mldn"));
                    System.out.println(prop.getProperty("mldnjava"));
                    System.out.println(prop.getProperty("sina"));
                    System.out.println(prop.getProperty("sina", "NotFound"));
                }
            }
            程序输出：
                www.mldn.cn
                www.mldnjava.cn
                null
                NotFound
            通过代码可以发现Properties里面可以像Map集合那样进行内容的设置与获取，
                但是唯一的差别是它只能操作String类型
            另外需要注意的是，之所以会提供有Properties类还有一个最重要的功能：
                它可以通过输出流输出属性，也可以使用输入流读取属性内容
        范例：将属性内容保存在文件之中
            import java.io.File;
            import java.io.FileOutputStream;
            import java.util.Properties;
            public class PropertiesDemo {
                public static void main(String[] args) throws Exception {
                    Properties prop = new Properties();
                    // 设置内容只允许是字符串
                    prop.setProperty("mldn", "www.mldn.cn");
                    prop.setProperty("mldnjava", "www.mldnjava.cn");
                    prop.setProperty("BeiJing", "北京");
                    prop.store(new FileOutputStream(new File("information" + File.separator + "info.properties")), "中文的注释-English");
                }
            }
            通过程序的执行可以发现，的确可以实现资源文件的输入处理，但是如果输出的是中文，则自动帮助用户进行转码处理
        
        范例：读取资源文件
            import java.io.File;
            import java.io.FileInputStream;
            import java.util.Properties;
            public class PropertiesDemo {
                public static void main(String[] args) throws Exception {
                    Properties prop = new Properties();
                    prop.load(new FileInputStream(new File("information" + File.separator + "info.properties")));
                    System.out.println(prop.getProperty("mldn"));
                }
            }
            程序输出：
                www.mldn.cn
            
        使用Properties类型的最大特点是可以进行资源内容的输入与输出的处理操作
            但是在实际的开发之中，Properties往往用于读取配置资源的信息，
            这一点主要是在标准程序设计之中做程序初始化准备的时候使用
*/
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream(new File("information" + File.separator + "info.properties")));
        System.out.println(prop.getProperty("mldn"));
    }
}