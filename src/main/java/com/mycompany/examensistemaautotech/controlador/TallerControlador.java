/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.controlador;

import com.mycompany.examensistemaautotech.modelo.*;
import com.mycompany.examensistemaautotech.repositorio.RepositorioGenerico;
import java.util.List;
/**
 *
 * @author Cristhian Navarro
 */


public class TallerControlador {
    private RepositorioGenerico<Cliente> repositorioClientes;
    private RepositorioGenerico<Vehiculo> repositorioVehiculos;
    private RepositorioGenerico<ServicioMecanico> repositorioServicios;
    private RepositorioGenerico<Repuesto> repositorioRepuestos;
    private RepositorioGenerico<OrdenTrabajo> repositorioOrdenes;
    private RepositorioGenerico<Factura> repositorioFacturas;

    public TallerControlador() {
        this.repositorioClientes = new RepositorioGenerico<>();
        this.repositorioVehiculos = new RepositorioGenerico<>();
        this.repositorioServicios = new RepositorioGenerico<>();
        this.repositorioRepuestos = new RepositorioGenerico<>();
        this.repositorioOrdenes = new RepositorioGenerico<>();
        this.repositorioFacturas = new RepositorioGenerico<>(); 
        
    }


    //   Clientes 
    public void registrarCliente(String codigo, String nombre, String identidad, String telefono, String direccion) {
        Cliente cliente = new Cliente(codigo, nombre, identidad, telefono, direccion);
        repositorioClientes.agregar(codigo, cliente);
    }

    public List<Cliente> obtenerListaClientes() {
        return repositorioClientes.obtenerTodos();
    }

    public Cliente buscarClientePorCodigo(String codigo) {
        return repositorioClientes.buscarPorClave(codigo);
    }

    // Vehículos 
    public void registrarVehiculo(String placa, String marca, String modelo, int anio, String color, Cliente propietario) {
        Vehiculo vehiculo = new Vehiculo(placa, marca, modelo, anio, color, propietario);
        repositorioVehiculos.agregar(placa, vehiculo);
    }

    public List<Vehiculo> obtenerListaVehiculos() {
        return repositorioVehiculos.obtenerTodos();
    }

    public Vehiculo buscarVehiculoPorPlaca(String placa) {
        return repositorioVehiculos.buscarPorClave(placa);
    }

    //  Servicios 
    public void registrarServicio(String codigo, String descripcion, double precioBase, int tiempoMinutos) {
        ServicioMecanico servicio = new ServicioMecanico(codigo, descripcion, precioBase, tiempoMinutos);
        repositorioServicios.agregar(codigo, servicio);
    }

    public List<ServicioMecanico> obtenerListaServicios() {
        return repositorioServicios.obtenerTodos();
    }

    public ServicioMecanico buscarServicioPorCodigo(String codigo) {
        return repositorioServicios.buscarPorClave(codigo);
    }

    //  Repuestos 
    public void registrarRepuesto(String codigo, String nombre, int stock, double precioUnitario, int stockMinimo) {
        Repuesto repuesto = new Repuesto(codigo, nombre, stock, precioUnitario, stockMinimo);
        repositorioRepuestos.agregar(codigo, repuesto);
    }

    public List<Repuesto> obtenerListaRepuestos() {
        return repositorioRepuestos.obtenerTodos();
    }

    public Repuesto buscarRepuestoPorCodigo(String codigo) {
        return repositorioRepuestos.buscarPorClave(codigo);
    }

    //  Trabajo 
    public void registrarOrdenTrabajo(OrdenTrabajo orden) {
        repositorioOrdenes.agregar(orden.getNumeroOrden(), orden);
    }

    public List<OrdenTrabajo> obtenerListaOrdenes() {
        return repositorioOrdenes.obtenerTodos();
    }

    public OrdenTrabajo buscarOrdenPorNumero(String numeroOrden) {
        return repositorioOrdenes.buscarPorClave(numeroOrden);
    }

    // Facturación 
    public void registrarFactura(Factura factura) {
        repositorioFacturas.agregar(factura.getNumeroFactura(), factura);
    }

    public List<Factura> obtenerListaFacturas() {
        return repositorioFacturas.obtenerTodos();
    }
}