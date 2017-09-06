package com.mientrascompila.pago.proveedor.controllers;


import com.mientrascompila.pago.proveedor.message.PagoRequest;
import com.mientrascompila.pago.proveedor.rabbitMq.producer.Producer;
import com.mientrascompila.pago.proveedor.services.PagoProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * Created by abelmeos on 2017/04/04.
 */

@RestController
@RequestMapping(value = "v1/proveedor/{proveedorId}/pago")
public class PagoProveedorServiceController {


    Producer producer = new Producer();

    @Autowired
    PagoProveedorService pagoProveedorService;


    @RequestMapping(value = "/{pagoId}",method = RequestMethod.GET)
    public String getProveedor( @PathVariable("proveedorId")String proveedorId,@PathVariable("pagoId") String pagoId){
        producer.sendMessage("hola mundo");
        return "Se obtiene el pago: " + pagoId + "del proveedor: " +proveedorId;
    }

    @RequestMapping(value = "/pago", method = RequestMethod.POST)
    public ResponseEntity<?> realizarPago(@RequestBody PagoRequest pagoRequest){


        pagoProveedorService.realizarPago(pagoRequest.getId());

        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/pagoAsin", method = RequestMethod.POST)
    public void realizarPagoAsincrono(@RequestBody final PagoRequest pagoRequest){

        pagoProveedorService.realizarPagoAsincrono(pagoRequest.getId());

    }




}
