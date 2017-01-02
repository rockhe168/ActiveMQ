package com.rock.activemq;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.usage.SystemUsage;

import javax.jms.*;

/**
 * Created by 何湘红[499233529@qq.com] on 2017/1/2 0002.
 */
public class MessageConsumerImpl implements IMessageConsumer{

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

    public void receive(String destinationName) throws JMSException {
        this.init();

        //创建消息
        Destination destination = session.createQueue(destinationName);
        //创建消息消费者
        MessageConsumer consumer =  session.createConsumer(destination);

        while (true)
        {
            TextMessage textMessage = (TextMessage) consumer.receive(100000);
            if(textMessage !=null)
            {
                System.out.println("收到消息内容为--->"+textMessage.getText());
            }else {
                break;
            }
        }
        this.close();

    }


}
