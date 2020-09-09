package com.wayne.websocket.Listener;

import com.wayne.websocket.Schedule.TimeSendMsgToWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

//建立连接
@Component
public class ConnectionListener implements ApplicationListener<SessionConnectedEvent> {
    @Autowired
    private TimeSendMsgToWeb web;

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor=StompHeaderAccessor.wrap(event.getMessage());
        web.AddPeople(headerAccessor.getSessionId());
    }
}
