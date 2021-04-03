import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import jdk.internal.cmm.SystemResourcePressureImpl;

/*
    XML的Schema约束
    XML Schema也是一种用于定义和描述XML文档结构与内容的模式语言，其出现是为了克服DTD的局限性
    XML Schema VS DTD:
        XML Schema符合XML语法结构
        DOM、SAX等XML API很容易解析出XML Schema文档中的内容
        XML Schema对名称空间支持得非常好（通过名称空间来区分多个Schema，名称空间类似于java包名）
        XML Schema比XML DTD支持更多的数据类型，并支持用户自定义新的数据类型
        XML Schema定义约束的能力非常强大，可以对XML实例文档作出细致的语义限制
        XML Schema不能像DTD一样定义实体，比DTD更复杂，但XML Schema现在已经是w3c组织的标准，它正逐步取代DTD
    Schema的快速入门
        应用schema约束开发xml过程
            W3C预先定义元素和属性 ---> Schema文档(模式文档、约束文档) ---> XML文档(实例文档)
            编写了一个XML Schema约束文档后，通常需要把这个文件中声明的元素绑定到一个URL地址上
                这个URL地址叫namespace名称空间，
                以后XML文件就可以通过这个URL(名称空间)引用绑定指定名称空间的元素
        创建一个Schema文件 后缀名是 .xsd
            根节点 <schema>
            <schema>元素可以包含一些属性，一个XML schema声明看起来经常以如下的形式出现
            <schema xmlns="http://www.w3.org/2001/XMLSchema"
            targetNamespace="http://www.itcast.cn/20151111"
            elementFormDefault="qualified">
            属性：
                xmlns="http://www.w3.org/2001/XMLSchema 表示当前xml文件是一个约束文件
                targetNamespace="http://www.itcast.cn/20151111" 使用schema约束文件，直接通过这个地址引入约束文件
                elementFormDefault="qualified"
        步骤：
            (1)看xml中有多少个元素
                <element>
            (2)看简单元素和复杂元素    
                如果是复杂元素：
                    <complexType>
                        <sequence>
                            子元素    
                        </sequence>
                    </complexType>
            (3)简单元素，写在复杂元素的<sequence>里面
                例子：
                    <element name="person">
                        <complexType>
                            <sequence>
                                <element name="name" type=":string"></element>
                                <element name="age" type=":int"></element>                
                            </sequence>
                        </complexType>
                    </element>
            (4)在被约束文件里面来引入约束文件
                <person xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns="http://www.itcast.cn/20151111"
                xsi:schemaLocation="http://www.itcast.cn/20151111 /home/kwj-at-lzu/Java/WebFoundation/XML/schemaDemo.xsd">

                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                    表示xml是一个被约束文件
                xmlns="http://www.itcast.cn/20151111"
                    是约束文档里面targetNamespace
                xsi:schemaLocation="http://www.itcast.cn/20151111 /home/kwj-at-lzu/Java/WebFoundation/XML/schemaDemo.xsd"
                    targetNamespace 空格 约束文档的地址路径
        XML Schema复杂元素指示器
            ALL：元素只能出现一次
            Choice：元素只能出现其中的一个
            Sequence：元素按照顺序出现
            maxOccurs="unbounded"表示出现次数没限制
                <element name="name" type="double" maxOccurs="unbounded"/>
            约束属性：
                写在复杂元素里面
                写在</complexType>之前
                <attribute name="p1" type="string" use="required"></attribute>
                    name：属性名称
                    type：属性类型 int string
                    use：属性是否必须出现，required表示属性必须要出现    
            <any></any>：表示任意元素
            复杂的schema约束：
                <company xmlns="http://www.example.org/company"
                xmlns:dept="http://www.example.org/department"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="http://www.example.org/company company.xsd http://www.example.org/department department.xsd">
                引入多个schema文件，可以给每个起一个别名

                <employee age="30">
                    <!--部门名称-->
                    <dept:name>100<dept:name>
                    <!--想要引入部门的约束文件里面的name, 使用部门的别名 dept:元素名称-->
                    <!--员工名称-->
                    <name>wangxiaoxiao<name>
                </employee>
    sax解析的原理
        解析XML有两种技术：DOM和SAX
            DOM解析：根据XML的层级结构在内存中分配一个树形结构
                把XML中的标签、属性、文本都封装成对象
            SAX解析：事件驱动，边读边解析
        在javax.xml.parsers包里面：
            public abstract class SAXParser entends Object
                实例可以通过SAXParserFactory.newSAXParser()来获得
                parse(File f, DefaultHandler dh);
                    两个参数：
                        第一个参数：XML路径
                        第二个参数：事件处理器
                DefaultHandler中会使用到的三个方法：
                    startElement(String uri, String localName, String qName, Attributes attributes); //解析到开始标签执行此方法 
                        qName返回标签名称
                    characters(char[] ch, int start, int length); //解析文本自动执行这个方法
                        string构造方法，返回文本内容
                    endElement(String uri, String localName, String qName); //解析到结束标签开始执行此方法
                        qName返回标签名称
            public abstract class SAXParserFactory
                实例通过 SAXParserFacotry.newInstance()方法得到   
    使用jaxp的sax方式解析XML
        sax方式不能实现增、删、改操作，只能做查询操作
        需求：打印出整个文档
            执行parse()，第一个参数为xml路径，第二参数是事件处理器
                创建一个类，重写时间处理器的类
                重写里面的三个方法
            class MyDefaultHandler extends DefaultHandler {
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    System.out.print("<" + qName + ">");
                }
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    System.out.print(new String(ch, start, length));
                }
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    System.out.print("</" + qName + ">");
                }
            }

            public class SchemaConstraint {
                public static void main(String[] args) throws Exception {
                    // 1.创建解析器工厂
                    // 2.获取解析器
                    // 3.执行parse()
                    // 4.自己创建一个类，继承DefaultHandler
                    // 5.重写类里面的三个方法

                    // 创建解析器工厂
                    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                    // 获取解析器
                    SAXParser SAXParser = saxParserFactory.newSAXParser();
                    // 执行parse()
                    SAXParser.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/p1.xml", new MyDefaultHandler());
                }
            }
            程序输出：
                <person>
                    <p1>
                        <name>zhangSan</name>
                        <age>20</age>
                    </p1>
                    <p1>
                        <name>liSi</name>
                        <age>30</age>
                    </p1>
                </person>
        需求：获取到所有name元素的值
            // 实现获取所有的name元素的值
            class NameDefaultHandler extends DefaultHandler {
                private boolean flag = false;
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    // 判断qName是否是name元素
                    if ("name".equals(qName)) {
                        flag = true;
                    }
                }
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    // 当flag的值是true的时候，表示解析到name元素
                    if (flag) {
                        System.out.println(new String(ch, start, length));
                    }
                }
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    // 把flag设置为false表示name元素结束
                    if ("name".equals(qName)) {
                        flag = false;
                    }
                }

            }

            public class SchemaConstraint {
                public static void main(String[] args) throws Exception {
                    // 1.创建解析器工厂
                    // 2.获取解析器
                    // 3.执行parse()
                    // 4.自己创建一个类，继承DefaultHandler
                    // 5.重写类里面的三个方法

                    // 创建解析器工厂
                    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                    // 获取解析器
                    SAXParser SAXParser = saxParserFactory.newSAXParser();
                    // 执行parse()
                    SAXParser.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/p1.xml", new NameDefaultHandler());
                }
            }
        需求：获取第一个name元素的值
            // 获取第一个name元素的值       
            // 获取第一个name元素的值
            class FirstNameDefaultHandler extends DefaultHandler {
                private boolean flag = false;
                private int index = 1;
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    // 判断qName是否是name元素
                    if ("name".equals(qName)) {
                        flag = true;
                    }
                }
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    // 当flag的值是true的时候，表示解析到name元素
                    // 索引为1时得到第一个name元素的值
                    if (flag && index == 1) {
                        System.out.println(new String(ch, start, length));
                    }
                }
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    // 把flag设置为false表示name元素结束
                    if ("name".equals(qName)) {
                        flag = false;
                        index++;
                    }
                }
            }
*/
class MyDefaultHandler extends DefaultHandler {
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.print("<" + qName + ">");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.print(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.print("</" + qName + ">");
    }

}

