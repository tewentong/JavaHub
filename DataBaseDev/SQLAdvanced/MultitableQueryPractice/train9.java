/*
    列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金、部门名称
        列：e.ename, e.sal, d.dname
        表：EMP e, DEPT d
        条件：sal>ALL(30部门员工薪金)

        答案：
            SELECT e.ename, e.sal, d.dname
            FROM EMP e, DEPT d
            WHERE e.deptno=d.deptno AND sal> ALL(SELECT sal from EMP WHERE deptno=30);
        程序输出：
            +-------+---------+------------+
            | ename | sal     | dname      |
            +-------+---------+------------+
            | JONES | 2975.00 | RESEARCH   |
            | SCOTT | 3000.00 | RESEARCH   |
            | KING  | 5000.00 | ACCOUNTING |
            | FORD  | 3000.00 | RESEARCH   |
            +-------+---------+------------+

*/
public class train9 {

}