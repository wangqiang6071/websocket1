package com.wayne.websocket.RabbitMQ.NomalModel.Work1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.wayne.websocket.Utils.RabbitConectionUtils;

//模式一：一个生产者==》多个消费者==》消费者平均消费消息
//问题：1.当有的消费者处理消息的速度相对其他消费者快一些，会导致消费者等待（消费者能力的资源浪费） 消息堆积（消息来不及消费）
//问题：2.平均消费模型是自动确认的消息的，首先队列把所有的消息平均分成多份(多个消费者) 放到通道中，等待消费者 去消费，万一在消费
       //过程中 有的消费者 当机了(中断不再进行消费)，那分配到通道的消息 没有来得及消费的消息就会丢失
// 能者多劳==任务模型==手动确认
public class MassageProduct {
	public static void main(String[] argus) throws Exception {
		// 2 获取连接对象
		Connection connect = RabbitConectionUtils.GetConnection();
		// 3 获取连接通道
		Channel channel = connect.createChannel();
		// 4 通道绑定队列
		channel.queueDeclare("work", true, false, true, null);
		// 5 发布消息到队列中
		for(int i=0;i<=10;i++) {
			channel.basicPublish("", "work", MessageProperties.
					PERSISTENT_TEXT_PLAIN, ("QueueFrist"+i).getBytes());
		}
		RabbitConectionUtils.CloseConnection(channel, connect);// 关闭资源
	}
}

