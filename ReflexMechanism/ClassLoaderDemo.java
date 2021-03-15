/*
    Java反射编程
        ClassLoader类加载器
            在Java语言里面提供一个系统的环境变量：CLASSPATH,这个环境属性的作用主要是在JVM进程启动的时候
                进行类加载路径的定义,在JVM里面可以根据类加载器而后进行指定路径中类的加载，
                也就是说找到了类加载器就是找到了类的来源
            系统类的加载器：
                如果说现在想要获得类的加载器，那么一定要通过ClassLoader来获取，而要想获取ClassLoader类的对象，
                则必须利用Class类（反射的根源）实现，方法：public ClassLoader getClassLoader();
                当获取了ClassLoader之后，还可以继续获取其父类的ClassLoader类对象：public final ClassLoader getParent();
                范例：观察类加载器
                    class Message0315 {

                    }

                    public class ClassLoaderDemo {
                        public static void main(String[] args) throws Exception {
                            Class<?> clazz = Message0315.class;
                            System.out.println(clazz.getClassLoader()); // 获取当前类的加载器
                            System.out.println(clazz.getClassLoader().getParent()); // 获取父类加载器
                            System.out.println(clazz.getClassLoader().getParent().getParent()); // 获取祖父类加载器
                        }
                    }
                    程序输出：
                        sun.misc.Launcher$AppClassLoader@18b4aac2
                        sun.misc.Launcher$ExtClassLoader@66cd51c3
                        null
                    从JDK1.8之后的版本（JDK1.9、JDK1.10）提供有一个“PlatformClassLoader”类加载器
                    而在JDK1.8及以前的版本里面，提高的为“ExtClassLoader”，因为在JDK的安装目录里面提供有一个ext的目录，
                    开发者可以将*.jar文件拷贝到此目录里面，这样就可以直接执行了
                    但是这样的处理开发并不安全，最初的时候也是不提倡使用的，所以从JDK1.9开始将其彻底废除
                    同时为了与系统类加载器和应用类加载器之间保持设计的平衡，提供有平台类加载器。

                    当你获得了类加载器之后就可以利用类加载器来实现类的反射加载处理
*/
class Message0315 {

}

public class ClassLoaderDemo {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Message0315.class;
        System.out.println(clazz.getClassLoader()); // 获取当前类的加载器
        System.out.println(clazz.getClassLoader().getParent()); // 获取父类加载器
        System.out.println(clazz.getClassLoader().getParent().getParent()); // 获取祖父类加载器
    }
}