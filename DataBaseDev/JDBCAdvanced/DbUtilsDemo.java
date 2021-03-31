import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class DbUtilsDemo {
    public static void main(String[] args) throws Exception {
        myQuery();
    }

    public static void myUpdate() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "insert into t_stu value(?, ?, ?, ?)";
        Object[] params = { 1003, "liSi", 88, "female" };
        qr.update(sql, params);
    }

    public static void myQuery() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from t_stu WHERE sid=?";
        Object[] params = { 1001 };

        // 执行query()方法，需要给出结果集处理器，即ResultHandler的实现类
        // 我们给的是BeanHandler，它实现了ResultSetHandler
        // 它需要一个类型，然后它会把rs中的数据封装到指定类型的javabean对象中，然后返回javabean
        Stu stu = qr.query(sql, new BeanHandler<Stu>(Stu.class), params);
        System.out.println(stu);
    }
}