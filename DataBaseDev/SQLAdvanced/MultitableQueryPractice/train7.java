/*
    列出薪金高于公司平均薪金的所有员工信息，所在部门名称，上级领导，工资等级
        1.分析：先只查询薪金高于公司平均薪金的所有员工信息
            列：*
            表：EMP e
            条件：sal>(查询出公司的平均工资)
            SELECT e.*
            FROM EMP e
            WHERE e.sal>(SELECT AVG(sal) FROM EMP);
        2.分析：现在加上部门名称
            SELECT e.*, d.dname
            FROM EMP e, DEPT d
            WHERE e.sal>(SELECT AVG(sal) FROM EMP) AND e.deptno=d.deptno;
        3.分析：现在加上上级领导
            SELECT e.*, d.dname, boss.ename boss_name
            FROM EMP e, DEPT d, EMP boss
            WHERE e.sal>(SELECT AVG(sal) FROM EMP) AND e.deptno=d.deptno AND e.mgr=boss.empno;
        4.分析：现在加上工资等级
            SELECT e.*, d.dname, boss.ename boss_name, s.grade sal_grade
            FROM EMP e, DEPT d, EMP boss, SALGRADE s
            WHERE e.sal>(SELECT AVG(sal) FROM EMP) AND e.deptno=d.deptno AND e.mgr=boss.empno
                            AND e.sal BETWEEN s.LOSAL AND s.HISAL;

        现在修改为三表左外连接：//表和条件一一对应
            SELECT e.*, d.dname, boss.ename boss_name, s.grade sal_grade
            FROM
                EMP e LEFT OUTER JOIN DEPT d ON e.deptno=d.deptno
                      LEFT OUTER JOIN EMP boss ON e.mgr=boss.empno
                      LEFT OUTER JOIN SALGRADE s ON e.sal BETWEEN s.LOSAL AND s.HISAL
            WHERE e.sal>(SELECT AVG(sal) FROM EMP);
            程序输出：
                +-------+-------+-----------+------+------------+---------+------+--------+------------+-----------+-----------+
                | EMPNO | ENAME | JOB       | MGR  | HIREDATE   | SAL     | COMM | DEPTNO | dname      | boss_name | sal_grade |
                +-------+-------+-----------+------+------------+---------+------+--------+------------+-----------+-----------+
                |  7566 | JONES | MANAGER   | 7839 | 1981-04-02 | 2975.00 | NULL |     20 | RESEARCH   | KING      |         4 |
                |  7698 | BLAKE | MANAGER   | 7839 | 1981-05-01 | 2850.00 | NULL |     30 | SALES      | KING      |         4 |
                |  7782 | CLARK | MANAGER   | 7839 | 1981-06-09 | 2450.00 | NULL |     10 | ACCOUNTING | KING      |         4 |
                |  7788 | SCOTT | ANALYST   | 7566 | 1987-04-19 | 3000.00 | NULL |     20 | RESEARCH   | JONES     |         4 |
                |  7839 | KING  | PRESIDENT | NULL | 1981-11-17 | 5000.00 | NULL |     10 | ACCOUNTING | NULL      |         5 |
                |  7902 | FORD  | ANALYST   | 7566 | 1981-12-03 | 3000.00 | NULL |     20 | RESEARCH   | JONES     |         4 |
                +-------+-------+-----------+------+------------+---------+------+--------+------------+-----------+-----------+

*/
public class train7 {

}