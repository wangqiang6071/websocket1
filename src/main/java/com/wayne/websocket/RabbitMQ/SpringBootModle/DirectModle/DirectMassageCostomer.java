package com.wayne.websocket.RabbitMQ.SpringBootModle.DirectModle;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//如果想设置队列的属性 在@RabbitListener中 默认值：持久化 true 非独占队列false 消费完不自动删除队列false
@Component
public class DirectMassageCostomer {

	// 第四种模型 路由：一条消息发送给指定路由的消息队列：消费者
	// 消费者1
	@RabbitListener(bindings = { @QueueBinding(value = @Queue, // 临时队列
					exchange = @Exchange(value = "directMsg", type = "direct"), 
			        key = { "order", "login" }) })
	public void ReciveMassage1(String msg) {
		System.out.println("收到MQ1=order消息==》" + msg);
	}

	// 消费者2
	@RabbitListener(bindings = { @QueueBinding(value = @Queue, // 临时队列
			exchange = @Exchange(value = "directMsg", type = "direct"), key = { "login" }) })
	public void ReciveMassage2(String msg) {
		System.out.println("收到MQ2=login消息==》" + msg);
	}
}
