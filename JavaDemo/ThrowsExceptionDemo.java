class MyMath {
    //这个代码执行的时候可能会产生异常，如果产生异常，调用处处理
    public static int div(int x, int y) throws Exception{
        return x / y;  
    }
}

public class ThrowsExceptionDemo {
    public static void main(String[] args) {
        try {
            System.out.println(MyMath.div(10,0));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

//throw： 是在代码块中使用的，主要是手工进行异常的抛出
//thorws: 是在方法定义上使用的，表示将此方法中可能产生的异常明确告诉给调用处