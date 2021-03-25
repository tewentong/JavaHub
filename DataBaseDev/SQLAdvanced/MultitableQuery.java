/*
    多表查询
        1.分类：
            合并结果集（了解）
            连接查询
            子查询
    合并结果集
        要求被合并的结果集中，列的类型和列数相同（结果集结构完全相同）
        UNION，去除重复行
        UNION ALL，不去除重复行
        例程：
            SELECT * FROM ab
            UNION ALL
            SELECT * FROM cd;
        
    连接查询
        1.分类
            内连接
            外连接
                左外连接
                右外连接
                全外连接（MySQL不支持）
            自然连接（属于一种简化方式）
        2.内连接
            方言：SELECT * FROM 表1 别名1， 表2 别名2 WHERE 别名1.xx=别名2.xx
            标准：SELECT * FROM 表1 别名1 INNER JOIN 表2 别名2 ON 别名1.xx=别名2.xx
            自然：SELECT * FROM 表1 别名1 NATURAL JOIN 表2 别名2
            内连接查询出的所有记录都满足条件
        3.外连接
            左外：SELECT * FROM 表1 别名1 LEFT OUTER JOIN 表2 别名2 ON 别名1.xx=别名2.xx
                左外表记录无论是否满足条件都会查询出来，而右表只有满足条件才能出来
                左表中不满足条件的记录，右表部分都为NULL
            左外自然：SELECT * FROM 表1 别名1 NATURAL LEFT OUTER JOIN 表2 别名2 ON 别名1.xx=别名2.xx
            右外：SELECT * FROM 表1 别名1 RIGHT OUTER JOIN 表2 别名2 ON 别名1.xx=别名2.xx
                右表记录无论是否满足条件都会查询出来，而左表只有满足条件才能出来
                右表不满足条件的记录，其左表部分都为NULL
            右外自然：SELECT * FROM 表1 别名1 NATURAL RIGHT OUTER JOIN 表2 别名2 ON 别名1.xx=别名2.xx
            全链接：可以使用UNION来完成全链接
    
    子查询：查询中有查询（查看select关键字的个数）
        1.出现的位置：
            where后作为条件存在
            from后作为表存在（多行多列）
        2.条件
            单行单列：SELECT * FROM 表1 别名1 WHERE 列1 [=、>、<、>=、<=、！=]  (SELECT 列 FROM 表2 别名2 WHERE 条件)
            多行单列：SELECT * FROM 表1 别名1 WHERE 列1 [IN, ALL, ANY] (SELECT 列 FROM 表2 别名2 WHERE 条件)
            单行多列：SELECT * FROM 表1 别名1 WHERE (列1、列2) IN (SELECT 列1, 列2 FROM 表2 别名2 WHERE 条件)
            多行多列：SELECT * FROM 表1 别名1, (SELECT ...) 别名2 WHERE 条件
*/
public class MultitableQuery {

}