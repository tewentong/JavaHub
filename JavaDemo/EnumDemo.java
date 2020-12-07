enum Color {
    RED("红色"), GREEN("绿色"), BLUE("蓝色"); //枚举对象要写在首行
    private String title; //定义属性
    private Color(String title) {
        this.title = title;
    }
    public String toString() {
        return this.title;
    }
}
public class EnumDemo {
    public static void main(String[] args) {
        for(Color c : Color.values()) {
            System.out.println(c.ordinal() + " - " + c.name() + " - " + c);
        }
    }
}