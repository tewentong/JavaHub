/*
    列出所有员工的姓名及其直接上级的姓名 
        列：员工姓名、上级姓名 
        表：EMP e, EMP boss 
        条件：员工的mgr=上级的empno
 
        答案： 
            SELECT * 
            FROM EMP e, EMP boss 
            WHERE e.mgr=boss.empno;
        最后结果：利用左外连接输出所有人，也就是把大老板也输出
            SELECT e.ename, boss.ename
            FROM EMP e LEFT OUTER JOIN EMP boss
            ON e.mgr=boss.empno;
            程序输出：
                +--------+-------+
                | ename  | ename |
                +--------+-------+
                | SMITH  | FORD  |
                | ALLEN  | BLAKE |
                | WARD   | BLAKE |
                | JONES  | KING  |
                | MARTIN | BLAKE |
                | BLAKE  | KING  |
                | CLARK  | KING  |
                | SCOTT  | JONES |
                | KING   | NULL  |         //大老板也被输出
                | TURNER | BLAKE |
                | ADAMS  | SCOTT |
                | JAMES  | BLAKE |
                | FORD   | JONES |
                | MILLER | CLARK |
                +--------+-------+

    
*/
public class train2 {

}