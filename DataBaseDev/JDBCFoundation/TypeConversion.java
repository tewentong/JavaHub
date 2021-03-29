/*
    时间类型
        数据库类型与java中类型的对应关系
            DATA -- java.sql.Date
            TIME -- java.sql.Time
            TIMESTAMP -- java.sql.Timestamp

            领域对象（domain）中的所有属性不能出现java.sql包下的东西，即不能使用java.sql.Date
            ResultSet#getDate()返回的是java.sql.Date()
            PreparedStatement#setDate(int, Date)，其中第二个参数也是java.sql.Date包下内容
    
        时间类型的转换
            java.util.Date -- java.sql.Date、Time、Timestamp
                把Util的Date转换成毫秒值
                使用毫秒值创建my.sql.Date、Time、Timestamp
            java.sql.Date、Time、Timestamp -- java.util.Date
                这一步不需要处理了，因为java.sql.Date是java.util.Date的子类
                java.util.Date date = new java.sql.Date();

*/
public class TypeConversion {

}