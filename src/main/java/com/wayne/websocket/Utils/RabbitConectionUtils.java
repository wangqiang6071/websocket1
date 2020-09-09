package com.wayne.websocket.Utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitConectionUtils {
	static ConnectionFactory connection = null;
	// 1 创建连接工厂
	static {
		//重量级资源：让他在类加载的时候，就创建连接，不会每次获取都要创建连接对象
		connection = new ConnectionFactory();
		connection.setHost("47.111.89.51");
		connection.setPort(5672);
		connection.setVirtualHost("admin");
		connection.setUsername("user");
		connection.setPassword("123456");
	}
	// 2 获取连接对象
	public static Connection GetConnection() {
		try {
			return connection.newConnection();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return null;
	}
	// 关闭资源对象
	public static void CloseConnection(Channel channel, Connection conn) {
		try {
			if (channel != null) {
				channel.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
}
