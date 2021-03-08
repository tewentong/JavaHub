/*
    文件操作优化处理
    1.在实际的软件项目开发和运行的过程之中，往往都会在windows中进行项目的开发
      而在项目部署的时候基于Linux或Unix系统来进行项目的发布，以保证生产环节的安全性
      在不同的操作系统之中会有不同的路径分割符，windows分割符 "\" Linux分割符 "/"
      所以在最初进行开发的时候必须考虑不同系统环境下的分割符的问题
      所以为了解决此问题，File类提供有一个常量：public static final String separator;
      范例：正常的路径编写
        File file = new File(File.separator + "home" + File.separator + "kwj-at-lzu" + File.separator + "Java" + File.separator + "CommonClassLibrary3" + File.separator + "mldn.txt");
      但是随着系统的适应性的不断加强，对于当前的路径操作，也可以随意使用了
    2.在使用File类进行文件处理的时候需要注意的是：程序 -- JVM -- 操作系统函数 -- 文件处理
      在进行同一文件的反复删除或创建的时候有可能会出现有延迟的问题，所以这个时候最好的方案是别重名
    3.在进行文件创建的时候有一个重要的前提：文件的父路径必须首先存在
        如何获取父路径：public File getParentFile();
        创建目录：public boolean mkdirs();
        这种判断并且建立父目录的操作在很多的情况下可能只需要一次，但如果将这个判断一直都停留在代码里面，那么就会造成时间复杂度的提升
        如果这时候想要提升性能，请先保证目录已经创建
*/
import java.io.File;
public class FileDemo2 {
    public static void main(String [] args) throws Exception {
        ///home/kwj-at-lzu/Java/CommonClassLibrary3/mldn.txt
        File file = new File(File.separator + "home" + File.separator + "kwj-at-lzu" + File.separator + "Java" + File.separator + "CommonClassLibrary3" + File.separator + "mldn.txt");
        if (！file.getParentFile().exists()) {    //父路径不存在
            file.getParentFile().mkdirs();  //创建父路径（创建目录）
        }
        if (file.exists()) {
            file.delete();
        } else {    //文件不存在
            System.out.println(file.createNewFile());
        }
    } 
}