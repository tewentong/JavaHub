import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
    C3P0
        C3P0简介：C3P0也是开源免费的连接池
        C3P0的使用：
            C3P0中池类是：CombpPooledDataSource
        C3P0也可以指定配置文件，且配置文件可以是properties，也可以是xml的
            但是C3P0的配置文件名必须为c309-config.xml，且必须放在类路径下
            
*/

public class C3p0 {
    public void c3p0Connection() throws Exception {
        // 创建连接池对象
        ComboPooledDataSource ds = new ComboPooledDataSource();

        // 对池进行四大连接参数的配置
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/mydb3");
        ds.setDriverClass("com.mysql.cj.jdbc.Driver");
        ds.setUser("kwj-at-lzu");
        ds.setPassword("code");

        // 池配置
        ds.setAcquireIncrement(5);
        ds.setInitialPoolSize(20);
        ds.setMinPoolSize(2);
        ds.setMaxPoolSize(50);

        Connection con = ds.getConnection();
        System.out.println(con);
        // com.mchange.v2.c3p0.impl.NewProxyConnection@78b1cc93 [wrapping:
        // com.mysql.cj.jdbc.ConnectionImpl@6646153]
        con.close();
    }

    public static void main(String[] args) throws Exception {
        new C3p0().c3p0Connection();
    }
}