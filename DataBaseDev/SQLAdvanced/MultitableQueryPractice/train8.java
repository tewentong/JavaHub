/*
    列出与FORD从事相同工作的所有员工及部门名称
        列：e.*, d.dname
        表：EMP e, DEPT d
        条件：job=(查询出庞统的工作)
        
        答案：
            SELECT e.*, d.dname
            FROM EMP e, DEPT d
            WHERE e.deptno=d.deptno AND job=(SELECT job from EMP WHERE ename='FORD');
        程序输出：
            +-------+-------+---------+------+------------+---------+------+--------+----------+
            | EMPNO | ENAME | JOB     | MGR  | HIREDATE   | SAL     | COMM | DEPTNO | dname    |
            +-------+-------+---------+------+------------+---------+------+--------+----------+
            |  7788 | SCOTT | ANALYST | 7566 | 1987-04-19 | 3000.00 | NULL |     20 | RESEARCH |
            |  7902 | FORD  | ANALYST | 7566 | 1981-12-03 | 3000.00 | NULL |     20 | RESEARCH |
            +-------+-------+---------+------+------------+---------+------+--------+----------+

*/
public class train8 {

}