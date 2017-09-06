package com.mientrascompila.pago.proveedor.rabbitMq.producer;


import com.mientrascompila.pago.proveedor.clients.RabbitMqClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class Producer {

    ApplicationContext ctx = new AnnotationConfigApplicationContext(RabbitMqClient.class);
    RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);

    public void sendMessage(String mensaje){

        CompletableFuture.runAsync(() -> rabbitTemplate.convertAndSend(RabbitMqClient.queueName, mensaje));


    }
}
