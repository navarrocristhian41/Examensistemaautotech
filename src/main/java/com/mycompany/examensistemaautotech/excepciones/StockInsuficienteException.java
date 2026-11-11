/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.excepciones;

/**
 *
 * @author Cristhian Navarro
 */

public class StockInsuficienteException extends Exception {
    private int stockDisponible;
    private int cantidadRequerida;

    public StockInsuficienteException(String mensaje, int stockDisponible, int cantidadRequerida) {
        super(mensaje);
        this.stockDisponible = stockDisponible;
        this.cantidadRequerida = cantidadRequerida;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public int getCantidadRequerida() {
        return cantidadRequerida;
    }
}
