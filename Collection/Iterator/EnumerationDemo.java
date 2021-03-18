
/*
    Enumeration输出
        Enumeration是在JDK1.0的时候就是用的输出接口，这个输出接口主要是为了Vector提供输出服务的
        一直到后续的JDK的发展，Enumeration依然只为Vector一个类服务
        如果要想获取Enumeration接口对象，就必须依靠Vector类提供的方法
            获取Enumeration:public Enumeration<E> elements();
        在Enumeration接口之中定义有两个操作方法：
            判断是否有下一个元素：public boolean hasMoreElements();
            获取当前元素：public E nextElement();

        范例：使用Enumeration实现输出
            import java.util.Vector;
            import java.util.Enumeration;

            public class EnumerationDemo {
                public static void main(String[] args) throws Exception {
                    Vector<String> all = new Vector<String>();
                    all.add("Hello");
                    all.add("World");
                    all.add("MLDN");
                    Enumeration<String> enu = all.elements();
                    while (enu.hasMoreElements()) {
                        String str = enu.nextElement();
                        System.out.println(str + "、");
                    }
                }
            }

        由于该接口出现的时间比较长了，所以在一些比较早的开发过程之中，也有部分的方法只支持Enumeration输出操作，
            但是随着类方法的不断完善，大部分的操作都直接利用Iterator实现了
*/
import java.util.Vector;
import java.util.Enumeration;

public class EnumerationDemo {
    public static void main(String[] args) throws Exception {
        Vector<String> all = new Vector<String>();
        all.add("Hello");
        all.add("World");
        all.add("MLDN");
        Enumeration<String> enu = all.elements();
        while (enu.hasMoreElements()) {
            String str = enu.nextElement();
            System.out.println(str + "、");
        }
    }
}