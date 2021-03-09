
/*
    Java IO编程
    综合案例：文件拷贝
    在操作系统里面有一个copy命令，这个命令的主要功能是可以实现文件的拷贝处理，现在要求模拟这个命令
        通过初始化参事输入拷贝的源文件路径与拷贝的目标路径实现文件的拷贝处理
    需求分析：
        1.需要实现文件的拷贝操作，那么这种拷贝就有可能拷贝各种类型的文件，所以肯定使用字节流
        2.在进行拷贝的时候有可能需要考虑到大文件的拷贝问题
    实现方案：
        方案一：使用InputStream将全部要拷贝的内容直接读取到程序里面，而后一次性地输出到目标文件
            如果现在拷贝的文件很大，基本上程序就死了
        方案二：采用部分拷贝，读取一部分输出一部分数据
            如果要采取方案二，核心的操作方法：
                InputStream:pulic int read(byte[] b) throws IOException;
                OutputStream:public void write(byte[] b, int off, int len) throws IOException;
    
    范例：实现文件拷贝处理
        import java.io.File;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        class FileUtil {    //定义一个文件操作的工具类
            private File srcFile;   //源文件路径
            private File desFile;   //目标文件路径
            public FileUtil(String src, String des) {
                this(new File(src), new File(des));
            }
            public FileUtil(File srcFile, File desFile) {
                this.srcFile = srcFile;
                this.desFile = desFile;
            }
            public boolean copy() throws Exception { //文件的拷贝处理
                if (!this.srcFile.exists()) {    //源文件必须存在
                    System.out.println("要拷贝的源文件不存在！");
                    return false;   //拷贝失败
                }
                if (!this.desFile.getParentFile().exists()) {
                    this.desFile.getParentFile().mkdirs();  //创建父目录
                }
                byte data [] = new byte[1024];  //开辟一个拷贝的缓冲区
                InputStream input = null;
                OutputStream output = null;
                try {
                    input = new FileInputStream(this.srcFile);
                    output = new FileOutputStream(this.desFile);
                    int len = 0;
                    //1.读取数据到数组之中，随后返回读取的个数   len = input.read(data)
                    //2.判断个数是否是-1，如果不是则进行写入    (len = input.read(data)) != -1
                    while ((len = input.read(data)) != -1) {
                        output.write(data, 0, len);
                    }
                    //避免使用do while结构
                    *do {
                    *    len = input.read(data); //拷贝的内容都在data数组里
                    *    System.out.println(len);
                    *    if (len != -1) {
                    *        output.write(data, 0 ,len);
                    *   }
                    *} while (len != -1);
                    
                    return true;
                } catch (Exception e){
                    throw e;
                } finally {
                    if (input != null) {
                        input.close();
                    }
                    if (output != null) {
                        output.close();
                    }
                }
            }
        }
        public class FileCopyDemo {
            public static void main(String [] args) throws Exception {
                if (args.length != 2) {    //程序执行出错
                    System.out.println("命令执行错误，执行结构： java FileCopyDemo 拷贝源文件路径 拷贝目标文件路径");
                    System.exit(1);
                }   
                long start = System.currentTimeMillis();
                FileUtil fu = new FileUtil(args[0], args[1]);
                System.out.println(fu.copy() ? "文件拷贝成功!" : "文件拷贝失败!");
                long end = System.currentTimeMillis();
                System.out.println("拷贝完成的时间： " + (end - start));
            }
        }
*/
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

class FileUtil { // 定义一个文件操作的工具类
    private File srcFile; // 源文件路径
    private File desFile; // 目标文件路径

    public FileUtil(String src, String des) {
        this(new File(src), new File(des));
    }

    public FileUtil(File srcFile, File desFile) {
        this.srcFile = srcFile;
        this.desFile = desFile;
    }

    public boolean copy() throws Exception { // 文件的拷贝处理
        if (!this.srcFile.exists()) { // 源文件必须存在
            System.out.println("要拷贝的源文件不存在！");
            return false; // 拷贝失败
        }
        if (!this.desFile.getParentFile().exists()) {
            this.desFile.getParentFile().mkdirs(); // 创建父目录
        }
        byte data[] = new byte[1024]; // 开辟一个拷贝的缓冲区
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(this.srcFile);
            output = new FileOutputStream(this.desFile);
            int len = 0;
            // 1.读取数据到数组之中，随后返回读取的个数 len = input.read(data)
            // 2.判断个数是否是-1，如果不是则进行写入 (len = input.read(data)) != -1
            while ((len = input.read(data)) != -1) {
                output.write(data, 0, len);
            }
            /*
             * //避免使用do while结构 do { len = input.read(data); //拷贝的内容都在data数组里
             * System.out.println(len); if (len != -1) { output.write(data, 0 ,len); } }
             * while (len != -1);
             */
            return true;
        } catch (Exception e) {
            throw e;
        } finally {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        }
    }
}

public class FileCopyDemo {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) { // 程序执行出错
            System.out.println("命令执行错误，执行结构： java FileCopyDemo 拷贝源文件路径 拷贝目标文件路径");
            System.exit(1);
        }
        long start = System.currentTimeMillis();
        FileUtil fu = new FileUtil(args[0], args[1]);
        System.out.println(fu.copy() ? "文件拷贝成功!" : "文件拷贝失败!");
        long end = System.currentTimeMillis();
        System.out.println("拷贝完成的时间： " + (end - start));
    }
}