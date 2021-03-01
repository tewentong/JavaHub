
/*
    在国内有一款神奇的所谓的36选7的彩票，那么就可以利用Random实现随机生成彩票号码
    范例： 随即生成彩票号
        数字不能有0，数字不能重复
*/  
import java.util.Random;  
public class RandomDemo2 {
    public static void main(String[] args) throws Exception {
        int data [] = new int [7];  //开辟7个大小的空间
        int foot = 0; //操作data的脚标
        Random rand = new Random();
        while (foot < 7) {  //选择7个数字
            int num = rand.nextInt(37); //生成一个数字
            if (isUse(num, data)) { //该数字现在可以使用
                data[foot++] = num; //保存数据
            }
        }

        for (int x = 0; x < data.length; x++) {
            System.out.print(data[x]);
        }
        //排序        
        java.util.Arrays.sort(data);
        for (int x = 0; x < data.length; x++) {
            System.out.print(data[x] + "、");
        }
    }
    /*
        判断传入的数字是否为0以及是否在数组之中存在
        @param num 要判断的数字
        @param temp 已经存在的数据
        @return 如果该数字不是0且可以使用反推true, 否则返回false
    */
    public static boolean isUse(int num, int temp[]) {
        if (num == 0 ) {
            return false;
        }
        for (int x = 0; x < temp.length; x ++) {
            if (num == temp[x]) {
                return false;
            }
        }
        return true;
    }
}