// 实现获取所有的name元素的值
class NameDefaultHandler extends DefaultHandler {
    private boolean flag = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 判断qName是否是name元素
        if ("name".equals(qName)) {
            flag = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // 当flag的值是true的时候，表示解析到name元素
        if (flag) {
            System.out.println(new String(ch, start, length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // 把flag设置为false表示name元素结束
        if ("name".equals(qName)) {
            flag = false;
        }
    }

}

// 获取第一个name元素的值
class FirstNameDefaultHandler extends DefaultHandler {
    private boolean flag = false;
    private int index = 1;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 判断qName是否是name元素
        if ("name".equals(qName)) {
            flag = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // 当flag的值是true的时候，表示解析到name元素
        // 索引为1时得到第一个name元素的值
        if (flag && index == 1) {
            System.out.println(new String(ch, start, length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // 把flag设置为false表示name元素结束
        if ("name".equals(qName)) {
            flag = false;
            index++;
        }
    }
}

public class SchemaConstraint {
    public static void main(String[] args) throws Exception {
        // 1.创建解析器工厂
        // 2.获取解析器
        // 3.执行parse()
        // 4.自己创建一个类，继承DefaultHandler
        // 5.重写类里面的三个方法

        // 创建解析器工厂
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        // 获取解析器
        SAXParser SAXParser = saxParserFactory.newSAXParser();
        // 执行parse()
        SAXParser.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/p1.xml", new MyDefaultHandler());
        System.out.println("-----------------------------------------------------------");
        SAXParser.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/p1.xml", new NameDefaultHandler());
        System.out.println("-----------------------------------------------------------");
        SAXParser.parse("/home/kwj-at-lzu/Java/WebFoundation/XML/p1.xml", new FirstNameDefaultHandler());
    }
}