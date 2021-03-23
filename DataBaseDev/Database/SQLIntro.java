/*
    SQL：
        什么是SQL：结构化查询语言(Structured Query Language)
        SQL作用：客户端使用SQL来操作服务器
            启动mysql，连接服务器后，就可以使用sql来操作服务器
            将来会使用Java程序连接服务器，然后使用sql来操作服务器
        SQL标准(例如SQL99，即1999年指定的标准)：
            由国际标准化组织（ISO）指定，对DBMS的统一操作方式（例如相同的语句可以操作mysql,Oracle等）
        SQL方言：
            某种DBMS不只会支持SQL标准，而且还会有一些自己的语法，称之为方言
    SQL语法：
        1.SQL语句可以在单行或多行书写，以分号结尾
        2.可使用空格和缩进来增强语句的可读性
        3.MySQL不区分大小写，建议使用大写
    SQL语句分类：
        1.DDL(Data Definition Language)：数据定义语言，用来定义数据库对象：库、表、列
            创建、删除、修改：库、表结构
        2.DML(Data Manipulation Language)：数据操作语言，用来定义数据库记录（数据）
            增、删、改：表记录
        3.DCL(Data Control Language)：数据控制语言，用来定义访问权限和安全级别
        4.DQL(Data Query Language)：数据查询语言，用来查询记录（数据）

        DDL：数据库或表的结构操作
        DML：对表的记录进行更新（增、删、改）
        DQL：对表的记录的查询
        DCL：对用户的创建及授权
*/
public class SQLIntro {

}