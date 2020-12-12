/*
    System类是一直陪伴着我们学习的类，之前使用的系统输出采用的就是System类中方法
    System类中定义的其他方法：
        数组拷贝： public static void arraycopy(Object src, int srcPos， Objetc dest, int desPos, int length);
        获取当前的日期时间数值： public static long currentTimeMillis();
        进行垃圾回收： public static void gc();

        //实现操作耗时的统计
        long start = System.currentTimeMillis();
        Runtime run = Runtime.getRuntime(); //获取实例化对象
        String str = "";
        for (int x = 0; x < 30000; x ++) {
            str += x;
        }
        long end = System.currentTimeMillis();
        System.out.println("操作耗时： " + (end - start));

    在System类中也提供有一个gc()方法： The call System.gc() is effectively equivalent to the call: Runtime.getRuntime().gc();
        public static void gc() {
            Runtime.getRuntime().gc();
        }
    GC的手工处理在整个JAVA系统中只有一个，这个方法就是 Runtime.getRuntime().gc();
*/
public class SystemDemo {
    public static void main(String [] args) {
        //实现操作耗时的统计
        long start = System.currentTimeMillis();
        Runtime run = Runtime.getRuntime(); //获取实例化对象
        String str = "";
        for (int x = 0; x < 30000; x ++) {
            str += x;
        }
        long end = System.currentTimeMillis();
        System.out.println("操作耗时： " + (end - start));

        //调用System.gc()
        System.gc();
    }
}