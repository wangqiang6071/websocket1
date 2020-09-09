package com.wayne.websocket.RabbitMQ.NomalModel.Direct0;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.springboot.WehatPublicProject.utils.RabbitConectionUtils;

import java.io.IOException;

public class MassageProducter {
    // 模式一：直接连接：生产者==》消息队列（类似于邮箱或者缓存 然后进行投递消息）==》消费者
    public static void main(String[] args) throws IOException{
        // 1 创建连接工厂
        // 2 获取连接对象
        Connection connect = RabbitConectionUtils.GetConnection();
        // 3 获取连接通道
        Channel channel = connect.createChannel();
        // 4 通道绑定队列
        // 参数1:queue 队列的名称 如果没有 默认自动创建
        // 参数2:durable 队列的特性 是否要持久化:重启之后 队列是否还存在但是不能保证队列中的消息持久化
        // 参数3:exclusive 是独占队列 是否只有当前连接可以使用 不允许其他连接使用
        // 参数4:autoDelete 是否在消费完成删除队列
        // 参数5:arguments 额外参数
        channel.queueDeclare("FirstQueues", true, false, false, null);
        //1.exchage:交换机
        //2.队列名称
        //3.pros 发布消息的额外属性：泽恩么保证消息队列中的消息持久化呢？
        // MessageProperties.PERSISTENT_TEXT_PLAIN:告诉队列重启服务后 持久化消息在队列
        //4.body：发布消息的内容
        channel.basicPublish("", "FirstQueues", MessageProperties.PERSISTENT_TEXT_PLAIN, "发布消息的内容".getBytes());
        //关闭连接和通道
        RabbitConectionUtils.CloseConnection(channel, connect);
    }
}
