/*
    数据排序处理
        从键盘输入以下数据，“TOM： 89|JERRY： 90|TONY： 95”，数据格式为
            “姓名：成绩|姓名：成绩|姓名：成绩”，对输入的内容按成绩进行排序，并将排序结果按照成绩由高到底排序
        对于排序的处理肯定使用Comparable接口完成，同时利用Arrays类来处理，这里面唯一不同的地方就在于此时的数据显示要通过键盘输入
        1.建立Student的程序类，并进行排序规则的配置
            class Student0311 implements Comparable<Student0311> {
                private String name;
                private double score;
                public Student0311(String name, double score) {
                    this.name = name;
                    this.score = score;
                }
                public String toString() {
                    return "姓名： " + this.name + "、成绩： " + this.score;
                }
                @Override
                public int compareTo(Student0311 obj) {
                    if (this.score > obj.score) {
                        return -1;
                    } else if (this.score < obj.score) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        2.建立数据的输入处理操作，因为牵扯到数据的拆分问题
            interface IStudentService {
                public Student0311[] getData();  //获取排序数据
            }
        3.建立IStudentService子类
            class StudentServiceImpl implements IStudentService {
                private String content;
                private Student0311[] students;
                public StudentServiceImpl(String content) {
                    this.content = content;
                    this.handle(); // 进行数据处理
                }
                private void handle() { // 进行字符串数据的处理操作
                    String result[] = this.content.split("\\|");
                    this.students = new Student0311[result.length];
                    for (int x = 0; x < result.length; x++) {
                        String temp[] = result[x].split(":");
                        this.students[x] = new Student0311(temp[0], Double.parseDouble(temp[1]));
                    }
                }
                @Override
                public Student0311[] getData() {
                    Arrays.sort(this.students);
                    return this.students;
                }
            }
        4.定义StudentFactory类
            class StudentFactory {
                private StudentFactory() {}
                public static IStudentService getInstance() {
                    return new StudentServiceImpl(InputUtil031103.getString("请输入数据信息："));
                }
            }
        5.编写测试类
            
*/
package Demo;

import java.util.Arrays;
import java.util.Scanner;

class Student0311 implements Comparable<Student0311> {
    private String name;
    private double score;

    public Student0311(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String toString() {
        return "姓名： " + this.name + "、成绩： " + this.score;
    }

    @Override
    public int compareTo(Student0311 obj) {
        if (this.score > obj.score) {
            return -1;
        } else if (this.score < obj.score) {
            return 1;
        } else {
            return 0;
        }
    }
}

interface IStudentService {
    public Student0311[] getData(); // 获取排序数据
}

class StudentServiceImpl implements IStudentService {
    private String content;
    private Student0311[] students;

    public StudentServiceImpl(String content) {
        this.content = content;
        this.handle(); // 进行数据处理
    }

    private void handle() { // 进行字符串数据的处理操作
        String result[] = this.content.split("\\|");
        this.students = new Student0311[result.length];
        for (int x = 0; x < result.length; x++) {
            String temp[] = result[x].split(":");
            this.students[x] = new Student0311(temp[0], Double.parseDouble(temp[1]));
        }
    }

    @Override
    public Student0311[] getData() {
        Arrays.sort(this.students);
        return this.students;
    }
}

class InputUtil031104 {
    private InputUtil031104() {
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

class StudentFactory {
    private StudentFactory() {
    }

    public static IStudentService getInstance() {
        return new StudentServiceImpl(InputUtil031104.getString("请输入数据信息："));
    }
}

public class DataSortDemo {
    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(StudentFactory.getInstance().getData()));
    }
}