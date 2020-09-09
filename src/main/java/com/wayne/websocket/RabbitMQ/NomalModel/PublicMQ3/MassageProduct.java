package com.wayne.websocket.RabbitMQ.NomalModel.PublicMQ3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.springboot.WehatPublicProject.utils.RabbitConectionUtils;

//模式三：广播模式==》一个生产者==》发送一个消息到==》一个交换机==》一条消息由多个消费者消费==》手动确认消息
public class MassageProduct {
	public static void main(String[] argus) throws Exception {
		// 2 获取连接对象
		Connection connect = RabbitConectionUtils.GetConnection();
		// 3 获取连接通道
		Channel channel = connect.createChannel();
		// 4 将通道绑定声明给交换机
		//1.exchange:交换机的名称(如果没有的话可以默认创建), 
		//2.type:交换机的类型(fanout:代表广播类型)
		channel.exchangeDeclare("logs","fanout");
		// 5 发布消息到交换机
		//1.exchange:交换机名称, 
		//2.routingKey:路由key(在广播模式下没有太大的意义), 
		//3.props:
		//4.body:消息的内容
		channel.basicPublish("logs", "", null, "LogsExchageMassage".getBytes());
		//6 关闭资源
		RabbitConectionUtils.CloseConnection(channel, connect);// 关闭资源
	}
}

