/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.modelo;

/**
 *
 * @author Cristhian Navarro
 */


public class Repuesto {
    private String codigoRepuesto;
    private String nombre;
    private int cantidadStock;
    private double precioUnitario;
    private int stockMinimo;

    public Repuesto(String codigoRepuesto, String nombre, int cantidadStock, double precioUnitario, int stockMinimo) {
        this.codigoRepuesto = codigoRepuesto;
        this.nombre = nombre;
        setCantidadStock(cantidadStock);
        this.precioUnitario = precioUnitario;
        this.stockMinimo = stockMinimo;
    }

    public String getCodigoRepuesto() { return codigoRepuesto; }
    public void setCodigoRepuesto(String codigoRepuesto) { this.codigoRepuesto = codigoRepuesto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCantidadStock() { return cantidadStock; }
    public void setCantidadStock(int cantidadStock) {
        if (cantidadStock < 0) {
            throw new IllegalArgumentException("La cantidad en stock no puede ser negativa.");
        }
        this.cantidadStock = cantidadStock;
    }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }

    @Override
    public String toString() {
        return "[" + codigoRepuesto + "] " + nombre + " - Stock: " + cantidadStock + " ($" + precioUnitario + ")";
    }
}
