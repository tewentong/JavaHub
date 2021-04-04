import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/*
    使用dom4j解析XML文件
        dom4j是一个组织，针对xml解析，提供解析器dom4j
        dom4j不是javase的一部分，想要使用第一步导入jar包:dom4j-1.6.1.jar
        得到document:
            SAXReader reader = new SAXReader();
            Document document = reader.read(url);
        Document的父接口是Node：如果在Document里面找不到想要的方法，到Node里面去找
        Document里面的方法：getRootElement();   //获取根节点，返回的是Element
        Element也是一个接口，父接口是Node();
            Element和Node里面的方法：
                getParent();    //获取父节点
                addElement();   //添加标签    
                element(qname); //表示获取标签下面的第一个子标签，qname：标签的名称
                elements(qname);    //获取标签下面一层，名称为qname的所有子标签，qname：标签的名称
                elements(); //获取标签下面一层的所有子标签
    使用dom4j查询XML:
        解析是从上到下来解析的
        需求：查询p1.xml文件中所有name元素里面的值
            public static void queryName() throws Exception {
                // 1.创建解析器
                // 2.得到document
                // 3.得到根节点, getRootElement(); 返回值为Element
                // 4.得到所有的p1标签 root.elements("p1"); 返回值为List集合
                // 5.得到p1下面的name
                // 6.得到name里面的值

                // 创建解析器
                SAXReader saxReader = new SAXReader();
                // 得到document
                Document document = saxReader.read("/home/kwj-at-lzu/Java/WebFoundation/XML/p1.xml");
                // 得到根节点
                Element root = document.getRootElement();

                // 得到所有的p1标签
                // element(qname),表示获取标签下面的第一个子标签，qname:标签的名称
                // elements(qname),获取标签下面一层，名称为qname的所有子标签，qname：标签的名称
                // elements(),获取标签下面一层的所有子标签
                List<Element> list = root.elements("p1");
                // 遍历list集合
                for (Element element : list) {
                    // element是每一个p1元素
                    // 得到p1下面的name元素
                    Element nameI = element.element("name");
                    // 得到name里面的值
                    String s = nameI.getText();
                    System.out.println(s);
                }
            }
        需求：查询p1.xml文件中第一个name元素的值
            public static void queryFirstName() throws Exception {
                // 1.创建解析器
                // 2.得到document
                // 3.得到根节点, getRootElement(); 返回值为Element
                // 4.获取文件中第一个p1
                // 5.得到第一个p1的name元素
                // 6.得到name里面的值

                // 创建解析器
                SAXReader saxReader = new SAXReader();
                // 得到document
                Document document = saxReader.read("/home/kwj-at-lzu/Java/WebFoundation/XML/p1.xml");
                // 得到根节点
                Element root = document.getRootElement();

                // 获取文件中第一个p1
                Element firstP1 = root.element("p1");
                // 得到第一个p1的name元素
                Element firstName = firstP1.element("name");
                // 得到name里面的值
                String s = firstName.getText();
                System.out.println(s);
            }
        需求：查询p1.xml文件中第二个name元素的值
            public static void querySecondName() throws Exception {
                // 1.创建解析器
                // 2.得到document
                // 3.得到根节点, getRootElement(); 返回值为Element
                // 4.得到所有的p1标签 root.elements("p1"); 返回值为List集合
                // 5.得到p1下面的name
                // 6.得到第二个name里面的值

                // 创建解析器
                SAXReader saxReader = new SAXReader();
                // 得到document
                Document document = saxReader.read("/home/kwj-at-lzu/Java/WebFoundation/XML/p1.xml");
                // 得到根节点
                Element root = document.getRootElement();

                // 得到所有的p1标签
                List<Element> list = root.elements("p1");
                // 得到第二个p1，list集合下标从0开始
                Element secondP1 = list.get(1);
                // 得到第二个p1的nam属性
                Element secondName = secondP1.element("name");
                // 得到name的具体的值
                String s = secondName.getText();
                System.out.println(s);
            }
    使用dom4j实现添加操作
        需求：在p2.xml文件的末尾添加一个元素 <sex>nv</sex>
            public static void addSex() throws Exception {
                // 1.创建解析器
                // 2.得到document
                // 3.得到根节点
                // 4.获取第一个p1
                // 5.在p1下面添加元素
                // 6.在添加完成之后元素下面添加文本
                // 7.回写xml文件

                // 创建解析器
                SAXReader saxReader = new SAXReader();
                // 得到document
                Document document = saxReader.read("/home/kwj-at-lzu/Java/WebFoundation/XML/p2.xml");
                // 得到根节点
                Element root = document.getRootElement();
                // 得到第一个p1
                Element firstP1 = root.element("p1");
                // 在p1下面直接添加元素
                Element addSex = firstP1.addElement("sex");
                // 在添加完成的元素addSex下面添加文本
                addSex.setText("nv");
                // 回写xml文件
                OutputFormat format = OutputFormat.createPrettyPrint(); // 表示可以有缩进的格式效果
                // OutputFormat format2 = OutputFormat.createCompactFormat();  // 压缩的格式，最后的文件完全没有缩进
                XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("/home/kwj-at-lzu/Java/WebFoundation/XML/p2.xml"),
                        format);
                xmlWriter.write(document);// 把内存中的document写到文件里面
                // 关闭流
                xmlWriter.close();
            }
*/
public class Dom4jParser {
    // 查询xml中所有name元素的值
    public static void queryName() throws Exception {
        // 1.创建解析器
        // 2.得到document
        // 3.得到根节点, getRootElement(); 返回值为Element
        // 4.得到所有的p1标签 root.elements("p1"); 返回值为List集合
        // 5.得到p1下面的name
        // 6.得到name里面的值

        // 创建解析器
        SAXReader saxReader = new SAXReader();
        // 得到document
        Document document = saxReader.read("/home/kwj-at-lzu/Java/WebFoundation/XML/p1.xml");
        // 得到根节点
        Element root = document.getRootElement();

        // 得到所有的p1标签
        // element(qname),表示获取标签下面的第一个子标签，qname:标签的名称
        // elements(qname),获取标签下面一层，名称为qname的所有子标签，qname：标签的名称
        // elements(),获取标签下面一层的所有子标签
        List<Element> list = root.elements("p1");
        // 遍历list集合
        for (Element element : list) {
            // element是每一个p1元素
            // 得到p1下面的name元素
            Element nameI = element.element("name");
            // 得到name里面的值
            String s = nameI.getText();
            System.out.println(s);
        }
    }

