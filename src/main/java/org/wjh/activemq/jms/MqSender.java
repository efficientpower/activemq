package org.wjh.activemq.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MqSender {
    private static final Log logger = LogFactory.getLog(MqSender.class);
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    @Qualifier("mqQueue")
    private Destination destination;

    public void send(final String msg) {
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) {
                ObjectMessage message = null;
                try {
                    message = session.createObjectMessage();
                    message.setObject(msg);
                } catch (JMSException e) {
                    logger.error("初始化jms失败", e);
                }
                return (Message) message;
            }
        };
        jmsTemplate.send(this.destination, messageCreator);
        logger.info("send msg =" + msg);
    }
}
