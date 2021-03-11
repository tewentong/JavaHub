
/*
    文件保存处理
        从键盘输入文件的内容和要保存的文件名称，然后根据输入的名称创建文件，并将内容保存到文件中
        在本程序里面只要求开发者保存的是文件名称，而并没有设置文件路径，那么对于文件路径就应该在程序启动之间就准备好
        1.定义一个文件操作的服务接口
            interface IFileService {
                public static final String SAVE_DIR = "/home/kwj-at-lzu/Java/CommonClassLibrary3/Demo" + File.separator + "mldndata"
                                                    + File.separator;
                **
                 * 定义文件的保存处理方法
                 * @return 保存成功返回true，否则返回false
                 *
                public boolean save();
            }
        2.在InputUtil类里面追加有输入字符串的方法
            class InputUtil031102 {
                private InputUtil031102() {
                }

                public static String getString(String prompt) {
                    String str = null;
                    boolean flag = true;
                    while (flag) {
                        Scanner input = new Scanner(System.in);
                        System.out.print(prompt);
                        if (input.hasNext()) {
                            str = input.next().trim();
                            if (!"".equals(str)) {    //不是空字符串
                                flag = false;   //结束循环                    
                            }else {
                                System.out.println("输入的内容不允许为空");
                            }
                        } else {
                            System.out.println("输入的内容不允许为空");
                        }
                    }
                    return str;
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
*/
package Demo;

import java.io.File;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

interface IFileService {
    public static final String SAVE_DIR = "/home/kwj-at-lzu/Java/CommonClassLibrary3/Demo" + File.separator + "mldndata"
            + File.separator;

    /**
     * 定义文件的保存处理方法
     * 
     * @return 保存成功返回true，否则返回false
     */
    public boolean save();
}

class InputUtil031102 {
    private InputUtil031102() {
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

class FileServiceImpl implements IFileService {
    private String name;
    private String content;

    public FileServiceImpl() {
        this.name = InputUtil031102.getString("请输入保存文件名称");
        this.content = InputUtil031102.getString("请输入保存文件的内容");
    }

    @Override
    public boolean save() {
        File file = new File(IFileService.SAVE_DIR + this.name);
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileOutputStream(file));
            out.print(this.content);
        } catch (FileNotFoundException e) {
            return false;
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return true;
    }
}

class FileFactory {
    private FileFactory() {
    }

    public static IFileService getInstance() {
        return new FileServiceImpl();
    }
}

public class FileSaveDemo {
    // 静态代码块优先于主方法执行
    static { // 项目启动的时候该路径已经首先创建
        File file = new File(IFileService.SAVE_DIR); // 路径，但是这个文件目录有可能不存在
        if (!file.exists()) { // 文件目录不存在
            file.mkdirs(); // 创建目录
        }
    }

    public static void main(String[] args) throws Exception {
        IFileService fileService = FileFactory.getInstance();
        fileService.save();
    }
}