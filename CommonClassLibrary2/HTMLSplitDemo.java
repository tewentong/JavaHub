/*
    给定下面的HTML代码：
        <font face="Arial,Serif" size="+2" color="red">
        要求对内容进行拆分，拆分之后的结果是：
            face Arial,Serif
            size +2
            color red
    对于此时的操作最简单的做法就是进行分组处理
*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class HTMLSplitDemo {
    public static void main(String [] args) {
        String str = "<font face=\"Arial,Serif\" size=\"+2\" color=\"red\">";
        String regex = "\\w+=\"[a-zA-Z0-9,\\+]+\"";
        Matcher matcher = Pattern.compile(regex).matcher(str);
        while (matcher.find()) {
            String temp = matcher.group(0);
            String result [] = temp.split("=");
            System.out.println(result[0] + " " + result[1].replaceAll("\"", ""));
        }
    }
}