import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/*
    XML解析的简介
        xml是标记型文档
        js使用dom解析标记型文档？
            根据html的层级结构，在内存中分配一个树形结构，把html的标签，属性和文本都封装成对象
            document对象、element对象、属性对象、文本对象、Node节点对象
        xml的解析方式(技术)：dom(Document Object Model)和sax(Simple API for XML)
            使用dom解析xml文件：
                根据xml的层级结构在内存中分配一个树形结构，把xml的标签、属性和文本都封装成对象
                使用dom的缺点：如果文件过大，会造成内容溢出
                使用dom的优点：很方便实现文件的增、删、改操作
            sax解析过程：
                采用事件驱动，边读边解析：
                    从上到下，一行一行的解析，解析到某一个对象（标签、属性、文本），返回对象名称
                使用sax的缺点：不能实现增、删、改操作
                使用sax的优点：如果文件过大，不会造成内存溢出，方便实现查询的操作
        想要解析xml，首先需要解析器
            不同的公司和组织提供了 针对dom和sax方式的解析器，通过API的方式提供
                sun公司,针对dom和sax解析器      jaxp
                dom4j组织，针对dom和sax解析器   dom4j(***实际开发中***)
                jdom组织，针对dom和sax解析器    jdom
    jaxp的API查看(Java API for XML Processing)
        jaxp是javase的一部分,由以下几个包及其子包组成：
            org.w3c.dom：提供DOM方式解析XML的标准接口
            org.xml.sax：提供SAX方式解析XML的标准接口
            javax.xml：提供了解析XML文档的类
        jaxp解析器在jdk的javax.xml.parsers包中
            四个类，分别是针对dom和sax解析使用的类
            dom: 
                DocumentBuilder：解析器类 
                    public abstract class DocumentBuilder extends Object
                    抽象类，实例可以从 DocumentBuilderFactory.newDocumentBuilder()方法获取   

                    解析xml: parse("xml路径");  返回Document整个文档
                        返回的Document是一个接口，其父接口是Node，如果在document里面找不到想要的方法，到Node里面去找
                    在Document里面方法： 
                        getElementsByTagName(String tagname)  这个方法可以得到标签  返回集合 NodeList
                        createElement(String tagName)   创建标签
                        createTextNode(String data)     创建文本
                        appendChild(Node newChild)      把文本添加到标签的下面
                        removeChild(Node oldChild)      删除节点
                        getParentNode()                 获取父节点
                    NodeList里面方法：
                        getLength()                     得到集合的长度
                        item(int index)                 根据下标得到具体的值
                            for(int i = 0 ; i < list.getLength() ; i ++) {
                                list.item(i);   //遍历整个集合
                            }
                        getTextContent()                得到标签里的内容
                DocumentBuilderFactory：解析器工厂
                    public abstract class DocumentBuilderFactory extends Object
                    抽象类，newInstance()获取DocumentBuilderFactory实例
            sax: 
                SAXParser：解析器类
                SAXParserFactory：解析器工厂
    使用jaxp实现查询操作
        查询person3.xml中所有的name元素的值
            public class XMLParsers {
                public static void main(String[] args) throws Exception {
                    // 查询所有name元素的值
                    // 1.创建解析器工厂
                    // 2.根据解析器工厂创建解析器
                    // 3.解析xml返回document
                    // 4.得到所有的Name元素
                    // 5.返回集合，遍历集合，得到每一个Name元素
                    // 创建解析器工厂
                    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                    // 创建解析器
                    DocumentBuilder builder = builderFactory.newDocumentBuilder();
                    // 解析xml返回Document
                    Document document = builder.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml");
                    // 得到所有的Name元素
                    NodeList list = document.getElementsByTagName("name");
                    // 遍历集合
                    for (int i = 0; i < list.getLength(); i++) {
                        Node name1 = list.item(i); // 得到每一个Name元素
                        // 得到Name元素里的值
                        String s = name1.getTextContent();
                        System.out.println(s);
                    }
                }
            }
            程序输出：
                zhangSan
                liSi 
            
        查询person3.xml中第一个name的值
            public static void selectSin() throws Exception {
                // 查询所有name元素的值
                // 1.创建解析器工厂
                // 2.根据解析器工厂创建解析器
                // 3.解析xml返回document
                // 4.得到所有的name元素
                // 5.返回集合，遍历集合，得到第一个name元素
                // 创建解析器工厂
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                // 创建解析器
                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                // 解析xml返回Document
                Document document = builder.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml");
                // 得到所有的Name元素
                NodeList list = document.getElementsByTagName("name");
                // 得到第一个元素
                Node firstNode = list.item(0);
                String firstName = firstNode.getTextContent();
                System.out.println(firstName);
            }
            程序输出：
                zhangSan
    使用jaxp来添加节点
        需求：在person3.xml第一个p1下面（末尾）添加<sex>nv</sex>
        在第一个p1下面(末尾)添加<sex>nv</sex>
            public static void addSex() throws Exception {
                // 1.创建解析器工厂
                // 2.根据解析器工厂创建解析器
                // 3.解析xml返回document
                // 4.得到第一个p1
                // ----得到所有的p1，使用item方法下标得到
                // 5.创建sex标签，createElement()
                // 6.创建文本，createTextNode()
                // 7.把文本添加到sex下面，appendChild()
                // 8.把sex添加到第一个p1下面
                // dom操作是在内存中操作，最后需要回写到person3.xml中
                // 9.回写person3.xml

                // 创建解析器工厂
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                // 创建解析器
                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                // 得到Document
                Document document = builder.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml");
                // 得到所有的p1
                NodeList list = document.getElementsByTagName("p1");
                // 得到第一个p1
                Node p1 = list.item(0);
                // 创建标签（元素）
                Element sex1 = document.createElement("sex");
                // 创建文本
                Text text1 = document.createTextNode("nv");
                // 把文本添加到text1下面
                sex1.appendChild(text1);
                // 把sex1添加到p1下面
                p1.appendChild(sex1);
                // 回写到xml中
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.transform(new DOMSource(document),
                        new StreamResult("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml"));
            }
    使用jaxp来修改节点
        需求：修改person3.xml中第一个p1下面sex内容为nan
        public static void modifySex() throws Exception {
            // 1.创建解析器工厂
            // 2.根据解析器工厂创建解析器
            // 3.解析xml返回document
            // 4.得到sex item()
            // 5.修改sex里面的值 setTextContent()
            // 6.回写xml

            // 创建解析器工厂
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            // 创建解析器
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            // 得到Document
            Document document = builder.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml");
            // 得到sex
            Node sex1 = document.getElementsByTagName("sex").item(0);
            // 修改sex的值
            sex1.setTextContent("nan");
            // 回写
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(new DOMSource(document),
                    new StreamResult("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml"));

        }
    使用jaxp来删除节点
        需求：删除<sex>nan</nan>节点
        public static void delSex() throws Exception {
            // 1.创建解析器工厂
            // 2.根据解析器工厂创建解析器
            // 3.解析xml返回document
            // 4.获取sex元素
            // 5.获取sex的父节点 getParentNode()
            // 6.删除使用父节点删除 removeChild方法
            // 7.回写xml操作

            // 创建解析器工厂
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            // 创建解析器
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            // 得到Document
            Document document = builder.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml");
            // 得到sex元素
            Node sex2 = document.getElementsByTagName("sex").item(1);
            // 得到sex1的父节点
            Node p1 = sex2.getParentNode();
            // 删除操作
            p1.removeChild(sex2);
            // 回写xml
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(new DOMSource(document),
                    new StreamResult("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml"));
        }
    使用jaxp来遍历节点
        需求：把person3.xml中的所有元素名称打印出来
        public static void listElement() throws Exception {
            // 1.创建解析器工厂
            // 2.根据解析器工厂创建解析器
            // 3.解析xml返回document
            // =============使用递归实现操作====================
            // 4.得到根节点
            // 5.得到根节点的子节点

            // 创建解析器工厂
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            // 创建解析器
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            // 得到Document
            Document document = builder.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml");

            // 编写一个方法实现遍历操作
            listRecursion(document);
        }

        递归遍历操作
        private static void listRecursion(Node node) {
            // 判断是元素类型时候才打印
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println(node.getNodeName());
            }
            // 得到一层子节点
            NodeList list = node.getChildNodes();
            // 遍历list
            for (int i = 0; i < list.getLength(); i++) {
                // 得到每一个节点
                Node nodeI = list.item(i);
                // 继续得到nodeI的子节点
                listRecursion(nodeI);
            }
        }
        程序输出：
            person
            p1
            name
            age
            sex
            p1
            name
            age
*/
public class XMLParsers {
    // 查询person3.xml中所有name的值
    public static void selectAll() throws Exception {
        // 查询所有name元素的值
        // 1.创建解析器工厂
        // 2.根据解析器工厂创建解析器
        // 3.解析xml返回document
        // 4.得到所有的Name元素
        // 5.返回集合，遍历集合，得到每一个Name元素
        // 创建解析器工厂
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // 创建解析器
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        // 解析xml返回Document
        Document document = builder.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml");
        // 得到所有的Name元素
        NodeList list = document.getElementsByTagName("name");
        // 遍历集合
        for (int i = 0; i < list.getLength(); i++) {
            Node name1 = list.item(i); // 得到每一个Name元素
            // 得到Name元素里的值
            String s = name1.getTextContent();
            System.out.println(s);
        }
    }

