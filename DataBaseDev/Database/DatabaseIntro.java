/*
    关系结构数据库（关系型）：使用二维表格来存储数据
    数据库——面向关系
    常见数据库：
        Oracle（神谕）:甲骨文
        DB2：IBM
        SQL Server：微软
        MySQL:甲骨文
    关系型数据库管理系统(RDBMS-Relational database management system),即“数据库服务器”   
        当我们安装了数据库服务后，就可以在数据库服务器中创建数据库，每个数据库还可以包含多张表
        数据库表就是一个多行多列的表格，在创建表时，需要指定表的列数，以及列名称、列类型等信息。
            而不用指定表格的行数，行数是没有上限的
            向表格中添加数据是以行为单位的
    应用程序与数据库：
        应用程序使用数据库完成对数据的存储

    理解数据库：
        RDBMS = 管理员(manager) + 仓库(database)
        database = N个table
        table:
            表结构：定义表的列名和列类型
            表记录：一行一行的记录

    服务器操作：
        启动服务：
            sudo service mysql start
        停止服务：
            sudo service mysql stop
        重启服务：
            sudo service mysql restart

    客户端操作：
        登陆服务器：
            sudo mysql -uroot -ppassword -hlocalhost
            -u 后面跟随用户名
            -p 后面跟随密码
            -h 后面跟随IP
        退出服务器：
            exit或者quit
*/
public class DatabaseIntro {

}