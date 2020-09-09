package com.wayne.websocket.RabbitMQ.NomalModel.Direct0;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.springboot.WehatPublicProject.utils.RabbitConectionUtils;

import java.io.IOException;

//模式一：消息消费者:一对一  一个发送者 生产消息 一个消费者 消费消息
public class MassageConsumer {
	public static void main(String[] argus) throws Exception {
		// 1 创建连接工厂
		// 2 获取连接对象
		Connection connect = RabbitConectionUtils.GetConnection();
		// 3 获取连接通道
		Channel channel = connect.createChannel();
		// 4 通道绑定队列
		// 参数1:queue 队列的名称 如果没有 默认自动创建
		// 参数2:durable 队列的特性 是否要持久化
		// 参数3:exclusive 是独占队列 是否只有当前连接可以使用 不允许其他连接使用
		// 参数4:autoDelete 是否在消费完成删除队列
		// 参数5:arguments 额外参数
		channel.queueDeclare("FirstQueues", true, false, false, null);
		// 5 消费消息
		// 参数1 queue：队列的名称
		// 参数2 autoAck: 开始消息的自动确认机制
		// 参数3 callback: 消息的回调接口
		channel.basicConsume("FirstQueues", true, new DefaultConsumer(channel) {
			// 参数1consumerTag
			// 参数2 Envelope
			// 参数3 BasicProperties
			// 参数4 byte[] body:要消费的消息
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("body==>" + new String(body));
			}
		});
		//注意 消费端的话 是一个多线程长期监听的状态，如果调用close方法  不会进行消息的处理  只会消费消息。
		//如果想实时的消费消息和处理消息 就不要调用close：不能使用@test  要使用main方法
		//channel.close();// 关闭通道
		//connect.close();// 关闭连接
	}
}
