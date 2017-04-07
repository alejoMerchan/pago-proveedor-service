package com.mientrascompila.pago.proveedor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by abelmeos on 2017/04/07.
 */
@Component
public class ServiceConfig {

    @Value("${example.property}")
    private String propiedadEjemplo;

    public String getPropiedadEjemplo(){
        return propiedadEjemplo;
    }

}
