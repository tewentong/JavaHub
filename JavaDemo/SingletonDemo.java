class Singleton{
    private static final Singleton INSTANCE = new Singleton();
    private Singleton() {} 
    public static Singleton getInstance() {
        return INSTANCE;
    }
    public void print() {
        System.out.println("www.mldn.com");
    }
}
public class SingletonDemo {
    public static void main(String[] args) {
        Singleton instance = null;
        instance = Singleton.getInstance();
        instance.print();
    }    
}

/*
    class Singleton {
        private static Singleton instance;
        private Singleton() {}
        public static Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }
*/