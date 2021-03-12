/*
    用户登录
        完成系统登陆程序，从命令行输入用户名和密码
            如果没有输入用户名和密码，则提示用户输入用户名和密码：
            如果输入了用户名但是没有输入密码，则提示用户输入密码，
            然后判断用户名是否为mldn，密码是否为hello
            如果正确，则提示登陆成功；如果错误，显示登陆失败的信息，用户再次输入用户名和密码
            连续3次输入错误后系统退出
        对于此时的程序发现可以将用户名和密码同时输入，也可以先输入用户名，而后输入密码
            如果超过了3次就表示登陆结束了
            对于用户名和密码的使用可以采用“用户名/密码”的形式完成，如果没有发现“/”表示没有输入密码
        1.定义用户的操作接口
            interface IUserService {
                public boolean isExit();
                public boolean login(String name, String password);
            }
        2.定义操作接口的子类
            class UserServiceImpl implements IUserService {
                private int count = 0; // 作为登陆统计

                @Override
                public boolean isExit() {
                    return this.count >= 3; //执行登陆退出的条件
                }

                @Override
                public boolean login(String name, String password) {
                    this.count ++;
                    return "mldn".equals(name) && "hello".equals(password);
                }
            }
        3.对于登陆失败的检测处理操作，应该单独定义一个用户的代理操作类
            class UserServiceProxy implements IUserService {
                private IUserService userService;

                public UserServiceProxy(IUserService userService) {
                    this.userService = userService;
                }

                @Override
                public boolean isExit() {
                    return this.userService.isExit();
                }

                @Override
                public boolean login(String name, String password) {
                    while (!this.isExit()) { // 不进行退出
                        String inputData = InputUtil031107.getString("请输入登陆信息： ");
                        if (inputData.contains("/")) { // 输入了用户名和密码
                            String temp[] = inputData.split("/"); // 数据拆分
                            if (this.userService.login(temp[0], temp[1])) { // 登陆认证
                                return true; // 循环结束了
                            } else {
                                System.out.println("登陆失败，错误的用户名或密码");
                            }
                        } else { // 现在只有用户名
                            String pwd = InputUtil031107.getString("请输入密码： ");
                            if (this.userService.login(inputData, pwd)) { // 登陆认证
                                return true; // 循环结束了
                            } else {
                                System.out.println("登陆失败，错误的用户名或密码");
                            }
                        }
                    }
                    return false;
                }
            }
        4.工厂类定义
            class UserFactory {
                private UserFactory() {}
                public static IUserService getInstance() {
                    return new UserServiceProxy(new UserServiceImpl());
                }
            }
        5.定义测试类处理
            public class UserLoginDemo {
                public static void main(String [] args) throws Exception {
                    System.out.println(UserFactory.getInstance().login(null, null));
                }
            }

        真实业务只实现核心功能，辅助逻辑处理交给代理控制
*/
package Demo;

import java.util.Scanner;

interface IUserService {
    public boolean isExit();

    public boolean login(String name, String password);
}

class UserServiceImpl implements IUserService {
    private int count = 0; // 作为登陆统计

    @Override
    public boolean isExit() {
        return this.count >= 3; // 执行登陆退出的条件
    }

    @Override
    public boolean login(String name, String password) {
        this.count++;
        return "mldn".equals(name) && "hello".equals(password);
    }
}

class UserServiceProxy implements IUserService {
    private IUserService userService;

    public UserServiceProxy(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isExit() {
        return this.userService.isExit();
    }

    @Override
    public boolean login(String name, String password) {
        while (!this.isExit()) { // 不进行退出
            String inputData = InputUtil031107.getString("请输入登陆信息： ");
            if (inputData.contains("/")) { // 输入了用户名和密码
                String temp[] = inputData.split("/"); // 数据拆分
                if (this.userService.login(temp[0], temp[1])) { // 登陆认证
                    return true; // 循环结束了
                } else {
                    System.out.println("登陆失败，错误的用户名或密码");
                }
            } else { // 现在只有用户名
                String pwd = InputUtil031107.getString("请输入密码： ");
                if (this.userService.login(inputData, pwd)) { // 登陆认证
                    return true; // 循环结束了
                } else {
                    System.out.println("登陆失败，错误的用户名或密码");
                }
            }
        }
        return false;
    }
}

class InputUtil031107 {
    private InputUtil031107() {
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

class UserFactory {
    private UserFactory() {
    }

    public static IUserService getInstance() {
        return new UserServiceProxy(new UserServiceImpl());
    }
}

public class UserLoginDemo {
    public static void main(String[] args) throws Exception {
        System.out.println(UserFactory.getInstance().login(null, null));
    }
}