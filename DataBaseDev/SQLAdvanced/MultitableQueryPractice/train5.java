/*
    列出最低薪金大于500的各种工作及从事次工作的员工人数
        列：job, COUNT(*)
        表：EMP e
        条件：MIN(sal)>500    //使用了聚合函数，在分组后
        分组：job
        
        答案：
            SELECT job, COUNT(*)
            FROM EMP
            GROUP BY job
            HAVING MIN(sal)>500;
*/
public class train5 {

}