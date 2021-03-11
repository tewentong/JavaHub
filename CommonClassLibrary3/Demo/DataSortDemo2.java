/*
    数据排序处理深入
        将上一题中的内容进行扩展，可以将全部输入的信息保存在文件中，还可以添加信息，并可以显示全部的数据
        如果此时要进行内容的保存，那么首先一定要确认好所有输入数据的保存位置，所有的数据之间如果要想沿用之前设计的结构
            则数据文件里面的保存应该做到格式统一，即：“姓名：成绩|”的形式进行存储，
            而在进行数据添加的时候可以添加两类数据：“单独的内容”、“一组内容”
            还有一个前提：暂时不去考虑数据过大的问题
        1.设置一个文件的处理类，该类之中只是提供有数据的增加以及读取
            class FileUtil0311 {
                public static String load(File file) {
                    Scanner scan = null;
                    try {
                        scan = new Scanner(file); // 文件加载
                        if (scan.hasNext()) {
                            String str = scan.next(); // 获取数据
                            return str;
                        }
                        return null;
                    } catch (Exception e) {
                        return null;
                    } finally {
                        if (scan != null) {
                            scan.close();
                        }
                    }
                }

                public static boolean append(File file, String content) {
                    PrintStream out = null;
                    try {
                        out = new PrintStream(new FileOutputStream(file, true));
                        out.print(content); // 内容追加
                        return true;
                    } catch (Exception e) {
                        return false;
                    } finally {
                        if (out != null) {
                            out.close();
                        }
                    }
                }
            }
        2.扩充IStudentService的操作方法
            interface IStudentService2 {
                public void append(String str); // 追加数据并且保存到文件里面
                public Student031102[] getData(); // 获取排序数据
            }
        3.修改StudentServiceImpl0311中的功能
            class StudentServiceImpl0311 implements IStudentService2 {
                private static final File SAVE_FILE = new File(
                        "/home/kwj-at-lzu/Java/CommonClassLibrary3/Demo" + File.separator + "student.txt");
                private String content;
                private Student031102[] students;

                public StudentServiceImpl0311() {
                    this.content = FileUtil0311.load(SAVE_FILE);    //读取已有的数据文件内容
                    this.handle(); // 进行数据处理
                }

                @Override
                public void append(String str) {
                    if (str.startsWith("|")) { // 最前面有“|”
                        str = str.substring(1); // 截取后面的部分
                    }
                    if (!str.endsWith("|")) { // 数据合理，可以直接追加
                        str = str + "|"; // 与后面的数据进行分割
                    }
                    FileUtil0311.append(SAVE_FILE, str); // 数据追加处理
                }

                private void handle() { // 进行字符串数据的处理操作
                    String result[] = this.content.split("\\|");
                    this.students = new Student031102[result.length];
                    for (int x = 0; x < result.length; x++) {
                        String temp[] = result[x].split(":");
                        this.students[x] = new Student031102(temp[0], Double.parseDouble(temp[1]));
                    }
                }

                @Override
                public Student031102[] getData() {
                    Arrays.sort(this.students);
                    return this.students;
                }
            }
        4.此时工厂类不再需要输入数据
            class StudentFactory0311 {
                private StudentFactory0311() {}
                public static IStudentService2 getInstance() {
                    return new StudentServiceImpl0311();
                }
            }
        5.定义一个菜单处理
            class Menu0311 {
                public Menu0311() {
                    this.choose();
                }

                public void choose() {
                    this.show();
                    String choose = InputUtil031105.getString("请进行选择： ");
                    switch (choose) {
                        case "1": { // 接收输入数据
                            String str = InputUtil031105.getString("请输入要追加的数据数据： ");
                            IStudentService2 studentService = StudentFactory0311.getInstance();
                            studentService.append(str); // 追加数据
                            choose(); // 重复出现
                        }
                        case "2": { // 逆序显示数据
                            IStudentService2 studentService = StudentFactory0311.getInstance();
                            System.out.println(Arrays.toString(studentService.getData()));
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
                    System.out.println("【2】逆序显示所有的学生数据\n");
                    System.out.println("【0】结束程序执行\n");
                    System.out.println("\n\n\n");
                }
            }
        6.定义一个测试类
            public class DataSortDemo2 {
                public static void main(String[] args) throws Exception {
                    new Menu0311();
                }
            }
*/
package Demo;

