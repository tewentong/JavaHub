import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

/*
    数据库连接池
        java当中主要利用Map实现池
        为了可重用（池内的对象创建和销毁都比较麻烦）
        池参数（池配置这些参数来调节池的性能）：管理连接的生命周期，创建连接以及销毁连接
            所有池参数都有默认值
            初始大小：10个
            最小空闲连接数：3个
            增量：一次创建的最小单位，5个
            最大空闲连接数：12个（销毁）
            最大连接数：20个
            最大等待时间：1000毫秒

        连接池也是使用四大连接参数来完成创建连接对象

        连接池必须实现: javax.sql.DataSource接口

        连接池返回的Connection对象，它的close()方法与众不同
            调用它的close()不是关闭，而是把连接归还给池

        通常一个项目创建一个连接池
        1.数据库连接池的概念
            任何实现了javax.sql中DataSource接口的类都是数据库连接池

            用池来管理Connection，这可以重复使用Connection。
            有了池，所以我们不用自己来创建Connection，而是通过池来获取Connection对象。
            当使用完Connection以后，调用Connection的close()方法也不会真的关闭Connection，
                而是把Connection归还给池，池就可以再利用这个Connection对象了
        
*/
//DBCP连接池
public class Dbcp {
    public void connectionPool() throws SQLException {
        // 1.创建连接池参数
        // 2.配置四大参数
        // 3.配置池参数
        // 4.得到连接对象
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mydb3");
        dataSource.setUsername("kwj-at-lzu");
        dataSource.setPassword("code");

        // 配置池参数
        dataSource.setMaxWaitMillis(1000);
        dataSource.setMaxTotal(20);
        dataSource.setMinIdle(3); // 设置空闲连接

        // 得到连接对象
        Connection con = dataSource.getConnection();
        System.out.println(con.getClass().getName());
        // org.apache.commons.dbcp2.PoolingDataSource$PoolGuardConnectionWrapper

        // 连接池内部使用四大参数创建了连接对象，即MySQL驱动提供的Connetion
        // 连接池使用MySQL的连接对象进行了装饰，只对close()方法进行了增强
        // 调用dbcp的方法都是在调用mysql的con，值欧close()才是dbcp自己的方法
        // 装饰之后的Connetion的close()方法，用来把当前连接归还给连接池
        con.close(); // 把连接归还给池
    }

    public static void main(String[] args) throws Exception {
        new Dbcp().connectionPool();
    }
}