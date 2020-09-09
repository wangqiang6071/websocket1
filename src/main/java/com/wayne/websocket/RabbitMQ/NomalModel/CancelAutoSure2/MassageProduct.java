package com.wayne.websocket.RabbitMQ.NomalModel.CancelAutoSure2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.springboot.WehatPublicProject.utils.RabbitConectionUtils;

//模式二：一个生产者==》多个消费者==》发送一个消息==》手动确认消息
// 解决 平均消费的两个问题
// 首先 消息队列中的消息只往消息通道中每次传递一条消息 手动确认之后在发第二条消息

public class MassageProduct {
	public static void main(String[] argus) throws Exception {
		// 2 获取连接对象
		Connection connect = RabbitConectionUtils.GetConnection();
		// 3 获取连接通道
		Channel channel = connect.createChannel();
		// 4 通道绑定队列
		channel.queueDeclare("handMakeSure", true, false, false, null);
		// 5 发布消息到队列中
		for(int i=0;i<=10;i++) {
			channel.basicPublish("", "handMakeSure", MessageProperties.
					PERSISTENT_TEXT_PLAIN, ("handMakeSure"+i).getBytes());
		}
		RabbitConectionUtils.CloseConnection(channel, connect);// 关闭资源
	}
}

