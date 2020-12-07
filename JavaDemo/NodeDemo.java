//直接操作Node
//应该有一个专门的类进行节点的引用关系配置，因为真实的使用者实际上关心的只是数据的存储与获取
class Node<E> {
    private E data;
    private Node next;
    public Node(E data) {
        this.data = data;
    }
    public E getData() {
        return this.data;
    }
    public void setNext(Node<E> next) {
        this.next = next;
    }
    public Node getNext() {
        return this.next;
    }
}

public class NodeDemo {
    public static void main(String[] args) {
        Node<String> n1 = new Node<String>("火车头");
        Node<String> n2 = new Node<String>("车厢一");
        Node<String> n3 = new Node<String>("车厢二");
        Node<String> n4 = new Node<String>("车厢三");
        Node<String> n5 = new Node<String>("车厢四");
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        print(n1);
    }

    public static void print(Node<?> node) {
        if(node != null) {  //有节点
            System.out.println(node.getData());
            print(node.getNext());  //递归调用
        }
    }
}