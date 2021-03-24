/*
    DQL——数据查询语言
    查询不会修改数据库表记录！

    一、基本查询
        1.字段（列）查询
            1)查询所有列
                SELECT * FROM 表名;
                
                SELECT * FROM emp;
                其中 "*"表示查询所有列
            2)查询指定列
                SELECT 列1 [, 列2, ... 列N] FROM 表名;
                
                SELECT empno, ename, sal, comm FROM 表名;
            3)完全重复的记录只一次
                当查询结果中的多行记录一模一样时，只显示一行。
                一般查询所有列时很少会有这种情况，但只查询一列（或几列）时，这种可能就比较大
                SELECT DISTINCT *|列1 [, 列2, ... 列N] FROM 表名;
                
                SELECT DISTINCT sal FROM emp;
                    查询员工表的工资，如果相同的工资只显示一次
                select distinct * from emp;
                select distinct job from emp;
            4)列运算
                数量类型的列可以做加、减、乘、除运算
                    SELECT sal*1.5 FROM emp;
                    SELECT sal+comm FROM emp;
                    select *,sal*1.5 from emp;
                    //无法当成整数的数据视为0
                    //任何数据同NULL相加结果为NULL
                字符串类型可以做连续运算
                    SELECT CONCAT('$', sal) FROM emp;
                    //MySQL不能用 '+'连接字符串
                    select concat(ename, job) from emp;
                    select concat('我叫', ename, ', 我的工作是', job) from emp;
                转换NULL值
                    有时候需要把NULL转换成其他值，例如com+1000时，如果com列存在NULL值，那么NULL+1000还是NULL
                        而我们这时希望把NULL当成0来运算
                    SELECT IFNULL(com, 0)+1000 FROM emp;
                    select *,sal+ifnull(com,0) from emp;
                    select empno, ename, job, ifnull(mgr, 'BOSS'), hiredate, sal, com, deptno from emp; 
                给列起别名
                    你也许已经注意到了，当使用列运算后，查询出的结果集中的列名称很不好看
                        这时我们给列起个别名，这样在结果集中列名就显示别名了
                    SELECT IFNULL(com, 0)+1000 AS 奖金 FROM emp;
                    select ename as 姓名, job as 工作 from emp;
                    select ename 姓名, job 工作 from emp;   //可以省略as
                    select concat('我叫', ename, ', 我的工作是', job) as 描述 from emp;
        2.条件控制
            1)条件查询
                与前面介绍的UPDATE和DELETE语句一样，SELECT语句也可以使用WHERE子句来控制记录
                    SELECT empno, ename, sal, comm FROM emp WHERE sal>1000 AND comm IS NOT NULL;
                    SELECT empno, ename, sal FROM emp WHERE sal BETWEEN 20000 AND 30000;
                    SELECT empno, ename, job FROM emp WHERE job IN ('经理', '董事长');
            2)模糊查询
                当你想查询姓张，并且姓名一共两个字的员工时，这时就可以使用模糊查询
                    SELECT * FROM emp WHERE ename LIKE '张_';
                        模糊查询需要使用运算符：LIKE, 其中_匹配任意一个字符，注意，只匹配一个字符而不是多个
                        上面语句查询的是姓张，名字由两个字组成的员工

                    SELECT * FROM emp WHERE ename LIKE '___';   //姓名由三个字组成的员工
                如果我们想查询姓张，名字几个字都可以的员工时就要使用 '%'了
                    SELECT * FROM emp WHERE ename LIKE '张%';
                        其中%匹配0～N个任意字符，所以上面语句查询的是姓张的所有员工
                    SELECT * FROM emp WHERE ename LIKE '%阿%';
                        千万不要以为上面语句是在查询姓名中间带有阿字的员工，因为%匹配0～N个字符，
                        所以姓名以阿开头或结尾的员工也都会查询到
                    SELECT * FROM emp WHERE ename LIKE '%';
                        这个条件等同于不存在，但是如果姓名为NULL的查询不出来
    二、排序
        1.升序排列
            SELECT * FROM WHERE emp ORDER BY sal ASC;
                按sal排序，升序
                其中ASC是可以省略的
        2.降序排列
            SELECT * FROM WHERE emp ORDER BY com DESC;
                按com排序，降序
                其中DESC不能省略
        3.使用多列作为排序条件
            SELECT * FROM WHERE emp ORDER BY sal ASC, com DESC;
                使用sal升序，如果sal相同时，使用com的降序排
            select * from emp order by sal asc, com desc, empno asc;
    三、聚合函数
        聚合函数用来做某列的纵向运算
        1.COUNT 计算有效行数
            SELECT COUNT(*) FROM emp;
                计算emp表中所有列都不为NULL的记录的行数
                即公司总共有多少人
                select count(1) from emp;   //count(2)、count(200)都可以，与count(*)没有区别
                SELECT COUNT(com) FROM emp;
                计算emp表中com列不为NULL的记录的列数
        2.MAX
            SELECT MAX(sal) FROM emp;
                查询最高工资
        3.MIN
            SELECT MIN(sal) FROM emp;
                查询最低工资
        4.SUM
            SELECT SUM(sal) FROM emp;
                查询工资和
        5.AVG
            SELECT AVG(sal) FROM emp;
                查询平均工资
        
        select count(*) 人数, sum(sal) 总和, max(sal) 最高, min(sal) 最低， avg(sal) 平均 from emp;
    四、分组查询
        分组查询是把记录使用某一列进行分组，然后查询组信息
        例如：查看所有部门的记录数
        SELECT deptno, COUNT(*) FROM emp GROUP BY deptno;
            使用deptno分组，查询部门编号和每个部门的记录数
        SELECT job, MAX(sal) FROM emp GROUP BY job;
            使用job分组，查询每种工作的最高工资

        select job, count(*) from emp group by job;
        select job, count(*), max(sal) from emp group by job;   //当前组的最大工资
        select gender, count(*) from stu group by gender;   //用性别分组，打印出每组人数
        
        组条件: 以部门分组，查询每组记录数，条件为记录数大于3
        SELECT deptno, COUNT(*) FROM emp GROUP BY deptno HAVING COUNT(*) > 3;

        select deptno, count(*) from emp where sal>15000 group by deptno;   //将薪水大于15000的人按照部门分组
        //先筛选出薪水大于15000的人（分组前的条件  where）

        select deptno, count(*) from emp where sal>15000 group by deptno having count(*)>=2;
        //先筛选出薪水大于15000的人（分组前的条件  where）
        //后筛选出人数超过两人的小组（分组后的条件  having） 用聚合函数做条件
    五、limit子句(方言)
        LIMIT用来限定查询结果的起始行，以及总行数
        例如：查询起始行为第5行，一共查询3行记录
        SELECT * FROM emp LIMIT 4, 3;
            其中4表示从第5行开始，其中3表示一共查询3行，即第5、6、7行记录



    如果一条语句中要用到所有的关键字，其顺序是：
        select
        from
        where 
        group by
        having
*/
public class DQL {

}