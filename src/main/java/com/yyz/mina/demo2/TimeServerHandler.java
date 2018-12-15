//package com.yyz.mina.demo2;
//
//import org.apache.mina.core.service.IoHandlerAdapter;
//import org.apache.mina.core.session.IoSession;
//
//import java.util.Date;
//
//public class TimeServerHandler extends IoHandlerAdapter {
//
//    @Override
//    public void sessionCreated(IoSession session) {
//        // 显示客户端的ip和端口
//        System.out.println(session.getRemoteAddress().toString());
//    }
//
//    @Override
//    public void messageReceived(IoSession session, Object message)
//            throws Exception {
//        String str = message.toString();
//        if (str.trim().equalsIgnoreCase("quit")) {
//            session.close();// 结束会话
//            return;
//        }
//        Date date = new Date();
//        session.write("服务端发给客户端的数据："+date.toString());// 返回当前时间的字符串
//        System.out.println("Message written..." + str);
//    }
//
//}