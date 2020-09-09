package com.wayne.websocket.RabbitMQ.NomalModel.CancelAutoSure2;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.wayne.websocket.Utils.RabbitConectionUtils;

import java.io.IOException;

//模式二：一个生产者==》多个消费者==》发送一个消息==》手动确认消息
//一个生产者==》多个消费者==》消费者平均消费消息
//平均分配缺点：消息生产者把消息放到队列 队列把所有的消息平均分给多个消费者 由于自动确认机制 消费者确认消息 不管你业务与消息处理是否完成 都会去确认 而MQ就会把所有消息删除掉 从而导致未来的及处理的消息丢失的问题
// 把多条消息给同时给到消费者时候，消息被消费了一部分 然而另一部分没有被来的及消费 当前消费者
// 进程被停掉了。那么会导致一个消息丢失的问题
public class MassageConsumer2 {
	public static void main(String[] argus) throws Exception {
		// 2 获取连接对象
		Connection connect = RabbitConectionUtils.GetConnection();
		// 3 获取连接通道
		Channel channel = connect.createChannel();
		// 4 通道绑定队列
		channel.queueDeclare("handMakeSure", true, false, false, null);
		//每次只能给到消费者1个消息发到通道中 不能全部给到消费者，其余的还是存放在消息队列中
		channel.basicQos(1);
		// 5 消费消息：取消自动确认机制 false
		channel.basicConsume("handMakeSure", false, new DefaultConsumer(channel) {
			public void handleDelivery(String consumerTag, Envelope envelope, 
					BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("body1==>" + new String(body));
				channel.basicAck(envelope.getDeliveryTag(),false);
			}
		});
		//RabbitConectionUtils.CloseConnection(channel, connect);// 关闭资源
	}
}
