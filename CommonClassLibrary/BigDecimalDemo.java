/*
    BigDecimal操作形式和BigInteger是非常类似的，都有基础的数学支持
*/
import java.math.BigDecimal;
import java.math.RoundingMode;
class MathUtil030101 {
    private MathUtil030101() {}
    /*
        实现数据的四舍五入
        @param num 要进行四舍五入的数字
        @param scale 四舍五入保留的小数位数
        @return 四舍五入后的处理结果
    */
    public static double round(double num, int scale) {
        return new BigDecimal(num).divide(new BigDecimal(1.0), scale, RoundingMode.HALF_UP).doubleValue();  //BigDecimal是Number的子类，可以用doubleValue()拆箱
        //HALF_UP是向上进位
    }
}
public class BigDecimalDemo {
    public static void main(String[] args) throws Exception {
        BigDecimal bigA = new BigDecimal("212122321321");
        BigDecimal bigB = new BigDecimal("21212");
        System.out.println("加法运算： " + bigA.add(bigB));
        BigDecimal result[] = bigA.divideAndRemainder(bigB);
        System.out.println("除法计算，商： " + result[0] + "余数： " + result[1]);

        //看是否完成了四舍五入
        System.out.println(MathUtil030101.round(19.237332, 2)); //19.24
    } 
}

/*
    但是在使用BigDecimal的时候有一个数据的进位问题，在这个类里定义有如下的除法计算
        除法计算：public BigDecimal divide(BigDecimal divisor, int scale, RoundingMode roundingMode)
    
    范例：使用BigDecimal实现四舍五入处理
    public static double round(double num, int scale) {
        return new BigDecimal(num).divide(new BigDecimal(1.0), scale, RoundingMode.HALF_UP).doubleValue();  //BigDecimal是Number的子类，可以用doubleValue()拆箱
        //HALF_UP是向上进位
    }

    Math的处理由于使用的都是基本数据类型，所以性能一定要高于大数字处理类
*/