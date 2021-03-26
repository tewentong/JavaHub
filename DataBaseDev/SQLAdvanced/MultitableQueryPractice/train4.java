/*
    列出部门名称和这些部门的员工信息，同时列出那些没有员工的部门
        列：*
        表：EMP e, DEPT d
        条件：e.deptno=d.deptno

        答案：
            SELECT *
            FROM EMP e, DEPT d
            WHERE e.deptno=d.deptno;

        最后结果：修改为右外连接
            SELECT *
            FROM EMP e RIGHT OUTER JOIN DEPT d
            ON e.deptno=d.deptno;
*/
public class train4 {

}