/*
    CGLIB实现代理设计模式
    从java的官方来讲已经明确的要求了如果要想实现代理设计模式，那么一定是基于接口的应用，所以在官方给出的Proxy类
        创建代理对象时都需要传递该对象所有的接口信息
        Proxy.newProxyInstance(target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),this);

    但是这个时候有一部分的开发者就认为不应该强迫性的基于接口来实现代理设计
        所以有一些开发者就开发出了一个叫CGLIB的开发包，利用这个开发包就可以实现基于类的代理设计模式
        1.CGLIB是一个第三方的程序包，需要单独在Eclipse之中进行配置，现在程序包的路径为：D:\jar-lib\cglib-nodep-3.2.6.jar;
            那么需要安装第三方开发包
        2.

*/
public class CGLIB {
    public static void main(String[] args) {

    }
}