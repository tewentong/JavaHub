/*
    UUID是一种生成无重复字符串的一种程序类，这种程序类的主要功能是根据时间戳实现一个自动的无重复的字符串定义
    一般在获取UUID的时候往往都是随机生成的一个内容，所以可以通过如下方式获取：
        获取UUID对象：public static UUID randomUUID();
        根据字符串获取UUID内容：public static UUID fromString(String name);
    在对一些文件进行自动命名处理的情况下，UUID类型非常好用
*/
import java.util.UUID;
public class UUIDDemo {
    public static void main(String[] args) {
        
        UUID uid = UUID.randomUUID();
        System.out.println(uid.toString());
        //System.out.println(UUID.randomUUID());
    }
}