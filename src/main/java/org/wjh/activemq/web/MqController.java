package org.wjh.activemq.web;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wjh.activemq.jms.MqSender;

@Controller
public class MqController {

    @Autowired
    private MqSender mqSender;

    @ResponseBody
    @RequestMapping("/mq/send.do")
    public Object sen() {
        String uuid = UUID.randomUUID().toString();
        mqSender.send(uuid);
        return uuid;
    }
}
