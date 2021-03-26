/*
    本题需要切换至growthrate数据库下操作
        这道题最关键的部分是——只有在同一行上才可以进行数学计算
                            所以我们需要用两张表左外连接出来一个结果集再计算利润增长率
    查出年份、利润、年度增长比
        SELECT *
        FROM profit_growth_rate y1 LEFT OUTER JOIN profit_growth_rate y2
        ON y1.year=y2.year+1; 
        程序输出：
            +------+--------+------+--------+
            | year | profit | year | profit |
            +------+--------+------+--------+
            | 2010 |    100 | NULL |   NULL |
            | 2011 |    150 | 2010 |    100 |
            | 2012 |    250 | 2011 |    150 |
            | 2013 |    800 | 2012 |    250 |
            | 2014 |   1000 | 2013 |    800 |
            | 2015 |   1200 | 2014 |   1000 |
            +------+--------+------+--------+

        SELECT y1.*, (y1.profit-y2.profit)/y2.profit*100 growthrate
        FROM profit_growth_rate y1 LEFT OUTER JOIN profit_growth_rate y2
        ON y1.year=y2.year+1;

        最终结果：
            SELECT y1.*, CONCAT((y1.profit-y2.profit)/y2.profit*100,'%') growthrate
            FROM profit_growth_rate y1 LEFT OUTER JOIN profit_growth_rate y2
            ON y1.year=y2.year+1;
            程序输出：
                +------+--------+------------+
                | year | profit | growthrate |
                +------+--------+------------+
                | 2010 |    100 | NULL       |
                | 2011 |    150 | 50.0000%   |
                | 2012 |    250 | 66.6667%   |
                | 2013 |    800 | 220.0000%  |
                | 2014 |   1000 | 25.0000%   |
                | 2015 |   1200 | 20.0000%   |
                +------+--------+------------+

        再次修改（不显示NULL）:
            SELECT y1.*, IFNULL(CONCAT((y1.profit-y2.profit)/y2.profit*100,'%'), '0%') growthrate
            FROM profit_growth_rate y1 LEFT OUTER JOIN profit_growth_rate y2
            ON y1.year=y2.year+1;
            程序输出：
                +------+--------+------------+
                | year | profit | growthrate |
                +------+--------+------------+
                | 2010 |    100 | 0%         |
                | 2011 |    150 | 50.0000%   |
                | 2012 |    250 | 66.6667%   |
                | 2013 |    800 | 220.0000%  |
                | 2014 |   1000 | 25.0000%   |
                | 2015 |   1200 | 20.0000%   |
                +------+--------+------------+

*/
public class train10 {

}