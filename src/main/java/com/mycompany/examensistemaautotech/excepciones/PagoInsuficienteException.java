/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.excepciones;

/**
 *
 * @author Cristhian Navarro
 */
public class PagoInsuficienteException extends Exception {
    private double montoFaltante;

    public PagoInsuficienteException(String mensaje, double montoFaltante) {
        super(mensaje);
        this.montoFaltante = montoFaltante;
    }

    public double getMontoFaltante() {
        return montoFaltante;
    }
}
