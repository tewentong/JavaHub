/*
    大数字的操作类，可以实现海量数字的计算（能提供的也只是基础计算）
    现在假设一个数字很大，超过了Double的范围，那么这个时候并没有一种数据类型可以保存下此类内容，最早的时候只能通过String保存
        String strA = "120";
        String strB = "230";
        如果现在需要进行加法计算，那么就需要逐位拆分，每一位自己计算，而后自己独立控制进位处理。
        这样的开发难度非常高，所以为了解决这类问题，提供有两个大数字的操作类：BigInteger、BigDecimal

    之前分析了，当数字很大的时候只能够利用字符串描述数字操作，所以这一点可以观察两个大数字类的构造方法
        BigInteger类构造：public BigInteger(String val);
        BigDecimal类构造：public BigDecimal(Stirng val);

    既然在进行数学除法的时候有可能无法进行整除处理，那么就可以使用其他的除法计算来求出余数
        求余： public BigInteger[] divideAndRemainder(BigInteger val), 数组第一个元素为熵，第二个元素为余数

    如果在开发中真进行计算的时候，该计算没有超过基本数据类型所包含的位数，强烈不建议使用大数字类，因为这种计算性能是很差的
*/

/*
    范例：使用BigInteger实现四则运算
*/
import java.math.BigInteger;

public class BigIntegerDemo {
    public static void main(String[] args) throws Exception {
        BigInteger bigA = new BigInteger("23423323123123");
        BigInteger bigB = new BigInteger("2121231");
        System.out.println("加法操作" + bigA.add(bigB));
        System.out.println("减法操作" + bigA.subtract(bigB));
        System.out.println("乘法操作" + bigA.multiply(bigB));
        System.out.println("除法操作" + bigA.divide(bigB));
        /*
        需要注意的是，虽然提供有大数字操作类，但是整体的操作之中还是要考虑到性能问题
        范例：观察性能问题
        */
        //System.out.println(bigA.pow(Integer.MAX_VALUE));    //计算次方

        //此时的计算过程是非常缓慢的，任何电脑都是有极限的。
        //既然在进行数学除法的时候有可能无法进行整除处理，那么就可以使用其他的除法计算来求出余数
        //  求余： public BigInteger[] divideAndRemainder(BigInteger val)

        BigInteger result [] = bigA.divideAndRemainder(bigB);
        System.out.println("商： " + result[0] + "、 余数： " + result[1]);
    }
}