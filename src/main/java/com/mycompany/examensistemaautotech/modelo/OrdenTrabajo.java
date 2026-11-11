/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.modelo;
import java.util.List;
/**
 *
 * @author Cristhian Navarro
 */


public class OrdenTrabajo {
    private String numeroOrden;
    private String fecha;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private String diagnostico;
    private String mecanicoAsignado;
    private List<ServicioMecanico> listaServicios;
    private List<Repuesto> listaRepuestos;
    private double costoTotal;
    private String estado; 

    public OrdenTrabajo(String numeroOrden, String fecha, Cliente cliente, Vehiculo vehiculo, String diagnostico, String mecanicoAsignado, List<ServicioMecanico> listaServicios, List<Repuesto> listaRepuestos, String estado) {
        this.numeroOrden = numeroOrden;
        this.fecha = fecha;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.diagnostico = diagnostico;
        this.mecanicoAsignado = mecanicoAsignado;
        this.listaServicios = listaServicios;
        this.listaRepuestos = listaRepuestos;
        this.estado = estado;
        this.costoTotal = calcularCostoTotal();
    }

    public double calcularCostoTotal() {
        double total = 0;
        for (ServicioMecanico servicio : listaServicios) {
            total += servicio.getPrecioBase();
        }
        for (Repuesto repuesto : listaRepuestos) {
            total += repuesto.getPrecioUnitario();
        }
        return total;
    }

    public String getNumeroOrden() { return numeroOrden; }
    public String getFecha() { return fecha; }
    public Cliente getCliente() { return cliente; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public String getDiagnostico() { return diagnostico; }
    public String getMecanicoAsignado() { return mecanicoAsignado; }
    public List<ServicioMecanico> getListaServicios() { return listaServicios; }
    public List<Repuesto> getListaRepuestos() { return listaRepuestos; }
    public double getCostoTotal() { return costoTotal; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Orden #" + numeroOrden + " - Estado: " + estado + " - Total: $" + costoTotal;
    }
}