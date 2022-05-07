package com.leo.consumer;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 基于Spring的RabbitMQ测试
 * 消费者模拟
 */
public class Consumer {
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


        //接收消息
        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel){
            //回调方法
            //1、consumerTag：标识
            //2、envelope：获取一些信息，交换机，路由等
            //3、properrties：配置信息
            //4、bady：数据
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag: " + consumerTag);
                System.out.println("envelope: " + envelope.getRoutingKey());
                System.out.println("properties: " + properties);
                String bodyMsg = new String(body, "UTF-8");
                System.out.println("body: " + bodyMsg);
            }
        };

        channel.basicConsume("hello_world233",true,consumer);

    }

}
