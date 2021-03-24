/*
    DDL
        1.数据库
            查看所有数据库：SHOW DATABASES
            切换（选择要操作的）数据库：USE 数据库名
            创建数据库：CREATE DATABASE [IF NOT EXIST] myda1 [CHARSET=utf8]
            删除数据库：DROP DATABASE [IF EXISTS] mydb1
            修改数据库编码：ALTER DATABASE mydb1 CHARACTER SET utf8
        2.数据类型（列类型）
            int:整型
            double：浮点型，例如double(5,2)表示最多5位，其中必须有2位小数，即最大值999.99
            decimal：浮点型，在表单钱方面使用该类型，因为不会出现精度缺失问题
            char：固定长度字符串类型   char(255)，数据的长度不足指定长度，补足到指定长度
            varchar：可变长度字符串类型    varchar(65535), zhangsan
            text(clob)：字符串类型
                很小
                小
                中
                大
            blob：字节类型
                很小
                小
                中
                大
            date：日期类型，格式为：yyyy-MM-dd
            time：时间类型，格式为：hh:mm:ss
            timestamp：时间戳类型
        3.表
            创建表：
                CREATE TABLE [IF NOT EXISTS] 表名(
                    列名 列类型，
                    列名 列类型，
                    ...
                    列名 列类型
                );
            查看当前数据库中所有表名称：SHOW TABLES;
            查看指定表的创建语句：SHOW CREATE TABLE 表名(了解);
            查看表结构：DESC 表名;
            删除表：DROP TABLE 表名
            修改表：
                前缀: ALTER TABLE 表名
                修改之添加列：
                    ALTER TABLE 表名 ADD(
                        列名 列类型,
                        列名 列类型,
                        ...
                    );
                修改之修改列类型（如果被修改的列已存在数据，那么新的类型可能会影响到已存在数据）:
                    ALTER TABLE 表名 MODIFY 列名 列类型;
                修改之修改列名：ALTER TABLE 表名 CHANGE 原列名 新列名 列类型;
                修改之删除列：ALTER TABLE 表名 DROP 列名;
                修改表名称：ALTER TABLE 原表名 RENAME TO 新表名;
*/
public class DDL {

}