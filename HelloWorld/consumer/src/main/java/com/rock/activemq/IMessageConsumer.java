package com.rock.activemq;

import javax.jms.JMSException;

/**
 * Created by 何湘红[499233529@qq.com] on 2017/1/2 0002.
 */
public interface IMessageConsumer {
    void receive(String destination) throws JMSException;
}
