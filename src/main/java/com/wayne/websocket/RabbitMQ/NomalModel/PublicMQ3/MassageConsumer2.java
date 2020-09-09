package com.wayne.websocket.RabbitMQ.NomalModel.PublicMQ3;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.springboot.WehatPublicProject.utils.RabbitConectionUtils;

import java.io.IOException;

//模式三：广播模式===》消费者2
public class MassageConsumer2 {
	public static void main(String[] argus) throws Exception {
		// 2 获取连接对象
		Connection connect = RabbitConectionUtils.GetConnection();
		// 3 获取连接通道
		Channel channel = connect.createChannel();
		// 4 通道绑定交换机
		channel.exchangeDeclare("logs", "fanout");
		//5获取临时创建的队列名称
		String queue=channel.queueDeclare().getQueue();
		//6交换机和队列绑定：使用通道对象进行绑定两端的交换机和队列
		channel.queueBind(queue, "logs", "");
		// 7 消费消息：取消自动确认机制 false
		channel.basicConsume(queue, false, new DefaultConsumer(channel) {
			public void handleDelivery(String consumerTag, Envelope envelope, 
					BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("body2==>" + new String(body));
				channel.basicAck(envelope.getDeliveryTag(),false);
			}
		});
	}
}
