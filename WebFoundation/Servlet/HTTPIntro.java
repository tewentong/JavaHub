/*
    HTTP协议(hypertext Transfer Protocol 超文本传输协议)是一个客户端请求和响应的标准协议
        这个协议详细规定了浏览器和万维网服务器之间相互通信的规则。
            用户输入地址和端口号之后就可以从服务器上取得所需要的网页信息。
            通信规则规定了客户端发送给服务器的内容格式，也规定了服务器发送给客户端的内容格式。
            客户端发送给服务器的格式叫做“请求协议”，服务器发送给客户端的格式叫做“响应协议”
            在浏览器中F12可查看
        浏览器中的书写格式
            服务器端资源需要通过浏览器进行，此是由浏览器将我们给出的请求解析为满足HTTP协议的格式并发出。
                告诉我规则  给我想要的内容
                HTTP://   127.0.0.1:8080    /myweb/servlet01?name=zhangSan
            当浏览器获取到信息以后，按照特定格式解析并发送即可。
            接收到服务器端给出的响应后时，也按照HTTP协议进行解析获取到各个数据，最后按照特定格式展示给用户
        HTTP协议的特点
            支持客户/服务器模式
            简单快捷：客户像服务器请求服务时，只需传送请求方法和路径。请求方法常用的有GET、POST。
                每种方法规定了客户与服务器联系的类型不同。
                由于HTTP协议简单，使得HTTP服务器的程序规模小，因而通信速度很快
            灵活：HTTP允许传输任意类型的数据对象。传输的类型由Content-Type加以标记。
            无连接：无连接是表示每次连接只处理一个请求。
                服务器处理完客户的请求，并受到客户的应答后，即断开连接。采用此方式可以节省传输时间。
                HTTP1.1版本后支持可持续连接。
                    通过这种连接，就有可能在建立一个TCP连接后，发送请求并得到回应，然后发送更多的请求并得到更多的回应。
                    通过把建立和释放TCP连接的开小分摊到多个请求上，则对于每个请求而言，由于TCP而造成的相对开销被大大降低了。
                    而且，还可以发送流水线请求，也就是说在发送请求1之后的回应到来之前就可以发送请求2
                    也可以认为，一次连接发送多个请求，由客户确认是否关闭连接，而服务器会认为这些请求分别来自不同的客户端
            无状态：HTTP协议是无状态协议。无状态是指协议对于事务处理没有记忆能力。
                缺少状态意味着如果后续处理需要前面的信息，则它必须重传，这样可能导致每次连接传送的数据量增大。
                另一方面，在服务器不需要先前信息时它的应答就较快。
        HTTP之URL
            HTTP(超文本传输协议)是一个基于请求与响应模式的、应用层的协议，常基于TCP的连接方式  
                绝大多数的Web开发，都是构建在HTTP协议之上的Web应用
            HTTP URL(URL是一种特殊的URI，包含了用于查找某个资源的足够的信息)的格式如下：
                http://host[:port]/[abs_path]
                http://IP(主机名/域名):端口/访问的资源路径
                http表示要通过HTTP协议来定位网络资源;
                host表示合法的Internet主机域名或者IP地址;
                port指一个端口哈，为空则使用缺省端口80;
                abs_path指定请求资源的URI;如果URL中没有给出abs_path，那么当它作为请求URI时，必须以 "/"的形式给出
                    通常这个工作浏览器自动帮我们完成
        HTTP请求
            HTTP请求由三部分组成，分别是：请求行、请求头、请求正文
            F12 ---> Network查看
            请求行以一个方法符号开头，以空格分开，后面跟着请求的URI和协议的版本
                格式如下： Method Request-URI HTTP-Version CRLF
                    Method 表示请求方法；
                    Request-URI 是一个统一资源标识符；
                    HTTP-Version 表示请求的HTTP协议版本；
                    CRLF 表示回车和换行
        HTTP响应
            在接受和解释请求后，服务器返回一个HTTP响应消息。
            HTTP响应也是由三个部分组成，分别是：状态行、消息报头、响应正文 
        消息头
            HTTP消息由客户端到服务器的请求和服务器到客户端的响应组成。
                请求消息和响应消息都是由开始行（对于请求消息，开始行就是请求行，对于响应消息，开始行就是响应行）、
                消息报头（可选）、空行（只有CRLF的行）、消息正文（可选）组成。
            每一个报头域都是由  名字+":"+空格+值  组成，消息报头域的名字是大小写无关的。
            请求头：
                请求报头允许客户端向服务器端传递请求的附加信息以及客户端自身的信息。
                Referer：该请求头指明请求从哪里来
                    如果是地址栏中输入地址访问的都没有该请求头
                    地址栏输入地址，通过请求头可以看到，此是多了一个Refer的请求头，并且后面的值为该请求从哪里发出
                    通常用来做统计工作、放盗链
            响应头
                响应报头允许服务器传递不能放在状态行中的附加响应信息
                    以及关于服务器的信息和对Request-URI所标识的资源进行下一步访问的信息
                Location：Location响应报头域用于重定向接收者到一个新的位置
                    Location响应报头域，常用在更换域名的时候
                    reponse.sendRedirection("http://www.baidu.com");
                Refresh：自动跳转（单位是秒），可以在页面通过meta标签实现，也可在后台实现
                    <meta http-equiv="refresh" content="3;url=http://www.baidu.com">
*/
public class HTTPIntro {

}