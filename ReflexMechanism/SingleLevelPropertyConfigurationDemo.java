
/*
    单级属性配置
        对于此时的Emp类里面会发现所给出的数据类型都没有其他的引用关联了，只是描述了Emp本类的对象
            所以这样的设置称为单级设置处理，此时应该处理两件事情：
            1.需要通过反射进行指定类对象的实例化处理
            2.进行内容的设置（Field属性类型、方法名称、要设置的内容）
        1.定义StringUtils实现首字母大写功能
            class StringUtils {
                public static String initcap(String str) {
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
        2.定义BeanUtils工具类，该工具类主要实现属性的设置
            class BeanUtils { // 进行Bean处理的类
                private BeanUtils() {
                }

                **
                 * 实现指定对象的属性设置
                 * 
                 * @param obj   要进行反射操作的实例化对象
                 * @param value 包含有指定内容的字符串，格式“属性：内容|属性：内容”
                 *
                public static void setValue(Object obj, String value) {
                    String result[] = value.split("\\|"); // 按照“|”进行每一组属性的拆分
                    for (int x = 0; x < result.length; x++) { // 循环设置属性内容
                        // attval[0]保存属性名称、attval[1]保存属性内容
                        String attval[] = result[x].split(":"); // 获取“属性名称”与“属性内容”
                        try {
                            Field field = obj.getClass().getDeclaredField(attval[0]); // 获取成员
                            Method setMethod = obj.getClass().getDeclaredMethod("set" + StringUtils.initcap(attval[0]),
                                    field.getType());
                            setMethod.invoke(obj, attval[1]); // 调用setter方法设置内容
                        } catch (Exception e) {
                            //即使有某个属性输入错误，程序仍会执行
                            //姓名： null、职位： Clerk
                        }
                    }
                }
            }
        3.ClassInstanceFactory负责实例化对象并且调用BeanUtils类实现属性内容的设置
            class ClassInstanceFactory031302 {
                private ClassInstanceFactory031302() {
                }

                **
                 * 实例化对象的创建方法，该对象可以根据传入的字符串结构“属性：内容|属性：内容”
                 * 
                 * @param clazz 要进行反射实例化的Class类对象，有Class就可以反射实例化对象
                 * @param value 要设置给对象的属性内容
                 * @return 一个已经配置好属性内容的java类对象
                 *
                public static <T> T create(Class<?> clazz, String value) {
                    try { // 如果要想采用反射进行简单java类对象属性设置的时候，类中必须要有无参构造
                        Object obj = clazz.getDeclaredConstructor().newInstance();
                        BeanUtils.setValue(obj, value); // 通过反射设置属性
                        return (T) obj; // 返回对象
                    } catch (Exception e) {
                        e.printStackTrace(); // 如果此是真的出现了错误，本质上抛出异常也没用
                        return null;
                    }
                }
            }

        即使现在类中的属性再多，那么也可以轻松的实现setter的调用（类对象实例化处理）
*/
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Emp031302 {
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

class ClassInstanceFactory031302 {
    private ClassInstanceFactory031302() {
    }

    /**
     * 实例化对象的创建方法，该对象可以根据传入的字符串结构“属性：内容|属性：内容”
     * 
     * @param clazz 要进行反射实例化的Class类对象，有Class就可以反射实例化对象
     * @param value 要设置给对象的属性内容
     * @return 一个已经配置好属性内容的java类对象
     */
    public static <T> T create(Class<?> clazz, String value) {
        try { // 如果要想采用反射进行简单java类对象属性设置的时候，类中必须要有无参构造
            Object obj = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.setValue(obj, value); // 通过反射设置属性
            return (T) obj; // 返回对象
        } catch (Exception e) {
            e.printStackTrace(); // 如果此是真的出现了错误，本质上抛出异常也没用
            return null;
        }
    }
}

class StringUtils {
    public static String initcap(String str) {
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

class BeanUtils { // 进行Bean处理的类
    private BeanUtils() {
    }

    /**
     * 实现指定对象的属性设置
     * 
     * @param obj   要进行反射操作的实例化对象
     * @param value 包含有指定内容的字符串，格式“属性：内容|属性：内容”
     */
    public static void setValue(Object obj, String value) {
        String result[] = value.split("\\|"); // 按照“|”进行每一组属性的拆分
        for (int x = 0; x < result.length; x++) { // 循环设置属性内容
            // attval[0]保存属性名称、attval[1]保存属性内容
            String attval[] = result[x].split(":"); // 获取“属性名称”与“属性内容”
            try {
                Field field = obj.getClass().getDeclaredField(attval[0]); // 获取成员
                Method setMethod = obj.getClass().getDeclaredMethod("set" + StringUtils.initcap(attval[0]),
                        field.getType());
                setMethod.invoke(obj, attval[1]); // 调用setter方法设置内容
            } catch (Exception e) {

            }
        }
    }
}

public class SingleLevelPropertyConfigurationDemo {
    public static void main(String[] args) throws Exception {
        String value = "ename:Smith|job:Clerk"; // 姓名： Smith、职位： Clerk
        // String value = "enames:Smith|job:Clerk"; //姓名： null、职位： Clerk
        Emp031302 emp = ClassInstanceFactory031302.create(Emp031302.class, value);
        System.out.println("姓名： " + emp.getEname() + "、职位： " + emp.getJob());
    }
}