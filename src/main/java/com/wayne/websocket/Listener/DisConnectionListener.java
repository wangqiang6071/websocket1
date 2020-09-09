package com.wayne.websocket.Listener;

import com.wayne.websocket.Schedule.TimeSendMsgToWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

//断开连接
@Component
public class DisConnectionListener implements ApplicationListener<SessionDisconnectEvent> {

    @Autowired
    private TimeSendMsgToWeb web;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor=StompHeaderAccessor.wrap(event.getMessage());
        web.removePeople(headerAccessor.getSessionId());
    }
}
