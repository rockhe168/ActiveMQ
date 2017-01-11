package com.rock.activemq;

import javax.jms.JMSException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "我是消息订阅者..." );

        String topicName = "HelloTopic";
        ISubscrib subscriber = new Subscriber();

        try {
           subscriber.recive(topicName);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
