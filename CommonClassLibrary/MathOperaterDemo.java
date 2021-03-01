/*
    进行数学计算的操作类，提供有基础的计算公式，这各类的构造方法被私有化了，而且该类之中提供的所有方法都是static型方法
    即，这些方法都可以直接通过类名称直接调用
*/

public class MathOperaterDemo {
    public static void main(String[] args) throws Exception {
        System.out.println(Math.abs(-10.1));    //10.1
        System.out.println(Math.max(10.2, 20.3));   //20.3
        System.out.println(Math.log(5));    //1.6094379124341003
        System.out.println(Math.round(15.1));   //15
        System.out.println(Math.round(-15.1));  //-15
        System.out.println(Math.round(-15.5));  //-15
        System.out.println(Math.round(-15.51));  //-16
        System.out.println(Math.pow(10.2, 20.2));   //2.364413713591828E20
    }
}
