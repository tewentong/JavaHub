import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
    JDBC代码规范化
        所谓规范化代码就是无论是否出现异常，都要关闭ResultSet、Statement，以及Connection，
        如果你还记得IO流的规范化代码，那么下面的代码你就知道是什么意思了

    public void query() {
        //在try外给出引用的定义
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //try内为对象实例化
            con = getConnection();
            stmt = con.createStatement();
            String sql = "select * from user";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String username = rs.getString(1);
                String password = rs.getString(2);
                System.out.println(username + ", " + password);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                //在finally中进行关闭
                if(rs != null) rs.close();  //if判断增加健壮性
                if(stmt != null) stmt.close();
                if(con != null) con.close();
            } catch (SQLException e) {}
        }
    }
*/
public class JDBCCodeNormalization {
    public void queryFunc() throws Exception {
        Connection con = null; // 定义引用
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 得到连接
            String driverClassName = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/mydb1";
            String username = "kwj-at-lzu";
            String password = "code";

            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, username, password); // 实例化

            // 创建Statement
            stmt = con.createStatement();
            String querySql = "select * from EMP";
            rs = stmt.executeQuery(querySql);

            // 循环遍历rs，打印其中数据
            while (rs.next()) {
                // getString()和getObject()是通用的
                System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getDouble("SAL"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e); // 转换异常
        } finally {
            // 关闭
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (con != null)
                con.close();
        }
    }

    public static void main(String[] args) throws Exception {
        new JDBCCodeNormalization().queryFunc();
    }
}