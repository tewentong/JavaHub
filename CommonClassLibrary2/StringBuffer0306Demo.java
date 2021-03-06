/*
    定义一个StringBuffer类对象，然后通过append()方法向对象中添加26个小写字母，要求每次只添加一次，共添加26次，然后按照逆序输出，并且可以删除前5个字符

    本操作主要训练StringBuffer类中的处理方法，因为StringBuffer的主要特点是内容允许修改,String内容不允许修改
    现在的程序是一个单线程开发，不需要考虑并发访问问题
*/
public class StringBuffer0306Demo {
    public static void main(String[] args) {
        StringBuffer buf = new StringBuffer();
        for(int x = 'a' ; x <= 'z' ; x ++) {
            buf.append((char)x);    //保存为字符
        }
        buf.reverse().delete(0,5);  //反转处理
        System.out.println(buf);    //utsrqponmlkjihgfedcba
    }
}