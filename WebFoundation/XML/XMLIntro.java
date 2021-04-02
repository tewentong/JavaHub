/*
    什么是XML
        XML是指可扩展标记语言： eXtensible Markup Language
            是一种标记语言，被设计的宗旨是存储数据传输数据，而非显示数据
        XML标签没有被预定义，需要用户自定义标签，也可以写中文标签 <person></person>、<猫></猫>
    XML的应用
        不同的系统之间传输数据
            QQ之间数据的传输
        用来表示生活中有关系的数据
        经常用在配置文件中
            比如现在连接数据库，肯定要知道数据库的用户名和密码，数据名称
            如果要修改数据库的信息，不需要修改源代码，只需要修改配置文件就可以了
    XML的语法：
        XML的文档声明
            创建一个文件，后缀名是  .xml
            如果写xml，第一步必须要有一个文档声明（写了文档声明之后，表示写xml文件的内容）
                <?xml version="1.0" encoding="UTF-8"?>
                文档声明必须写在“第一行第一列”
            属性：
                -version: xml的版本 1.0(使用)
                -encoding: xml的编码 gbk utf-8 iso8859-1(不包含中文)
                -standalone: 是否需要依赖其他文件 yes/no
        定义元素（标签）
            
        定义属性
        注释
        特殊字符
        CDATA区
        PI指令
*/
public class XMLIntro {

}