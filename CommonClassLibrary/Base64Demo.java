/*
    base64加密工具
    正常来讲加密基本上永远都要伴随着解密，所谓的加密或者解密往往都需要有一些所谓的规则
    在JDK1.8开始提供有一组新的加密处理操作类，Base64处理，在这个类里面有两个内部类：
        Base64.Encoder:进行加密处理；
            加密处理：public byte[] encode(byte[] src);
        Base64.Decoder:进行解密处理；
            解密处理：public byte[] decode(String[] src);
    范例：实现加密与解密操作
        import java.util.Base64;
        public class Base64Demo {
            public static void main(String[] args) throws Exception {
                String msg = "www.mldn.cn"; //要发送的信息内容
                String encMsg = new String(Base64.getEncoder().encode(msg.getBytes())); //数据加密
                System.out.println(encMsg);
                String decMsg = new String(Base64.getDecoder().decode(encMsg.getBytes()));
                System.out.println(decMsg);
            }
        }
    
    虽然Base64可以实现加密和解密的处理，但是由于是一个公版的算法，所以如果直接对数据进行加密往往并不安全，那么
    最好的做法是使用盐值操作:
        import java.util.Base64;
        public class Base64Demo {
            public static void main(String[] args) throws Exception {
                String salt = "mldnJava";
                String msg = "www.mldn.cn" + "{" + salt + "}"; //要发送的信息内容
                String encMsg = new String(Base64.getEncoder().encode(msg.getBytes())); //数据加密
                System.out.println(encMsg);
                String decMsg = new String(Base64.getDecoder().decode(encMsg.getBytes()));
                System.out.println(decMsg);
            }
        }

    即便现在有盐值，发现加密的效果也不好，最好的做法是多次加密
    范例：复杂加密操作
        import java.util.Base64;
        class StringUtil {
            private static final String SALT = "mldnJava";  //公共的盐值
            private static final int REPEAT = 5;    //加密次数
        
            * 加密处理
            * @param str 要加密的字符串，需要与盐值整合
            * @return 加密后的数据
            *
            public static String encode(String str) {   //加密处理
                String temp = str + "{" + SALT + "}";   //盐值对外不公布
                byte data [] = temp.getBytes(); //将字符串变为字节数组
                for (int x = 0; x < REPEAT; x ++) {
                    data = Base64.getEncoder().encode(data);    //重复加密
                }
                return new String(data);
            }
            *
            * @param str 要解密的内容
            * @return 解密后的原始数据
            *
            public static String decode(String str) {
                byte data [] = str.getBytes();
                for (int x = 0; x < REPEAT; x ++) {
                    data = Base64.getDecoder().decode(data);
                }
                return new String(data).replaceAll("\\{\\w+\\}", "");
            }
        }
        public class Base64Demo {
            public static void main(String[] args) throws Exception {
                String str = StringUtil.encode("www.mldn.cn");
                System.out.println(str);
                System.out.println(StringUtil.decode(str));
            }
        }
    
    最好的做法是使用2-3种加密程序，同时再找到一些完全不可解密的加密算法
*/

import java.util.Base64;

class StringUtil {
    private static final String SALT = "mldnJava"; // 公共的盐值
    private static final int REPEAT = 5; // 加密次数

    /**
     * 加密处理
     * 
     * @param str 要加密的字符串，需要与盐值整合
     * @return 加密后的数据
     */
    public static String encode(String str) { // 加密处理
        String temp = str + "{" + SALT + "}"; // 盐值对外不公布
        byte data[] = temp.getBytes(); // 将字符串变为字节数组
        for (int x = 0; x < REPEAT; x++) {
            data = Base64.getEncoder().encode(data); // 重复加密
        }
        return new String(data);
    }

    /**
     * @param str 要解密的内容
     * @return 解密后的原始数据
     */
    public static String decode(String str) {
        byte data[] = str.getBytes();
        for (int x = 0; x < REPEAT; x++) {
            data = Base64.getDecoder().decode(data);
        }
        return new String(data).replaceAll("\\{\\w+\\}", "");
    }
}

public class Base64Demo {
    public static void main(String[] args) throws Exception {
        String str = StringUtil.encode("www.mldn.cn");
        System.out.println(str);
        System.out.println(StringUtil.decode(str));
    }
}