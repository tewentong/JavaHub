
/*  导jar包：驱动
    加载驱动类：Class.forName("类名");
    给出url、user、password，其中url背下来！
    使用DriverManager类来得到Connection对象！

    JDBC入门 
        1.什么是JDBC 
            JDBC(Java DatabaseConnectivity)就是Java数据库连接，说白了就是用Java语言来操作数据库。
            原来我们操作数据库是在控制台使用SQL语句来操作数据库，JDBC使用Java语言向数据库发送SQL语句 
        2.JDBC原理
            早期SUN公司的天才们想编写一套可以连接天下所有数据库的API，但是当他们刚刚开始时就发现这是不可完成的任务，
                因为各个厂商的数据库服务器差异太大了
            后来SUN公司开始与数据库厂商们讨论，最终得出的结论是：
                由SUN提供一套访问数据库的规范（就是一套接口），并提供连接数据库的协议标准
                然后各个数据库厂商会遵循SUN的规范提供一套访问自己公司的数据库服务器的API
            SUN提供的规范命名为JDBC，而各个厂商提供的，遵循了JDBC规范的，可以访问自己数据库的API被称之为驱动
    
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCIntro {
    /**
     * @throws ClassNotFoundException 没导驱动包 拼写错误
     * @throws SQLException           检查三个参数：url、username、password是否正确
     *                                检查是否开启了MySQL服务器
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // 加载驱动类
        String url = "jdbc:mysql://localhost:3306/mydb1"; // 连接服务器需要：1.IP地址 2.端口号
        String username = "kwj-at-lzu";
        String password = "code";

        try {
            // 使用url、username、password得到连接对象
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}