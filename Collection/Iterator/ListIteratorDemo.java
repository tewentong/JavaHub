
/*
    ListIterator输出
        使用Iterator进行的输出操作有一个特点：只允许由前向后进行输出，
        而如果说你现在需要双向迭代处理，那么就必须依靠Iterator的子接口：ListIterator接口来实现
        需要注意的是，如果要想获得ListIterator接口对象，Collection并没有定义相关的处理方法
        但是List子接口有，也就是说这个输出接口是专门为List集合准备的
        ListIterator接口里面定义有如下的操作方法：
            判断是否有前一个元素：public boolean hasPrevious();
            获取当前元素：public E previous();
    
        范例：实现双向迭代
            import java.util.ArrayList;
            import java.util.List;
            import java.util.ListIterator;

            public class ListIteratorDemo {
                public static void main(String[] args) throws Exception {
                    List<String> all = new ArrayList<String>();
                    all.add("Hello");
                    all.add("World");
                    all.add("MLDN");
                    ListIterator<String> iter = all.listIterator(); // 实例化Iterator接口对象
                    System.out.print("由前向后输出：");
                    while (iter.hasNext()) {
                        System.out.print(iter.next() + "、");
                    }
                    System.out.print("\n由后向前输出：");
                    while (iter.hasPrevious()) {
                        System.out.print(iter.previous() + "、");
                    }
                }
            }
        
        如果要想实现由后向前的遍历，首先要实现的是由前向后的遍历处理
*/
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorDemo {
    public static void main(String[] args) throws Exception {
        List<String> all = new ArrayList<String>();
        all.add("Hello");
        all.add("World");
        all.add("MLDN");
        ListIterator<String> iter = all.listIterator(); // 实例化Iterator接口对象
        System.out.print("由前向后输出：");
        while (iter.hasNext()) {
            System.out.print(iter.next() + "、");
        }
        System.out.print("\n由后向前输出：");
        while (iter.hasPrevious()) {
            System.out.print(iter.previous() + "、");
        }
    }
}