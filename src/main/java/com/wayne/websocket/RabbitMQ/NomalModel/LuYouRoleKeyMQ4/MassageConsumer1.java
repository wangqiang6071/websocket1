package com.wayne.websocket.RabbitMQ.NomalModel.LuYouRoleKeyMQ4;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.springboot.WehatPublicProject.utils.RabbitConectionUtils;

import java.io.IOException;

//模式四：直连模式(direct)==》消费者1
public class MassageConsumer1 {
	public static void main(String[] argus) throws Exception {
		// 2 获取连接对象
		Connection connect = RabbitConectionUtils.GetConnection();
		// 3 获取连接通道
		Channel channel = connect.createChannel();
		// 4 通道绑定交换机 
		//参数1.交换机的名称 
		//参数2.交换机的类型 
		channel.exchangeDeclare("LuYou", "direct");
		// 5 获取临时创建的队列名称
		String queue=channel.queueDeclare().getQueue();
		// 6 绑定交换机和队列：使用通道对象进行绑定两端的交换机和队列
		//参数1:临时的队列名称 
		//参数2 交换机的名称 
		//参数3 routingKey
		channel.queueBind(queue, "LuYou", "delayed");
		// 7 消息限流：手动确认消息
		// 参数1:prefetchSize：消息发送的限制0:不限制 
		//参数2prefetchCount：每次要处理消息的条数
		//参数3 global：
		channel.basicQos(0, 1, false);
		// 8 消费消息：取消自动确认机制 false
		channel.basicConsume(queue, false, new DefaultConsumer(channel) {
			public void handleDelivery(String consumerTag, Envelope envelope, 
					BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("body1==>" + new String(body));
				//手动确认消息
				channel.basicAck(envelope.getDeliveryTag(),false);
			}
		});
		//RabbitConectionUtils.CloseConnection(channel, connect);// 关闭资源
	}
}
