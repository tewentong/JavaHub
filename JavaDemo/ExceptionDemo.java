public class ExceptionDemo {
    public static void main(String [] args) {
        System.out.println("[1]***************");
        try {
            System.out.println("[2]************" + (10/0));
        }catch (ArithmeticException e){
            //System.out.println(e); //异常处理
            e.printStackTrace();  //输出完整的异常信息
        }finally {
            System.out.println("不管是否出现异常，我都会执行"); //即便没有算术除以0异常，finall也会正常执行
        }
        System.out.println("[3]****************");
    }
}