/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.modelo;

/**
 *
 * @author Cristhian Navarro
 */


public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int anio;
    private String color;
    private Cliente propietario;

    public Vehiculo(String placa, String marca, String modelo, int anio, String color, Cliente propietario) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
        this.propietario = propietario;
    }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public Cliente getPropietario() { return propietario; }
    public void setPropietario(Cliente propietario) { this.propietario = propietario; }

    @Override
    public String toString() {
        return "[" + placa + "] " + marca + " " + modelo + " (" + anio + ") - Propietario: " + propietario.getNombreCompleto();
    }
}
