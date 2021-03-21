/*
    Java数据库编程：
        JDBC简介：
            对于现代的开发几乎所有的项目都是围绕着数据库展开的，你很少会见到没有数据库而单独存在的项目
            所以任何一门编程语言如果要想发展，那么必须对数据库的开发有所支持
            同样，java从最初的时代开始就一直支持着数据库的开发标准——JDBC(Java Database Connectivity,JAVA数据库连接)
            JDBC本质上来讲并不属于一种技术，它属于一种服务
                而所有服务的特征：必须按照指定的套路来进行操作
            在Java里面专门为JDBC提供有一个模块(java.sql)，里面的一个核心开发包(java.sql)
                在JDBC里面核心的组成就是DriverManager类以及若干接口(Connection、Statement、PreparedStatement、ResultSet)
            对于JDBC的程序数据库访问也分为如下四种形式：
                1.JDBC-ODBC桥连接：利用微软的ODBC技术进行数据库的连接，而后再利用JDBC技术访问ODBC技术进行数据库的开发
                    处理流程：程序 - JDBC - ODBC - 数据库，操作性能很差，不会有人用的
                    这种技术为Java默认支持的技术，不需要做任何额外配置即可实现
                2.JDBC连接：直接利用JDBC进行数据库的连接处理
                    处理流程：程序 - JDBC - 数据库， 这种连接一般只连接本地数据库的服务器
                3.JDBC网络连接：通过特定的网络协议连接指定的数据库服务 *
                    处理流程：程序 - JDBC - 网络数据库（IP地址、端口）
                4.JDBC协议连接：自己通过编写指定的协议操作实现数据库的访问
*/
public class JDBCIntroDemo {
    public static void main(String[] args) {

    }
}