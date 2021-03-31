import java.io.IOException;
import java.sql.Connection;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
    Tomcat配置连接池
        1.Tomcat配置JNDI资源
            JNDI(Java Naming and Directory Interface), Java命名和目录接口。
            JNDI的作用就是：在服务器上配置资源，然后通过统一的方式来获取配置的资源
            我们这里要配置的资源当然是连接池，这样项目中就可以通过统一的方式来获取连接池对象了

            配置JNDI资源需要用到<Context>元素中配置<Resource>子元素：
                name:指定资源的名称，这个名称可以随便给，在获取资源时需要这个名称
                factory:用来创建资源的工厂，这个值基本上是固定的，不用修改
                type:资源的类型，我们需要给出的类型当然是我们的连接池的类型了
                bar:表示资源的属性，如果资源存在名为bar的属性，那么就配置bar的值。
                    对于DBCP连接池而言，你需要配置的不是bar，因为它没有bar这个属性，而是应该去配置url、username等属性
        2.获取资源
            配置资源的目的当然是为了获取资源。
            只要你启动了Tomcat，那么就可以在项目中任何类中通过JNDI获取资源的方式来获取资源了。
            Tomcat文档提供的，与Tomcat文档提供的配置资源是对应的
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");
                MyBean bean = (MyBean) envCtx.lookup("");

                writer.println("foo = " + bean.getFoo() + ", bar = " + bean.getBar());
            获取资源：
                Context: javax.naming.Context;
                InitialContext: javax.naming.InitialContext;
                lookup(String): 获取资源的方法，其中“java:comp/env”是资源的入口（这是固定的名称）
                    获取过来还是一个Context，这说明需要在获取到的Context上进一步进行获取

*/
class Servlet0331 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // 1.创建JNDI的上下文对象
            Context ctx = new InitialContext();
            // 2.查询出入口
            Context envContext = (Context) ctx.lookup("java:comp/env");
            // 3.再进行二次查询，找到我们的资源
            // 使用的名称是与JDNI_config.xml文件中<Resource>元素的name对应的
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/dataSource");

            Connection con = dataSource.getConnection();
            System.out.println(con);
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

public class JNDI {
    public static void main(String[] args) {
        new Servlet0331().doGet(request, response);
    }
}