/*
    属性自动设置方案
        经过了简单分析之后已经确定了当前简单java类操作的问题所在
            而对于开发者而言，就需要想办法通过一种解决方案来实现属性内容的自动设置
            那么设置的时候强烈建立采用字符串的形式来描述对应的类型
        1.在进行程序开发的时候String字符串可以描述的内容有很多，并且也可以由开发者自行定义字符串的结构
            下面就采用“属性：内容|属性：内容|属性：内容|”的形式来为简单java类中的属性初始化
        2.类设计的基本结构：应该由一个专门的ClassInstanceFactory类负责所有的反射处理，
            即：接收反射对象与要设置的属性内容，同时可以获取指定类的实例化对象
        3.设计的基本结构;
            class ClassInstanceFactory {
                private ClassInstanceFactory() {
                }

                **
                 * 实例化对象的创建方法，该对象可以根据传入的字符串结构“属性：内容|属性：内容”
                 * 
                 * @param clazz 要进行反射实例化的Class类对象，有Class就可以反射实例化对象
                 * @param value 要设置给对象的属性内容
                 * @return 一个已经配置好属性内容的java类对象
                 *
                public static <T> T create(Class<?> clazz, String value) {
                    return null;
                }
            }
            在当前的开发之中，所需要留给用户去完善的就是ClassInstanceFactory.create()方法
*/
class Emp0313 {
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

class ClassInstanceFactory {
    private ClassInstanceFactory() {
    }

    /**
     * 实例化对象的创建方法，该对象可以根据传入的字符串结构“属性：内容|属性：内容”
     * 
     * @param clazz 要进行反射实例化的Class类对象，有Class就可以反射实例化对象
     * @param value 要设置给对象的属性内容
     * @return 一个已经配置好属性内容的java类对象
     */
    public static <T> T create(Class<?> clazz, String value) {
        return null;
    }
}

public class FieldAutoSettingDemo {
    public static void main(String[] args) throws Exception {
        String value = "ename:Smith|job:Clerk";
        Emp0313 emp = ClassInstanceFactory.create(Emp0313.class, value);
        System.out.println("姓名： " + emp.getEname() + "、职位： " + emp.getJob());
    }
}