package com.works.jms;

import com.google.gson.Gson;
import com.works.props.JmsData;
import org.springframework.context.annotation.Configuration;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Configuration
public class MessageConsumer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if ( message instanceof TextMessage) {
            try {
                String msg = ((TextMessage) message).getText();
                Gson gson = new Gson();
                JmsData obj = gson.fromJson(msg, JmsData.class);
                System.out.println( obj.getId() );
                System.out.println( obj.getName() );
                System.out.println( obj.getMessage() );
            }catch (Exception ex) {
                System.err.println("onMessage Error  : " + ex);
            }
        }
    }

}
