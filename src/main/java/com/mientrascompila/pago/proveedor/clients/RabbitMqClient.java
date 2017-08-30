package com.mientrascompila.pago.proveedor.clients;




import com.rabbitmq.client.Consumer;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;

/**
 * Implementacion de cliente RabbitMq.
 */
public class RabbitMqClient {

    public final static String publishQname = "";
    public final static String consumeQname = "";

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(){

        Jackson2JsonMessageConverter con = new Jackson2JsonMessageConverter();
        return con;

    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate();
        template.setExchange("");
        template.setQueue(publishQname);
        template.setMessageConverter(jsonMessageConverter());
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(consumeQname);
        container.setMessageConverter(jsonMessageConverter());
        container.setMessageListener(consumer());

        return container;
    }

    @Bean MessageListener consumer(){
        return new Consumer();
    }

}
