/*
    String类是在开发之中常用的功能类，此类拥有如下的特点
        1. 每一个字符串的常量都属于一个String类的匿名对象，并且不可更改
        2. String有两个常量池： 静态常量池、运行时常量池
        3. String类对象实例化建议使用直接赋值的形式完成，这样可以直接将对象保存在对象池之中以方便下次重用
    
                public static void main(String [] args) {
                    String sb = "Hello";
                    change(sb);
                    System.out.println(sb);
                }
                public static void change(String temp) {    //内容并没有发生改变
                    temp += " World!";
                }

    虽然String类很好使用，但是如果认真思考也会发现其最大的弊端： 内容不允许修改
    虽然大部分情况下都不会涉及到字符串内容的频繁修改，但是依然可能会存在有这样的情况，为了解决此问题，专门提供了StringBuffer类
    StringBuffer类可以实现内容的修改处理

    StringBuffer并不像String类那样拥有两种对象的实例化方式
        StringBuffer必须像普通类对象那样首先进行对象实例化，而后才可以调用方法进行处理
        StringBuffer类中的如下方法：
            构造方法： public StringBuffer();
            构造方法： public StringBuffer(String str); //接受初始化的字符串内容
            数据追加： public StringBuffer append(数据类型 变量);   //此方法相当于字符串中的 + 操作

                public static void main(String [] args) {
                    StringBuffer buf = new StringBuffer("Hello");
                    change(buf);
                    System.out.println(buf.toString());
                }
                public static void change(StringBuffer temp) {    //内容发生改变
                    temp.append(" World!");
                }


    实际上大部分的情况下，很少会出现有字符串内容的改变，这种改变指的并不是针对于静态常量池的改变
                public static void main(String [] args) {
                    String strA = "www.mldn.cn";
                    String strB = "www." + "mldn" + ".cn";
                    System.out.println(strA == strB);   //返回true
                }
    这个时候StrB对象的内容并不算是改变，或者更加严格的意义上来讲，对于现在的strB当程序编译之后会变为如下的形式：
                StringBuffer buf = new StringBuffer();
                buf.append("www.").append("mldn").append("cn");
    所有的 + 在编译之后都变为了StringBuffer中的append()方法，并且在程序之中StirngBuffer与String类的对象之间本来就可以直接互相转换：
                String类对象变为StringBuffer可以依靠StringBuffer类的构造方法或者使用append()方法
                所有类对象都可以通过toString()方法将其变为String类型


    在StringBuffer类里面除了可以支持有字符串内容的修改之外，实际上也提供有一些String类不具备的方法 
        插入数据： public StringBuffer insert(int offset, 数据类型 b);  
        删除指定范围的数据： public StringBuffer delete(int start, int end);
        字符串内容反转： public StringBuffer reverse();

    实际上与StringBuffer类还有一个类似的功能类： StirngBuilder类，这个类是JDK1.5的时候提供的，该类中提供的方法与StringBuffer功能相同
    最大的区别在于StringBuffer类中的方法属于线程安全的，全部使用了synchronized关键字进行标注，StringBuilder属于非线程安全的

    面试题： 请解释String、StringBuffer、StirngBuilder的区别
        String类是字符串的首选类型，其最大的特点是内容不允许修改
        StirngBuffer与StringBuilder类的内容允许修改
        StringBuffer是在JDK1.0的时候提供的，属于线程安全的操作
        StringBuilder是在JDK1.5之后提供的，属于非线程安全的操作
*/
public class StringBufferDemo {
   public static void main(String [] args) {
       StringBuffer buf = new StringBuffer();
       buf.append(".cn").insert(0, "www.").insert(4, "mldn");
       System.out.println(buf.toString());
       buf.delete(4,9);
       System.out.println(buf.toString());

       buf.insert(4, "mldn.");
       System.out.println(buf.toString());
       buf.reverse();
       System.out.println(buf.toString());
   }
}