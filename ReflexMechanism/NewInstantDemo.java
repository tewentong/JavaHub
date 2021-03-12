/*
    反射编程案例：反射案例说明
        经过一系列的分析之后发现，虽然获得了Class类的实例化对象，但是依然觉得这个对象获取的意义不是很大
            所以为了进一步地帮助大家理解反射的核心意义所在，下面将通过几个案例进行程序的说明（在实际开发之中一定会使用到的）
        1.反射实例化对象
            获取Class对象之后最大的意义实际上并不是在于只是一个对象的实例化的操作形式
                更重要的是，Class类里面提供有一个对象的反射实例化方法(代替了关键字new)：
                在JDK1.9以前的实例化：
                    public T newInsatance() throws InstantiationException, IllegalAccessException
                JDK1.9之后：
                    clazz.getDeclaredConstructor().newInstance();
            范例：通过newInstance()方法实例化Person类对象
                class Person031202 {
                    // 任何情况下如果要实例化对象则一定要调用类中的构造方法
                    public Person031202() {
                        System.out.println("***********Person类构造方法************");
                    }

                    @Override
                    public String toString() {
                        return "我是一个人，一个脱离了低级趣味的好人！";
                    }
                }

                public class NewInstantDemo {
                    public static void main(String[] args) throws Exception {
                        Class<?> cls = Class.forName("Person031202");
                        Object obj = cls.newInstance();
                        System.out.println(obj); // 输出对象调用toString()方法
                    }
                }

                程序执行结果：  
                    【cls.newInstance();】    ***********Person类构造方法************
                    【System.out.println(obj);】      我是一个人，一个脱离了低级趣味的好人！  
                现在通过反射实现的对象实例化处理，依然要调用类中的无参构造方法，其本质等价于 “类 对象 = new 类();”    
                    相当于隐含了关键字new，而直接使用字符串进行了代替
            
            范例：从JDK1.9之后newInstance()被替代了
                因为默认的Class类中的newIntance()方法只能够调用无参构造，所以很多开发者会认为其描述的不准确
                    于是将其变换了形式（构造方法会讲解）
                class Person031202 {
                    // 任何情况下如果要实例化对象则一定要调用类中的构造方法
                    public Person031202() {
                        System.out.println("***********Person类构造方法************");
                    }

                    @Override
                    public String toString() {
                        return "我是一个人，一个脱离了低级趣味的好人！";
                    }
                }

                public class NewInstantDemo {
                    public static void main(String[] args) throws Exception {
                        Class<?> cls = Class.forName("Person031202");
                        Object obj = cls.getDeclaredConstructor().newInstance();
                        System.out.println(obj); // 输出对象调用toString()方法
                    }
                }
                
                程序执行结果：
                    【cls.getDeclaredConstructor().newInstance();】***********Person类构造方法************
                    【System.out.println(obj);】我是一个人，一个脱离了低级趣味的好人！
*/
class Person031202 {
    // 任何情况下如果要实例化对象则一定要调用类中的构造方法
    public Person031202() {
        System.out.println("***********Person类构造方法************");
    }

    @Override
    public String toString() {
        return "我是一个人，一个脱离了低级趣味的好人！";
    }
}

public class NewInstantDemo {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("Person031202");
        Object obj = cls.getDeclaredConstructor().newInstance();
        System.out.println(obj); // 输出对象调用toString()方法
    }
}