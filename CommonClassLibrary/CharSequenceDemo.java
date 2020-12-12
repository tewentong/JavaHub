/*
    CharSequence是一个描述字数传结构的接口，在这个接口里面一般可以发现三种常用的子类：
        1. String类：           public final class String extends Object implements Serializable, Comparable<String>, CharSequence
        2. StringBuffer类：     public final class StringBuffer extends Object implements Serializable, CharSequence
        3. StringBuilder类：    public final class StringBuilder extends Object implements Serializable, CharSequence

    现在只要有字符串就可以为CharSequence接口实例化
        CharSequence str = "www.mldn.cn";   //子类实例向父接口转型

    CharSequence本身也是一个接口，在该接口之中也定义有如下操作方法：
        获取指定索引字符： public char charAt(int index);  
        获取字符串的长度： public int lengtg();
        截取部分字符串：   public CharSequence subSequence(int start, int end); 
             CharSequence sub = str.subSequence(4, 8);

    以后只要看到CharSequence描述的就是一个字符串
*/
public class CharSequenceDemo {
    public static void main(String [] args) {
        CharSequence str = "www.mldn.cn";   //子类实例向父接口转型
        System.out.println(str);

        //字符串的截取
        CharSequence sub = str.subSequence(4, 8);
        System.out.println(sub);
    }
}