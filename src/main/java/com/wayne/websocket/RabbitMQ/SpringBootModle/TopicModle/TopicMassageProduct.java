package com.wayne.websocket.RabbitMQ.SpringBootModle.TopicModle;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("rabbit")
public class TopicMassageProduct {
	// 第五种模型 动态路由：一条消息发送给指定路由的消息队列：生产者
	@Autowired
	private RabbitTemplate templte;
	
	@ResponseBody
	@RequestMapping(value = "topic",method = RequestMethod.POST)
	public String SendMassage(@RequestParam(value = "msg",required = true)String msg) {
		//不能主动创建队列 只有消费者才可以创建队列(没有消费者的队列是没有意义的)
		templte.convertAndSend("TopicMsg", "login.abc", msg);
		return "OK";
	}
}
