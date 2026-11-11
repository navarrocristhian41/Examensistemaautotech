/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.modelo;

/**
 *
 * @author Cristhian Navarro
 */


public class Cliente {
    private String codigoUnico;
    private String nombreCompleto;
    private String identidad;
    private String telefono;
    private String direccion;

    public Cliente(String codigoUnico, String nombreCompleto, String identidad, String telefono, String direccion) {
        this.codigoUnico = codigoUnico;
        this.nombreCompleto = nombreCompleto;
        this.identidad = identidad;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getCodigoUnico() { return codigoUnico; }
    public void setCodigoUnico(String codigoUnico) { this.codigoUnico = codigoUnico; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getIdentidad() { return identidad; }
    public void setIdentidad(String identidad) { this.identidad = identidad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    @Override
    public String toString() {
        return "[" + codigoUnico + "] " + nombreCompleto + " - Tel: " + telefono;
    }
}
