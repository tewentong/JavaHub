/*
    DML（数据操作语言，对表记录的操作：增、删、改）
    1.插入数据
        INSERT INTO 表名(列名1, 列名2, ...) VALUES(列值1, 列值2, ...);
            在表名后给出要插入的列名，其他没有指定的列等同于插入null值。所以插入记录总是插入一行，不可能是半行。
            在VALUES后面给出列值，值的顺序和个数必须与前面指定的列对应

            //插入所有列
            INSERT INTO stu(
                number, name, age, gender
            )
            VALUES(
                'ITCAST_0001', 'ZhangSan', 28, 'male'
            );

            //插入部分列，没有指定的列为默认值NULL
            INSERT INTO stu(
                number, name
            )VALUES (
                'ITCAST_0002', 'LiSi'
            );
        INSERT INTO 表名 VALUES(列值1, 列值2)
            没有给出要插入的列，那么表示插入所有列
            值的个数必须是该表列的个数
            值的顺序，必须与表创建时给出的列的顺序相同

            //不给出插入列，那么默认为插入所有列
            //值的顺序要与创建表时列的顺序相同
            INSERT INTO stu VALUES(
                'ITCAST_0003', 'WangWu', 82, 'female'
            );
    2.修改数据
        UPDATE 表名 SET 列名1=列值1, 列名2=列值2, ... [WHERE 条件]
        条件（条件可选的）：
            条件必须是一个boolean类型的值或表达式：
                UPDATE t_person SET gender='男', age=age+1 WHERE sid='1';
            运算符：
                =、！=、<>、>、<、>=、<=、BETWEEN...AND、IN(...)、IS NULL、NOT、OR、AND
                
                update stu set age=age+1 where age>=20 and age<=40;
                update stu set age=age+1 where age between 20 and 40;
                update stu set age=36 where name in('ZhangSan', 'Lisi');
                update stu set age=38 where age is null;    //age=null本身就是false，不用看某一列的值
                update stu set age=48 where not age between 20 and 40;
                update stu set age=100 where age is not null;
    3.删除数据
        DELETE FROM 表名 [WHERE 条件];
        TRUNCATE TABLE 表名: TRUNCATE是DDL语句, 它是先删除drop该表， 在create该表，而且无法回滚！

    在数据库中，所有的字符串类型必须使用单引号，不能使用双引
    日期类型也要使用单引
*/
public class DML {

}