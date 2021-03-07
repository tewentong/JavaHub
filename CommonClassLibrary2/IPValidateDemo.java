/*
    编写正则表达式，判断给定的是否是一个合法的IP地址
    IP地址的组成就是数字，对于数字的组成有一个基础的要求，第一位的内容只能是 无、1、2，后面的内容是0-9，第三位的内容是0-9
*/
class Validator0307 {
    public static boolean validateIP(String ip) {
        if (ip == null | "".equals(ip)) {
            return false;
        }
        String regex = "([12]?[0-9]?[0-9]\\.){3}([12]?[0-9]?[0-9])";
        if (ip.matches(regex)) {    //验证通过，还需要对IP地址进行拆分处理
            String result [] = ip.split("\\."); //进行拆分
            for (int x = 0 ; x < result.length; x ++)  {
                int temp = Integer.parseInt(result[x]);
                System.out.println(temp);
                if (temp > 255) {
                    return false;
                }
            }
        }else {
            return false;
        }
        return true;
    }
}
public class IPValidateDemo {
    public static void main(String [] args) {
        String str = "192.168.1.2"; //"192.168.1.299"
        System.out.println(Validator0307.validateIP(str));
    }    
}