
/*
    但需要注意的是，以上的做法是属于文件拷贝的最原始实现，而从JDK1.9开始InputStream和Reader类中都
    追加有数据转存的处理操作方法：
        InputStream:    public long transferTo(OutputStream out) throws IOException;
        Reader:         public long transferTo(Writer out) throws IOException;
    范例：使用转存的方式处理
        此时千万要注意程序的运行版本问题,JDK1.9未普及开，JDK1.10更是完全没有普及开
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
                InputStream input = null;
                OutputStream output = null;
                try {
                    input = new FileInputStream(this.srcFile);
                    output = new FileOutputStream(this.desFile);
                    input.transferTo(output);
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

        public class FileCopyDemo2 {
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
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(this.srcFile);
            output = new FileOutputStream(this.desFile);
            input.transferTo(output);
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

public class FileCopyDemo2 {
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