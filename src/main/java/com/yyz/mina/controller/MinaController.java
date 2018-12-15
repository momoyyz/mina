package com.yyz.mina.controller;

import com.yyz.mina.demo1.MinaServer;
import com.yyz.mina.demo1.MyClientHandler;
import com.yyz.mina.demo1.MyHandler;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

@RestController
public class MinaController {
    static int PORT = 7081;
    static IoAcceptor accept = null; // 提供服务端实现

    private static String host = "127.0.0.1";
    private static int port = 7081;

    @RequestMapping("test1")
    public Object test1(){
        System.out.println("****");
        try {
            accept = new NioSocketAcceptor();
            // 设置过滤器
            accept.getFilterChain().addLast(
                    "codec",
                    new ProtocolCodecFilter(new TextLineCodecFactory(Charset
                            .forName("UTF-8"),
                            LineDelimiter.WINDOWS.getValue(),
                            LineDelimiter.WINDOWS.getValue())));

            // 设置缓冲区
            accept.getSessionConfig().setReadBufferSize(1024);// 大小
            accept.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);// 时间10s
            accept.setHandler(new MyHandler());// 业务
            accept.bind(new InetSocketAddress(PORT));// 绑定端口并启动
            System.out.println("Server ->" + PORT);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "success1";
    }
    @RequestMapping("test2")
    public String test2(){
        IoSession session = null;
        IoConnector connector = new NioSocketConnector();// 提供客户端实现
        connector.setConnectTimeout(3000);// 设置超时时间
        // 设置过滤器(编码和解码)
        connector.getFilterChain().addLast("coderc", new ProtocolCodecFilter(
                new TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter.WINDOWS.getValue())));
        // 业务处理
        connector.setHandler(new MyClientHandler());
        // 设置session属性,获取服务端连接
        ConnectFuture future = connector.connect(new InetSocketAddress(host,port));
        future.awaitUninterruptibly();// 等待我们的连接
        session = future.getSession();
       // session.write("-------------------------");// 写入数据,发往服务端
        session.getCloseFuture().awaitUninterruptibly();// 等待关闭连接
        connector.dispose();// 释放资源
        return "success2";
    }
    @RequestMapping("test3")
    public String test3(){
        System.out.println(getConNum());
        sendConMessage();
        return "success3";
    }
    /**
     * 获得客户端连接总数
     * @return
     */
    public static int getConNum(){

        int num = accept.getManagedSessionCount();
        System.out.println("num:" + num);
        return num;
    }

    /**
     * 向每个客户端发送消息
     * @return
     */
    public static void sendConMessage(){

        IoSession session;

        Map conMap = accept.getManagedSessions();

        Iterator iter = conMap.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            session = (IoSession)conMap.get(key);
            session.write("" + key.toString()+"服务地发送数据");
        }


    }
}
