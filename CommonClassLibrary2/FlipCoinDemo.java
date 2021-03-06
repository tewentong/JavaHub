/*
    编写程序,用0~1之间的随机数来模拟扔硬币实验,统计扔1000次后正反出现次数
*/
import java.util.Random;
class Coin {
    private int front;  //保存正面次数
    private int back;   //保存反面次数
    private Random random = new Random();
    /**
     * 扔硬币的处理
     * @param num 扔硬币的执行次数
     */
    public void throwCoin(int num) {
        for (int x = 0 ; x < num ; x ++) {
            int temp = random.nextInt(2);
            if (temp == 0) {
                this.front ++;
            }else {
                this.back ++;
            }
        }
    }
    public int getFront() {
        return this.front;
    }
    public int getBack() {
        return this.back;
    }
}
public class FlipCoinDemo {
    public static void main(String[] args) {
        Coin coin = new Coin();
        coin.throwCoin(1000);
        System.out.println("正面出现的次数: " + coin.getFront());
        System.out.println("反面出现的次数: " + coin.getBack());
    }
}