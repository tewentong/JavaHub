/*
    如果想要进行正则的处理操作，那么就首先需要对常用的正则标记有所掌握，从JDK1.4开始就提供有java.util.regex的开发包
    这个包里面提供有一个Pattern的程序类，在这个程序类里面定义有所有支持的正则标记。
        1.【数量：单个】字符匹配
            任意字符：表示由任意字符组成；
                String str = "a";   //要判断的数据
                String regex = "a"; //正则表达式
                System.out.println(str.matches(regex));
            \\     :匹配 "\"
            \n     :匹配换行
            \t     :匹配制表符
        2.【数量：单个】字符集匹配（可以从里面任选一个字符）
            [abc]  :表示可以是字母a,b,c中的任意一个
            [^abc] :表示不能是字母a,b,c中的任意一个
            [a-zA-Z]：表示由一个任意字母所组成，不区分大小写
            [0-9]  :表示由一位数字所组成
                String str = "a";   //要判断的数据
                String regex = "[abc]"; //正则表达式
                String regex2 = "[a-zA-Z]";
                System.out.println(str.matches(regex));
                System.out.println(str.matches(regex2));
                String str2 = "1";  //要判断的数据
                String regex3 = "[0-9]"; //正则表达式
                System.out.println(str2.matches(regex3));
        3.【数量：单个】简化字符集
            .      :表示任意一个字符
            \d     :等价于 "[0-9]"范围
                String str = "1";   //要判断的数据
                String regex = "\\d";    //正则表达式    \\ 是 "\"   \\d 是 "\d"
                System.out.println(str.matches(regex));
            \D     :等价于 "[^0-9]"范围
            \s     :匹配任意的一位空格，可能是空格、换行、制表符
                String str = "a\n"; //要判断的数据
                String regex = "\\D\\s";    //正则表达式       \\D\\s 是 \D\s 
                System.out.println(str.matches(regex));
            \S     :匹配任意的非空格数据
            \w     :匹配字母、下划线、数字，等价于 "[a-zA-Z_0-9]"
            \W     :匹配不是字母、下划线、数字，等价于 "[^a-zA-Z_0-9]"
        4. 边界匹配
            ^      :匹配边界开始
            $      :匹配边界结束
        5. 数量表示，默认情况下只有添加上了数量单位才可以匹配多位字符
            表达式？ :该正则可以出现0次或1次
            表达式*  :该正则可以出现0次、1次或多次
            表达式+  :该正则可以出现1次或多次
                String str = "aabc";    //要判断的数据
                String regex = "\\w+";  //正则表达式 一位或以上
                System.out.println(str.matches(regex));
            表达式{n} :表达式的长度正好为n次
                String str = "abc";    //要判断的数据
                String regex = "\\w{3}";    //郑则表达式
                System.out.println(str.matches(regex));
            表达式{n,}:表达式的长度为n次及以上
            表达式{n,m}:表达式的长度在n~m次
        6. 逻辑表达式：可以连接多个正则
            表达式X表达式Y：X表达式之后紧跟上Y表达式
                String str = "ax";  //要判断的数据
                String regex = "ax";    //正则表达式
                System.out.println(str.matches(regex));
            表达式X|表达式Y：有一个表达式满足即可
            (表达式)：为表达式设置一个整体描述，可以为整体描述设置数量单位
*/
public class RegularMarkerDemo {
    public static void main(String[] args) throws Exception {
        /*
        String str = "a";   //要判断的数据
        String regex = "a"; //正则表达式
        System.out.println(str.matches(regex));
        */
        /*
        String str = "a";   //要判断的数据
        String regex = "[abc]"; //正则表达式
        String regex2 = "[a-zA-Z]";
        System.out.println(str.matches(regex));
        System.out.println(str.matches(regex2));
        String str2 = "1";  //要判断的数据
        String regex3 = "[0-9]"; //正则表达式
        System.out.println(str2.matches(regex3));
        */
        /*
        String str = "1";   //要判断的数据
        String regex = "\\d";    //正则表达式    \\ 是 "\"   \\d 是 "\d"
        System.out.println(str.matches(regex));
        */
        /*
        String str = "a\n"; //要判断的数据
        String regex = "\\D\\s";    //正则表达式       \\D\\s 是 \D\s 
        System.out.println(str.matches(regex));
        */
        /*
        String str = "aabc";    //要判断的数据
        String regex = "\\w+";  //正则表达式 一位或以上
        System.out.println(str.matches(regex));
        */
        /*
        String str = "abc";    //要判断的数据
        String regex = "\\w{3}";    //郑则表达式
        System.out.println(str.matches(regex));
        */
        String str = "ax";  //要判断的数据
        String regex = "ax";    //正则表达式
        System.out.println(str.matches(regex));
    }
}