    // 查询xml中第一个name元素的值
    public static void queryFirstName() throws Exception {
        // 1.创建解析器
        // 2.得到document
        // 3.得到根节点, getRootElement(); 返回值为Element
        // 4.获取文件中第一个p1
        // 5.得到第一个p1的name元素
        // 6.得到name里面的值

        // 创建解析器
        SAXReader saxReader = new SAXReader();
        // 得到document
        Document document = saxReader.read("/home/kwj-at-lzu/Java/WebFoundation/XML/p1.xml");
        // 得到根节点
        Element root = document.getRootElement();

        // 获取文件中第一个p1
        Element firstP1 = root.element("p1");
        // 得到第一个p1的name元素
        Element firstName = firstP1.element("name");
        // 得到name里面的值
        String s = firstName.getText();
        System.out.println(s);
    }

    // 查询xml中第二个name元素的值
    public static void querySecondName() throws Exception {
        // 1.创建解析器
        // 2.得到document
        // 3.得到根节点, getRootElement(); 返回值为Element
        // 4.得到所有的p1标签 root.elements("p1"); 返回值为List集合
        // 5.得到p1下面的name
        // 6.得到第二个name里面的值

        // 创建解析器
        SAXReader saxReader = new SAXReader();
        // 得到document
        Document document = saxReader.read("/home/kwj-at-lzu/Java/WebFoundation/XML/p1.xml");
        // 得到根节点
        Element root = document.getRootElement();

        // 得到所有的p1标签
        List<Element> list = root.elements("p1");
        // 得到第二个p1，list集合下标从0开始
        Element secondP1 = list.get(1);
        // 得到第二个p1的nam属性
        Element secondName = secondP1.element("name");
        // 得到name的具体的值
        String s = secondName.getText();
        System.out.println(s);
    }

    // 在p2.xml文件的末尾添加<sex>nv</sex>
    public static void addSex() throws Exception {
        // 1.创建解析器
        // 2.得到document
        // 3.得到根节点
        // 4.获取第一个p1
        // 5.在p1下面添加元素
        // 6.在添加完成之后元素下面添加文本
        // 7.回写xml文件

        // 创建解析器
        SAXReader saxReader = new SAXReader();
        // 得到document
        Document document = saxReader.read("/home/kwj-at-lzu/Java/WebFoundation/XML/p2.xml");
        // 得到根节点
        Element root = document.getRootElement();
        // 得到第一个p1
        Element firstP1 = root.element("p1");
        // 在p1下面直接添加元素
        Element addSex = firstP1.addElement("sex");
        // 在添加完成的元素addSex下面添加文本
        addSex.setText("nv");
        // 回写xml文件
        OutputFormat format = OutputFormat.createPrettyPrint(); // 表示可以有缩进的格式效果
        // 压缩的格式，最后的文件完全没有缩进
        // OutputFormat format2 = OutputFormat.createCompactFormat();
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("/home/kwj-at-lzu/Java/WebFoundation/XML/p2.xml"),
                format);
        xmlWriter.write(document);// 把内存中的document写到文件里面
        // 关闭流
        xmlWriter.close();
    }

    public static void main(String[] args) throws Exception {
        queryName();
        System.out.println("---------------------------------------------");
        queryFirstName();
        System.out.println("---------------------------------------------");
        querySecondName();
        addSex();
    }
}