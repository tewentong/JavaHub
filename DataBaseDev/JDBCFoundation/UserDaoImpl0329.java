import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.management.RuntimeErrorException;

/*
    面向接口编程
        这里主要理解UserFactory，代码并未具体实现
        1.把UserDao修改为接口，然后把原来的UserDao修改类名为UserDaoImpl
        2.修改UserService中对UserDao的实例化：private UserDao userDao = DaoFactory.getUserDao();
        3.创建DaoFactory，提供getUserDao()


    UserDao
        1.DAO模式
            DAO(Data Access Object)模式就是写一个类，把访问数据库的代码封装起来。
            DAO在数据库与业务逻辑(service)之间
                实体域：即操作的对象，例如我们操作的表是user表，那么就需要先写一个User类
                DAO模式需要先提供一个DAO接口
                然后再提供一个DAO接口的实现类
                再编写一个DAO工厂，Service通过工厂来获取DAO实现
*/
class User0329 {
    public boolean login(String username, String password) throws Exception {
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mydb3";
        String mysqlUsername = "kwj-at-lzu";
        String mysqlPassword = "code";

        Class.forName(driverClassName);
        Connection con = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

        ///////////////////////////////////////////////////////////////////////////
        // 得到PreparedStatement
        // 1.给出SQL模板，所有的参数使用？来替代
        String sql = "select * from t_user WHERE username=? AND password=?";
        // 2.调用Connection方法，得到PreparedStatement对象
        PreparedStatement pstmt = con.prepareStatement(sql);

        // 为参数赋值
        pstmt.setString(1, username); // 为第一个？赋值，值为username
        pstmt.setString(2, password); // 为第二个？赋值，值为password

        // 进行查询
        ResultSet rs = pstmt.executeQuery(); // 调用查询方法，向数据库发送查询语句

        // 程序已经校验并编译过sql模板了
        // 现在要执行 用户liSi 相关的操作时，不用再校验语法，编译模板，可以直接执行下面两条命令
        // pstmt.setString(1, "liSi");
        // pstmt.setString(2, "123");
        return rs.next();
    }
}

class UserService0329 {
    // 把具体的实现类的创建隐藏到工厂中了
    private UserDao0329 userDao = DaoFactory0329.getUserDao();
}

/**
 * 通过配置文件得到dao实现类的名称 通过类名称，完成创建对象（反射完成）
 */
class DaoFactory0329 {
    private static Properties props = null;
    static {
        // 加载配置文件内容到props对象中
        try {
            InputStream in = DaoFactory0329.class.getClassLoader().getResourceAsStream("dao.properties");
            props = new Properties();
            props.load(in);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回一个具体的UserDao的实现类对象
     * 
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static UserDao0329 getUserDao()
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 给出一个配置文件，文件中给出UserDao接口的实现类名称
        // 我们这个方法，获取实现类的类名，通过反射完成创建对象

        // 得到dao实现类的名称
        String daoClassName = props.getProperty("UserDao0329");

        // 通过反射来创建实现类的对象
        try {
            Class clazz = Class.forName(daoClassName);
            return (UserDao0329) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

interface UserDao0329 {
    public void addUser(User0329 form);

    public User0329 findByUsername(String username);
}

public class UserDaoImpl0329 implements UserDao0329 {

    @Override
    public void addUser(User0329 form) {
        // TODO Auto-generated method stub

    }

    @Override
    public User0329 findByUsername(String username) {
        // TODO Auto-generated method stub
        return null;
    }

}
