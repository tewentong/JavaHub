//引用数据类型最大的特点是可以进行内存的指向处理
//方法的引用： 不同的方法名称可以描述同一个方法

/*
    引用静态方法：             类名称 :: static方法名称;
    引用某个实例对象的方法：    实例化对象 :: 普通方法;
    引用特定类的方法：        特定类 :: 普通方法;
    引用构造方法：             类名称 :: new
*/

//利用方法引用可以为一个方法定义多个名字，但是要求必须是函数式接口


@FunctionalInterface    //函数式接口
//R描述的是返回值
interface IFunction1129<R> {
    public R upper();
}

public class MethodReferenceDemo {
    public static void main(String[] args) {
        IFunction1129<String> fun = "www.mldn.cn" :: toUpperCase;   //"www.mldn.cn"是一个实例化对象
        System.out.println(fun.upper());
    }
}