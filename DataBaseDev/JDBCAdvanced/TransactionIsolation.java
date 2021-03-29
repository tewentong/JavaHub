/*
    事务隔离级别
        1.事务的并发读问题
            脏读：读取到另一个事务未提交数据（不能允许出现的事情）
            不可重复读：两次读取不一致
            幻读（虚读）：读到另一事务已提交数据
        2.并发事务问题
            因为并发事务导致的问题大致有5类，其中两类是更新问题，三类是读问题
            脏读（dirty read）:读到另一个事务的未提交数据，即读取到了脏数据
            不可重复读(unrepeatable read):对同一记录的两次读取不一致，因为另一事务对该记录做了修改
            幻读（phantom read）:对同一张表的两次查询不一致，因为另一事务插入了一条记录

            脏读：
                事务1：张三给李四转账100元
                事务2：李四查看自己的账户
                t1:事务1——开始事务
                t2:事务1——张三给李四转账100元
                t3:事务2——开始事务
                t4:事务2——李四查看自己的账户，看到账户多出100元（脏读）
                t5:事务2——提交事务
                t6:事务1——回滚事务，回到转账之前的状态
            不可重复读：
                事务1：酒店查看两次1048号房间状态
                事务2：预定1048号房间
                t1:事务1——开始事务
                t2:事务1——查看1048号房间状态为空闲
                t3:事务2——开始事务
                t4:事务2——预定1048号房间
                t5:事务2——提交事务
                t6:事务1——再次查看1048号房间状态为使用
                t7:事务1——提交事务
                对同一记录的两次查询结果不一致
            幻读：
                事务1：对酒店房间预定记录两次统计
                事务2：添加一条预定房间记录
                t1:事务1——开始事务
                t2:事务1——统计预定记录100条
                t3:事务2——开始事务
                t4:事务2——添加一条预定房间记录
                t5:事务2——提交事务
                t6:事务1——再次统计预定记录为101记录
                t7:事务1——提交
                对同一表的两次查询不一致
            
            不可重复读和幻读的区别：
                不可重复读是读取到了另一事务的更新
                幻读是读取到了另一事务的插入（MySQL中无法测试到幻读）

        3.四大隔离级别
            4个等级的事务隔离级别，在相同数据环境下，使用相同的输入，执行相同的工作，根据不同的隔离级别，可以导致不同的结果
            不同事务隔离级别能够解决的数据并发问题的能力是不同的

            SERIALIZABLE（串行化）——三种读问题都能处理
                不会出现任何并发问题，因为它是对同一数据的访问是串行的，非并发访问的
                性能最差
            REPEATABLE READ(可重复读)（MySQL）——脏读、不可重复读，不能处理幻读
                防止脏读和不可重复读，无法处理幻读问题
                性能比SERIALIZABLE好
            READ COMMITTED（读已提交数据）（Oracle）——只能处理脏读
                防止脏读
                性能比REPEATABLE READ好
            READ UNCOMMITTED（读未提交数据）——啥都不处理
                可能出现任何事务并发问题
                性能最好
            
            MySQL默认隔离级别未REPEATABLE READ
        4.MySQL隔离级别
            MySQL的默认隔离级别未Repeatable read,可以通过下面的语句查看：
                select @@transaction_isolation;
                +-------------------------+
                | @@transaction_isolation |
                +-------------------------+
                | REPEATABLE-READ         |
                +-------------------------+

            也可以通过下面语句来设置当前连接的隔离级别：
                set transcation isolationlevel [4 选 1]
        5.JDBC设置隔离级别
            con.setTranscationIsolation(int level)
            参数可选值如下：
                Connection.TRANSAATION_READ_UNCOMMITTED;
                Connection.TRANSACTION_READ_COMMITTED;
                Connection.TRANSACTION_REPEATABLE_READ;
                Connection.TRANSACTION_SERIALIZABLE;

        事务总结：
            事务的特性：ACID
            事务开始边界与结束边界：
                开始边界：con.setAutoCommit(false);
                结束边界：con.commit();
            事务的隔离级别：
                READ_UNCOMMITTED、READ_COMMITTED、REPEATABLE_READ、SERIALIZABLE
                多个事务并发执行时才需要考虑并发事务
*/
public class TransactionIsolation {

}