package com.wayne.websocket.RabbitMQ.SpringBootModle.WorkModel;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//如果想设置队列的属性 在@RabbitListener中 默认值：持久化 true 非独占队列false 消费完不自动删除队列false
@Component
public class WorkMassageCostomer {
	
	//第二种模型 1对多：平均消费：消费者
	@RabbitListener(queuesToDeclare = @Queue(value = "WrokQueues",durable = "false",autoDelete = "false"))
	public void ReciveMassage1(String msg) {
		System.out.println("收到MQ1消息==》"+msg);
	}

	@RabbitListener(queuesToDeclare = @Queue(value = "WrokQueues",durable = "false",autoDelete = "false"))
	public void ReciveMassage2(String msg) {
		System.out.println("收到MQ2消息==》"+msg);
	}
	
}
