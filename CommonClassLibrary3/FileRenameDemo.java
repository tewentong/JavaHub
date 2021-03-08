/*
    File操作案例：批量修改文件名称
    编写程序，程序运行时输入目录名称，并把该目录下的所有文件名后缀修改为.txt
    对于这类的操作必须设置一些假设的约定，能够重命名的文件都是有后缀的
    如果没有后缀的路径，则为其追加路径
    如果有后缀的路径，则必须以最后一个 "."进行截取
*/
import java.io.File;
public class FileRenameDemo {
    public static void main(String [] args) {
        File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3" + File.separator + "test/");
        long start = System.currentTimeMillis();
        renameDir(file);
        long end = System.currentTimeMillis();
        System.out.println("本次操作所花费的时间： " + (end - start))
    }
    public static void renameDir(File file) {
        if (file.isDirectory()) {   //是一个目录
            File results [] = file.listFiles(); //列出子目录中的内容
            if (results != null) {
                for (int x = 0 ; x < results.length ; x ++) {
                    renameDir(results[x]);
                }
            }
        } else {
            if (file.isFile()) {    //如果是个文件则必须进行重命名
                String fileName = null;
                if (file.getName().contains(".")) {
                    fileName = file.getName().substring(0, file.getName().lastIndexOf(".")) + ".txt";
                } else {
                    fileName = file.getName() + ".txt";
                }
                File newFile = new File(file.getParentFile(),fileName);  //新的文件名称
                file.renameTo(newFile); //重命名
            }
        }
    }
}