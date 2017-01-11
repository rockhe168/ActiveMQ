package com.rock.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by xhhe on 2017/1/9.
 */
public class Subscriber implements ISubscrib {
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
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

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

    public void recive(String topicName) throws JMSException {

        this.init();

        //4.创建要发布的主题，和Queue 区别就在此
        Destination destination = session.createTopic(topicName);

        //5. MessageConsumer 消息接收者
        MessageConsumer consumer = session.createConsumer(destination);

        //6.发送消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    String msg =  textMessage.getText();
                    System.out.println("订阅者，收到的消息是:"+msg);

                }catch (JMSException e)
                {
                    e.printStackTrace();
                }
            }
        });

        //关闭会话
        this.close();

    }

}
