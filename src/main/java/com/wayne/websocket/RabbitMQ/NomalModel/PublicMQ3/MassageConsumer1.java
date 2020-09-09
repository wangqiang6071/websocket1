package com.wayne.websocket.RabbitMQ.NomalModel.PublicMQ3;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.springboot.WehatPublicProject.utils.RabbitConectionUtils;

import java.io.IOException;

//模式三：广播模式===》消费者1
public class MassageConsumer1 {
	public static void main(String[] argus) throws Exception {
		// 2 获取连接对象
		Connection connect = RabbitConectionUtils.GetConnection();
		// 3 获取连接通道
		Channel channel = connect.createChannel();
		// 4 通道绑定交换机 
		//参数1.交换机的名称 
		//参数2.交换机的类型
		channel.exchangeDeclare("logs", "fanout");
		//5获取临时创建的队列名称
		String queue=channel.queueDeclare().getQueue();
		//6交换机与队列绑定：使用通道对象进行绑定两端的交换机和队列绑定
		//参数1:临时的队列名称 
		//参数2:交换机的名称 
		//参数3:routingKey：在广播模式下没有特殊的意义,默认为空
		channel.queueBind(queue, "logs", "");
		// 7 消费消息：取消自动确认机制 false
		channel.basicConsume(queue, false, new DefaultConsumer(channel) {
			public void handleDelivery(String consumerTag, Envelope envelope, 
					BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("body1==>" + new String(body));
				//deliveryTag:手动处理消息的标志
				//multiple:是否开启多消息处理机制
				channel.basicAck(envelope.getDeliveryTag(),false);
			}
		});
	}
}
