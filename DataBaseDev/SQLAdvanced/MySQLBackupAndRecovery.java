/*
    备份与恢复
    1.数据库导出SQL脚本(备份数据库内容，并不是备份数据库)
        mysqldump -u用户名 -p密码 数据库名>生成的脚本文件路径
        例如：mysqldump -uroot -ppassword mydb1>/home/kwj-at-lzu/Java/DatabaseDev/SQLAdvanced/mydb1.sql
        注意：生成的脚本文件中不包含create database语句
    2.执行SQL脚本
        第一种方式
            mysql -u用户名 -p密码 数据库<脚本文件路径
            例如：
                先删除mydb1库，再重新创建mydb1库
                mysql -uroot -ppassword mydb1</home/kwj-at-lzu/Java/DatabaseDev/SQLAdvanced/mydb1.sql
            注意：不要打分号，不要登陆mysql，直接在命令行下运行
        第二种方式：
            登陆mysql
            source SQL脚本路径
            例如：
                先删除mydb1库，再重新创建mydb1库
                切换到mydb1库
                source /home/kwj-at-lzu/Java/DatabaseDev/SQLAdvanced/mydb1.sql

    数据库 --> sql：备份
    sql --> 数据库：恢复
*/
public class MySQLBackupAndRecovery {

}