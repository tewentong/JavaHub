/*
    通过之前的一系列分析可以发现，String是一个非常万能的类型：
    因为String不仅仅支持各种字符串的操作，也支持有向各个数据类型的转换功能，所以在项目的开发之中，只要是用户输入的信息基本都用String表示。
    于是为了在向其他数据类型转换的时候，为了保证转换的正确性，往往需要对其进行一些复杂的验证处理
    那么这种情况下，如果只是单纯地依靠String类中的方法是非常麻烦的

    认识正则表达式
        现在假设有一个字符串要求你判断字符串是否由数字所组成，如果由数字所组成则将其变为数字进行乘法计算
        public class RegularExpressionDemo {
            public static void main(String[] args) throws Exception {
                String str = "123";
                if (isNumber(str)) {
                    int num = Integer.parseInt(str);
                    System.out.println(num * 2);
                }
            }
            public static boolean isNumber(String str) {
                char data [] = str.toCharArray();
                for (int x = 0 ; x < data.length; x ++) {
                    if (data[x] > '9' || data[x] < '0') {
                        return false;
                    }
                }
                return true;
            }
        }
        实际上，这种验证的功能是非常简单的，但是如此简单的功能却需要开发者编写大量的程序逻辑代码，如果是更加复杂的验证呢？
        那么，在这样的情况下，对于验证而言最好的做法就是利用正则表达式来完成。
        范例：使用正则表达式实现同样的效果
            public static void main(String[] args) {
                String str = "123";
                if (str.matches("\\d+")) {
                    int num = Integer.parseInt(str);
                    System.out.println(num * 2);
                }
            } 
    正则表达式最早从perl语言里发展而来的，而后在JDK1.4以前如果需要使用到正则表达式的相关定义则需要引入其他的*.jar文件
    但是在JDK1.4之后，正则已经默认被JDK所支持，并且提供有java.util.regex开发包，同时针对String类也进行了修改，使其有方法直接支持正则处理。
*/
public class RegularExpressionDemo {
    /*
    public static void main(String[] args) throws Exception {
        String str = "123";
        if (isNumber(str)) {
            int num = Integer.parseInt(str);
            System.out.println(num * 2);
        }
    }
    public static boolean isNumber(String str) {
        char data [] = str.toCharArray();
        for (int x = 0 ; x < data.length; x ++) {
            if (data[x] > '9' || data[x] < '0') {
                return false;
            }
        }
        return true;
    }
    */
    public static void main(String[] args) {
        String str = "123";
        if (str.matches("\\d+")) {
            int num = Integer.parseInt(str);
            System.out.println(num * 2);
        }
    }
}