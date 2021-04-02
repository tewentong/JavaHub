/*
    DTD约束(Document type definition)
        dtd的快速入门：创建一个文件 后缀名.dtd
            步骤：
                (1)看xml中有多少个元素，有几个元素，在dtd文件中写几个<!ELEMENT>
                (2)判断元素是简单元素还是复杂元素
                    -简单元素：没有子元素
                        <!ELEMENT 元素名称 (#PCDATA)>
                    -复杂元素：有子元素的元素
                        <!ELEMENT 元素名称 (子元素)>
                (3)需要在xml文件中引入dtd文件
                    <!DOCTYPE 根元素名称 SYSTEM "dtd文件的路径">
                    可在浏览器键入如下路径浏览xml文件
                        file:///home/kwj-at-lzu/Java/WebFoundation/XML/person.xml
                    使用浏览器打开xml文件，浏览器只负责校验xml的语法，不负责校验约束
                    如果想要校验xml的约束，就需要使用工具
        dtd的三种引入方式：
            (1)引入外部的dtd文件
                <!DOCTYPE 根元素名称 SYSTEM "dtd路径">
            (2)使用内部的dtd文件
                <!DOCTYPE 根元素名称 [
                    <!ELEMENT 书架 (书)>
                    <!ELEMENT 书 (书名,作者,售价)>
                    <!ELEMENT 书名 (#PCDATA)>
                    <!ELEMENT 作者 (#PCDATA)>
                    <!ELEMENT 售价 (#PCDATA)>
                ]>
            (3)使用外部的dtd文件(使用网络的dtd文件)
                <!DOCTYPE 根元素 PUBLIC "DTD名称" "DTD文档的URL">
                后面会用到框架 struts2 使用配置文件 使用 外部的dtd文件
                
*/
public class DTDConstraints {
    public static void main(String[] args) throws Exception {

    }
}