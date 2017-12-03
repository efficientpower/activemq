package org.wjh.activemq.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class MqMessageListener implements MessageListener {
    private static final Log logger = LogFactory.getLog(MqMessageListener.class);

    public void onMessage(Message msg) {
        // TODO Auto-generated method stub
        ObjectMessage objMsg = (ObjectMessage) msg;
        try {
            String ip = (String) objMsg.getObject();
            logger.info("receive msg = " + ip);
        } catch (JMSException e) {
            logger.error("获取消息内容时出错", e);
        } catch (Exception e) {
            logger.error("MqMessageListener出错", e);
        }
    }

}
