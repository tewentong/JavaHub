import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/*
    编写一个小工具类，封装方法
*/
public class Dom4jUtils {
    // xml文件路径
    public static final String PATH = "/home/kwj-at-lzu/Java/WebFoundation/XML/p2.xml";

    // 返回document
    public static Document getDocument(String path) {
        try {
            // 创建解析器
            SAXReader saxReader = new SAXReader();
            // 得到document
            Document document = saxReader.read(path);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 封装回写xml方法
    public static void xmlWriters(String path, Document document) {
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path), format);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}