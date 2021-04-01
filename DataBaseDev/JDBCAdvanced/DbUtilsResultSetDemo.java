import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/*
    common-dbutils.jar

    QueryRunner
        update方法:
            int update(String sql, Object[] params) ---> 可执行增、删、改语句
            int update(Connection con, String sql, Object... params) --> 需要调用者提供Connecion，这说明本方法不再管理Connection了
                支持事务——保证同一事务使用同一个连接    
        query方法：
            T query(String sql, ResultSetHandler rsh, Object... params) --> 可执行查询
                它会先得到ResultSet, 然后调用rsh的handle()把rs转换成需要的类型！
            T query(Connection con, String sql, ResultSetHandler rsh, Object... params);
                支持事务——保证同一事务使用同一个连接
        ResultSetHandler接口：
            BeanHandler(单行) --> 构造器需要一个Class类型的参数，用来把一行结果转换成指定类型的javaBean对象
            BeanListHandler(多行) --> 构造器需要一个Class类型的参数，用来把一行结果集转换成一个javabean，
                                                                那么多行结果集转换成List对象（一堆javabean）
            MapHandler(单行) --> 把一行结果集转换成Map对象
                一行记录：
                    sid  sname  age  gender
                    1001 zs     88   male
                一个Map:
                    {sid:1001, sname:zs, age:99, gender:male}
            MapListHandler(多行) --> 把一行记录转换成一个Map，多行就是多个Map，即List<Map>
            ScalarHandler(单行单列) --> 通常与select count(*) from t_stu语句结合使用
                结果集是单行单列的，它返回一个Object
*/
public class DbUtilsResultSetDemo {
    public static void main(String[] args) throws Exception {
        // multiLineQuery();
        // mapQuery();
        // multiLineMapQuery();
        countQuery();
    }

    // BeanListHandler的应用，它是多行结果集处理器
    // 每行对应一个Stu对象
    public static void multiLineQuery() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from t_stu";
        List<Stu> stuList = qr.query(sql, new BeanListHandler<Stu>(Stu.class));

        System.out.println(stuList);
    }

    // MapHandler的应用，它是单行处理器，把一行转换成一个Map对象
    public static void mapQuery() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from t_stu where sid = ?";
        Object[] params = { 1001 };
        Map map = qr.query(sql, new MapHandler(), params);

        System.out.println(map);
    }

    // MapListHandler，它是多行处理器，把每行都转换成一个Map，即List<Map>
    public static void multiLineMapQuery() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from t_stu";

        List<Map<String, Object>> stuMapList = qr.query(sql, new MapListHandler());
        System.out.println(stuMapList);
    }

    // ScalarHandler，它是单行单列时使用，最为合适！
    public static void countQuery() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select count(*) from t_stu";
        Object obj = qr.query(sql, new ScalarHandler());

        System.out.println(obj); // 6
        System.out.println(obj.getClass().getName()); // java.lang.Long
    }
}