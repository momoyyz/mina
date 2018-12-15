package com.yyz.mina.demo1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

import com.yyz.mina.demo2.TimeServerHandler;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecException;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {
    static int PORT = 7080;
    static IoAcceptor accept = null; // 提供服务端实现

//    public static void main(String[] args) {
//        try {
//            accept = new NioSocketAcceptor();
//            // 设置过滤器
//            accept.getFilterChain().addLast(
//                    "codec",
//                    new ProtocolCodecFilter(new TextLineCodecFactory(Charset
//                            .forName("UTF-8"),
//                            LineDelimiter.WINDOWS.getValue(),
//                            LineDelimiter.WINDOWS.getValue())));
//
//            // 设置缓冲区
//            accept.getSessionConfig().setReadBufferSize(1024);// 大小
//            accept.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);// 时间10s
//            accept.setHandler(new MyHandler());// 业务
//            accept.bind(new InetSocketAddress(PORT));// 绑定端口并启动
//            System.out.println("Server ->" + PORT);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }


//    /**
//     * 获得客户端连接总数
//     * @return
//     */
//    public static int getConNum(){
//
//        int num = accept.getManagedSessionCount();
//        System.out.println("num:" + num);
//        return num;
//    }
//
//    /**
//     * 向每个客户端发送消息
//     * @return
//     */
//    public static void sendConMessage(){
//
//        IoSession session;
//
//        Map conMap = accept.getManagedSessions();
//
//        Iterator iter = conMap.keySet().iterator();
//        while (iter.hasNext()) {
//            Object key = iter.next();
//            session = (IoSession)conMap.get(key);
//            session.write("" + key.toString());
//        }
//
//
//    }
}
