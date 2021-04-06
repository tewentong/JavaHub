/*
    Tomcat服务器
    什么是Tomcat？
        Tomcat是一个符合JavaEE WEB标准的最小的WEB容器，
            所有的JSP程序一定要由WEB容器的支持才能运行，而且在给定的WEB容器里面都会支持事务处理操作
        Tomcat参单的说就是一个运行Java的网络服务器，底层是一个Socket的程序，它也是JSP和Servlet的一个容器。
        Tomcat服务器是一个免费的开源WEB应用服务器，
            属于轻量级应用服务器，在中小型系统和并发访问用户不是很多的场合下被普遍使用，是开发和调试JSP程序的首选。
                并发量比较小，250左右，但是可以集群
            当在一台机器上配置好Apache服务器，可利用它响应HTML页面的访问请求。
                实际上Tomcat部分是Apache服务器的扩展，但它是独立运行的，所以当你运行Tomcat时，它作为一个与Apache独立的进程单独运行
            当配置正确时，Apache为HTML页面服务，而Tomcat实际上是在运行JSP页面和Servlet
*/
public class TomcatServer {

}