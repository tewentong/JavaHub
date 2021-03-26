/*
    列出在销售部工作的员工的姓名，假定不知道销售部的部门编号
        列：e.ename
        表：EMP
        条件：e.deptno=(通过d.dname查询出deptno)
             e.deptno=(select deptno from DEPT WHERE dname='sales')      

        答案：子查询
            SELECT *
            FROM EMP e
            WHERE e.deptno=(SELECT deptno from DEPT WHERE dname='sales');

        最终结果：
            SELECT e.ename
            FROM EMP e
            WHERE e.deptno=(SELECT deptno from DEPT WHERE dname='sales');
            程序输出;
                +--------+
                | ename  |
                +--------+
                | ALLEN  |
                | WARD   |
                | MARTIN |
                | BLAKE  |
                | TURNER |
                | JAMES  |
                +--------+

*/
public class train6 {

}