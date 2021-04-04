import java.util.List;
import org.dom4j.Document;
import org.dom4j.Node;

/*
    Xpath简介
        可以直接获取到某个元素
        第一种形式：
            /AAA/BBB/CCC ---> 表示一层一层的，第一层AAA，第二层BBB，第三层CCC
        第二种形式：
            //BBB ---> 表示只要名称是BBB就都可以得到
        第三种形式：
            /* ---> 表示所有元素
        第四种形式：
            /AAA/BBB[1] ---> 表示第一个BBB元素
            /AAA/BBB[last()] ---> 表示最后一个BBB元素
        第五种形式：
            //BBB[@id] ---> 表示只要BBB元素上有id属性，都得到
        第六种形式：
            //BBB[@id='bi'] ---> 表示元素的名称是BBB，并且在BBB上面有id属性，且id属性值为b1

    使用Dom4j来支持Xpath
        默认的情况下，dom4j是不支持Xpath的
        如果想要在dom4j中引入Xpath，需要导入jar包: jaxen-1.1.6.jar
        在dom4j里面提供了两个方法，用来支持Xpath:
            获取多个节点： selectNodes("Xpath表达式");
            获取单一节点： selectSingleNode("Xpath表达式");
        使用Xpath来实现：查询p2.xml中所有name元素的值
            所有name元素的Xpath表示： //name
            使用: selectNodes("//name");
            public static void selectAllName() throws Exception {
                // 1.得到document
                // 2.直接使用selectNodes("")方法来得到所有的name元素

                // 得到document
                Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
                // 使用selectNode("//name")得到所有的name元素
                List<Node> list = document.selectNodes("//name");
                // 遍历list集合
                for (Node node : list) {
                    // 每次遍历中，每一个node都是一个name元素
                    String name = node.getText();
                    System.out.println(name);
                }
            }
        使用Xpath来实现：查询p2.xml中第一个p1下面的name的值
            第一个p1下面的name的值的表示：  //p1[@id1='aaaa']/name
            使用: selectSingleNode("//p1[@id1='aaaa']/name");
            public static void getNameOfFirstP1() throws Exception {
                // 1.得到document
                // 2.直接使用selectSingleNode("")方法来获取第一个p1下面的name值
                // --->Xpath: //pa[@id1='aaaa'/ma,e]

                // 得到document
                Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
                // 使用selectSingleNode("//p1[@id1='aaaa']/name")来获取第一个p1下面的name属性值
                Node name1 = document.selectSingleNode("//p1[@id1='aaaa']/name");
                String attributeOfname1 = name1.getText();
                System.out.println(attributeOfname1);
            }
*/
public class XPathIntroAndDom4j {
    // 使用Xpath来实现：查询p2.xml中所有name元素的值
    public static void selectAllName() throws Exception {
        // 1.得到document
        // 2.直接使用selectNodes("")方法来得到所有的name元素

        // 得到document
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        // 使用selectNode("//name")得到所有的name元素
        List<Node> list = document.selectNodes("//name");
        // 遍历list集合
        for (Node node : list) {
            // 每次遍历中，每一个node都是一个name元素
            String name = node.getText();
            System.out.println(name);
        }
    }

    // 查询p2.xml中第一个p1下面的name的值
    public static void getNameOfFirstP1() throws Exception {
        // 1.得到document
        // 2.直接使用selectSingleNode("")方法来获取第一个p1下面的name值
        // --->Xpath: //pa[@id1='aaaa'/ma,e]

        // 得到document
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        // 使用selectSingleNode("//p1[@id1='aaaa']/name")来获取第一个p1下面的name属性值
        Node name1 = document.selectSingleNode("//p1[@id1='aaaa']/name");
        String attributeOfname1 = name1.getText();
        System.out.println(attributeOfname1);
    }

    public static void main(String[] args) throws Exception {
        selectAllName();
        System.out.println("------------------------------");
        getNameOfFirstP1();
    }
}