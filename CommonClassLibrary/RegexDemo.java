/*
    需要使用到java.util.regex开发包中提供的正则处理类，这个包中一共定义有两个类
    Pattern(正则表达式编译)、Mathcer(匹配)
    Pattern类提供正则表达式的编译处理支持：public static Pattern compile(String regex);
        同时也提供字符串的拆分操作支持：public String[] split(CharSequence input);
            String str = "j309uwhio3yh83r3 orh2$#@^#^#@$5djflksdjf";
            String regex = "[^a-zA-Z]+";
            Pattern pat = Pattern.compile(regex);   //编译正则表达式
            String result [] = pat.split(str); //拆分
            for (int x = 0; x < result.length; x ++){
                System.out.println(result[x] + "、");
            }
    Matcher类实现了正则匹配的处理类，这个类的对象实例化依靠Pattern类完成：
        Pattern类提供的方法：public Matcher matcher(CharSequence input);
    当获取了Matcher类的对象之后就可以利用该类中的方法进行如下操作：
        正则匹配：public boolean matches();
        字符串替换：public String replaceAll(String replacement);
        范例：字符串匹配
            String str = "101";
            String regex = "\\d+";
            Pattern pat = Pattern.compile(regex);   //编译正则表达式
            Matcher mat = pat.matcher(str);
            System.out.println(mat.matches());  //true

    如果纯粹的是以拆分、替换、匹配三种操作为例，根本用不到java.util.regex开发包，只依靠String类就都可以实现
        但是Matcher类里面提供由分组的功能，而这种分组的功能是Stirng不具备的
            //要求取出 "#{内容}"标记中的所有内容
            String str = "INSERT INTO dept(deptno, dname, loc) VALUES (#{deptno},#{dname}.#{loc})";
            String regex = "#\\{\\w+\\}";
            Pattern pat = Pattern.compile(regex);   //编译正则表达式
            Matcher mat = pat.matcher(str);
            while(mat.find()) { //是否有匹配成功的内容
                System.out.println(mat.group(0));   //#{deptno}
                                                    //#{dname}
                                                    //#{loc}
                System.out.println(mat.group(0).replaceAll("#|\\{|\\}", ""));   //deptno
                                                                                //dname
                                                                                //loc
            }
        java.util.regex开发包，如果不是进行一些更为复杂的正则处理是很难使用到的，而String类提供的功能只适合正则的基本操作
*/
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class RegexDemo {
    public static void main(String[] args) {
        /*
        String str = "j309uwhio3yh83r3 orh2$#@^#^#@$5djflksdjf";
        String regex = "[^a-zA-Z]+";
        Pattern pat = Pattern.compile(regex);   //编译正则表达式
        String result [] = pat.split(str); //拆分
        for (int x = 0; x < result.length; x ++){
            System.out.println(result[x] + "、");
        }
        */
        /*
        String str = "101";
        String regex = "\\d+";
        Pattern pat = Pattern.compile(regex);   //编译正则表达式
        Matcher mat = pat.matcher(str);
        System.out.println(mat.matches());
        */

        //要求取出 "#{内容}"标记中的所有内容
        String str = "INSERT INTO dept(deptno, dname, loc) VALUES (#{deptno},#{dname}.#{loc})";
        String regex = "#\\{\\w+\\}";
        Pattern pat = Pattern.compile(regex);   //编译正则表达式
        Matcher mat = pat.matcher(str);
        while(mat.find()) { //是否有匹配成功的内容
            System.out.println(mat.group(0));   //#{deptno}
                                                //#{dname}
                                                //#{loc}
            System.out.println(mat.group(0).replaceAll("#|\\{|\\}", ""));   //deptno
                                                                            //dname
                                                                            //loc
        }
    }
}