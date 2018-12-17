package com.yyz.mina.service;


import java.util.Date;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ServerHandler extends IoHandlerAdapter{

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("服务端连接出现异常");
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        String msg = (String)message;
        session.setAttribute("msg",msg);
        //System.out.println("服务端接收到数据：" + msg);
        if (msg.trim().equalsIgnoreCase("quit")) {
            session.close();
            return;
        }
        //Date date = new Date();
       // session.write("来自服务端的数据"); // 用于写入数据并发送
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("服务端发送数据");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("服务端session关闭");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("服务端创建Session");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        System.out.println("服务端空闲状态");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("服务端打开Session用于读写数据");
    }

}
