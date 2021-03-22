
/*
    Java数据库编程
        连接Oracle数据库
            本次将基于Oracle数据库来实现JDBC的编程实现，那么在这样的情况下请一定要保证成功启动了
                Oracle数据库两个服务：数据监听服务(Listener)、实例服务(SID)
            由于本次将直接利用JDBC进行Oracle数据库的连接，所以必须保证你的系统之中已经配置了Oracle的驱动程序
            如果现在要连接Oracle数据库，则必须按照如下的步骤进行处理：
                1.通过反射机制加载数据库驱动程序：oracle.jdbc.driver.OracleDriver;
                2.数据库的连接需要有一个网络的连接地址，该地址结构如下：
                    地址结构： jdbc:oracle:thin:@主机名称：端口号:SID;
                    MLDN数据库： jdbc:oracle:thin:@localhost:1521:mldn
                3.数据库的用户名：scott;
                4.数据库的密码：tiger;
            对于数据库的连接提供有java.sql.DriverManager的程序类，利用此类中的方法可以获取一个Connection的接口对象
                获取Connection接口对象：
                    public static Connetion getConnection(String url,String user, String password)
                                            throws SQLException
        范例：实现具体的数据库连接操作


*/
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectOracleDemo {
    private static final String DATABASE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DATABASE_URL = "jdbc:oracle:thin:@localhost:1521:mldn";
    private static final String DATABASE_USER = "scott";
    private static final String DATABASE_PASSWORD = "tiger";

    public static void main(String[] args) throws Exception {
        Connection conn = null; // 每一个Connection接口对象描述的就是一个用户连接
        Class.forName(DATABASE_DRIVER); // 向容器之中加载数据库驱动程序
        conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        System.out.println(conn);
    }
}