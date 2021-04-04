import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
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
    使用dom4j在特定的位置添加元素
        需求：在p2.xml文件第一个p1下面的age标签之前添加<school>ecit.edu.cn</school>
            public static void addSchoolBeforeAge() throws Exception {
                // 1.创建解析器
                // 2.得到document
                // 3.得到根节点
                // 4.获取第一个p1
                // 5.获取p1下面的所有的元素
                // --->elements()方法，返回List集合
                // --->使用list里面的方法，在特定位置添加元素
                // --->创建元素，并在新创建的元素下面添加文本
                // ------->add(int index, E element);第一个参数下标从0开始，第二个参数是要添加的元素
                // 6.回写xml

                // 创建解析器
                SAXReader saxReader = new SAXReader();
                // 得到document
                Document document = saxReader.read("/home/kwj-at-lzu/Java/WebFoundation/XML/p2.xml");
                // 得到根节点
                Element root = document.getRootElement();
                // 得到第一个p1
                Element firstP1 = root.element("p1");
                // 返回包含p1所有子元素的List集合
                List<Element> list = firstP1.elements();
                // 创建需要添加的元素，使用帮助类DocumentHelper
                Element schoolElement = DocumentHelper.createElement("school");
                // 在新元素schoolElement下面追加文本信息
                schoolElement.setText("ecit.edu.cn");
                // 在list特定位置添加元素
                list.add(1, schoolElement);
                // 将内存中的操作回写到p2.xml文件当中
                OutputFormat format = OutputFormat.createPrettyPrint();
                XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("/home/kwj-at-lzu/Java/WebFoundation/XML/p2.xml"),
                        format);
                xmlWriter.write(document);
                xmlWriter.close();
            }
    使用dom4j实现修改节点的操作
        需求：修改p2.xml文件中第一个p1下面的age元素的值，改为<age>30</age>
            public static void modifyAge() throws Exception {
                // 1.得到document
                // 2.得到根节点，然后再得到第一个p1的元素
                // 3.得到第一个p1下面的age元素
                // 4.修改值为30
                // 5.回写xml

                // 得到document
                Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
                // 得到根节点，然后再得到第一个p1
                Element root = document.getRootElement();
                Element firstP1 = root.element("p1");
                // 得到第一个p1下面的age元素
                Element age = firstP1.element("age");
                // 修改age值为30
                age.setText("300");
                // 回写xml
                Dom4jUtils.xmlWriters(Dom4jUtils.PATH, document);
            }
    使用dom4j来实现删除节点的操作
        删除是通过父节点执行删除操作
        需求：删除p2.xml文件中第一个p1下面的<school>ecit.edu.cn</school>元素
            public static void deleteSchool() throws Exception {
                // 1.得到document
                // 2.得到根节点
                // 3.得到第一个p1标签
                // 4.得到第一个p1下面的school元素
                // 5.删除school（使用父节点p1删除school）
                // 6.回写xml

                // 得到document
                Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
                // 得到根节点
                Element root = document.getRootElement();
                // 得到第一个p1标签
                Element firstP1 = root.element("p1");
                // 得到第一个p1下面的school元素
                Element school = firstP1.element("school");
                // 借助父节点firstP1来删除school
                // school.getParent(); //获取父节点
                firstP1.remove(school);
                // 回写
                Dom4jUtils.xmlWriters(Dom4jUtils.PATH, document);
            }
    使用dom4j获取属性
        需求：获取p2.xml文件中第一个p1里面的id1的属性值
            public static void getAttributeValue() throws Exception {
                // 1.得到document
                // 2.得到根节点
                // 3.得到第一个p1元素
                // 4.得到第一个p1里面的属性值

                // 得到document
                Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
                // 得到根节点
                Element root = document.getRootElement();
                // 得到第一个p1元素
                Element firstP1 = root.element("p1");
                // 得到第一个p1的属性值
                String id1 = firstP1.attributeValue("id1");
                System.out.println(id1);
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

    // 在p2.xml文件第一个p1下面的age标签之前添加<school>ecit.edu.cn</school>
    public static void addSchoolBeforeAge() throws Exception {
        // 1.创建解析器
        // 2.得到document
        // 3.得到根节点
        // 4.获取第一个p1
        // 5.获取p1下面的所有的元素
        // --->elements()方法，返回List集合
        // --->使用list里面的方法，在特定位置添加元素
        // --->创建元素，并在新创建的元素下面添加文本
        // ------->add(int index, E element);第一个参数下标从0开始，第二个参数是要添加的元素
        // 6.回写xml

        // 创建解析器
        SAXReader saxReader = new SAXReader();
        // 得到document
        Document document = saxReader.read("/home/kwj-at-lzu/Java/WebFoundation/XML/p2.xml");
        // 得到根节点
        Element root = document.getRootElement();
        // 得到第一个p1
        Element firstP1 = root.element("p1");
        // 返回包含p1所有子元素的List集合
        List<Element> list = firstP1.elements();
        // 创建需要添加的元素，使用帮助类DocumentHelper
        Element schoolElement = DocumentHelper.createElement("school");
        // 在新元素schoolElement下面追加文本信息
        schoolElement.setText("ecit.edu.cn");
        // 在list特定位置添加元素
        list.add(1, schoolElement);
        // 将内存中的操作回写到p2.xml文件当中
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("/home/kwj-at-lzu/Java/WebFoundation/XML/p2.xml"),
                format);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    // 修改p2.xml文件中第一个p1下面的age元素的值，改为<age>30</age>
    public static void modifyAge() throws Exception {
        // 1.得到document
        // 2.得到根节点，然后再得到第一个p1的元素
        // 3.得到第一个p1下面的age元素
        // 4.修改值为30
        // 5.回写xml

        // 得到document
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        // 得到根节点，然后再得到第一个p1
        Element root = document.getRootElement();
        Element firstP1 = root.element("p1");
        // 得到第一个p1下面的age元素
        Element age = firstP1.element("age");
        // 修改age值为30
        age.setText("300");
        // 回写xml
        Dom4jUtils.xmlWriters(Dom4jUtils.PATH, document);
    }

    // 删除p2.xml文件中第一个p1下面的<school>ecit.edu.cn</school>元素
    public static void deleteSchool() throws Exception {
        // 1.得到document
        // 2.得到根节点
        // 3.得到第一个p1标签
        // 4.得到第一个p1下面的school元素
        // 5.删除school（使用父节点p1删除school）
        // 6.回写xml

        // 得到document
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        // 得到根节点
        Element root = document.getRootElement();
        // 得到第一个p1标签
        Element firstP1 = root.element("p1");
        // 得到第一个p1下面的school元素
        Element school = firstP1.element("school");
        // 借助父节点firstP1来删除school
        // school.getParent(); //获取父节点
        firstP1.remove(school);
        // 回写
        Dom4jUtils.xmlWriters(Dom4jUtils.PATH, document);
    }

    // 获取p2.xml文件中第一个p1里面的id1的属性值
    public static void getAttributeValue() throws Exception {
        // 1.得到document
        // 2.得到根节点
        // 3.得到第一个p1元素
        // 4.得到第一个p1里面的属性值

        // 得到document
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        // 得到根节点
        Element root = document.getRootElement();
        // 得到第一个p1元素
        Element firstP1 = root.element("p1");
        // 得到第一个p1的属性值
        String id1 = firstP1.attributeValue("id1");
        System.out.println(id1);
    }

    public static void main(String[] args) throws Exception {
        queryName();
        System.out.println("---------------------------------------------");
        queryFirstName();
        System.out.println("---------------------------------------------");
        querySecondName();
        // addSex();
        // addSchoolBeforeAge();
        // modifyAge();
        // deleteSchool();
        System.out.println("---------------------------------------------");
        getAttributeValue();
    }
}