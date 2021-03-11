
/*
    奇偶数统计
        编写程序，当程序运行后，根据屏幕提示输入一个数字字符串，输入后统计有多少个偶数数字和奇数数字
        本质的流程是进行每一个字符串的拆分，而后进行数字的转换处理
        1.定义INumberService接口，进行数字的处理服务
            interface INumberService0311 {
                public int[] stat();    //进行统计
            }
        2.定义NumberServiceImpl接口子类
            class NumberServiceImpl0311 implements INumberService0311 {
                @Override
                public int[] stat() {
                    int stat[] = new int[] { 0, 0 }; // 第一个为偶数，第二个为奇数
                    String str = InputUtil031106.getString("请输入数字信息： ");
                    String result[] = str.split(""); // 按照每个字符拆分
                    for (int x = 0 ; x < result.length ; x ++) {
                        if (Integer.parseInt(result[x]) % 2 == 0)) {
                            stat[0] ++;
                        }else {
                            stat[1] ++;
                        }
                    }
                    return stat;
                }
            }
        3.建立工厂类
            class OddEvenFactory {
                private OddEvenFactory() {}
                public static INumberService0311 getInstance() {
                    return new NumberServiceImpl0311();
                }
            }
        4.定义主类调用程序
            
*/
package Demo;

import java.util.Scanner;
import java.util.Arrays;

interface INumberService0311 {
    public int[] stat(); // 进行统计
}

class NumberServiceImpl0311 implements INumberService0311 {
    @Override
    public int[] stat() {
        int stat[] = new int[] { 0, 0 }; // 第一个为偶数，第二个为奇数
        String str = InputUtil031106.getString("请输入数字信息： ");
        String result[] = str.split(""); // 按照每个字符拆分
        for (int x = 0; x < result.length; x++) {
            if (Integer.parseInt(result[x]) % 2 == 0) {
                stat[0]++;
            } else {
                stat[1]++;
            }
        }
        return stat;
    }
}

class InputUtil031106 {
    private InputUtil031106() {
    }

    public static String getString(String prompt) {
        String str = null;
        boolean flag = true;
        while (flag) {
            Scanner input = new Scanner(System.in);
            System.out.print(prompt);
            if (input.hasNext()) {
                str = input.next().trim();
                if (!"".equals(str)) { // 不是空字符串
                    flag = false; // 结束循环
                } else {
                    System.out.println("输入的内容不允许为空");
                }
            } else {
                System.out.println("输入的内容不允许为空");
            }
        }
        return str;
    }

    /**
     * 实现键盘接收数字的操作
     * 
     * @param prompt 提示信息
     * @return 一个可以使用的数字
     */
    public static int getInt(String prompt) {
        int num = 0;
        boolean flag = true;
        while (flag) {
            Scanner input = new Scanner(System.in);
            System.out.println(prompt); // 打印提示信息
            if (input.hasNext("\\d+")) {
                num = Integer.parseInt(input.next("\\d+"));
                flag = false;
            } else {
                System.out.println("输入的内容不是数字!");
            }
        }
        return num;
    }
}

class OddEvenFactory {
    private OddEvenFactory() {
    }

    public static INumberService0311 getInstance() {
        return new NumberServiceImpl0311();
    }
}

public class CountOddAndEvenDemo {
    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(OddEvenFactory.getInstance().stat()));
    }
}