import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/*
    commons-dbutils 简化jdbc的代码

*/
class StuOperation {
    public void addStu(Stu stu) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "insert into t_stu values(?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, stu.getSid());
            pstmt.setString(2, stu.getSname());
            pstmt.setInt(3, stu.getAge();
            pstmt.setString(4, stu.getGender());

            pstmt.executeUpdate();
        } catch (Exception e) {
            //处理异常
        } finally {
            //各种关闭
        }
    }

    public void updateStu(Stu stu) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "update t_stu set sname = ?, age = ?, gender = ? WHERE sid = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, stu.getSname());
            pstmt.setInt(2, stu.getAge();
            pstmt.setString(3, stu.getGender());
            pstmt.setInt(4, stu.getSid());

            pstmt.executeUpdate();
        } catch (Exception e) {
            //处理异常
        } finally {
            //各种关闭
        }
    }

    public void deleteStu(int sid) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "delete from t_stu WHERE sid = ?";
            pstmt.setInt(1, sid);

            pstmt.executeUpdate();
        } catch (Exception e) {
            // 处理异常
        } finally {
            // 各种关闭
        }
    }

    public Stu load(int sid) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "select * from t_stu WHERE sid = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, sid);

            rs = pstmt.executeQuery();
            if (!rs.next())
                return null;

            // 需要把rs转换成Stu对象
            // rs ----> javaBean
            Stu stu = new Stu();
            stu.setSid(rs.getInt("sid"));
            stu.setSname(rs.getString("sname"));
            stu.setAge(rs.getInt("age"));
            stu.setGender(rs.getString("gender"));

            return stu;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 各种关闭
        }
    }
}

class QR<T> {
    private DataSource dataSource;

    // 做insert、update、delete
    public int update(String sql, Object... params) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dataSource.getConnection(); // 通过连接池得到连接对象

            pstmt = con.prepareStatement(sql); // 使用sql创建pstmt
            initParams(pstmt, params); // 设置参数

            return pstmt.executeUpdate(); // 执行
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null)
                try {
                    if (pstmt != null)
                        pstmt.close();
                    if (con != null)
                        con.close();
                } catch (SQLException ee) {
                    ee.printStackTrace();
                }
        }
    }

    // 给参数赋值
    private void initParams(PreparedStatement pstmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
    }

    public T query(String sql, RsHandler<T> rh, Object... params) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection(); // 通过连接池得到连接对象

            pstmt = con.prepareStatement(sql); // 使用sql创建pstmt
            initParams(pstmt, params); // 设置参数

            rs = pstmt.executeQuery();

            return rh.handle(rs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        }
    }

    public QR() {
    }

    public QR(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}

// 用来把结果集转换成需要的类型
interface RsHandler<T> {
    public T handle(ResultSet rs) throws SQLException;
}

public class CustomDBUtils {
    public static void main(String[] args) {
        Stu s = new Stu(1005, "wangWu", 29, "male");
        addStu(s);

        Stu slook = load(1005);
        System.out.println(slook);
    }

    public static void addStu(Stu stu) {
        QR qr = new QR(JdbcUtils.getDataSource()); // 创建对象时给出连接池
        String sql = "insert into t_stu values(?, ?, ?, ?)"; // 给出sql模板
        // 给出参数
        Object[] params = { stu.getSid(), stu.getSname(), stu.getAge(), stu.getGender() };
        // 调用update执行增、删、改操作
        qr.update(sql, params);
    }

    public static Stu load(int sid) {
        QR qr = new QR(JdbcUtils.getDataSource()); // 创建对象时给出连接池
        String sql = "SELECT * FROM t_stu WHERE sid=?"; // 给出sql模板
        // 给出参数
        Object[] params = { sid };

        RsHandler<Stu> rh = new RsHandler<Stu>() {
            public Stu handle(ResultSet rs) throws SQLException {
                if (!rs.next())
                    return null;
                Stu stu = new Stu();
                stu.setSid(rs.getInt("sid"));
                stu.setSname(rs.getString("sname"));
                stu.setAge(rs.getInt("age"));
                stu.setGender(rs.getString("gender"));
                return stu;
            }
        };
        return (Stu) qr.query(sql, rh, params);
    }
}