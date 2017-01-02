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
        System.out.println( "我是消费者..." );
        IMessageConsumer consumer = new MessageConsumerImpl();
        try{
            consumer.receive(destinationName);
        } catch (JMSException e) {
            System.out.println("消费失败-->");
            e.printStackTrace();
        }
    }
}
