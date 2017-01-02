package com.rock.activemq;

import javax.jms.JMSException;

/**
 * Created by Administrator on 2017/1/2 0002.
 */
public interface IMessageProducer {
    void sendMessage(String destinationName,String Message) throws JMSException;
}
