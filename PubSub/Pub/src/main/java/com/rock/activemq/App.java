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

        System.out.println( "我是消息发布者...." );

        IPublish publisher = new Publisher();

        String topicName = "HelloTopic";

        try {

            for (int i=0;i<=10;i++)
            {
                publisher.sendMessage(topicName,"我是发布者发布的信息，代号为..."+i);
            }


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
