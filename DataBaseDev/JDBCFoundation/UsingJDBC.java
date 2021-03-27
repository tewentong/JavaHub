import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsingJDBC {
    // 连接数据库，得到Connection就算成功
    // 对数据库做增、删、改
    public void getDriverConnection() throws ClassNotFoundException, SQLException {
        // 1.准备四大参数
        // 2.加载驱动类
        // 3.得到Connection
        // 准备四大参数
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        // jdbc协议的格式
        // jdbc:厂商的名字:子协议（由厂商自己决定）
        // 对MySQL而言，其子协议结构 //主机:端口号/数据库名称
        String url = "jdbc:mysql://localhost:3306/mydb3";
        String username = "kwj-at-lzu";
        String password = "code";

        // 加载驱动类
        Class.forName(driverClassName);

        // 使用DriverManager，以及剩下的3个参数，得到Connection
        Connection con = DriverManager.getConnection(url, username, password);

        // 对数据库做增、删、改
        // 1，通过Connection对象创建Statement
        // ->Statement语句的发送器，功能就是向数据库发送sql语句
        // 2.调用它的int executeUpdate(String sql)方法，它可以发送DML、DDL

        // 通过Connection得到Statement对象
        Statement stmt = con.createStatement();
        // 使用Statement发送sql语句
        // 增加记录
        // String sql = "INSERT INTO stu VALUES('itcast_0003', 'wangWu', 88, 'male')";

        // 修改记录
        // String sql = "UPDATE stu SET name='zhaoLiu', age=22, gender='female' WHERE
        // number='itcast_0003'";

        // 删除记录
        String sql = "DELETE FROM stu";
        int r = stmt.executeUpdate(sql);
        System.out.println(r);
    }

    // 执行查询
    public void executeQueryFunc() throws ClassNotFoundException, SQLException {
        // 一、得到Connection
        // 二、得到Statement，发送select语句
        // 三、对查询返回的“表格”进行解析

        // 一
        // 准备四大参数
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mydb1";
        String username = "kwj-at-lzu";
        String password = "code";

        // 加载驱动类
        Class.forName(driverClassName);

        // 通过剩下的三个参数调用DriverManager的getConnection()，得到连接
        Connection con = DriverManager.getConnection(url, username, password);

        // 二
        // 得到Statement，执行select语句
        // 得到Statement对象：Connection的createStatement()方法
        Statement stmt = con.createStatement();

        // 调用Statement的ResultSet rs = executeQuery(String querySql)
        String querySql = "SELECT * from EMP";
        ResultSet rs = stmt.executeQuery(querySql);
        System.out.println(rs); // 程序输出：com.mysql.cj.jdbc.result.ResultSetImpl@1c9b0314

        // 三、解析ResultSet
        // 1.把行光标移动到第一行，可以调用next()方法完成
        while (rs.next()) { // 把光标向下移动一行，并判断下一行是否存在
            int empno = rs.getInt(1); // 通过列编号来获取该列的值
            String ename = rs.getString("ENAME"); // 通过列名称来获取来获取该列的值
            Double sal = rs.getDouble("SAL");
            System.out.println("EMPNO: " + empno + ", ENAME: " + ename + ", SAL: " + sal);
        }

        // 四、关闭资源（先得到的对象后关闭）
        rs.close();
        stmt.close();
        con.close(); // 此对象必须要关闭，如果一直不关闭，多次执行以后会导致资源耗尽
    }

    public static void main(String[] args) throws Exception {
        new UsingJDBC().executeQueryFunc();
    }
}