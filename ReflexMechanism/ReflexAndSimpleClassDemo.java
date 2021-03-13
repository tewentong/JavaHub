/*
    反射与简单Java类
        简单Java类主要是由属性所组成，并且提供由相应的setter、getter处理方法，
            同时简单Java类最大的特征就是通过对象保存相应的类属性内容
            但是如果使用传统的简单java类开发，也会面临非常麻烦的困难
        范例：传统的简单java类操作
            class Emp {
                private String ename;
                private String job;
                public void setEname(String ename) {
                    this.ename = ename;
                }
                public String getEname() {
                    return ename;
                }
                public void setJob(String job) {
                    this.job = job;
                }
                public String getJob() {
                    return job;
                }
            }
            特别强调，为了方便理解，本次Emp类之中定义的ename,job两个属性的类型使用的都是String类型
            按照传统的做法，此时应该首先实例化Emp类对象，而后通过实例化对象进行setter、getter方法的调用以设置属性内容
        范例：传统的调用
            public class ReflexAndSimpleClassDemo {
                public static void main(String[] args) throws Exception {
                    Emp emp = new Emp();    //更多情况下，开发者关注的是内容的设置
                    emp.setEname("SMITH");
                    emp.setJob("CLERK");
                    // 使用为对象设置之后
                    System.out.println("姓名： " + emp.getEname() + "、职位： " + emp.getJob());
                }
            }
            在整个进行Emp对象实例化并设置数据的操作过程之中，设置数据的部分是最麻烦的，你可以想象一下，
                如果说现在Emp类里面提供有50个属性，那么对于整个的程序将会出现一堆的setter方法调用
                在实际的开发之中，简单java类的个数是非常多的，如果说所有的简单java类都牵扯到属性赋值的时候
                这种情况下，代码编写的重复性将会非常高
            
        按照传统的直观的编程方式，所带来的问题就是代码会存在有大量的重复操作
        如果要想解决对象的重复处理操作，那么唯一的解决方案就是反射机制
        反射机制最大的特征——Object类直接操作，可以直接操作属性或方法
            实现相同功能类的重复操作的抽象处理
*/
class Emp {
    private String ename;
    private String job;

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEname() {
        return ename;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJob() {
        return job;
    }
}

public class ReflexAndSimpleClassDemo {
    public static void main(String[] args) throws Exception {
        Emp emp = new Emp();
        emp.setEname("SMITH");
        emp.setJob("CLERK");
        // 使用为对象设置之后
        System.out.println("姓名： " + emp.getEname() + "、职位： " + emp.getJob());
    }
}