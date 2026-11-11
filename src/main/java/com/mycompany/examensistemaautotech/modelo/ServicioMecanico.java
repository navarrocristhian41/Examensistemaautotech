/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.modelo;

/**
 *
 * @author Cristhian Navarro
 */


public class ServicioMecanico {
    private String codigoServicio;
    private String descripcion;
    private double precioBase;
    private int tiempoEstimadoMinutos;

    public ServicioMecanico(String codigoServicio, String descripcion, double precioBase, int tiempoEstimadoMinutos) {
        this.codigoServicio = codigoServicio;
        this.descripcion = descripcion;
        setPrecioBase(precioBase);
        this.tiempoEstimadoMinutos = tiempoEstimadoMinutos;
    }

    public String getCodigoServicio() { return codigoServicio; }
    public void setCodigoServicio(String codigoServicio) { this.codigoServicio = codigoServicio; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) {
        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio base del servicio debe ser positivo.");
        }
        this.precioBase = precioBase;
    }

    public int getTiempoEstimadoMinutos() { return tiempoEstimadoMinutos; }
    public void setTiempoEstimadoMinutos(int tiempoEstimadoMinutos) { this.tiempoEstimadoMinutos = tiempoEstimadoMinutos; }

    @Override
    public String toString() {
        return "[" + codigoServicio + "] " + descripcion + " - $" + precioBase;
    }
}
