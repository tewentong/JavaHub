
/*
    现在对此程序要求进一步扩展，可以实现一个目录文件的拷贝
    一旦进行了文件目录的拷贝，还需要拷贝所有子目录中的文件

    本程序是IO操作的核心代码，本程序可以理解，整个的IO处理机制就非常容易理解了
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
        return this.copyFileImpl(this.srcFile, this.desFile);
    }

    private boolean copyFileImpl(File srcFile, File desFile) throws Exception {
        if (!desFile.getParentFile().exists()) {
            desFile.getParentFile().mkdirs(); // 创建父目录
        }
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(srcFile);
            output = new FileOutputStream(desFile);
            // 1.读取数据到数组之中，随后返回读取的个数 len = input.read(data)
            // 2.判断个数是否是-1，如果不是则进行写入 (len = input.read(data)) != -1
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

    public boolean copyDir() throws Exception {
        this.copyImpl(this.srcFile);
        return false;
    }

    private void copyImpl(File file) throws Exception { // 递归操作
        if (file.isDirectory()) { // 是目录
            File results[] = file.listFiles(); // 列出全部目录
            if (results != null) {
                for (int x = 0; x < results.length; x++) {
                    copyImpl(results[x]);
                }
            } else { // 是文件
                String newFilePath = file.getPath().replace(this.srcFile.getPath() + File.separator, "")
                File newFile = new File(this.desFile, newFilePath);
                this.copyFileImpl(file, newFile);
            }
        }
    }
}

public class FileCopyDemo3 {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) { // 程序执行出错
            System.out.println("命令执行错误，执行结构： java FileCopyDemo 拷贝源文件路径 拷贝目标文件路径");
            System.exit(1);
        }
        long start = System.currentTimeMillis();
        FileUtil fu = new FileUtil(args[0], args[1]);
        if (new File(args[0]).isFile()) { // 文件拷贝
            System.out.println(fu.copy() ? "文件拷贝成功!" : "文件拷贝失败!");
        } else {
            System.out.println(fu.copyDir() ? "文件拷贝成功!" : "文件拷贝失败!");
        }
        long end = System.currentTimeMillis();
        System.out.println("拷贝完成的时间： " + (end - start));
    }
}