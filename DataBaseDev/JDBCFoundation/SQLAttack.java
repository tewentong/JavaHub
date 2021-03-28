import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
    PreparedStatement
        Statement接口的子接口
        优点：
            防SQL攻击
            提高代码的可读性、可维护性
            提高效率
        
    1.什么是SQL攻击
        在需要用户输入的地方，用户输入的是SQL语句的片段，
        最终用户输入的SQL片段与我们DAO中写的SQL语句合成一个完成的SQL语句
        例如：用户在登陆时输入的用户名和密码都是SQL语句的片段
    2.演示SQL攻击
        首先我们需要创建一张用户表，用来存储用户的信息
            CREATE TABLE t_user(
                username VARCHAR(50),
                `password` VARCHAR(50)
            );

            INSERT INTO t_user VALUES('zhangSan', '123');
            INSERT INTO t_user VALUES('liSi', '123');
            INSERT INTO t_user VALUES('wangWu', '123');
        现在用户表中记录就是：
            +----------+----------+
            | username | password |
            +----------+----------+
            | zhangSan | 123      |
            | liSi     | 123      |
            | wangWu   | 123      |
            +----------+----------+

        当我们利用下面的SQL语句进行SQL攻击以后，我们会在控制台看到如下输出：
            select * from t_user WHERE username = 'a' or 'a'='a' AND password = 'a' or 'a'='a';
            +----------+----------+
            | username | password |
            +----------+----------+
            | zhangSan | 123      |
            | liSi     | 123      |
            | wangWu   | 123      |
            +----------+----------+
            3 rows in set (0.00 sec)

*/

class User0328 {
    /**
     * 登陆 使用username和password去查询数据 若查出结果集，说明正确！返回true 若查不出结果，说明用户名或密码错误，返回false
     * 
     * @param username
     * @param password
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean login(String username, String password) throws ClassNotFoundException, SQLException {
        // 一、得到Connection
        // 二、得到Statement
        // 三、得到ResultSet
        // 四、rs.next()返回的是什么，我们就返回什么

        // 准备四大参数
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mydb3";
        String mysqlUsername = "kwj-at-lzu";
        String mysqlPassword = "code";
        // 加载驱动类
        Class.forName(driverClassName);
        Connection con = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

        // 得到Statement
        Statement stmt = con.createStatement();

        // 给出sql语句，执行stmt的executeQuery()，得到ResultSet
        String sql = "select * from t_user WHERE username = '" + username + "' AND password = '" + password + "'";
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);

        return rs.next();
    }
}

// PreparedStatement防SQL攻击
public class SQLAttack {
    public static void main(String[] args) throws Exception {
        User0328 user = new User0328();

        // SQL攻击
        String username = "a' or 'a'='a";
        String password = "a' or 'a'='a";
        // 我们获得SQL语句
        // select * from t_user WHERE username = 'a' or 'a'='a' AND password = 'a' or
        // 'a'='a'
        // 执行上述的SQL语句以后，我们将会得到全部的数据库记录
        // 我们利用SQL语句的片段骗过了SQL语句的逻辑判断
        boolean made = user.login(username, password);
        System.out.println(made);
    }
}