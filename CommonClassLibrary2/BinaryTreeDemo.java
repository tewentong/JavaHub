/*
    二叉树结构：
        在进行链表结构开发的过程之中会发现所有的数据按照首位相连的状态进行保存
        那么进行某一个数据查询的时候（判断该数据是否存在），这种情况下，它所面对的时间复杂度O(n)
        如果他的数据量小（不超过30个）情况下，性能上是不会有太大的差别的
        而一旦保存的数据量很大，这个时候时间复杂度就会严重损耗程序运行的性能，那么数据的存储结构就必须发生改变
        应该可以尽可能的减少检索次数为出发点进行设计
        对于现在的数据结构而言，最好的性能就是O(logn)
        所以现在要想实现它就可以利用二叉树的结构来完成

    如果要实现一棵树的定义，那么就需要去考虑数据的存储形式，在二叉树的实现之中其基本的实现原理如下：
        取第一个数据为保存的根节点，小于等于根节点的数据要放在节点的左子树，而大于节点的数据要放在节点的右子树
    如果要进行数据检索的话，要进行每个数据节点的判断
        但是它的判断是区分左右的，不会是整个的结构都进行判断处理，那么他的时间复杂度是O(logn)
    而对于二叉树而言，在进行数据获取的时候也有三种形式：前序遍历（根-左-右）、中序遍历（左-根-右）、后序遍历（左-右-根）


    二叉树的基础实现：
        在实现二叉树的处理之中最为关键性的问题在于数据的保存，而且数据由于牵扯到对象比较的问题，那么一定要有比较器的支持
        这个比较器首选的一定是Comparable，所以本次将保存一个Person类数据
        随后如果需要进行数据的保存一定需要一个节点类，节点类里面牵扯到数据的保存问题，所以必须Comparble（可以区分大小）
    
    在进行数据添加的时候只是实现了节点关系的保存，而这种关系保存后的结果就是所有的数据都属于有序排列
    
*/
import java.util.Arrays;
import java.lang.Comparable;
/**
 * 实现二叉树的数据操作
 * @param <T> 要进行二叉树的实现
 */
class BinaryTree<T extends Comparable<T>> {
    private class Node {
        private Comparable<T> data; //存放Comparable,可以比较大小
        private Node parent;    //保存父节点
        private Node left;  //保存左子树
        private Node right; //保存右子树
        public Node(Comparable<T> data) {   //构造方法进行数据的存储
            this.data = data;
        }
        /**
         * 实现节点数据的适当位置的存储
         * @param newNode 创建的新节点
         */
        public void addNode(Node newNode) {
            if(newNode.data.compareTo((T)this.data) <= 0 ) {    //比当前节点的数据小
                if (this.left == null) {    //现在没有左子树
                    this.left = newNode;    //保存左子树
                    newNode.parent = this; //保存父节点
                }else { //需要向左边继续判断
                    this.left.addNode(newNode); //继续向下判断
                }
            }else { //比根节点的数据要大
                if (this.right == null) {   //现在没有右子树
                    this.right = newNode;   //保存右子树
                    newNode.parent = this;  //保存父节点
                }else { //需要向右边继续判断
                    this.right.addNode(newNode);    //继续向下判断
                }
            }
        }
        /**
         * 实现所有数据的获取处理，按照中序遍历的形式来完成
         */
        public void toArrayNode() {
            if (this.left != null) {    //有左子树
                this.left.toArrayNode();    //递归调用
            }
            BinaryTree.this.returnData[BinaryTree.this.foot ++] = this.data;
            if (this.right != null) {
                this.right.toArrayNode();
            }
        }
    }
    //-------------以下为二叉树的功能实现--------------
    private Node root;  //保存的是根节点
    private int count;  //保存数据个数
    private Object[] returnData; //返回的数据
    private int foot = 0;   //角标控制
    /**
     * 进行数据的保存
     * @param data  要保存的数据内容
     * @exception NullPointerException 保存数据为空时抛出的异常
     */
    public void add(Comparable<T> data) throws Exception {
        if (data == null) {
            throw new NullPointerException("保存的数据不允许为空");
        }
        //所有的数据本身不具备节点关系的匹配，那么一定要将其包装在Node类之中
        Node newNode = new Node(data);  //保存节点
        if (this.root == null) {    //现在没有根节点，则第一个节点作为根节点
            this.root = newNode;
        }else { //需要为其保存到一个合适的节点
            this.root.addNode(newNode); //交由Node类负责处理
        }
        this.count ++;
    }
    /**
     * 以对象数组的形式返回全部的数据，如果没有数据返回null
     * @return 全部数据
     */
    public Object[] toArray() {
        if (this.count == 0) {
            return null;
        }
        this.returnData = new Object[this.count];   //保存长度为数组长度
        this.foot = 0;  //角标清零
        this.root.toArrayNode();    //直接通过Node类负责
        return this.returnData; //返回全部的数据
    }
}
class Person030504 implements Comparable<Person030504> {
    private String name;
    private int age;
    public Person030504(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public int compareTo(Person030504 per) {
        return this.age - per.age;
    }
    //无参构造、setter、getter略
    @Override
    public String toString() {
        return "【Person类对象】姓名： " + this.name + "、年龄：" + this.age + "\n";
    }
}
public class BinaryTreeDemo {
    public static void main(String[] args) throws Exception {
        BinaryTree<Person030504> tree = new BinaryTree<Person030504>();
        tree.add(new Person030504("小强-80", 80));
        tree.add(new Person030504("小强-30", 30));
        tree.add(new Person030504("小强-50", 50));
        tree.add(new Person030504("小强-60", 60));
        tree.add(new Person030504("小强-90", 90));
        System.out.println(Arrays.toString(tree.toArray()));
    }
}