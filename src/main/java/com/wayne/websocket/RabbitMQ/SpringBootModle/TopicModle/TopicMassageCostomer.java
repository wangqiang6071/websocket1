package com.wayne.websocket.RabbitMQ.SpringBootModle.TopicModle;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//如果想设置队列的属性 在@RabbitListener中 默认值：持久化 true 非独占队列false 消费完不自动删除队列false
@Component
public class TopicMassageCostomer {

	// 第五种模型 动态路由：一条消息发送给指定路由的消息队列：消费者
	// 消费者1
	@RabbitListener(bindings = { @QueueBinding(value = @Queue, // 临时队列
			exchange = @Exchange(value = "TopicMsg", type = "topic"), key = { "login.*" }) })
	public void ReciveMassage1(String msg) {
		System.out.println("收到MQ1=order消息==》" + msg);
	}

	// 消费者2
	@RabbitListener(bindings = { @QueueBinding(value = @Queue, // 临时队列
			exchange = @Exchange(value = "TopicMsg", type = "topic"), key = { "login.#" }) })
	public void ReciveMassage2(String msg) {
		System.out.println("收到MQ2=login消息==》" + msg);
	}

	// 消费者3
	@RabbitListener(bindings = { @QueueBinding(value = @Queue, // 临时队列
			exchange = @Exchange(value = "TopicMsg", type = "topic"), key = { "login.abc" }) })
	public void ReciveMassage3(String msg) {
		System.out.println("收到MQ3=login消息==》" + msg);
	}

	// 消费者4
	@RabbitListener(bindings = { @QueueBinding(value = @Queue, // 临时队列
			exchange = @Exchange(value = "TopicMsg", type = "topic"), key = { "login.abcd" }) })
	public void ReciveMassage4(String msg) {
		System.out.println("收到MQ4=login消息==》" + msg);
	}
}
