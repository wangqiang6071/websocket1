package com.wayne.websocket.RabbitMQ.SpringBootModle.FanoutModle;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//如果想设置队列的属性 在@RabbitListener中 默认值：持久化 true 非独占队列false 消费完不自动删除队列false
@Component
public class FanoutMassageCostomer {
	
	//第三种模型 广播：一条消息可以给多个消费者消费：消费者
	//消费者1
	@RabbitListener(
			bindings= {
			@QueueBinding(
					value = @Queue,//临时队列
					exchange = @Exchange(value="publicMsg",type = "fanout")
				         )
			          }
	)
	public void ReciveMassage1(String msg) {
		System.out.println("收到MQ1消息==》"+msg);
	}
	//消费者2
	@RabbitListener(
			bindings= {
			@QueueBinding(
					value = @Queue,//临时队列
					exchange = @Exchange(value="publicMsg",type = "fanout")
				         )
			          }
	)
	public void ReciveMassage2(String msg) {
		System.out.println("收到MQ2消息==》"+msg);
	}
}
