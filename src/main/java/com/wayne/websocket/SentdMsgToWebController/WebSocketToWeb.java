package com.wayne.websocket.SentdMsgToWebController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/server")
public class WebSocketToWeb {

    @Autowired
    private SimpMessagingTemplate templeMsg;
    //服务端往web端发送数据的页面
    @RequestMapping("/web")
    public String SocketToWeb(){
        return "ServerToWeb";
    }

    //服务端给指定的web端发送数据的页面
    @RequestMapping("/point")
    public String SocketToPointWeb(Integer id, ModelMap model){
        model.addAttribute("id",id);
        return "ServerToPointWeb";
    }

    //服务端与web端 一对一 发送消息的页面
    @RequestMapping("/point2point")
    public String SocketPointToPointWeb(Integer id, ModelMap model){
        model.addAttribute("id",id);
        return "ServerPointToPointWeb";
    }

    //服务端与web端 一对一 发送消息接口
    @RequestMapping(value = "/point2pointmsg",method = RequestMethod.POST)
    @ResponseBody
    public void SocketPointToPointWebMsg(String userid, String msg){
        templeMsg.convertAndSendToUser(userid,"/serverPointToPointWeb",msg);
    }
}
