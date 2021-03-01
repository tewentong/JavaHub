class MathUtil {
    private MathUtil() {}
    /*
        实现数据的四舍五入操作
        @param num 要进行四舍五入操作的数字
        @param scale 四舍五入保留的小数位数
        @return 四舍五入处理后的结果
    */
    public static double round(double num, int scale) {
        return Math.round(num * Math.pow(10, scale)) / Math.pow(10, scale);
    }
}
public class MyMathDemo030101 {
    public static void main(String[] args) throws Exception {
        System.out.println(MathUtil.round(19.86273, 2));    //19.86
    }
}
//Math类里面提供的基本上都是基础的数学公式，需要的时候需要自己重新整合