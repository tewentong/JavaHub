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
*/
public class SchemaConstraint {

}