package com.wayne.websocket.RabbitMQ.NomalModel.Work1;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.springboot.WehatPublicProject.utils.RabbitConectionUtils;

import java.io.IOException;

//消息消费者
//模式一：一个生产者==》多个消费者==》消费者平均消费消息
public class MassageConsumer1 {
	public static void main(String[] argus) throws Exception {
		// 2 获取连接对象
		Connection connect = RabbitConectionUtils.GetConnection();
		// 3 获取连接通道
		Channel channel = connect.createChannel();
		// 4 通道绑定队列
		channel.queueDeclare("work", true, false, true, null);
		// 5 消费消息：ture 代表消息自动确认：消费者自动向MQ确认消息消费：
		channel.basicConsume("work", true, new DefaultConsumer(channel) {
			public void handleDelivery(String consumerTag, Envelope envelope, 
					BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("body1==>" + new String(body));
			}
		});
		//RabbitConectionUtils.CloseConnection(channel, connect);// 关闭资源
	}
}
