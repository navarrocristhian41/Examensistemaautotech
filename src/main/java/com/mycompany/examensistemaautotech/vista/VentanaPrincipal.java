/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.vista;

import com.mycompany.examensistemaautotech.controlador.TallerControlador;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Cristhian Navarro
 */



public class VentanaPrincipal extends JFrame {
    private TallerControlador controlador;
    private JPanel panelContenedor;
    private CardLayout gestorTarjetas;

    public VentanaPrincipal() {
        this.controlador = new TallerControlador();
        
        setTitle("AutoTech Solutions ");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Panel de Navegación Superior
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(50, 50, 50));
        panelMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 10));

        JButton botonClientes = new JButton("Clientes");
        JButton botonVehiculos = new JButton("Vehículos");
        JButton botonServicios = new JButton("Servicios");
        JButton botonRepuestos = new JButton("Repuestos");
        JButton botonOrdenes = new JButton("Órdenes");
        JButton botonFacturacion = new JButton("Facturación");
        JButton botonReportes = new JButton("Reportes Graficos");

        panelMenu.add(botonClientes);
        panelMenu.add(botonVehiculos);
        panelMenu.add(botonServicios);
        panelMenu.add(botonRepuestos);
        panelMenu.add(botonOrdenes);
        panelMenu.add(botonFacturacion);
        panelMenu.add(botonReportes);

        add(panelMenu, BorderLayout.NORTH);

        // Panel Central 
        gestorTarjetas = new CardLayout();
        panelContenedor = new JPanel(gestorTarjetas);

        // INSTANCIAMOS LOS PANELES FUNCIONALES 
        PanelClientes panelClientesVista = new PanelClientes(controlador);
        PanelVehiculos panelVehiculosVista = new PanelVehiculos(controlador);
        PanelServicios panelServiciosVista = new PanelServicios(controlador);
        PanelRepuestos panelRepuestosVista = new PanelRepuestos(controlador);
        PanelFacturacion panelFacturacionVista = new PanelFacturacion(controlador);
        PanelOrdenes panelOrdenesVista = new PanelOrdenes(controlador);
        
        
        
        
        
        CanvasReporte2D panelReportesVista = new CanvasReporte2D(controlador);

        
        panelContenedor.add(panelClientesVista, "CLIENTES");
        panelContenedor.add(panelVehiculosVista, "VEHICULOS");
        panelContenedor.add(panelServiciosVista, "SERVICIOS");
        panelContenedor.add(panelRepuestosVista, "REPUESTOS");
        panelContenedor.add(panelOrdenesVista, "ORDENES");
        panelContenedor.add(panelFacturacionVista, "FACTURACION");
        panelContenedor.add(panelReportesVista, "REPORTES");

        add(panelContenedor, BorderLayout.CENTER);

        // EVENTOS DE NAVEGACIÓN Y ACTUALIZACIÓN 
        botonClientes.addActionListener(e -> {
            panelClientesVista.actualizarTabla();
            gestorTarjetas.show(panelContenedor, "CLIENTES");
        });

        botonVehiculos.addActionListener(e -> {
            panelVehiculosVista.actualizarDatos();
            gestorTarjetas.show(panelContenedor, "VEHICULOS");
        });

        botonServicios.addActionListener(e -> {
            panelServiciosVista.actualizarTabla();
            gestorTarjetas.show(panelContenedor, "SERVICIOS");
        });

        botonOrdenes.addActionListener(e -> {
            panelOrdenesVista.actualizarDatos();
            gestorTarjetas.show(panelContenedor, "ORDENES");
        });

       botonRepuestos.addActionListener(e -> {
    panelRepuestosVista.actualizarTabla();
    gestorTarjetas.show(panelContenedor, "REPUESTOS");
});
        botonFacturacion.addActionListener(e -> {
    panelFacturacionVista.actualizarDatos();
    gestorTarjetas.show(panelContenedor, "FACTURACION");
});
        
        botonReportes.addActionListener(e -> {
            panelReportesVista.repaint();
            gestorTarjetas.show(panelContenedor, "REPORTES");
        });
    }

    private JPanel crearPanelModulo(String titulo) {
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel etiqueta = new JLabel(titulo);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(etiqueta);
        return panel;
    }
}
