package com.rock.activemq;

import javax.jms.JMSException;

/**
 * Created by xhhe on 2017/1/9.
 */
public interface IPublish {

    //
    void sendMessage(String topicName,String message) throws JMSException;

}
