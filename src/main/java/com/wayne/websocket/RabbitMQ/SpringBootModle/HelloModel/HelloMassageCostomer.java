package com.wayne.websocket.RabbitMQ.SpringBootModle.HelloModel;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//如果想设置队列的属性 在@RabbitListener中 默认值：持久化 true 非独占队列false 消费完不自动删除队列false
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "FirstQueues",durable = "false",autoDelete = "false"))
public class HelloMassageCostomer {
	//第一种模型 1对1：消费者
	@RabbitHandler
	public void ReciveMassage(String msg) {
		System.out.println("收到MQ消息==》"+msg);
	}
	
}
