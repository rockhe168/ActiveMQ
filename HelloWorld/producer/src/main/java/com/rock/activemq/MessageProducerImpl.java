package com.rock.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2017/1/2 0002.
 */
public class MessageProducerImpl implements  IMessageProducer {

    //默认的链接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认的链接秘密
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认的链接地址
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

    //连接工厂
    ConnectionFactory connectionFactory;
    //链接
    Connection connection;
    //会话（接收或者发送消息的线程）
    Session session;

    private void init() throws JMSException {

        System.out.println("broker Url ---------->"+BROKEURL);

        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);

        try{
            //通过工厂创建一个链接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建session
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        } catch (JMSException e) {
            e.printStackTrace();
            if(connection !=null)
            {
                connection.close();
            }
        }
    }

    private void close() throws JMSException {
        if(connection !=null)
        {
            connection.close();
        }
    }

    public void sendMessage(String destinationName,String message) throws JMSException {
        //初始化链接
        this.init();
        //消息目的地
        Destination destination = session.createQueue(destinationName);
        //创建一个消息生产者
        MessageProducer producer =  session.createProducer(destination);
        //创建一条文本消息
         TextMessage textMessage = session.createTextMessage(message);
        //通过消息生产者进行发送
         producer.send(textMessage);

         System.out.println("发送消息：消息内容为:"+message);
        //关闭链接
        this.close();
    }
}
