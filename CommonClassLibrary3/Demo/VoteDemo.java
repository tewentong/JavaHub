/*
    投票选举
        功能描述：
            某班采用民主投票方法推选班长，班长候选人共4位，每个人姓名及代号分别为
            “张三 1；李四 2；王五 3；赵刘 4”，程序操作员将每张选票上所填的代号（1、2、3或4）循环输入电脑，
            输入数字0结束输入，然后将所有候选人的得票情况显示出来，并显示最终当选者的信息
        具体要求：
            1.要求用面向对象方法，编写学生类Student，将候选人姓名、代号和票数保存到类Student中，并实现setXxx、getXxx方法
            2.输入数据前，显示出各位候选人的代号及姓名（提示，建立一个候选人类型数组）
            3.循环执行接收键盘输入的班长候选人代号，直到输入的数字为0，结束选票输入工作
            4.在接收每次输入的选票后要求验证该选票是否有效，即如果输入的数不是0、1、2、3、4这5个数字之一， 
                或者输入的是一串字母，应显示出错误提示信息“此选票无效，请输入正确的候选人代号！”，并继续等待输入
            5.输入结束后显示所有候选人的得票情况，如参考样例所示
            6.输出最终当选者的相关信息，如参考样例所示
        参考样例：
            1：张三【0票】
            2：李四【0票】
            3：王五【0票】
            4：赵刘【0票】
            请输入班长候选人代号（数字0结束）：1
            请输入班长候选人代号（数字0结束）：1
            请输入班长候选人代号（数字0结束）：1
            请输入班长候选人代号（数字0结束）：2
            请输入班长候选人代号（数字0结束）：3
            请输入班长候选人代号（数字0结束）：4
            请输入班长候选人代号（数字0结束）：5
            此选票无效，请输入正确的候选人代号！
            请输入班长候选人代号（数字0结束）：hello
            此选票无效，请输入正确的候选人代号！
            请输入班长候选人代号（数字0结束）：0
                1：张三【4票】
                2：李四【1票】
                3：王五【1票】
                4：赵六【1票】
            投票最终结果：张三同学，最后以4票当选班长！
        1.建立学生类，这各类里面需要保存有编号、姓名、票数
            class Student0312 implements Comparable<Student0312> {
                private long sid;
                private String name;
                private int vote;
                public Student0312(long sid， String name, int vote) {
                    this.sid = sid; 
                    this.name = name;
                    this.vote = vote;
                }
                @Override
                public String toString() {
                    return "【" + this.sid + "】姓名： " + this.name + "、票数： " + this.vote; 
                }
                @Override
                public int compareTo(Student0312 stu) {
                    return stu.vote - this.vote;
                }
                public void setSid(long sid) {
                    this.sid = sid;
                }
                public long getSid() {
                    return sid;
                }
                public void setName(String name) {
                    this.name = name;
                }
                public String getName() {
                    return this.name;
                }
                public int getVote() {
                    return vote;
                }
                public void setVote(int vote) {
                    this.vote = vote;
                }
            }
        2.定义投票处理的业务接口
            interface IVoteService {
                public boolean inc(long sid);   //根据编号进行增长
                public Student0312[] result();  //获取投票的结果
                public student0312[] getData(); // 获取全部数据
            }
        3.定义VoteServiceImpl的实现子类
            class VoteServiceImpl implements IVoteService {
                private Student0312[] students = new Student0312[] { new Student0312(1, "张三", 0), new Student0312(2, "李四", 0),
                        new Student0312(3, "王五", 0), new Student0312(4, "赵六", 0) };
                @Override
                public void inc(long sid) {
                    for (int x = 0; x < students.length; x++) {
                        if (this.students[x].getSid() == sid) { // 获取了指定编号
                            this.students[x].setVote(this.students[x].getVote() + 1); // 票数增长
                            return true;
                        }
                    }
                    return false;
                }
                @Override
                public Student0312[] result() {
                    Arrays.sort(this.students);
                    return this.students;
                }
                @Override
                public Student0312[] getData() {
                    return this.students;
                }
            }
        4.定义一个菜单的信息显示类
            class Menu0312 {
                private IVoteService voteService;

                public Menu0312() {
                    this.voteService = VoteFactory.getInstance();
                    this.vote();
                }

                public void vote() { // 投票处理
                    Student0312 stu[] = this.voteService.getData();
                    for (Student0312 temp : stu) {
                        System.out.println(temp.getSid() + " : " + temp.getName() + "【" + temp.getVote() + "】");
                    }
                    int num = 10;
                    while (num != 0) { // 循环投票
                        num = InputUtil0312.getInt("请输入班长候选人代号（数字0结束）：");
                        if (num != 0) {
                            if (!this.voteService.inc(num)) {
                                System.out.println("此选票无效，请输入正确的候选人代号！");
                            }
                        }
                    }
                    System.out.println("头牌最终结果： ");
                    stu = this.voteService.result();
                    System.out.println(stu[0].getName() + "同学，以" + stu[0].getVote() + "票数当选班长！");
                }
            }
        5.定义工厂类
            class VoteFactory {
                private VoteFactory() {
                }
                public static IVoteService getInstance() {
                    return new VoteServiceImpl();
                }
            }
        6.编写测试类
            public class VoteDemo {
                public static void main(String[] args) throws Exception {
                    new Menu0312();
                }
            }
*/
package Demo;

