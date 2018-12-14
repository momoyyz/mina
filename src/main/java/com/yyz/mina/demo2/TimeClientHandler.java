package com.yyz.mina.demo2;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class TimeClientHandler extends IoHandlerAdapter {

    public TimeClientHandler() {
    }
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("客户端接收到："+message);//显示接收到的消息
    }


}