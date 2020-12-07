//在进行数学计算开始与结束的时候进行信息提示
//如果在进行计算的过程之中产生了异常，则要交给调用处来处理
class MyMath2 {
    //try, finally版本，即简化版本，尤其是当与一些资源进行访问操作的时候尤其重要
    //异常交给被调用处处理则一定要在方法上使用throws
    public static int div(int x, int y) throws Exception {
        int temp = 0;
        System.out.println("*****【START】除法计算开始。 ");
        try {
            temp = x / y;
        }finally {
            System.out.println("*****【END】除法计算结束。 ");
        }
        return temp;
    }
 }
public class ThrowsExceprionDemo2 {
    public static void main(String[] args) {
        try {
            System.out.println(MyMath2.div(10,0));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
//简化版为未被注释版本
class MyMath2 {
    //异常交给被调用处处理则一定要在方法上使用throws
    public static int div(int x, int y) throws Exception {
        int temp = 0;
        System.out.println("*****【START】除法计算开始。 ");
        try {
            temp = x / y;
        }catch (Exception e) {
            throw e; //向上抛出异常对象
        }finally {
            System.out.println("*****【END】除法计算结束。 ");
        }
        return temp;
    }
 }
public class ThrowsExceprionDemo2 {
    public static void main(String[] args) {
        try {
            System.out.println(MyMath2.div(10,0));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

*/