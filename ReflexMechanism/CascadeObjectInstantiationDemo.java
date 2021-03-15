/*
    级联对象实例化
        如果说现在给定的类对象之中存在有其它的引用的级联关系的情况下，称为多级设置
            例如：一个雇员属于一个部门，一个部门属于一个公司，所以这个时候简单java类的基本关系定义如下：
                1.公司类：
                    class Company0314 {
                        private String name;
                        private Date createDate;
                    }
                2.部门类：
                    class Dept0314 {
                        private String name;
                        private String loc;
                        private Company0314 company;
                    }
                3.雇员类
                    class Emp0314 {
                        private long empno;
                        private String ename;
                        private String job;
                        private double salary;
                        private Date hiredate;
                        private Dept0314 dept;
                    }
                如果要通过Emp进行操作，则应该使用“.”作为级联关系的处理
                    dept.dname:财务部
                        Emp类实例化对象.getDept().setDname("财务部")
                    dept.company.name:MLDN
                        Emp类实例化对象.getDept().getCompany().setName("MLDN")
                但是考虑到代码的简洁性，所以应该考虑到可以通过级联的配置自动实现类中属性的实例化
                    String value = "empno:7369|ename:Smith|job:Clerk|salary:750.00|hiredate:1989-10-10|" + 
                                    "dept.dname:财务部|dept.company.name:MLDN";
            现在的属性存在有多级的配置，对于多级的关系就必须与单级的配置区分开
*/

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

class Emp0314 {
    private long empno;
    private String ename;
    private String job;
    private double salary;
    private Date hiredate;
    private Dept0314 dept;

    public void setEname(final String ename) {
        this.ename = ename;
    }

    public void setJob(final String job) {
        this.job = job;
    }

    public String getEname() {
        return ename;
    }

    public long getEmpno() {
        return empno;
    }

    public void setEmpno(final long empno) {
        this.empno = empno;
    }

