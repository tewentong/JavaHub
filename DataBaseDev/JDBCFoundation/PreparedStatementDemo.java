import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
    PreparedStatement
        Statement接口的子接口
        优点：
            防SQL攻击
            提高代码的可读性、可维护性
            提高效率
        学习PreparedStatement的用法：
            如何得到PreparedStatement对象
                给出SQL模板
                调用Connection的PreparedStatement prepareStatement(String sql模板);
                调用pstmt的setXxx()系列方法为sql模板中的？赋值
                调用pstmt的executeUpdate()或executeQuery()方法，但两个方法都没有参数

        PreparedStatement预处理原理（预编译）
            服务器的工作：
                校验SQL语句的语法
                编译：最后把SQL语句变为一个与函数相似的东西
                执行：调用函数
            Preparedstatement:
                前提：连接的数据库必须支持预处理（几乎没有不支持的）
                每个pstmt都与一个sql模板绑定在一定，先把模板给数据库，
                    数据先进行校验，再进行编译
                    执行时只是把参数传递过去而已
                若二次执行时，就不用再次校验语法，也不用再次编译，可以直接执行
*/
class User032802 {
    public boolean login(String username, String password) throws Exception {
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mydb3";
        String mysqlUsername = "kwj-at-lzu";
        String mysqlPassword = "code";

        Class.forName(driverClassName);
        Connection con = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

        ///////////////////////////////////////////////////////////////////////////
        // 得到PreparedStatement
        // 1.给出SQL模板，所有的参数使用？来替代
        String sql = "select * from t_user WHERE username=? AND password=?";
        // 2.调用Connection方法，得到PreparedStatement对象
        PreparedStatement pstmt = con.prepareStatement(sql);

        // 为参数赋值
        pstmt.setString(1, username); // 为第一个？赋值，值为username
        pstmt.setString(2, password); // 为第二个？赋值，值为password

        // 进行查询
        ResultSet rs = pstmt.executeQuery(); // 调用查询方法，向数据库发送查询语句

        // 程序已经校验并编译过sql模板了
        // 现在要执行 用户liSi 相关的操作时，不用再校验语法，编译模板，可以直接执行下面两条命令
        // pstmt.setString(1, "liSi");
        // pstmt.setString(2, "123");
        return rs.next();
    }
}

public class PreparedStatementDemo {
    public static void main(String[] args) throws Exception {
        User032802 user = new User032802();
        String username = "a' or 'a'='a";
        String password = "a' or 'a'='a";
        boolean made = user.login(username, password);
        System.out.println(made); // false
    }
}