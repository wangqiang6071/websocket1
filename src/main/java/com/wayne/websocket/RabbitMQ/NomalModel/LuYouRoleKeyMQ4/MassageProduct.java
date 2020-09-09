package com.wayne.websocket.RabbitMQ.NomalModel.LuYouRoleKeyMQ4;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.springboot.WehatPublicProject.utils.DateTimeUtil;
import com.springboot.WehatPublicProject.utils.RabbitConectionUtils;

import java.util.Date;

//模式四：直连模式(direct)==》一个生产者==》发送多个不同消息==》一个交换机==》多个routingKey==》不同的路由key发送给不同的消费者==》多个消费者==》手动确认消息
public class MassageProduct {
	public static void main(String[] argus) throws Exception {
		// 2 获取连接对象
		Connection connect = RabbitConectionUtils.GetConnection();
		// 3 获取连接通道
		Channel channel = connect.createChannel();
		// 4 将通道绑定声明给交换机：
		// 1 logs_direct:交换机名称,
		// 2 type:交换机的类型(direct:代表路由类型==直连)
		channel.exchangeDeclare("LuYou", "direct");
		// 5 发布消息到交换机
		//1.logs_direct:交换机名称, 
		//2.logs_key:路由key,
		//3.props:null
		//4.direct_logs_key:消息的内容
		channel.basicPublish("LuYou", "delayed",null, DateTimeUtil.dateToStr(new Date()).getBytes());
		// 6 关闭资源
		RabbitConectionUtils.CloseConnection(channel, connect);// 关闭资源
	}
}