    public String getJob() {
        return job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(final double salary) {
        this.salary = salary;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(final Date hiredate) {
        this.hiredate = hiredate;
    }

    public Dept0314 getDept() {
        return dept;
    }

    public void setDept(final Dept0314 dept) {
        System.out.println("*****set Dept*****");
        this.dept = dept;
    }
}

class Company0314 {
    private String name;
    private Date createDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

class Dept0314 {
    private String dname;
    private String loc;
    private Company0314 company;

    public String getDname() {
        return dname;
    }

    public void setName(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Company0314 getCompany() {
        return company;
    }

    public void setCompany(Company0314 company) {
        System.out.println("********set company********");
        this.company = company;
    }
}

class ClassInstanceFactory0314 {
    private ClassInstanceFactory0314() {
    }

    /**
     * 实例化对象的创建方法，该对象可以根据传入的字符串结构“属性：内容|属性：内容”
     * 
     * @param clazz 要进行反射实例化的Class类对象，有Class就可以反射实例化对象
     * @param value 要设置给对象的属性内容
     * @return 一个已经配置好属性内容的java类对象
     */
    public static <T> T create(final Class<?> clazz, final String value) {
        try { // 如果要想采用反射进行简单java类对象属性设置的时候，类中必须要有无参构造
            final Object obj = clazz.getDeclaredConstructor().newInstance();
            BeanUtils0314.setValue(obj, value); // 通过反射设置属性
            return (T) obj; // 返回对象
        } catch (final Exception e) {
            e.printStackTrace(); // 如果此是真的出现了错误，本质上抛出异常也没用
            return null;
        }
    }
}

class StringUtils0314 {
    public static String initcap(final String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }
}

class BeanUtils0314 { // 进行Bean处理的类
    private BeanUtils0314() {
    }

    /**
     * 实现指定对象的属性设置
     * 
     * @param obj   要进行反射操作的实例化对象
     * @param value 包含有指定内容的字符串，格式“属性：内容|属性：内容”
     */
    public static void setValue(final Object obj, final String value) {
        String result[] = value.split("\\|"); // 按照“|”进行每一组属性的拆分
        for (int x = 0; x < result.length; x++) { // 循环设置属性内容
            // attval[0]保存属性名称、attval[1]保存属性内容
            String attval[] = result[x].split(":"); // 获取“属性名称”与“属性内容”
            try {
                if (attval[0].contains(".")) { // 多级配置
                    String temp[] = attval[0].split("\\.");
                    Object currentObject = obj;
                    // 最后一位肯定是指定类中的属性名称，所以不在本次实例化处理的范畴之内
                    for (int y = 0; y < temp.length - 1; y++) { // 实例化
                        // 调用相应的getter方法，如果getter方法返回了null表示该对象未实例化
                        Method getMethod = obj.getClass().getDeclaredMethod("get" + StringUtils.initcap(temp[y]));
                        Object tempObject = getMethod.invoke(currentObject);
                        if (tempObject == null) { // 该对象现在并没有实例化
                            Field field = currentObject.getClass().getDeclaredField(temp[y]); // 获取属性类型
                            Method method = currentObject.getClass()
                                    .getDeclaredMethod("set" + StringUtils0314.initcap(temp[y]), field.getType());
                            Object newObject = field.getType().getDeclaredConstructor().newInstance();
                            method.invoke(currentObject, newObject);
                            currentObject = newObject;
                            // 当此时代码循环处理完成之后，currentObject表示的就是可以进行setter方法调用的对象了
                            // 并且理论上该对象一定不可能为null
                            // 随后就可以按照之前的处理方式利用对象进行setter方法调用

                        } else {
                            currentObject = tempObject;
                        }
                        System.out.println(temp[y] + " -- " + currentObject);
                    }
                    // 进行属性内容的设置
                    Field field = currentObject.getClass().getDeclaredField(temp[temp.length - 1]); // 获取成员
                    Method setMethod = currentObject.getClass()
                            .getDeclaredMethod("set" + StringUtils0314.initcap(temp[temp.length - 1]), field.getType());
                    Object convertValue = BeanUtils0314.convertAttributeValue(field.getType().getName(), attval[1]);
                    setMethod.invoke(currentObject, convertValue); // 调用setter方法设置内容
                } else {
                    Field field = obj.getClass().getDeclaredField(attval[0]); // 获取成员
                    Method setMethod = obj.getClass().getDeclaredMethod("set" + StringUtils0314.initcap(attval[0]),
                            field.getType());
                    Object convertValue = BeanUtils0314.convertAttributeValue(field.getType().getName(), attval[1]);
                    setMethod.invoke(obj, convertValue); // 调用setter方法设置内容
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Object convertAttributeValue(String type, String value) {
        if ("long".equals(type) || "java.lang.Long".equals(type)) { // 长整型
            return Long.parseLong(value);
        } else if ("int".equals(type) || "java.lang.int".equals(type)) {
            return Integer.parseInt(value);
        } else if ("double".equals(type) || "java.lang.double".equals(type)) {
            return Double.parseDouble(value);
        } else if ("java.util.Date".equals(type)) {
            SimpleDateFormat sdf = null;
            if (value.matches("\\d{4}-\\d{2}-\\d{2}")) { // 日期类型
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            } else if (value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            } else {
                return new Date();
            }
            try {
                return sdf.parse(value);
            } catch (ParseException e) {
                return new Date(); // 当前日期
            }
        } else {
            return value;
        }
    }
}

public class CascadeObjectInstantiationDemo {
    public static void main(final String[] args) throws Exception {
        String value = "empno:7369|ename:Smith|job:Clerk|salary:750.00|hiredate:1989-10-10|"
                + "dept.dname:财务部|dept.company.name:MLDN";
        Emp0314 emp = ClassInstanceFactory0314.create(Emp0314.class, value);
        System.out.println("雇员编号： " + emp.getEmpno() + "姓名： " + emp.getEname() + "、职位：" + emp.getJob() + "、部门："
                + emp.getDept() + "、薪水： " + emp.getSalary() + "、雇佣日期： " + emp.getHiredate());
        System.out.println(emp.getDept().getDname());
        System.out.println(emp.getDept().getCompany().getName());
    }
}