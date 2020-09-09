package com.wayne.websocket.RabbitMQ.SpringBootModle.WorkModel;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("rabbit")
public class WorkMassageProduct {
	//第二种模型 1对多:消息平均分配:生产者
	@Autowired
	private RabbitTemplate templte;
	
	@ResponseBody
	@RequestMapping(value = "work",method = RequestMethod.POST)
	public String SendMassage(@RequestParam(value = "msg",required = true)String msg) {
		//不能主动创建队列 只有消费者才可以创建队列(没有消费者的队列是没有意义的)
		for(int i=0;i<10;i++) {
			templte.convertAndSend("WrokQueues",msg+i);
		}
		return "OK";
	}
}
