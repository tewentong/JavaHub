
/*
    Java类集框架
        Stack栈
            栈是一种先进后出的数据结构，例如，在文本编辑器上都有撤销功能，那么每次使用的时候你会发现，
                最后一次的编辑操作永远是最先撤销，这个功能就是利用栈来实现的，栈的基本操作形式如下：
            在Java里面用Stack描述栈的操作，这个类的定义如下：
                public class Stack<E> extends Vector<E>;
                可以发现Stack是Vector子类，但是它使用的并不是Vector类之中提供的方法，而是采用如下的两个方法：
                    入栈：public E push(E item);
                    出栈：public E pop();
        范例：实现栈的操作
            import java.util.Stack;

            public class StackDemo {
                public static void main(String[] args) throws Exception {
                    Stack<String> all = new Stack<String>();
                    all.push("A");
                    all.push("B");
                    all.push("C");
                    System.out.println(all.pop());
                    System.out.println(all.pop());
                    System.out.println(all.pop());
                    System.out.println(all.pop());  //无数据，EmptyStackException
                }
            }
            程序输出：                
                C
                B
                A
                Exception in thread "main" java.util.EmptyStackException
                此时的操作可以发现，所有的数据保存之后将按照倒序的形式弹出
                如果栈空，则会抛出“EmptyStackException”异常
*/
import java.util.Stack;

public class StackDemo {
    public static void main(String[] args) throws Exception {
        Stack<String> all = new Stack<String>();
        all.push("A");
        all.push("B");
        all.push("C");
        System.out.println(all.pop());
        System.out.println(all.pop());
        System.out.println(all.pop());
        System.out.println(all.pop()); // 无数据，EmptyStackException
    }
}