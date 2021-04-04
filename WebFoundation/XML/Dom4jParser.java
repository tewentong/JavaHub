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
    
*/
public class Dom4jParser {

}