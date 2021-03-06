/*
    利用Random类产生5个1～30之间（包括1和30）的随机整数
    Random产生随机数的操作之中会包含有数字0，所以此时不应该存在有数字0

*/
import java.util.Random;
import java.util.Arrays;
class NumberFactory {
    private static Random random = new Random();
    /**
     * 通过随机数来生成一个数组的内容，该内容不包括0
     * @param len 要开辟的数组大小
     * @return 包含有随机数的内容
     */
    public static int [] create(int len) {
        int data [] = new int [len];
        int foot = 0;
        while (foot < data.length) {
            int num = random.nextInt(30);
            if (num != 0) {
                data[foot ++] = num;    //保存数据
            }
        }
        return data;
    }
}
public class Random0306Demo {
    public static void main(String[] args) {
        int result [] = NumberFactory.create(5);
        System.out.println(Arrays.toString(result));
    }
}