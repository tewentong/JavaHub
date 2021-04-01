import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.apache.commons.dbutils.QueryRunner;

/*
    Service事务
        在Service中使用ThreadLocal来完成事务，为将来学习Spring事务打基础
        1.DAO中的事务（在DAO中处理事物非常简单）
            在dao中处理事务，但dao中不应该存在业务，而只是对数据库的基本访问
            public void xxx() {
                Connection con = null;
                try {
                    con = JdbcUtils.getConnection();
                    con.setAutoCommitted(false);
                    QueryRunner qr = new QueryRunner();
                    String sql = "...";
                    Object [] params = ...;
                    qr.update(con, sql, params);

                    sql = ...;
                    Object [] params = ...;
                    qr.update(con, sql, params);
                    con.commit();
                } catch (Exception e) {
                    try {
                        if (con != null) {con.rooback();}
                    } catch (Exception ee){}
                } finally {
                    try {
                        con.close();
                    } catch (Exception e) {}
                }
            }
        2.Service才是处理事务的地方
            我们要清楚一件事情，DAO中不是处理事务的地方，
                因为DAO中的每个方法都是对数据库的一次操作，而Service中的方法才是对应一个业务逻辑。
                也就是说，我们需要在Service中的一方法中调用DAO的多个方法，而这些方法应该在一个事务中。
            怎么才能让DAO的多个方法使用相同的Connetion呢？
                方法不能自己来获得Connection，而是由外界传递进去。
                public void daoMethod1(Connection con, ...) {                
                }
                public void daoMethod2(Connection con, ...) {
                }
            在Service中调用DAO的多个方法时，传递相同的Connetion就可以了
                public class XXXService{
                    private XXXDao dao = new XXXDao();
                    public void serviceMethod() {
                        //Service中不应该出现Connection，Connection只应该在DAO中出现
                        Connection con = null;
                        try {
                            con = jdbcUtils.getConnection();
                            con.setAutoCommitted(false);
                            dao.daoMethod1(con, ...);
                            dao.daoMethod2(con, ...);
                            con.commit();
                        } catch (Exception e) {
                            try {
                                con.rollback();
                            } catch (Exception e) {}
                        } finally {
                            try {
                                con.close();
                            } catch (Exception e) {}
                        }
                    }
                }
        3.修改JdbcUtils 
            我们把对事务的开启和关闭放到JdbcUtils中，
                在Service中调用JdbcUtils的方法来完成事务的处理，
                但在Sercice中就不会再出现Connetion这一“禁忌”了
            DAO中的方法不用再让Service来传递Connection了，
                DAO会主动从JdbcUtils中获取Connetion对象，
                这样，jdbcUtils成为了DAO和Service的中介
            我们在jdbcUtils中添加beginTranscation()和rollbackTranscation(),
                以及commitTranscation()方法
                这样在Service中的代码如下：
                    我们希望可以这样来处理事务，但是jdbcUtils还没有写好
                    public class XXXSerive {
                        private XXXDao dao = new XXXDao();
                        public void serviceMethod() {
                            try {
                                jdbcUtils.beginTransaction();   //为con赋值，它不再为null
                                dao.daoMethod1(...);    //内部会调用JdbcUils.getConnection()
                                //如果con不为null，那么返回的是con
                                //多次调用daoMethodX()，返回的是同一个con
                                dao.daoMethod2(...);
                                jdbcUtils.commitTransaction();  //调用con.commit();
                            } catch (Exception e) {
                                jdbcUtils.rollbackTransaction();//调用con.rollback();
                            }
                        }
                    }
        
*/
class JdbcUtils {
    // 配置文件的默认配置，要求你必须给出c3p0-config.xml
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    // 它是事务专用连接
    private static Connection con = null;

    // 使用连接池返回一个连接对象
    public static Connection getConnection() throws SQLException {
        // 当con不等于null，说明已经调用过beginTransaction(),表示开启了事务
        if (con != null)
            return con;
        return dataSource.getConnection();
    }

    // 返回连接池对象
    public static DataSource getDataSource() {
        return dataSource;
    }

    // 开启事务
    // 1.获取一个Connetion，设置它的setAutoCommit(false)
    // 2.还要保证dao中使用的连接是我们刚刚创建的
    // --------------------------------------------------------------------
    // 1.创建一个Connetion，设置为手动提交
    // 2.把这个Connetion给Dao用
    // 3.还要让commitTransaction或rollbackTransaction可以获取到
    public static void beginTransaction() throws SQLException {
        if (con != null)
            throw new SQLException("已经开启了事务，就不要重复开启了！");
        // 1.给con赋值
        // 2.给con设置为手动提交
        con = getConnection(); // 给con赋值，表示事务已经开启了
        con.setAutoCommit(false);
    }

    // 提交事务
    // 1.获取beginTransaction提供的Connection，然后调用commit()方法
    public static void commitTransaction() throws SQLException {
        if (con == null)
            throw new SQLException("还没有开启事务，不能提交！");
        // 1.直接使用con.commit();
        con.commit();
        con.close();
        // 把con设置为null，表示事务已经结束了
        // 下次再调用getConnection()，返回的就不是这个con了
        con = null;
    }

    // 回滚
    // 1.获取beginTransaction提供的Connection，然后调用rollback()方法
    public static void rollbackTransaction() throws SQLException {
        if (con == null)
            throw new SQLException("还没有开启事务，不能回滚！");
        // 1.直接调用con.rollback();
        con.rollback();
        con.close();
        // 把con设置为null，表示事务已经结束了
        // 下次再调用getConnection()，返回的就不是这个con了
        con = null;
    }

    // 释放连接
    public static void releaseConnection(Connection connection) throws SQLException {
        // 判断它是不是事务专用
        // 如果是，就不关闭
        // 如果不是事务专用，就要关闭

        // 如果con==null，说明现在没有事务，那么connection一定不是事务专用的
        if (con == null)
            connection.close();
        // 如果con!=null，说明有事务，那么需要判断参数连接是否与con相等
        // 若不等，说明参数连接不是事务专用连接
        if (con != connection)
            connection.close();

        // 如果con!=null，是哦名有事务
        // 而且con==connection，说明这是事务专用连接
        // 所以不关闭此连接
    }
}

class AccountDao0330 {
    public static void update(String name, double money) throws SQLException {
        QueryRunner qr = new TxQueryRunner();
        String sql = "update account set balance = balance + ? WHERE name = ?";
        Object[] params = { money, name };

        // 我们需要自己来提供连接，保证多次调用使用的是同一个连接
        qr.update(sql, params);
    }
}

public class JdbcUtilsDemo {
    private AccountDao0330 dao = new AccountDao0330();

    public void serviceMethod() throws Exception {
        try {
            JdbcUtils.beginTransaction();
            dao.update("zs", -20000);
            dao.update("ls", 20000);
            JdbcUtils.commitTransaction();
        } catch (Exception e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (Exception ee) {
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new JdbcUtilsDemo().serviceMethod();
    }
}