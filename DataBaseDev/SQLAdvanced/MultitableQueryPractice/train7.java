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
*/
public class train7 {

}