/*
    数字大小比较
        编写java程序，输入3个整数，并求出3个整数的最大值和最小值
        如果想要进行数字的输入处理，那么应该保证输入错误的时候可以重新输入
            那么为了可以达到重用的设计，应该准备一个单独的输入数据类
            1.定义一个输入工具类
                class InputUtil {
                    private InputUtil() {
                    }

                    **
                     * 实现键盘接收数字的操作
                     * 
                     * @param prompt 提示信息
                     * @return 一个可以使用的数字
                     *
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
            2.定义数据的输入处理
                interface INumberService {
                    **
                     * 输入数据并且返回数据数据的最大值和最小值
                     * @param count 表示要输入数据的个数
                     * @return  包含有两个内容，第一个是最大值，第二个是最小值
                     *
                    public int[] stat(int count);
                }
            3.定义接口的实现子类
                class NumberServiceImpl implements INumberService {
                    @Override
                    public int[] stat(int count) {
                        int result[] = new int[2]; // 定义返回的结果
                        int data[] = new int[count]; // 开辟一个数组
                        for (int x = 0; x < data.length; x++) { // 数字的循环输入
                            data[x] = InputUtil.getInt("请输入第" + (x + 1) + "个数字");
                        }
                        result[0] = data[0]; // 最大值
                        result[1] = data[0]; // 最小值
                        for (int x = 0; x < data.length; x++) {
                            if (data[x] > result[0]) {
                                result[0] = data[x];
                            }
                            if (data[x] < result[1]) {
                                result[1] = data[x];
                            }
                        }
                        return result;
                    }
                }
            4.定义工厂类获取接口对象
                class NumberFactory {
                    private NumberFactory() {}
                    public static INumberService getInstance() {
                        return new NumberServiceImpl();
                    }
                }
            5.编写测试程序类
                
*/
package Demo;

import java.util.Scanner;

class InputUtil {
    private InputUtil() {
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

interface INumberService {
    /**
     * 输入数据并且返回数据数据的最大值和最小值
     * 
     * @param count 表示要输入数据的个数
     * @return 包含有两个内容，第一个是最大值，第二个是最小值
     */
    public int[] stat(int count);
}

class NumberServiceImpl implements INumberService {
    @Override
    public int[] stat(int count) {
        int result[] = new int[2]; // 定义返回的结果
        int data[] = new int[count]; // 开辟一个数组
        for (int x = 0; x < data.length; x++) { // 数字的循环输入
            data[x] = InputUtil.getInt("请输入第" + (x + 1) + "个数字");
        }
        result[0] = data[0]; // 最大值
        result[1] = data[0]; // 最小值
        for (int x = 0; x < data.length; x++) {
            if (data[x] > result[0]) {
                result[0] = data[x];
            }
            if (data[x] < result[1]) {
                result[1] = data[x];
            }
        }
        return result;
    }
}

class NumberFactory {
    private NumberFactory() {
    }

    public static INumberService getInstance() {
        return new NumberServiceImpl();
    }
}

public class NumberCompareDemo {
    public static void main(String[] args) throws Exception {
        INumberService numberService = NumberFactory.getInstance();
        int result[] = numberService.stat(5);
        System.out.println("最大值： " + result[0] + "、最小值： " + result[1]);
    }
}