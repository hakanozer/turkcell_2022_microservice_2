package com.works.restcontrollers;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendRestController {

    final JmsTemplate jmsTemplate;
    public SendRestController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("/message")
    public String message() {
        jmsTemplate.convertAndSend("new message send");
        return "";
    }

}
