package com.mientrascompila.pago.proveedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * Clase que inicializa el servicio.
 *
 * Created by abelmeos on 2017/04/04.
 */
@EnableAutoConfiguration
@SpringBootApplication
@RefreshScope
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

}
