package com.mientrascompila.pago.proveedor.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by abelmeos on 2017/04/04.
 */

@RestController
@RequestMapping(value = "v1/proveedor/{proveedorId}/pago")
public class PagoProveedorServiceController {

    @RequestMapping(value = "/{pagoId}",method = RequestMethod.GET)
    public String getProveedor( @PathVariable("proveedorId")String proveedorId,@PathVariable("pagoId") String pagoId){
        return "Se obtiene el pago: " + pagoId + "del proveedor: " +proveedorId;
    }

}
