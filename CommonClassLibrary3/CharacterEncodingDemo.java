/*
    java IO编程
    字符编码：在计算机的世界里只认0、1的比特数据，如果要想描述一些文字的编码，就要对这些二进制的数据进行一些组合
    在进行编码的时候如果要想正确显示出内容则一定需要有解码，所以编码和解码肯定要采用统一的标准，如果不统一的话就会出现乱码
    在实际的开发之中，常用的编码有如下几种：
        GBK\GB2312：国标编码，可以描述中文信息，其中GB2312只描述简体中文，而GBK包含简体中文与繁体中文
        ISO8859-1：国际通用编码，可以用其描述所有的字母信息，如果是象形文字则需要进行编码处理
        UNICODE编码：采用十六进制的方式存储，可以描述所有的文字信息
        UTF编码：象形文字部分使用十六进制编码，而普通的字母使用ISO8859-1的编码
            优势在于适合快速的传输，节约带宽，这个也就成为了开发之中首选的编码方式，主要使用 “UTF-8”编码
    如果要想知道当前系统中支持的编码规则，则可以采用如下代码列出全部的本机属性
    范例：列出本机属性
        System.getProperties().list(System.out);
        //【文件路径分割符】file.separator=/
        //【文件默认编码】file.encoding=UTF-8

    范例：默认方式写文件
        OutputStream output = new FileOutputStream(
                "/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt");
        output.write("我们都是专业的程序员".getBytes());
        output.close();
        //此时为默认的处理操作，不设置编码的时候就将采用默认的编码方式进行
    
    范例：强制性设置编码
        OutputStream output = new FileOutputStream(
                "/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt");
        output.write("我们都是专业的程序员".getBytes("UTF-8")); //UTF-8本来就是默认的方式，不会出现乱码
        output.close();

        OutputStream output = new FileOutputStream(
                "/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt");
        output.write("我们都是专业的程序员".getBytes("ISO8859-1")); //强制使用ISO8859-1编码方式，出现乱码
                //??????????
        output.close();

    项目中出现的乱码问题就是编码和解码的标准不统一，而最好的解决乱码的方式，所有的编码都使用UTF-8
*/

import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;

public class CharacterEncodingDemo {
    public static void main(String[] args) throws Exception {
        OutputStream output = new FileOutputStream(
                "/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt");
        output.write("我们都是专业的程序员".getBytes("ISO8859-1"));
        output.close();
    }
}