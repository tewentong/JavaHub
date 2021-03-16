/*
    Java类集框架
        Collection集合接口
            java.util.Collection是单值集合操作的最大的父接口，在该接口之中定义有所有的单值数据的处理操作
        核心操作方法：
            public boolean add(E e) 普通 向集合保存数据 *
            public boolean addAll(Collection<? extends E> c) 普通 追加一组数据
            public void clear() 普通 清空集合，让根节点为空，同时执行GC处理
            public boolean contains(Object o) 普通 查询数据是否存在，需要equals()方法支持
            public boolean remove(Object o) 普通 数据删除，需要equals()方法支持
            public int size() 普通 获取数据长度
            public Object[] toArray() 普通 将集合变为对象数组返回
            public Iterator<E> iterator() 普通 将集合变为Iterator接口返回  *
            在进行集合处理的时候有两个方法最为常用：【增加】add()、【输出】iterator()
        在JDK1.5以前，Collection只是一个独立的接口，从1.5之后提供有了Iterable父接口，且在JDK1.8之后针对于Iterable接口也进行了扩充
        在JDK1.2~JDK1.4的时代里面如果要进行集合的使用往往会直接操作Collection接口，但是从JDK1.5时代开始，更多选择Collection的两个子接口：
            允许重复的List子接口
            不允许重复的Set子接口
*/
public class CollectionInterfaceDemo {

}