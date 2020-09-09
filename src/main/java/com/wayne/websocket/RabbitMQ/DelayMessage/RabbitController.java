package com.wayne.websocket.RabbitMQ.DelayMessage;

import com.wayne.websocket.Utils.DateTimeUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@Controller
@RequestMapping("rabbit")
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ResponseBody
    @RequestMapping("direct")
    public void sendDelayMessage(String msg) {
        System.err.println("发送时间:"+DateTimeUtil.dateToStr(new Date()));
        rabbitTemplate.convertAndSend(MyRabbitMQConfig.delayExchange, MyRabbitMQConfig.DELAY_ROUTING_KEY, ",发送时间:"+DateTimeUtil.dateToStr(new Date())+msg);
    }

    @RabbitListener(queues = "normalQueue")
    public void ReciveDelayMessage(String msg) {
        System.err.println("接收消息时间:"+DateTimeUtil.dateToStr(new Date())+msg);
    }
}
