package com.rock.activemq;

import javax.jms.JMSException;

/**
 * Hello world!
 *
 */
public class App
{

    private static  final String destinationName = "HelloWorld";

    public static void main( String[] args )
    {
        System.out.println( "我是消息生产者..." );

        IMessageProducer messageProducer = new MessageProducerImpl();
        try {
            messageProducer.sendMessage(destinationName,"消息生产者生产的消息1");
            messageProducer.sendMessage(destinationName,"消息生产者生产的消息2");
        }catch (JMSException ex)
        {
            System.out.print("消息生产失败-->"+ex.getStackTrace());
        }


    }
}
