import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

public class DbUtilsDemo {
    public static void main(String[] args) throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "insert into t_stu value(?, ?, ?, ?)";
        Object[] params = { 1002, "liSi", 88, "female" };
        qr.update(sql, params);
    }
}