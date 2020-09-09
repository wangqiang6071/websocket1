package com.wayne.websocket.RabbitMQ.NomalModel.LuYouRoleKeyMQ4;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.springboot.WehatPublicProject.utils.RabbitConectionUtils;

import java.io.IOException;

//模式四：直连模式(direct)==》消费者2
public class MassageConsumer2 {
	public static void main(String[] argus) throws Exception {
		Connection connect = RabbitConectionUtils.GetConnection();
		Channel channel = connect.createChannel();
		// 4 通道绑定交换机
		channel.exchangeDeclare("LuYou", "direct");
		// 5 临时队列
		String queue=channel.queueDeclare().getQueue();
		//6绑定交换机和队列：使用通道对象进行绑定两端的交换机和队列
		channel.queueBind(queue, "LuYou", "delayed2");
		channel.basicQos(0, 1, false);
		// 7 消费消息：取消自动确认机制 false
		channel.basicConsume(queue, false, new DefaultConsumer(channel) {
			public void handleDelivery(String consumerTag, Envelope envelope, 
					BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("body2==>" + new String(body));
				channel.basicAck(envelope.getDeliveryTag(),false);//deliveryTag:手动处理消息的标志//multiple:是否开启多消息处理机制
			}
		});
	}
}
