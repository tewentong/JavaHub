
/*
    Java IO编程
    RandomAccessFile    随机读取
        对于文件内容的处理操作主要是通过InputStream(Reader)、OutputStream(Writer)来实现，
        但是利用这些类实现的内容读取只能够将数据部分部分读取进来，如果说现在有这样一种要求：
            现在给了你一个非常庞大的文件，这个文件的大小有20G,
            如果此时按照传统的IO操作进行读取和分析，根本就不能完成
            在这种情况下，java.io包里面提供有一个RandomAccessFile类，这个类可以实现文件的跳跃式的读取
            可以只读取中间的部分内容（前提：需要有一个完善的保存形式），数据的保存的位数要都确定好
        RandomAccessFile类里面定义有如下的操作方法：
            构造方法：pulic RandomAccessFile(File file, String mode) throws FileNotFoundException;
                文件的处理模式：r、rw；
        
        范例：实现文件的保存
            import java.io.File;
            import java.io.RandomAccessFile;

            public class RandomAccessFileDemo {
                public static void main(String[] args) throws Exception {
                    File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt"); // 定义操作文件
                    RandomAccessFile raf = new RandomAccessFile(file, "rw"); // 读写模式
                    String names[] = new String[] { "zhangsan", "wangwu  ", "lisi    " };
                    int ages[] = new int[] { 30, 20, 16 };
                    for (int x = 0; x < names.length; x++) {
                        raf.write(names[x].getBytes()); // 写入字符串
                        raf.writeInt(ages[x]); // 写入数字
                    }
                    raf.close();
                }
            }

        RandomAccessFile最大的特点是在于数据的读取处理上，因为所有的数据是按照固定的长度进行的保存，
        所以读取的时候就可以进行跳字节读取：
            向下跳：public int skipBytes(int n) throws IOException;
            向回跳：public void seek(long pos) throws IOException;
        范例：读取数据
            import java.io.File;
            import java.io.RandomAccessFile;
            public class RandomAccessFileDemo {
                public static void main(String[] args) throws Exception {
                    File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt"); // 定义操作文件
                    RandomAccessFile raf = new RandomAccessFile(file, "rw"); // 读写模式
                    { // 读取lisi的数据，跳过24位
                        raf.skipBytes(24); // raf.seek(0); 读取张三的数据是回到头 raf.seek(0);
                        byte[] data = new byte[8];
                        int len = raf.read(data);
                        System.out.println("姓名： " + new String(data, 0, len).trim() + "、年龄： " + raf.readInt());
                    }
                    raf.close();
                }
            }

*/
import java.io.File;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
    public static void main(String[] args) throws Exception {
        File file = new File("/home/kwj-at-lzu/Java/CommonClassLibrary3/test" + File.separator + "mldn.txt"); // 定义操作文件
        RandomAccessFile raf = new RandomAccessFile(file, "rw"); // 读写模式
        { // 读取lisi的数据，跳过24位
            raf.skipBytes(24); // raf.seek(0); 读取张三的数据是回到头 raf.seek(0);
            byte[] data = new byte[8];
            int len = raf.read(data);
            System.out.println("姓名： " + new String(data, 0, len).trim() + "、年龄： " + raf.readInt());
        }
        raf.close();
    }
}