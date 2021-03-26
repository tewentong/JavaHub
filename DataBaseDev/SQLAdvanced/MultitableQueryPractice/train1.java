/*
    查出至少有一个员工的部门，显示部门编号、部门名称、部门位置、部门人数
        列：d.deptno, d.dname, d.loc, 部门人数
        表：DEPT d, EMP e
        条件：e.deptno=d.deptno //去笛卡尔积
            
        SELECT * FROM DEPT;
        SELECT deptno, COUNT(*) FROM EMP GROUP BY deptno;

        答案：方言形式的内连接，连接了两张表
            SELECT *
            FROM DEPT d, (SELECT deptno, COUNT(*) FROM EMP GROUP BY deptno) z1
            WHERE d.deptno=z1.deptno;

        最后结果：
            SELECT d.*, z1.cnt
            FROM DEPT d, (SELECT deptno, COUNT(*) cnt FROM EMP GROUP BY deptno) z1
            WHERE d.deptno=z1.deptno;
            程序输出：
                +--------+------------+----------+-----+
                | DEPTNO | DNAME      | LOC      | cnt |
                +--------+------------+----------+-----+
                |     10 | ACCOUNTING | NEW YORK |   3 |
                |     20 | RESEARCH   | DALLAS   |   5 |
                |     30 | SALES      | CHICAGO  |   6 |
                +--------+------------+----------+-----+
 
*/
public class train1 {

}