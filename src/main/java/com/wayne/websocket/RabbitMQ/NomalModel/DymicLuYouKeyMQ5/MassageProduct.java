package com.wayne.websocket.RabbitMQ.NomalModel.DymicLuYouKeyMQ5;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.springboot.WehatPublicProject.utils.RabbitConectionUtils;

//模式五：动态路由模式(topic)==》一个生产者==》发送多个消息==》一个交换机==》多个routingKey==》不同的路由key发送给不同的消费者==》多个消费者==》手动确认消息
public class MassageProduct {
	public static void main(String[] argus) throws Exception {
		// 2 获取连接对象
		Connection connect = RabbitConectionUtils.GetConnection();
		// 3 获取连接通道
		Channel channel = connect.createChannel();
		// 4 将通道绑定声明给交换机：
		channel.exchangeDeclare("Dimaic_LuYou","topic");
		// 5 发布消息到交换机
		channel.basicPublish("Dimaic_LuYou", "user.abc", null,"user.topic".getBytes());
		//6 关闭资源
		RabbitConectionUtils.CloseConnection(channel, connect);// 关闭资源
	}
}

