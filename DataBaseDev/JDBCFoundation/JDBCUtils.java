import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * V1.0
 */
public class JDBCUtils {
    private static Properties props = null;
    // 只在JDBCUtils类被加载时执行一次
    static {
        // 给props进行初始化，即加载dbconfig.properties文件到props对象中
        try {
            InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
            props = new Properties();
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(props.getProperty("driverClassName"));
        String driverClassName = props.getProperty("driverClassName");
        System.out.println(driverClassName);
        System.out.println("com.mysql.cj.jdbc.Driver");
        System.out.println(props.getProperty("url"));
        System.out.println(props.getProperty("password"));
        // 加载驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Class.forName();这里加载类的时候，只能使用直接填入字符串的方式，而不能加载配置文件
        // Class.forName(props.getProperty("driverClassName"));
    }

    public static Connection getConnection() throws SQLException {
        // 1.加载配置文件
        // 2.加载驱动类
        // 3.调用DriverManager.getConnection()

        // 得到Connection
        return DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"),
                props.getProperty("password"));
    }
}