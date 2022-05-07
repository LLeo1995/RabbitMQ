package com.leo.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 基于Spring的RabbitMQ测试
 * 生产者模拟
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置参数

        //创建链接
        Connection connection = factory.newConnection();
        //创建Channel
        Channel channel = connection.createChannel();

        //创建队列Queue
        //public void queueDeclareNoWait(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) throws IOException {
        //1、queue：队列名称
        //2、durable：是否持久化，当mq重启后是否还在
        //3、exclusive
        //  *是否独占。即只能有一个消费者监听这个队列
        //  *当Connection关闭时，是否删除队列
        //4、autoDelete：是否自动删除
        //5、arguments：参数
        //channel.queueDeclare("hello_world233",true,true,false,null);
        String msg = "Hello RobbitMQ";

        //发送消息
        //public void basicPublish(String exchange, String routingKey, AMQP.BasicProperties props, byte[] body) throws IOException
        //1、exchange：交换机名称，简单模式下交换机使用默认的
        //2、routingKey：路由名称
        //3、props：配置信息
        //4、body：发送消息数据
        channel.basicPublish("","hello_world233",null,msg.getBytes());

        //释放资源
        channel.close();
        connection.close();
    }
}