    // 查询person3.xml中第一个name的值
    public static void selectSin() throws Exception {
        // 查询所有name元素的值
        // 1.创建解析器工厂
        // 2.根据解析器工厂创建解析器
        // 3.解析xml返回document
        // 4.得到所有的name元素
        // 5.返回集合，遍历集合，得到第一个name元素
        // 创建解析器工厂
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // 创建解析器
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        // 解析xml返回Document
        Document document = builder.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml");
        // 得到所有的Name元素
        NodeList list = document.getElementsByTagName("name");
        // 得到第一个元素
        Node firstNode = list.item(0);
        String firstName = firstNode.getTextContent();
        System.out.println(firstName);
    }

    // 在第一个p1下面(末尾)添加<sex>nv</sex>
    public static void addSex() throws Exception {
        // 1.创建解析器工厂
        // 2.根据解析器工厂创建解析器
        // 3.解析xml返回document
        // 4.得到第一个p1
        // ----得到所有的p1，使用item方法下标得到
        // 5.创建sex标签，createElement()
        // 6.创建文本，createTextNode()
        // 7.把文本添加到sex下面，appendChild()
        // 8.把sex添加到第一个p1下面
        // dom操作是在内存中操作，最后需要回写到person3.xml中
        // 9.回写person3.xml

        // 创建解析器工厂
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // 创建解析器
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        // 得到Document
        Document document = builder.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml");
        // 得到所有的p1
        NodeList list = document.getElementsByTagName("p1");
        // 得到第一个p1
        Node p1 = list.item(0);
        // 创建标签（元素）
        Element sex1 = document.createElement("sex");
        // 创建文本
        Text text1 = document.createTextNode("nv");
        // 把文本添加到text1下面
        sex1.appendChild(text1);
        // 把sex1添加到p1下面
        p1.appendChild(sex1);
        // 回写到xml中
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(document),
                new StreamResult("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml"));
    }

    // 需求：修改第一个p1下面sex内容为nan
    public static void modifySex() throws Exception {
        // 1.创建解析器工厂
        // 2.根据解析器工厂创建解析器
        // 3.解析xml返回document
        // 4.得到sex item()
        // 5.修改sex里面的值 setTextContent()
        // 6.回写xml

        // 创建解析器工厂
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // 创建解析器
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        // 得到Document
        Document document = builder.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml");
        // 得到sex
        Node sex1 = document.getElementsByTagName("sex").item(0);
        // 修改sex的值
        sex1.setTextContent("nan");
        // 回写
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(document),
                new StreamResult("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml"));

    }

    // 删除<sex>nan<sex>节点
    public static void delSex() throws Exception {
        // 1.创建解析器工厂
        // 2.根据解析器工厂创建解析器
        // 3.解析xml返回document
        // 4.获取sex元素
        // 5.获取sex的父节点 getParentNode()
        // 6.删除使用父节点删除 removeChild方法
        // 7.回写xml操作

        // 创建解析器工厂
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // 创建解析器
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        // 得到Document
        Document document = builder.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml");
        // 得到sex元素
        Node sex2 = document.getElementsByTagName("sex").item(1);
        // 得到sex1的父节点
        Node p1 = sex2.getParentNode();
        // 删除操作
        p1.removeChild(sex2);
        // 回写xml
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(document),
                new StreamResult("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml"));
    }

    // 遍历节点，把所有元素的名称打印出来
    public static void listElement() throws Exception {
        // 1.创建解析器工厂
        // 2.根据解析器工厂创建解析器
        // 3.解析xml返回document
        // =============使用递归实现操作====================
        // 4.得到根节点
        // 5.得到根节点的子节点

        // 创建解析器工厂
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // 创建解析器
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        // 得到Document
        Document document = builder.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/person3.xml");

        // 编写一个方法实现遍历操作
        listRecursion(document);
    }

    // 递归遍历操作
    private static void listRecursion(Node node) {
        // 判断是元素类型时候才打印
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println(node.getNodeName());
        }
        // 得到一层子节点
        NodeList list = node.getChildNodes();
        // 遍历list
        for (int i = 0; i < list.getLength(); i++) {
            // 得到每一个节点
            Node nodeI = list.item(i);
            // 继续得到nodeI的子节点
            listRecursion(nodeI);
        }
    }

    public static void main(String[] args) throws Exception {
        // selectAll();
        // System.out.println("--------------------------------------");
        // selectSin();

        // addSex();
        // modifySex();
        // delSex();
        listElement();
    }

}