package com.mientrascompila.pago.proveedor.services;


import com.mientrascompila.pago.proveedor.rabbitMq.producer.Producer;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class PagoProveedorService {

    Producer producer = new Producer();

    /**
     * Metodo encargado de realizar el proceso de pago  de forma sincrona y bloqueante.
     * @param idProveedor
     */
    public void realizarPago(String idProveedor){

        // se ejecuta el proceso de negocio para realizar pagos.
        Integer idPago = procesoRealizarPago(idProveedor);
        validarPoliticaPago(idPago);

    }

    /**
     * Metodo encargado de realizar el proceso de pago simulando el llamado a bd, procedimiento o cualquier
     * servicio externo (side effect).
     *
     * Presenta throughput de  3 segundos
     * @param id
     * @return
     */
    private Integer  procesoRealizarPago(String id){

        Random rnd = new Random();
        try{
            Thread.sleep(3000);

        }catch (Exception e){
            e.printStackTrace();
        }
        return rnd.nextInt();

    }

    /**
     * Metodo encargado de realizar el proceso de pago de forma asincrona.
     * @param idProveedor
     */
    public void realizarPagoAsincrono(String idProveedor){

        procesoRealizarPagoAsin(idProveedor).thenAccept(x -> validarPoliticaPago(x));
    }

    /**
     * Metodo encargado de realizar el proceso de pago simulando el llamado a bd, procedimiento o cualquier
     * servicio externo (side effect).
     *
     * Maneja el side effect mediante un futuro, permitiendo liberar el hilo que hace el llamado mientrsa completa
     * el proceso asincronamente.
     *
     * @param id
     * @return
     */
    private CompletableFuture<Integer>   procesoRealizarPagoAsin(String id){

        return CompletableFuture.supplyAsync(() -> {
            Random rnd = new Random();
            try{
                Thread.sleep(3000);

            }catch (Exception e){
                e.printStackTrace();
            }
            return rnd.nextInt();
        });

    }


    private void validarPoliticaPago(Integer id) {
        producer.sendMessage(id.toString());
    }


}
