@FunctionalInterface
interface IMath1129 {
    public int add(int x, int y);
}

public class LambdaDemo2{
    public static void main(String[] args) {
        IMath1129 math = (t1,t2) -> {
           return t1 + t2;
        };
        System.out.println(math.add(10, 20));
    }
}

/*
简化为只有一行的操作
public class LambdaDemo2 {
    public static void main(Stirng[] args) {
        IMath1129 math = (t1, t2) -> t1 + t2;
    }
    System.out.println(math.add(10, 20));
}

*/