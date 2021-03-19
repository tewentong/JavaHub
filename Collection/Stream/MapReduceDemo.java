
/*
    MapReduce基础模型：
        在进行数据分析的时候有一个最重要的基础模型：MapReduce模型
        对于这个模型一共是分为两个部分：
            Map处理部分、Reduce分析部分
        在进行数据分析之前必须要对数据进行合理的处理，而后才可以做统计分析操作
        范例：MapReduce基础模型
            import java.util.ArrayList;
            import java.util.DoubleSummaryStatistics;
            import java.util.List;

            class Order { // 订单信息
                private String name; // 商品名称
                private double price; // 商品单价
                private int amount; // 商品数量

                public Order(String name, double price, int amount) {
                    this.name = name;
                    this.price = price;
                    this.amount = amount;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public int getAmount() {
                    return amount;
                }

                public void setAmount(int amount) {
                    this.amount = amount;
                }
            }

            public class MapReduceDemo {
                public static void main(String[] args) throws Exception {
                    // 如果要想使用Stream进行分析处理，则一定要将全部要分析的数据保存在集合之中
                    List<Order> all = new ArrayList<Order>();
                    all.add(new Order("小强娃娃", 9.9, 10));
                    all.add(new Order("林弱充气娃娃", 2987.9, 3));
                    all.add(new Order("不强牌笔记本电脑", 8987.9, 8));
                    all.add(new Order("弱强茶杯", 2.9, 800));
                    all.add(new Order("阿强牌煎饼", 0.9, 138));
                    // 分析购买商品之中带有“强”的信息数据,并且进行商品单价和数量的处理，随后分析汇总
                    DoubleSummaryStatistics stat = all.stream().filter((ele) -> ele.getName().contains("强"))
                            .mapToDouble((orderObject) -> orderObject.getPrice() * orderObject.getAmount()).summaryStatistics();
                    System.out.println("购买数量： " + stat.getCount());
                    System.out.println("购买总价： " + stat.getSum());
                    System.out.println("平均花费： " + stat.getAverage());
                    System.out.println("最高花费： " + stat.getMax());
                    System.out.println("最低花费： " + stat.getMin());
                }
            }
            这些分析操作只是JDK本身提供的支持，而实际之中，肯定不可能这样进行，
                因为所有的数据如果都保存在内存里面，
                那么面对于大数据的环境，就崩了
*/
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

class Order { // 订单信息
    private String name; // 商品名称
    private double price; // 商品单价
    private int amount; // 商品数量

    public Order(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

public class MapReduceDemo {
    public static void main(String[] args) throws Exception {
        // 如果要想使用Stream进行分析处理，则一定要将全部要分析的数据保存在集合之中
        List<Order> all = new ArrayList<Order>();
        all.add(new Order("小强娃娃", 9.9, 10));
        all.add(new Order("林弱充气娃娃", 2987.9, 3));
        all.add(new Order("不强牌笔记本电脑", 8987.9, 8));
        all.add(new Order("弱强茶杯", 2.9, 800));
        all.add(new Order("阿强牌煎饼", 0.9, 138));
        // 分析购买商品之中带有“强”的信息数据,并且进行商品单价和数量的处理，随后分析汇总
        DoubleSummaryStatistics stat = all.stream().filter((ele) -> ele.getName().contains("强"))
                .mapToDouble((orderObject) -> orderObject.getPrice() * orderObject.getAmount()).summaryStatistics();
        System.out.println("购买数量： " + stat.getCount());
        System.out.println("购买总价： " + stat.getSum());
        System.out.println("平均花费： " + stat.getAverage());
        System.out.println("最高花费： " + stat.getMax());
        System.out.println("最低花费： " + stat.getMin());
    }
}