/*
    java.util.Random类的主要功能是产生随机数，这个类主要依靠内部提供的方法来完成
        产生一个不大于边界的随机整数：public int nextInt(int bound);
*/
import java.util.Random;
public class RandomDemo {
public static void main(String[] args) throws Exception {
        Random rand = new Random();
        for (int x = 0; x < 10; x ++) {
            System.out.print(rand.nextInt(100) + "、");
        }
    }
}
