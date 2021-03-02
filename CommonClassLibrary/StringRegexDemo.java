/*
    String类对正则的支持
    在进行正则表达式大部分处理的情况下都会基于String类来完成，并且在String里面提供有如下与正则有关的方法
        public boolean matches(String regex)                            普通    将指定字符串进行正则判断
        public String replaceAll(String regex, String replacement)      普通    替换全部
        public String replaceFirst(String regex, String replacement)    普通    替换首个
        public String[] split(String regex)                             普通    正则拆分
        public String[] split(String regex, int limit)                  普通    正则拆分

    范例：实现字符串的替换（删除掉非字母与数字）
        String str = "sjdlajdkaj@@!$#$^&^*dsadd12123";  //要判断的数据
        String regex = "[^a-zA-Z0-9]+";    //正则表达式 一位及以上一起匹配
        System.out.println(str.replaceAll(regex, ""));
    范例：实现字符串的拆分（按字母拆分，把所有的数字删除）
        String str2 = "a1b22c333d4444e55555f666666g"; //要判断的数据
        String regex2 = "\\d";    //正则表达式  a、b、、c、、、d、、、、e、、、、、f、、、、、、g、
        String result2 [] = str2.split(regex2);
        for (int x = 0; x < result2.length; x ++) {
            System.out.print(result2[x] + "、");
        }
        String str3 = "a1b22c333d4444e55555f666666g"; //要判断的数据
        String regex3 = "\\d+";   //正则表达式  a、b、c、d、e、f、g、
        String result3 [] = str3.split(regex3);
        for (int x = 0; x < result3.length; x ++) {
            System.out.print(result3[x] + "、");
        }
        在正则处理的时候对于拆分与替换的操作相对容易一些，但是比较麻烦的是数据验证部分
    范例：判断一个数据是否为小数，如果是小数则将其变为double类型
        String str = "100.32";  //要判断的数据
        String regex = "\\d+(\\.\\d+)?";   //正则表达式  \\d+\\.\\d+  是 整数.整数
        //      (\\.\\d+)?    表示    .整数  一起出现0次或1次      
        //      ()            表示    一起出现    
        System.out.println(str.matches(regex));
    范例：现在判断一个字符串是否由日期所组成，如果是由日期所组成则将其转为Date类型
        String str = "1984-10-15";  //要判断的数据
        String regex = "\\d{4}-\\d{2}-\\d{2}";  //正则表达式
        if (str.matches(regex)) {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse(str));
        }
        需要注意的是，正则表达式无法对里面的内容进行判断，只能对格式进行判断处理
    范例：判断给定的电话号码是否正确
        电话号码：51283346
            String str = "51283346";    //要判断的数据
            String regex = "\\d{7,8}";  //正则表达式
            System.out.println(str.matches(regex));
        电话号码：01051283346
            String str = "(010)51283346";    //要判断的数据
            String regex = "(\\d{3,4})?\\d{7,8}";  //正则表达式
            System.out.println(str.matches(regex));
        电话号码：(010)-51283346
            String str = "(010)51283346";    //要判断的数据
            String regex = "((\\d{3,4})|(\\(\\d{3,4}\\)))?\\d{7,8}";  //正则表达式
            //              (          |  \(         \)
            //              ()   要写成  \\(   \\) 才可以
            System.out.println(str.matches(regex));

    范例：利用正则实现email地址格式的验证
        email的用户名可以由字母、数字、_组成
        email的域名可以由字母、数字、_、-所组成
        域名的后缀必须是： .cn、.com、.net、.com.cn、gov
        String str = "mldnjava888@mldn.cn";  //要判断的数据
        String regex = "[a-zA-Z0-9]\\w+@\\w+.(cn|com|com.cn|gov)";  //正则表达式
        System.out.println(str.matches(regex));
*/
import java.text.SimpleDateFormat;
public class StringRegexDemo {
    public static void main(String[] args) throws Exception {
        /*
        String str = "sjdlajdkaj@@!$#$^&^*dsadd12123";  //要判断的数据
        String regex = "[^a-zA-Z0-9]+";    //正则表达式 一位及以上一起匹配
        System.out.println(str.replaceAll(regex, ""));
        */
        /*
        String str2 = "a1b22c333d4444e55555f666666g"; //要判断的数据
        String regex2 = "\\d";    //正则表达式  a、b、、c、、、d、、、、e、、、、、f、、、、、、g、
        String result2 [] = str2.split(regex2);
        for (int x = 0; x < result2.length; x ++) {
            System.out.print(result2[x] + "、");
        }
        String str3 = "a1b22c333d4444e55555f666666g"; //要判断的数据
        String regex3 = "\\d+";   //正则表达式  a、b、c、d、e、f、g、
        String result3 [] = str3.split(regex3);
        for (int x = 0; x < result3.length; x ++) {
            System.out.print(result3[x] + "、");
        }
        */
        /*
        String str = "100.32";  //要判断的数据
        String regex = "\\d+(\\.\\d+)?";   //正则表达式  \\d+\\.\\d+  是 整数.整数
        //      (\\.\\d+)?    表示    .整数  一起出现0次或1次      
        //      ()            表示    一起出现    
        System.out.println(str.matches(regex));
        */
        /*
        String str = "1984-10-15";  //要判断的数据
        String regex = "\\d{4}-\\d{2}-\\d{2}";  //正则表达式
        if (str.matches(regex)) {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse(str));
        }
        */
        /*
        String str = "(010)51283346";    //要判断的数据
        String regex = "((\\d{3,4})|(\\(\\d{3,4}\\)))?\\d{7,8}";  //正则表达式
        //              (          |  \(         \)
        //              ()   要写成  \\(   \\) 才可以
        System.out.println(str.matches(regex));
        */
        String str = "mldnjava888@mldn.cn";  //要判断的数据
        String regex = "[a-zA-Z0-9]\\w+@\\w+.(cn|com|com.cn|gov)";  //正则表达式
        System.out.println(str.matches(regex));
    }
}