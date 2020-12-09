public class MainThreadPriorityDemo{
    public static void main(String[] args) throws Exception {
        System.out.println(Thread.currentThread().getPriority());
        System.out.println(new Thread().getPriority());
    }
}
//主线程优先级为5，属于NORM_PRIORITY,中等优先级
//默认线程优先级为5，属于NORM_PRIORITY，中等优先级