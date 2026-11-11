/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.hilos;
import com.mycompany.examensistemaautotech.modelo.OrdenTrabajo;
/**
 *
 * @author Cristhian Navarro
 */


public class ProcesoServicioHilo implements Runnable {
    private OrdenTrabajo ordenTrabajo;
    private ProcesoServicioListener listener;

    
    public interface ProcesoServicioListener {
        void onInicio(String mensaje);
        void onProgreso(int porcentaje, String mensaje);
        void onExito(String mensaje);
        void onError(String mensajeError);
    }

    public ProcesoServicioHilo(OrdenTrabajo ordenTrabajo, ProcesoServicioListener listener) {
        this.ordenTrabajo = ordenTrabajo;
        this.listener = listener;
    }

    @Override
    public void run() {
        listener.onInicio("Iniciando procesamiento de la Orden #" + ordenTrabajo.getNumeroOrden() + "...");
        
        try {
            
            for (int i = 1; i <= 5; i++) {
                Thread.sleep(1000); 
                int porcentajeProgreso = i * 20; 
                listener.onProgreso(porcentajeProgreso, "Ejecutando servicios y aplicando repuestos... " + porcentajeProgreso + "%");
            }

           
            ordenTrabajo.setEstado("Finalizada");
            listener.onExito("¡Orden #" + ordenTrabajo.getNumeroOrden() + " procesada y finalizada exitosamente!");

        } catch (InterruptedException excepcion) {
            listener.onError("El proceso concurrente de la orden fue interrumpido.");
        }
    }
}
