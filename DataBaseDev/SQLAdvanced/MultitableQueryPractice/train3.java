/*
    列出受雇日期早于直接上级的所有员工的编号、姓名、部门名称
        列：e.empno, e.ename, d.dname
        表：EMP e, EMP boss, DEPT d
        条件：e.hiredate < boss.hiredate

        思路：
        1.先不查部门名称，只查部门编号
            列：e.empno, e.ename, e.deptno
            表：EMP e, EMP boss
            条件：e.mgr=boss.empno, e.hiaredate<boss.hiredate
        答案：
            SELECT e.empno, e.ename, e.deptno
            FROM EMP e, EMP boss
            WHERE e.mgr=boss.empno AND e.hiredate<boss.hiredate;
        
        2.现在查询部门名称
        最后结果：
            SELECT e.empno, e.ename, d.dname
            FROM EMP e, EMP boss, DEPT d
            WHERE e.mgr=boss.empno AND e.hiredate<boss.hiredate AND e.deptno=d.deptno; 
                                                                  //e.deptno=d.deptno是为了去除笛卡尔积   
            程序输出：
                +-------+-------+------------+
                | empno | ename | dname      |
                +-------+-------+------------+
                |  7369 | SMITH | RESEARCH   |
                |  7499 | ALLEN | SALES      |
                |  7521 | WARD  | SALES      |
                |  7566 | JONES | RESEARCH   |
                |  7698 | BLAKE | SALES      |
                |  7782 | CLARK | ACCOUNTING |
                +-------+-------+------------+

*/
public class train3 {

}