/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.modelo;

/**
 *
 * @author Cristhian Navarro
 */


public class Factura {
    private String numeroFactura;
    private OrdenTrabajo ordenTrabajo;
    private double subtotalServicios;
    private double subtotalRepuestos;
    private double impuestoPorcentaje;
    private double valorImpuesto;
    private double totalPagar;
    private String metodoPago;

    public Factura(String numeroFactura, OrdenTrabajo ordenTrabajo, double impuestoPorcentaje, String metodoPago) {
        this.numeroFactura = numeroFactura;
        this.ordenTrabajo = ordenTrabajo;
        this.metodoPago = metodoPago;
        this.impuestoPorcentaje = impuestoPorcentaje;
        calcularTotales();
    }

    private void calcularTotales() {
        subtotalServicios = 0;
        for (ServicioMecanico servicio : ordenTrabajo.getListaServicios()) {
            subtotalServicios += servicio.getPrecioBase();
        }

        subtotalRepuestos = 0;
        for (Repuesto repuesto : ordenTrabajo.getListaRepuestos()) {
            subtotalRepuestos += repuesto.getPrecioUnitario();
        }

        double subtotalGeneral = subtotalServicios + subtotalRepuestos;
        valorImpuesto = subtotalGeneral * (impuestoPorcentaje / 100.0);
        totalPagar = subtotalGeneral + valorImpuesto;
    }

    public String getNumeroFactura() {
        return numeroFactura; }
    public OrdenTrabajo getOrdenTrabajo() {
        return ordenTrabajo; }
    
    public double getSubtotalServicios() { 
        return subtotalServicios; }
    
    public double getSubtotalRepuestos() {
        return subtotalRepuestos; }
    
    public double getValorImpuesto() {
        return valorImpuesto; }
    
    public double getTotalPagar() { 
        return totalPagar; }
    
    public String getMetodoPago() {
        return metodoPago;
    }
}
