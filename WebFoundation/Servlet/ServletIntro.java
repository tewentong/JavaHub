import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
    什么是Servlet
        Servlet是JavaWeb的三大组件之一，它属于动态资源。
        Servlet的作用是处理请求，服务器会把接收到的请求交给Servlet来处理，在Servlet中通常需要：
            接收请求数据
            处理请求
            完成响应
        例如客户端发出登陆请求，或者输出注册请求，这些请求都应该由Servlet来完成处理！
        Servlet需要我们自己来编写，每个Servlet必须实现javax.servlet.Servlet接口
    实现Servlet的方式(由自己编写)
        实现Servlet有三种方式：
            实现javax.servlet.Servlet接口
            继承javax.servlet.GenericServlet类
            继承javax.servlet.http.HttpServlet类
            通常我们会去继承HttpServlet类来完成我们的Servlet，但学习Servlet还要从javax.servlet.Servlet接口开始学习
            public interface Servlet {
                public void init(ServletConfig config) throws ServletException;
            
                public ServletConfig getServletConfig();
            
                public void service(ServletConfig req, ServletResponse res) throws ServletException, IOException;
            
                public String getServletInfo();
            
                public void destory();
            }
            Servlet中的方法大多数不由我们来调用，而是由Tomcat(服务器)来调用
                并且Servlet对象也不由我们来创建，而是由Tomcat来创建
*/

public class ServletIntro implements Servlet {

    @Override
    public void destroy() {
        System.out.println("destroy()...");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("getServletConfig()...");
        return null;
    }

    @Override
    public String getServletInfo() {
        System.out.println("getServletInfo()...");
        return null;
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("servletConfig()...");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {
        System.out.println("servletRequest " + "servletResponse");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("destroy()...");
    }
}