import java.time.chrono.MinguoChronology;

/*
    编码
    1.查看MySQL数据库编码
        SHOW VARIABLES LIKE 'char%';

    2.编码解释
        character_set_client: MySQL使用该编码来解读客户端发送过来的数据，例如该编码为UTF8
            那么如果客户端发送过来的数据不是UTF8，那么就会出现乱码
        character_set_results: MySQL会把数据转换成该编码后，再发送给客户端，例如改编码为UTF8
            那么如果客户端不使用UTF8来解读，那么就会出现乱码
            其他编码只要支持中文即可，也就是说不能使用latin1

    3.控制台乱码问题
        插入或修改时出现乱码：
            这是因为cmd下默认使用GBK，而character_set_client不是GBK的原因，我们只需要让这两个编码相同即可
            因为修改cmd的编码不方便，所以我们去设置character_set_client为GBK即可
        查询出的数据为乱码：
            这是因为character_set_results不是GBK，而cmd默认使用GBK的原因，我们只需要让这两个编码相同即可
            因为修改cmd的编码不方便，所以我们去设置character_set_results为GBK即可
        设置变量的语句：
            set character_set_client=gbk;
            set character_set_results=gbk;

        注意，设置变量只对当前连接有效，当退出窗口后，再次登陆mysql，还需要再次设置变量
        为了一劳永逸，可以在my.init中设置
            设置default-character-set=gbk即可
    
    4.指定默认编码
        我们在安装MySQL时已经指定了默认编码为UTF8，所以我们在创建数据库，创建表时，都无需再次指定编码
        为了一劳永逸，可以在my.init中设置
            设置default-set-server=utf8即可

    character_set_client=utf8, 无论客户端发送的是什么编码的数据，mysql都当成是utf8的数据

*/
public class MySQLEncoding {
    public static void main(String[] args) {

    }
}