import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

class Student031102 implements Comparable<Student031102> {
    private String name;
    private double score;

    public Student031102(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String toString() {
        return "姓名： " + this.name + "、成绩： " + this.score;
    }

    @Override
    public int compareTo(Student031102 obj) {
        if (this.score > obj.score) {
            return -1;
        } else if (this.score < obj.score) {
            return 1;
        } else {
            return 0;
        }
    }
}

class InputUtil031105 {
    private InputUtil031105() {
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

interface IStudentService2 {
    public void append(String str); // 追加数据并且保存到文件里面

    public Student031102[] getData(); // 获取排序数据
}

class StudentServiceImpl0311 implements IStudentService2 {
    private static final File SAVE_FILE = new File(
            "/home/kwj-at-lzu/Java/CommonClassLibrary3/Demo" + File.separator + "student.txt");
    private String content;
    private Student031102[] students;

    public StudentServiceImpl0311() {
        this.content = FileUtil0311.load(SAVE_FILE); // 读取已有的数据文件内容
        this.handle(); // 进行数据处理
    }

    @Override
    public void append(String str) {
        if (str.startsWith("|")) { // 最前面有“|”
            str = str.substring(1); // 截取后面的部分
        }
        if (!str.endsWith("|")) { // 数据合理，可以直接追加
            str = str + "|"; // 与后面的数据进行分割
        }
        FileUtil0311.append(SAVE_FILE, str); // 数据追加处理
    }

    private void handle() { // 进行字符串数据的处理操作
        if (this.content == null | "".equals(this.content)) {
            return; // 没有数据可以处理
        }
        String result[] = this.content.split("\\|");
        this.students = new Student031102[result.length];
        for (int x = 0; x < result.length; x++) {
            String temp[] = result[x].split(":");
            this.students[x] = new Student031102(temp[0], Double.parseDouble(temp[1]));
        }
    }

    @Override
    public Student031102[] getData() {
        Arrays.sort(this.students);
        return this.students;
    }
}

class FileUtil0311 {
    public static String load(File file) {
        Scanner scan = null;
        try {
            scan = new Scanner(file); // 文件加载
            if (scan.hasNext()) {
                String str = scan.next(); // 获取数据
                return str;
            }
            return null;
        } catch (Exception e) {
            return null;
        } finally {
            if (scan != null) {
                scan.close();
            }
        }
    }

    public static boolean append(File file, String content) {
        PrintStream out = null;
        try {
            out = new PrintStream(new FileOutputStream(file, true));
            out.print(content); // 内容追加
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}

class StudentFactory0311 {
    private StudentFactory0311() {
    }

    public static IStudentService2 getInstance() {
        return new StudentServiceImpl0311();
    }
}

class Menu0311 {
    public Menu0311() {
        this.choose();
    }

    public void choose() {
        this.show();
        String choose = InputUtil031105.getString("请进行选择： ");
        switch (choose) {
            case "1": { // 接收输入数据
                String str = InputUtil031105.getString("请输入要追加的数据数据： ");
                IStudentService2 studentService = StudentFactory0311.getInstance();
                studentService.append(str); // 追加数据
                choose(); // 重复出现
            }
            case "2": { // 逆序显示数据
                IStudentService2 studentService = StudentFactory0311.getInstance();
                System.out.println(Arrays.toString(studentService.getData()));
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
        System.out.println("【2】逆序显示所有的学生数据\n");
        System.out.println("【0】结束程序执行\n");
        System.out.println("\n\n\n");
    }
}

public class DataSortDemo2 {
    public static void main(String[] args) throws Exception {
        new Menu0311();
    }
}