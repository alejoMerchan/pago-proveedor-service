package com.mientrascompila.pago.proveedor.services;


import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.util.logging.Logger;

public class Consumer implements MessageListener {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(Consumer.class);

    @Override
    public void onMessage(Message message){
        String parseMessage = new String(message.getBody());

    }
}
