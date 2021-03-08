/*
    File操作案例：列出指定目录中的全部文件
    现在可以由开发者任意设置一个目录的路径，而后将这个目录中所有的文件的信息全部列出，包括子目录中的所有文件
    在这样的情况下，最好的做法就是利用递归完成
*/
import java.io.File;
public class ListFileDemo {
    public static void main(String [] args) throws Exception {
        File file = new File("/home/kwj-at-lzu/Java" + File.separator); //是一个目录
        listDir(file);
    }
    public static void listDir(File file) {
        if (file.isDirectory()) {   //是一个目录
            File results [] = file.listFiles();  //列出目录中的全部内容
            if (results != null) {
                for (int x = 0 ; x < results.length ; x ++) {
                    listDir(results[x]);
                }
            }
        }
        System.out.println(file);   //获得完整的路径
    }
}