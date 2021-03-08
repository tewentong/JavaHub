/*
    获取文件信息
    除了可以进行文件的操作之外也可以通过File类来获取一些文件本身提供的信息
    可以获取如下内容：
        文件是否可读：public boolean canRead();
        文件是否可写：public boolean canWrite();
        获取文件长度：public long length(); //该方法返回long数据类型、字节长度
        最后一次修改日期时间：public long lastModified();
        判断是否是目录：public boolean isDirectory();
        判断是否是文件：public boolean ifFile();
            import java.io.File;
            import java.lang.Math;
            import java.text.SimpleDateFormat;
            class MathUtil0308 {
                private MathUtil0308() {}
                    public static double round(double num, int scale) {
                        return Math.round(Math.pow(10, scale) * num) / Math.pow(10, scale); //保留scale位小数
                }
            }
            public class GetFileInformationDemo {
                public static void main(String [] args) {
                    File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/FileDemo.java");
                    System.out.println("文件是否可读： " + file.canRead());
                    System.out.println("文件是否可写： " + file.canWrite());
                    System.out.println("文件大小： " + MathUtil.round(file.length()/(double)1024/1024, 2));
                    System.out.println("最后的修改时间： " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
                    System.out.println("是目录吗？" + file.isDirectory());
                    System.out.println("是文件吗？" + file.isFile());
                }
            }
            既然可以判断给定的路径是文件还是目录，那么就可以进一步的判断，
            如果发现是目录，则应该列出目录中的全部内容
        列出目录内容：pulic File[] listFiles();
            import java.io.File;
            public class GetFileInformationDemo {
                public static void main(String [] args) {
                    File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3" + File.separator);
                    if (file.isDirectory()) {   //当前是一个目录
                        File result [] = file.listFiles();  //列出目录中的全部内容
                        for (int x = 0 ; x < result.length ; x ++) {
                            System.out.println(result[x]);
                        }
                    }   
                }
            }
*/
import java.io.File;
public class GetFileInformationDemo {
    public static void main(String [] args) {
        File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3" + File.separator);
        if (file.isDirectory()) {   //当前是一个目录
            File result [] = file.listFiles();  //列出目录中的全部内容
            for (int x = 0 ; x < result.length ; x ++) {
                System.out.println(result[x]);
            }
        }   
    }
}