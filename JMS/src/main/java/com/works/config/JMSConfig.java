package com.works.config;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;


import com.works.jms.MessageConsumer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

@Configuration
@PropertySource("classpath:application.properties")
public class JMSConfig {

    @Value("${jms.broker.url}")
    private String brokerUrl;

    @Value("${jms.queue.name}")
    private String queueName;

    final MessageConsumer messageConsumer;
    public JMSConfig(MessageConsumer messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    @Bean
    public BrokerService broker() throws Exception {
        BrokerService broker = new BrokerService();
        broker.addConnector(brokerUrl);
        broker.setPersistent(false);
        return broker;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(new ActiveMQConnectionFactory(brokerUrl));
    }

    @Bean
    public Destination destination() {
        return new ActiveMQQueue(queueName);
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, Destination destination) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestination(destination);

        return jmsTemplate;
    }

    @Bean
    public MessageListenerContainer defaultMessageListenerContainer(ConnectionFactory connectionFactory,
                                                                    Destination destination) {
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
        defaultMessageListenerContainer.setDestination(destination);
        defaultMessageListenerContainer.setMessageListener(messageConsumer);

        return defaultMessageListenerContainer;
    }


}
