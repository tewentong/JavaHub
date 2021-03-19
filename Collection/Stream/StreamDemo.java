
/*
    Stream数据流
        从JDK1.8开始，由于已经进入了大数据时代，所以在类集里面也支持有数据的流式分析处理操作
        为此提供了一个Stream的接口，同时在Collection接口里面也提供有为此接口实例化的方法
            获取Stream接口对象：public default Stream<E> stream();
        Stream基础操作：
            Stream主要功能是进行数据的分析处理，同时主要是针对于集合中的数据进行分析操作
        范例：Stream的基本操作
            import java.util.ArrayList;
            import java.util.Collections;
            import java.util.List;
            import java.util.stream.Stream;
            public class StreamDemo {
                public static void main(String[] args) throws Exception {
                    List<String> all = new ArrayList<String>();
                    Collections.addAll(all, "Java", "JavaScript", "python", "Ruby", "Go");
                    Stream<String> stream = all.stream(); // 可以获得Stream接口对象
                    // 要求将每一个元素的字母变为小写字母，而后判断字母j是否存在
                    System.out.println(stream.filter((ele) -> ele.toLowerCase().contains("j")).count());    //输出元素的个数
                }
            }
            程序输出：
                2
            但是以上的程序只是实现了一些最基础的数据的个数的统计
                而更多情况下我们可能需要的是获取里面的满足条件的数据内容
                所以此时可以实现数据的采集操作
        范例：数据采集
            import java.util.ArrayList;
            import java.util.Collections;
            import java.util.List;
            import java.util.stream.Stream;
            import java.util.stream.Collectors;
            public class StreamDemo {
                public static void main(String[] args) throws Exception {
                    List<String> all = new ArrayList<String>();
                    Collections.addAll(all, "Java", "JavaScript", "python", "Ruby", "Go");
                    Stream<String> stream = all.stream(); // 可以获得Stream接口对象
                    // 要求将每一个元素的字母变为小写字母，而后判断字母j是否存在,将满足条件的数据收集起来转为List集合
                    List<String> result = stream.filter((ele) -> ele.toLowerCase().contains("j")).collect(Collectors.toList());
                    System.out.println(result);
                }
            }
            程序输出：
                [Java, JavaScript]
            在Stream数据流处理的过程之中还允许进行数组的分页处理，提供有有两个方法：
                设置取出的最大的数据量：public Stream<T> limit(long maxSize);
                跳过指定数据量：public Stream<T> skip(long n);
        范例：观察分页
            import java.util.ArrayList;
            import java.util.Collections;
            import java.util.List;
            import java.util.stream.Stream;
            import java.util.stream.Collectors;
            public class StreamDemo {
                public static void main(String[] args) throws Exception {
                    List<String> all = new ArrayList<String>();
                    Collections.addAll(all, "Java", "JavaScript", "JSP", "Json", "python", "Ruby", "Go");
                    Stream<String> stream = all.stream(); // 可以获得Stream接口对象
                    // 要求将每一个元素的字母变为小写字母，而后判断字母j是否存在,将满足条件的数据收集起来转为List集合
                    List<String> result = stream.filter((ele) -> ele.toLowerCase().contains("j")).skip(2).limit(2)
                            .collect(Collectors.toList());
                    System.out.println(result);
                }
            }
            程序输出：
                [JSP, Json]

        Stream的操作主要是利用自身的特点实现数据的分析处理操作
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) throws Exception {
        List<String> all = new ArrayList<String>();
        Collections.addAll(all, "Java", "JavaScript", "JSP", "Json", "python", "Ruby", "Go");
        Stream<String> stream = all.stream(); // 可以获得Stream接口对象
        // 要求将每一个元素的字母变为小写字母，而后判断字母j是否存在,将满足条件的数据收集起来转为List集合
        List<String> result = stream.filter((ele) -> ele.toLowerCase().contains("j")).skip(2).limit(2)
                .collect(Collectors.toList());
        System.out.println(result);
    }
}