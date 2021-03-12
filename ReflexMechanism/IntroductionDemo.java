
/*
    Java反射编程
        认识反射机制
            在Java语言里面之所以有如此众多的开源技术支撑，很大的一部分是来自于Java最大的特征——反射机制
            如果你现在不能灵活的使用反射机制进行项目的开发与设计，那么可以说你并未接触到Java的精髓所在
        所有的技术实现的目标只有一点：重用性
        对于反射技术首先要考虑的是“反”与“正”的概念：
            所谓的“正”操作指的是当我们要使用一个类的时候：
                我们一定要先导入程序所在的包，而后根据类进行对象的实例化，并且依靠对象调用类中的方法
            但是，如果说“反”：
                根据实例化对象反推出其类型
                如果要想实现反的操作处理，那么首先要采用的就是Obejct类中所提供的操作方法：
                    获取类对象信息：pulic final Class<?> getClass();
    范例：正向操作：
        public class IntroductionDemo {
            public static void main(String[] args) throws Exception {
                Date date = new Date(); // 2.通过类产生实例化对象
                System.out.println(date.getTime()); // 3.根据对象调用类中的方法
            }
        }
    范例：观察Class对象的使用
        public class IntroductionDemo {
            public static void main(String[] args) throws Exception {
                Date date = new Date(); // 2.通过类产生实例化对象
                System.out.println(date.getClass());    //根据实例化对象找到对象的所属类型
                            //class java.util.Date
            }
        }

        getClass()可以帮助使用者找到对象的根源
*/
import java.util.Date; //1.导入程序所在的包.类  知道对象的出处了

public class IntroductionDemo {
    public static void main(String[] args) throws Exception {
        Date date = new Date(); // 2.通过类产生实例化对象
        System.out.println(date.getClass());    //根据实例化对象找到对象的所属类型
                    //class java.util.Date
    }
}