/*
    事务(转账)
        为了方便演示事务，我们需要创建一个account表
            CREATE TABLE account (
                id INT PRIMARY KEY AUTO_INCREMENT,
                NAME VARCHAR(50),
                balance NUMERIC(10.2)
            );

            INSERT INTO account(NAME, balance) VALUES('zs', 100000);
            INSERT INTO account(NAME, balance) VALUES('ls', 100000);
            INSERT INTO account(NAME, balance) VALUES('ww', 100000);

            SELECT * FROM account;
        1.什么是事务？
            银行转账，张三转10000块到李四的账户，其实需要两条SQL语句
                给张三的账户减去10000元
                给李四的账户加上10000元
                如果在第一条SQL语句执行成功后，在执行第二条SQL语句之前，
                    程序被中断了（可能是抛出了某个异常，也可能是其他什么原因）
                    那么李四的账户没有加上10000元，但是张三的账户却减少了10000元。这肯定不行。
            事务中的多个操作要么完全成功，要么完全失败，不可能存在成功一半的情况。
        2.事务的四大特性（ACID）
            原子性（Atomicity）:事务中的所有操作是不可再分割的原子单位。事务中所有操作要么全部执行成功，要么全部执行失败。
            一致性（Consistency）:事务执行后，数据库状态与其他业务规则保持一致。
                如转账业务，无论事务执行成功与否，参与转账的两个账号余额之和应该是不变的
            隔离性（Isolation）:隔离性是指在并发操作中，不同事务之间应该隔离开来，使每个并发中的事务不会相互干扰
            持久性（Durability）:一旦事务提交成功，事务中所有的数据操作都必须被持久化到数据库中，
                即提交事务后，数据库马上崩溃，在数据库重启之后，也必须能保证通过某种机制恢复数据
        3.MySQL中的事务
            在默认情况下，MySQL每执行一条SQL语句，都是一个单独的事务。
            如果需要在一个事务中包含多条SQL语句，那么需要开启事务和结束事务。
            开启事务： start transaction
            结束事务： commit 或 rollback
            在执行SQL语句之前，先执行start transaction，这就开启了一个事务（事务的起点），然后可以去执行多条SQL语句，
                最后要结束事务，commit表示提交，即事务中的多条SQL语句所做出的影响会持久化到数据库中。
                或者rollback，表示回家，即回滚到事务的起点，之前做的所有操作都被撤销了。
            下面演示zs给ls转账10000元的案例：
                START TRANSACTION;
                    UPDATE account SET balance=balance-10000 WHERE id=1;
                    UPDATE account SET balance=balance+10000 WHERE id=2;
                ROLLBACK;   //回滚结束，任务执行失败
                START TRANSACTION;
                    UPDATE account SET balance=balance-10000 WHERE id=1;
                    UPDATE account SET balance=balance+10000 WHERE id=2;
                COMMIT;     //提交结束，事务执行成功
                START TRANSCATION;
                    UPDATE account SET balance=balance-10000 WHERE id=1;
                    UPDATE account SET balance=balance+10000 WHERE id=2;
                quit;       //退出，MySQL会自动回滚事务
*/
public class ACID {

}