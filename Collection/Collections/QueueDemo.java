
/*
    Java类集框架
        Queue队列：
            Queue描述的是一个队列，而队列的主要特点是实现先进先出的操作形式
            如果将队列应用在多线程的“生产者与消费者”的模型处理上，
                那么对于生产者过快的情况下就没有必要等待消费者获取数据，可以将所有的内容直接保存在队列之中
            队列的实现可以使用LinkedList子类来完成，观察这个类的定义：
                队列的使用主要依靠Queue接口之中提供的方法来处理，提供有如下的方法：
                    向队列之中增加数据：public boolean offer(E e); //可以直接使用add()方法
                    通过对列获取数据：public E poll(); //弹出后删除数据
            范例：实现队列操作
                import java.util.LinkedList;
                import java.util.Queue;

                public class QueueDemo {
                    public static void main(String[] args) throws Exception {
                        Queue<String> queue = new LinkedList<String>();
                        queue.offer("X"); // 追加队列数据，通过队尾追加
                        queue.offer("A"); // 追加队列数据，通过队尾追加
                        queue.offer("Z"); // 追加队列数据，通过队尾追加
                        System.out.println(queue.poll()); // 弹出数据X
                        System.out.println(queue.poll()); // 弹出数据A
                        System.out.println(queue.poll()); // 弹出数据Z
                    }
                }
                程序输出：
                    X
                    A
                    Z
            
            除了LinkedList之外，还有一个优先级队列的概念，可以使用PriorityQueue实现优先级队列（比较功能）
            范例：使用优先级队列
                import java.util.PriorityQueue;
                import java.util.Queue;

                public class QueueDemo {
                    public static void main(String[] args) throws Exception {
                        Queue<String> queue = new PriorityQueue<String>();
                        queue.offer("X"); // 追加队列数据，通过队尾追加
                        queue.offer("A"); // 追加队列数据，通过队尾追加
                        queue.offer("Z"); // 追加队列数据，通过队尾追加
                        System.out.println(queue.poll()); // 弹出数据X
                        System.out.println(queue.poll()); // 弹出数据A
                        System.out.println(queue.poll()); // 弹出数据Z
                    }
                }
                程序输出：
                    A
                    X
                    Z
                    
                对于队列的选用原则也是需要根据实际的项目环境来决定的
*/
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueDemo {
    public static void main(String[] args) throws Exception {
        Queue<String> queue = new PriorityQueue<String>();
        queue.offer("X"); // 追加队列数据，通过队尾追加
        queue.offer("A"); // 追加队列数据，通过队尾追加
        queue.offer("Z"); // 追加队列数据，通过队尾追加
        System.out.println(queue.poll()); // 弹出数据X
        System.out.println(queue.poll()); // 弹出数据A
        System.out.println(queue.poll()); // 弹出数据Z
    }
}