import java.util.Arrays;
import java.util.Scanner;

class Student0312 implements Comparable<Student0312> {
    private long sid;
    private String name;
    private int vote;

    public Student0312(long sid, String name, int vote) {
        this.sid = sid;
        this.name = name;
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "【" + this.sid + "】姓名： " + this.name + "、票数： " + this.vote;
    }

    @Override
    public int compareTo(Student0312 stu) {
        return stu.vote - this.vote;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public long getSid() {
        return sid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}

interface IVoteService {
    public boolean inc(long sid); // 根据编号进行增长

    public Student0312[] result(); // 获取投票的结果

    public Student0312[] getData(); // 获取全部数据
}

class VoteServiceImpl implements IVoteService {
    private Student0312[] students = new Student0312[] { new Student0312(1, "张三", 0), new Student0312(2, "李四", 0),
            new Student0312(3, "王五", 0), new Student0312(4, "赵六", 0) };

    @Override
    public boolean inc(long sid) {
        for (int x = 0; x < students.length; x++) {
            if (this.students[x].getSid() == sid) { // 获取了指定编号
                this.students[x].setVote(this.students[x].getVote() + 1); // 票数增长
                return true;
            }
        }
        return false;
    }

    @Override
    public Student0312[] result() {
        Arrays.sort(this.students);
        return this.students;
    }

    @Override
    public Student0312[] getData() {
        return this.students;
    }
}

class VoteFactory {

    private VoteFactory() {
    }

    public static IVoteService getInstance() {
        return new VoteServiceImpl();
    }
}

class InputUtil0312 {
    private InputUtil0312() {
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

class Menu0312 {
    private IVoteService voteService;

    public Menu0312() {
        this.voteService = VoteFactory.getInstance();
        this.vote();
    }

    public void vote() { // 投票处理
        Student0312 stu[] = this.voteService.getData();
        for (Student0312 temp : stu) {
            System.out.println(temp.getSid() + " : " + temp.getName() + "【" + temp.getVote() + "】");
        }
        int num = 10;
        while (num != 0) { // 循环投票
            num = InputUtil0312.getInt("请输入班长候选人代号（数字0结束）：");
            if (num != 0) {
                if (!this.voteService.inc(num)) {
                    System.out.println("此选票无效，请输入正确的候选人代号！");
                }
            }
        }
        System.out.println("头牌最终结果： ");
        stu = this.voteService.result();
        System.out.println(stu[0].getName() + "同学，以" + stu[0].getVote() + "票数当选班长！");
    }
}

public class VoteDemo {
    public static void main(String[] args) throws Exception {
        new Menu0312();
    }
}