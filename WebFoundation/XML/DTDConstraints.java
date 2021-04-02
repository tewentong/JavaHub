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
        使用dtd来定义元素
            语法格式：<!ELEMENT 元素名 约束>
            简单元素：没有子元素的元素  <!ELEMENT name (#PCDATA)>
                    (#PCDATA)：约束name是字符串类型
                    EMPTY：元素为空（没有内容）
                    ANY：任意
            复杂元素：<!ELEMENT person (name,age,sex,school)>   子元素只能出现一次
                    <!ELEMENT 元素名称 (子元素)>
                    表示子元素出现的次数：
                        +   表示一次或多次
                        ?   表示零次或一次
                        *   表示零次、一次或多次
                    子元素之间使用逗号(,)隔开
                        表示元素出现的顺序
                    子元素之间使用竖线(|)隔开
                        表示元素只能出现其中任意一个
*/
public class DTDConstraints {
    public static void main(String[] args) throws Exception {

    }
}