/*
    输入一个Email的地址,然后使用正则表达式验证该Email地址是否正确
    对于此时的输入可以通过命令参数实现数据的输入,如果想要验证,最好的做法是设置一个单独的验证处理类
    以后如果有更多的验证,只需要在Validator类中扩展即可
*/
class Validator {   //定义一个专门的验证程序类
    private Validator() {}
    public static boolean isEmail(String email) {
        if (email == null || "".equals(email)) {    //数据为空
            return false;
        }
        String regex = "\\w+@\\w+.\\w+";
        return email.matches(regex);
    }
}
public class RegexValiEmailDemo {
    public static void main(String [] args) {
        if (args.length != 1) { //输入有一个参数
            //否则会报错
            //java.lang.ArrayIndexOutOfBoundsException: 0
            System.out.println("程序执行错误,没有输入初始化参数,正确格式为 java RegexValiEmailDemo EMIAL地址");
            System.exit(1); //系统退出
        }
        String email = args[0]; //获取初始化参数
        if (Validator.isEmail(email)) {
            System.out.println("输入的email地址正确!");
        }else {
            System.out.println("输入的email地址错误!");
        }
    }
}