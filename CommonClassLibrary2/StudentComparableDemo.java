/*
    按照 “姓名： 年龄： 成绩|姓名： 年龄： 成绩”的格式定义字符串“张三:21:98|李四:22:89|王五:20:70”,
    要求将每组值分别保存在student对象之中，并对这些对象进行排序，
    排序的原则为：按照成绩由高到低排序，如果成绩相等，则按照年龄由低到高排序

    本程序最典型的做法是利用比较器完成处理，如果不使用比较器也能完成，相当于自己采用冒泡的方式进行排列
    使用了比较器就可以使用Arrays类作处理
*/
import java.util.Comparable;
import java.util.Arrays;
class Student implements Comparable<Student> {
    private String name;
    private int age;
    private double score;
    public Student(String name, int age, double score) {
        super();
        this.name = name;
        this.age = age;
        this.score = score;
    }
    @Override
    public int compareTo(Student stu) {
        if (this.score < stu.score) {
            return 1;
        } else if (this.score > stu.score) {
            return -1;
        } else {
            return this.age - stu.age;
        }
    }
    @Override
    public String toString() {
        return "【学生信息】姓名： " + this.name + "、年龄： " + this.age + "、成绩： " + this.score + "\n";
    }
}
public class StudentComparableDemo {
    public static void main(String [] args) {
        String input = "张三:21:98|李四:22:89|王五:20:70";
        String result [] = input.split("\\|");
        Student students []  = new Student [result.length];
        for (int x = 0 ; x < result.length ; x ++) {
            String temp [] = result[x].split(":");
            students[x] = new Student(temp[0], Integer.parseInt(temp[1]), Double.parseDouble(temp[2]));
        }
        Arrays.sort(students);
        System.out.println(Arrays.toString(students));
    }
}