interface ILink<E> {    //设置泛型避免安全隐患
    public void add(E e);   //增加数据
    public int size();  //获取数据的个数
    public boolean isEmpty(); //链表是否为空集合，数据是否被存储
    public Object [] toArray();  //将集合的元素以 对象数组的形式 返回
    public E get(int index);    //根据索引获取数据
    public void set(int index, E data); //修改指定索引的数据
    public boolean contains(E data);    //判断数据是否存在
    public void remove(E data);  //数据删除
    public void clean();    //清空集合
}

//Link类只负责数据的操作与根节点的处理，而所有后续节点的处理全部都是由Node类完成
class LinkImpl<E> implements ILink<E> {
    private class Node {    //保存节点的数据关系
        private E data; //保存的数据
        private Node next; //保存下一个引用
        public Node(E data) {   //有数据的情况下才有意义
            this.data = data;
        }

        //第一次调用： this == LinkImpl.root
        //第二次调用： this == LinkImpl.root.next
        //第三次调用： this == LinkImpl.root.next.next
        //递归调用，总会找到    Node.next == null
        public void addNode(Node newNode) { //保存新的Node数据
            if(this.next == null) { //当前节点的下一个节点为空
                this.next = newNode;    //
            }else{
                this.next.addNode(newNode); //递归调用
            }
        }

        //第一次调用：this == LinkImpl.root
        //第二次调用：this == LinkImpl.root.next
        //第三次调用：this == LinkImpl.root.next.next
        public void toArrayNode() {
            LinkImpl.this.returnData [LinkImpl.this.foot ++] = this.data;   //foot是对象数组角标，不是链表的根root
            if(this.next != null) {
                this.next.toArrayNode();
            }
        }

        public E getNode(int index) {
            if (LinkImpl.this.foot ++ == index) {  //索引相同就返回，索引不同则考虑下一次： foot++
                return this.data;   //返回当前数据
            }else {
                return this.next.getNode(index);
            }
        }
        public void setNode(int index, E data) {
            if (LinkImpl.this.foot ++ == index) {   //索引相同就修改数据，索引不同则考虑下一次： foot++
                this.data = data;
            }else {
                this.next.setNode(index, data);  //递归调用
            }
        }
        public boolean containsNode(E data) {   
            if(data.equals(this.data)) {    //对象比较
                return true;
            }else {
                if (this.next == null) {    //最后一个，整个链表已经全部比较完毕，没有相等的值
                    return false;
                }else{
                    return this.next.containsNode(data);   //递归继续比较
                }
            }
        }
        public void removeNode(Node previous, E data) {
            if (this.data.equals(data)) {
                previous.next = this.next;  //当前节点的前驱节点指向当前节点的后继节点
            }else {
                if (this.next != null) {    //当前节点有后续节点
                    this.next.removeNode(this, data);   //向后继续删除
                }
            }
        }
    }
    //-------------以下为Link类中的结构------------
    private Node root;  //保存根节点
    private int count;  //保存数据个数
    private int foot;   //描述的是操作数组的角标
    private Object [] returnData;   //保存返回的数据
    //-------------以下为Link类中定义的方法---------
    public void add(E e) {
        if (e == null) {    //保存的数据为null
            return; //方法调用直接结束
        }
        //数据本身是不具有关联特性的，只有Node类有，那么要想实现关联处理就必须将数据封装在Node类之中
        Node newNode = new Node(e); //创建一个新的节点
        if (this.root == null) {    //现在没有根节点
            this.root = newNode;    //第一个新节点作为根节点
        }else { //根节点存在
            this.root.addNode(newNode); //将新节点保存在合适的位置
        }
        this.count++;   //增加个数
    }

    public int size() {
        return this.count;
    }
    public boolean isEmpty() {
        // return this.root == null;
        return this.count == 0; //两种判断方法本质相同
    }
    public Object[] toArray() {
        if(this.isEmpty()) {    //空集合
            return null;    //现在没有数据
        }
        this.foot = 0;  //脚标清零
        this.returnData = new Object [this.count];  //根据已有的长度开辟数组
        this.root.toArrayNode();    //利用Node类递归获取数据
        return this.returnData;
    }
    public E get(int index) {
        if(index >= this.count) {   //索引应该在指定的范围之内 
            return null;
        }   //索引数据的获取应该由Node类完成
        this.foot = 0;  //重置索引的下标，从root开始搜索
        return this.root.getNode(index);
    }
    public void set(int index, E data) {
        if(index >= this.count) {   //索引应该在指定范围之内
            return; //方法结束
        }   //索引数据的修改应该由Node类完成
        this.foot = 0;  //重置索引下标， 从root开始搜索
        this.root.setNode(index, data);
    }
    public boolean contains(E data) {
        if (data == null) {
            return false;   //没有数据，传递了一个空值，没有比较的意义
        }
        return this.root.containsNode(data);    //交给Node类判断
    }
    public void remove(E data){
        if (this.contains(data)) {   //判断数据是否存在
            if (this.root.data.equals(data)) {  //根节点为需要删除节点
                this.root = this.root.next; //根的下一个节点
            }else { //根节点不是要删除的节点，后续工作交由Node类继续
                this.root.next.removeNode(this.root, data);
            }
            this.count --;
        }
    }
    public void clean() {
        this.root = null;   //后续的所有节点都没了
        this.count = 0;     //计数器清零
    }
}
//现在所定义的Node类中并没有setter()和getter()方法，因为内部类中的私有属性也方便外部类访问


public class LinkListDemo {
    public static void main(String[] args) {
        ILink<String> all = new LinkImpl<String>();
        System.out.println("【增加之前】数据个数： " + all.size() +", 链表是否为空集合： " + all.isEmpty());
        all.add("Hello");
        all.add("World");
        all.add("MLDN");
        all.set(1, "世界");
        //all.remove("Hello");
        all.remove("MLDN");
        //all.clean();
        System.out.println("【增加之后】数据个数： " + all.size() + ", 链表是否为空集合： " + all.isEmpty());
        Object result [] = all.toArray();
        if (result != null) {
            for(Object obj : result) {
                System.out.println(obj);
            }
        }
        System.out.println("------------------数据获取的分割线-----------------");
        System.out.println(all.get(0));
        System.out.println(all.get(1));
        System.out.println(all.get(4));
        System.out.println("------------------数据判断的分割线-----------------");
        System.out.println(all.contains("Hello"));
        System.out.println(all.contains("高"));
    }
}