package com.mientrascompila.pago.proveedor.clients;

import com.mientrascompila.pago.proveedor.rabbitMq.consumer.Consumer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.mientrascompila.rabbitmq")
public class RabbitMqClient {

    public final static String queueName = "demo";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    Queue queue() {
        return new Queue(queueName);
    }



    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange-entrenamiento-topic");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setRoutingKey(queueName);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageConverter(jsonMessageConverter());
        container.setMessageListener(new Consumer());
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return container;
    }


}
