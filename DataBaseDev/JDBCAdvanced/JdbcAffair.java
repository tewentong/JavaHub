import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
    JDBC事务
        在JDBC中处理事务，都是通过Connection完成的
        同一事务中所有的操作，都在使用Connection对象
        1.JDBC中的事务
            Connection的三个方法与事务相关：
                setAutoCommit(boolean):设置是否为自动提交事务，
                    如果true（默认值就是true）,表示自动提交，也就是每条执行的SQL语句都是一个单独的事务
                    如果false，那么就相当于开启了事务
                        con.setAutoCommit(false);表示开启事务
                commit():提交结束事务
                    con.commit();表示提交事务
                rollback():回滚结束事务
                    con.rollback();表示回滚事务
            jdbc处理事务的代码格式：
                try {
                    con.setAutoCommit(false);   //开启事务
                    ... 
                    ... 
                    con.commit();   //最后提交事务
                } catch () {
                    con.rollback(); //回滚事务
                }
*/
class AccountDao {
    public void updateBalance(Connection con, String name, double balance) {
        try {
            // 2.给出SQL模板，创建pstmt
            String sql = "update account set balance=balance+? WHERE name=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            // 3.对参数进行赋值
            pstmt.setDouble(1, balance);
            pstmt.setString(2, name);
            // 4.执行之
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class TransferAccount {
    // 转账方法
    // 所有对Connection的操作都在service层进行处理
    // 我们要解决这个问题，把所有对Connection的操作隐藏起来，这需要使用自定的小工具了
    public void transferAcc(String from, String to, double money) {
        // 对事务的操作必须使用相同的Connection对象
        Connection con = null; // 这里要保证使用相同的Connection对象
        try {
            con = JDBCUtils.getConnection();
            // 开启事务
            con.setAutoCommit(false);

            AccountDao dao = new AccountDao();
            dao.updateBalance(con, from, -money); // 给from减去相应金额
            if (true) {
                // throw new RuntimeException("不好意思，程序中断！");
            }
            dao.updateBalance(con, to, money); // 给to加上相应金额

            // 提交事务
            con.commit();
            con.close();
        } catch (Exception e) {
            // 回滚事务
            try {
                con.rollback();
                con.close();
            } catch (SQLException SQLe) {
                SQLe.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }
}

public class JdbcAffair {
    public static void main(String[] args) throws Exception {
        new TransferAccount().transferAcc("ls", "ww", 25000);
    }
}