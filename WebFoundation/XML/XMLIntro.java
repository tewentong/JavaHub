/*
    什么是XML？
        XML是指可扩展标记语言： eXtensible Markup Language
            是一种标记语言，被设计的宗旨是存储数据传输数据，而非显示数据
        XML标签没有被预定义，需要用户自定义标签，也可以写中文标签 <person></person>、<猫></猫>
    XML的应用：
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
            XML的中文乱码问题的解决——设置保存时候的编码和打开时候的编码一致
        定义元素（标签）
            标签的定义又开始必须要有结束： <person></person>
            标签没有内容，可以在标签内结束： <mytag/>
            标签可以嵌套，必须要合理嵌套：
                合理嵌套： <aa><bb></bb></aa>
            一个XML文档必须有且仅有一个根标签，其他标签都是这个根标签的子标签或孙标签
            对于XML标签中出现的所有空格和换行，XML解析程序都会当作标签内容进行处理，例如，下面两段内容的意义不同：
                <网址>www.itcast.cn</网址>

                <网址>
                    www.itcast.com
                </网址>
            一个XML元素可以包含字母、数字以及其他一些可见字符，但必须遵守下面的一些规范
                区分大小写，例如，<P>和<p>是两个不同的标记
                不能以数字或“_(下划线)”开头
                不能以xml(或XML、或Xml等)开头
                名称中间不能包含空格和冒号(:)，例如<a b> <a:b>
        定义属性
            XML是标记型文档，可以有属性
            <person id1="aaa" id2="bbb"></persin>
            属性定义的要求：
                一个标签上可以有多个属性： <person id1="aaa" id2="bbb"></person>
                属性名称不能相同： <person id1="aaa" id1="bbb"></person>，这个是不正确的，不能有两个id1
                属性名称和属性值之间使用=，属性值使用引号包起来（可以是单引号，也可以是双引号）
                属性的命名规范与元素的命名规范相同
        注释
            注释语法为： <!--这是注释-->
            注意：注释不能放到第一行，第一行第一列必须是文档声明
                 注释不能嵌套，例如：
                    <!--大段注释
                    ... 
                        <!--有一段注释-->
                    ...
                    -->
        特殊字符（转义字符）
            对于一些单个字符，若想显示其原始样式，也可以使用转移的形式予以处理
                特殊字符                    替代符号
                &                          &amp;
                <                          &lt;
                >                          &gt;
                "                          &qout;
                '                          &apos;
            例如：    
                <a>a<b</a>    ---->    <a>a&lt;b</a>
        CDATA区(Character Data的缩写)
            可以解决多个字符都需要转义的操作    if(a<b && b<c && d>f) {}
            把这些内容放到CDATA区里面，不需要转义
            写法：
                <![CDATA[ 内容 ]]>
                <![CDATA[ if(a<b && b<c && d>f) {} ]]>
            可以把CDATA区内容当作普通文本内容，而不用当成标签进行解析:
                <![CDATA[
                    <itcast>www.itcast.cn</itcast>
                ]]>
        PI指令(处理指令--Processing Instruction)
            可以在xml中设置样式
            写法：<?xml-stylesheet type="text/css" href="css的路径"?>
            注意：只能对英文标签名称起作用，对中文的标签名称不起作用
        XML语法规则总结：
            所有XML元素都必须关闭标签
            XML标签对大小写敏感
            XML必须正确地嵌套顺序
            XML文档必须有根元素（只有一个）
            XML的属性值须加引号
            特殊字符必须转义--CDATA
            XML中的空格、回车换行在解析时会被保留
        XML的约束：
            为什么需要约束：比如现在定义一个person.xml文件，只想要这个文件里面保存人的信息，比如name、age
                但是如果在xml文件中写了一个标签<猫>，发现可以正常显示，因为符合语法规范，但是这个猫肯定不是人的信息，
                xml的标签是自定义的，需要技术来规定xml中只能出现的元素，这个时候需要约束
            xml的约束的技术：
                DTD约束
                schema约束
*/
public class XMLIntro {

}