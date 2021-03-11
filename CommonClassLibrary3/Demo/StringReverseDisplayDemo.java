/*
    字符串的逆序显示处理
        从键盘传入多个字符串到程序中，并将它们按逆序输出在屏幕上
        本程序之中应该考虑到如下的几种设计：
            既然字符串的内容可以随时修改，那么最好建立一个StringBuffer做保存
            在进行数据处理的时候应该由用户自己来决定是否继续输入
        1.定义字符串的操作标准
            interface IStringService {
                public void append(String str); //追加数据
                public String[] reverse();  //反转处理
            }
        2.实现子类里面就使用StringBuffer来操作
            class StringServiceImpl implements IStringService {
                private StringBuffer data = new StringBuffer();

                @Override
                public void append(String str) {
                    this.data.append(str).append("|");
                }

                @Override
                public String[] reverse() {
                    String result[] = this.data.toString().split("\\|");
                    int center = result.length / 2;
                    int head = 0;
                    int tail = result.length - 1;
                    for (int x = 0; x < center; x++) {
                        String temp = result[head];
                        result[head] = result[tail];
                        result[tail] = temp;
                    }
                    return result;
                }
            }
        3.定义工厂类
            class StringFactory {
                private StringFactory() {}
                public static IStringService getInstance() {
                    return new StringServiceImpl();
                }
            }
        4.定义一个Menu处理类，采用交互式的界面形式完成处理
            class Menu {
                private IStringService stringService;

                public Menu() {
                    this.stringService = StringFactory.getInstance();
                    this.choose();
                }

                public void choose() {
                    this.show();
                    String choose = InputUtil031103.getString("请进行选择： ");
                    switch (choose) {
                        case "1": { // 接收输入数据
                            String str = InputUtil031103.getString("请输入字符串数据： ");
                            this.stringService.append(str); // 进行数据的保存
                            choose(); // 重复出现
                        }
                        case "2": { // 逆序显示数据
                            String result[] = this.stringService.reverse();
                            System.out.println(Arrays.toString(result)); // 直接输出
                            choose(); // 重复出现
                        }
                        case "0": {
                            System.out.println("下次再见，拜拜！");
                            System.exit(1);
                        }
                        default: {
                            System.out.println("您输入了非法的程序，无法进行处理，请确认后再次执行程序");
                            choose();
                        }
                    }
                }

                public void show() {
                    System.out.println("【1】追加字符串数据\n");
                    System.out.println("【2】逆序显示所有的字符串数据\n");
                    System.out.println("【0】结束程序执行\n");
                    System.out.println("\n\n\n");
                }
            }
        5.编写测试类
            public class StringReverseDisplayDemo {
                public static void main(String[] args) throws Exception {
                    new Menu(); // 启动程序界面
                }
            }
*/
package Demo;

import java.lang.StringBuffer;
import java.util.Scanner;
import java.util.Arrays;

interface IStringService {
    public void append(String str); // 追加数据

    public String[] reverse(); // 反转处理
}

class StringServiceImpl implements IStringService {
    private StringBuffer data = new StringBuffer();

    @Override
    public void append(String str) {
        this.data.append(str).append("|");
    }

    @Override
    public String[] reverse() {
        String result[] = this.data.toString().split("\\|");
        int center = result.length / 2;
        int head = 0;
        int tail = result.length - 1;
        for (int x = 0; x < center; x++) {
            String temp = result[head];
            result[head] = result[tail];
            result[tail] = temp;
        }
        return result;
    }
}

class StringFactory {
    private StringFactory() {
    }

    public static IStringService getInstance() {
        return new StringServiceImpl();
    }
}

class Menu {
    private IStringService stringService;

    public Menu() {
        this.stringService = StringFactory.getInstance();
        this.choose();
    }

    public void choose() {
        this.show();
        String choose = InputUtil031103.getString("请进行选择： ");
        switch (choose) {
            case "1": { // 接收输入数据
                String str = InputUtil031103.getString("请输入字符串数据： ");
                this.stringService.append(str); // 进行数据的保存
                choose(); // 重复出现
            }
            case "2": { // 逆序显示数据
                String result[] = this.stringService.reverse();
                System.out.println(Arrays.toString(result)); // 直接输出
                choose(); // 重复出现
            }
            case "0": {
                System.out.println("下次再见，拜拜！");
                System.exit(1);
            }
            default: {
                System.out.println("您输入了非法的程序，无法进行处理，请确认后再次执行程序");
                choose();
            }
        }
    }

    public void show() {
        System.out.println("【1】追加字符串数据\n");
        System.out.println("【2】逆序显示所有的字符串数据\n");
        System.out.println("【0】结束程序执行\n");
        System.out.println("\n\n\n");
    }
}

class InputUtil031103 {
    private InputUtil031103() {
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

public class StringReverseDisplayDemo {
    public static void main(String[] args) throws Exception {
        new Menu(); // 启动程序界面
    }
}