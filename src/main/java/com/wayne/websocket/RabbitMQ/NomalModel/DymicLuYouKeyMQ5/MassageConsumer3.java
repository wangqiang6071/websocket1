package com.wayne.websocket.RabbitMQ.NomalModel.DymicLuYouKeyMQ5;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.springboot.WehatPublicProject.utils.RabbitConectionUtils;

import java.io.IOException;

//模式五：动态路由模式(topic)==》消费者3
public class MassageConsumer3 {
	public static void main(String[] argus) throws Exception {
		// 2 获取连接对象
		Connection connect = RabbitConectionUtils.GetConnection();
		// 3 获取连接通道
		Channel channel = connect.createChannel();
		// 4 通道绑定交换机
		channel.exchangeDeclare("Dimaic_LuYou", "topic");
		//5获取临时创建的队列名称
		String queue=channel.queueDeclare().getQueue();
		//6绑定交换机和队列
		channel.queueBind(queue, "Dimaic_LuYou", "user.*");
		// 7 消费消息：取消自动确认机制 false
		channel.basicConsume(queue, false, new DefaultConsumer(channel) {
			public void handleDelivery(String consumerTag, Envelope envelope, 
					BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("body3==>" + new String(body));
				channel.basicAck(envelope.getDeliveryTag(),false);
			}
		});
	}
}
