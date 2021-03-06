interface ILink1204<E> {    //设置泛型避免安全隐患
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
class LinkImpl1204<E> implements ILink1204<E> {    
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
            LinkImpl1204.this.returnData [LinkImpl1204.this.foot ++] = this.data;   //foot是对象数组角标，不是链表的根root
            if(this.next != null) {
                this.next.toArrayNode();
            }
        }

        public E getNode(int index) {
            if (LinkImpl1204.this.foot ++ == index) {  //索引相同就返回，索引不同则考虑下一次： foot++
                return this.data;   //返回当前数据
            }else {
                return this.next.getNode(index);
            }
        }
        public void setNode(int index, E data) {
            if (LinkImpl1204.this.foot ++ == index) {   //索引相同就修改数据，索引不同则考虑下一次： foot++
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
interface IGoods {  //定义商品标准
    public String getName();
    public double getPrice();
}

interface IShoppingCart {   //定义购物车标准
    public void add(IGoods good);   //
    public void delete(IGoods good);    //删除商品
    public Object[] getAll();    //获得购物车中的全部商品信息
}

class ShoppingCartImpl implements IShoppingCart {   //购物车
    private ILink1204<IGoods> allGoodses = new LinkImpl1204<IGoods>();
    public void add(IGoods goods) {
        this.allGoodses.add(goods);
    }
    public void delete(IGoods goods) {
        this.allGoodses.remove(goods);
    }
    public Object [] getAll() {
        return this.allGoodses.toArray();
    }
}

class Cashier { //收银台
    private IShoppingCart shoppingCart;
    public Cashier(IShoppingCart shoppingCart){
        this.shoppingCart = shoppingCart;
    }
    public double allPrice(){  //计算总价
        double all = 0.0;
        Object result [] = this.shoppingCart.getAll();
        for (Object obj : result) {
            IGoods goods = (IGoods) obj;
            all += goods.getPrice();
        }
        return  all;
    }
    public int allCount() { //商品数量
        return this.shoppingCart.getAll().length;
    }
}
class Book implements IGoods {
    private String name;
    private double price;
    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return this.name;
    }
    public double getPrice() {
        return this.price;
    }
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        Book book = (Book) obj;
        return this.name.equals(book.name) && this.price == book.price;
    }
    public String toString() {
        return "【图书信息】名称： " +this.name + "、 价格： " + this.price;
    }
}

class Bag implements IGoods {
    private String name;
    private double price;
    public Bag(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return this.name;
    }
    public double getPrice() {
        return this.price;
    }
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        Bag bag = (Bag) obj;
        return this.name.equals(bag.name) && this.price == bag.price;
    }
    public String toString() {
        return "【背包信息】名称： " +this.name + "、 价格： " + this.price;
    }
}

public class ShoppingCartDemo {
    public static void main(String[] args) {
        IShoppingCart cart = new ShoppingCartImpl();
        cart.add(new Book("Java开发", 79.8));
        cart.add(new Book("Oracle", 89.8));
        cart.add(new Bag("小强背包", 889.8));
        Cashier cas = new Cashier(cart);
        System.out.println("总价格：" + cas.allPrice() + "、购买数量" + cas.allCount());
    }
}