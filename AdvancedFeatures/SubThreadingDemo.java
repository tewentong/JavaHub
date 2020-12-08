public class SubThreadingDemo {
    public static void main(String[] args) {
        System.out.println("1、执行操作一。");
        /*int temp = 0;
        for(int x = 0; x < Integer.MAX_VALUE; x++)
            temp += x;
        */
        new Thread(() -> {  //子线程负责统计
            int temp = 0;
            for(int x = 0; x < Integer.MAX_VALUE; x++)
                temp += x;
        }).start();     //使用子线程方式，不会影响主线程执行速度
        System.out.println("2、执行操作二。");
        System.out.println("3、执行操作三。");
    }
}