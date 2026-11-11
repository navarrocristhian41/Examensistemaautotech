/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.vista;

import com.mycompany.examensistemaautotech.controlador.TallerControlador;
import com.mycompany.examensistemaautotech.hilos.ProcesoServicioHilo;
import com.mycompany.examensistemaautotech.modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Cristhian Navarro
 */


public class PanelOrdenes extends JPanel {
    private TallerControlador controlador;
    private JTable tablaOrdenes;
    private DefaultTableModel modeloTabla;
    
    private JTextField campoNumeroOrden;
    private JTextField campoFecha;
    private JTextField campoDiagnostico;
    private JTextField campoMecanico;
    private JComboBox<Cliente> comboClientes;
    private JComboBox<Vehiculo> comboVehiculos;
    private JComboBox<ServicioMecanico> comboServicios;
    private JComboBox<Repuesto> comboRepuestos;
    
    private JProgressBar barraProgreso;
    private JLabel etiquetaEstadoHilo;

    public PanelOrdenes(TallerControlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        initComponents();
        actualizarDatos();
    }

    private void initComponents() {
        JPanel panelFormulario = new JPanel(new GridLayout(8, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Panel Órdenes de Trabajo"));

        campoNumeroOrden = new JTextField("OXL-100");
        campoFecha = new JTextField("2026-10-24");
        campoDiagnostico = new JTextField("Mantenimiento preventivo");
        campoMecanico = new JTextField("Alexis Mecánico Amante");
        
        comboClientes = new JComboBox<>();
        comboVehiculos = new JComboBox<>();
        comboServicios = new JComboBox<>();
        comboRepuestos = new JComboBox<>();

        panelFormulario.add(new JLabel("Número de Orden:"));
        panelFormulario.add(campoNumeroOrden);
        panelFormulario.add(new JLabel("Fecha:"));
        panelFormulario.add(campoFecha);
        panelFormulario.add(new JLabel("Cliente:"));
        panelFormulario.add(comboClientes);
        panelFormulario.add(new JLabel("Vehículo:"));
        panelFormulario.add(comboVehiculos);
        panelFormulario.add(new JLabel("Servicio Principal:"));
        panelFormulario.add(comboServicios);
        panelFormulario.add(new JLabel("Repuesto Principal:"));
        panelFormulario.add(comboRepuestos);
        panelFormulario.add(new JLabel("Diagnóstico:"));
        panelFormulario.add(campoDiagnostico);
        panelFormulario.add(new JLabel("Mecánico Asignado:"));
        panelFormulario.add(campoMecanico);

        add(panelFormulario, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new BorderLayout(3, 3));
        
        modeloTabla = new DefaultTableModel(new Object[]{"N° Orden", "Fecha", "Cliente", "Vehículo", "Mecánico", "Total ($)", "Estado"}, 0);
        tablaOrdenes = new JTable(modeloTabla);
        panelCentral.add(new JScrollPane(tablaOrdenes), BorderLayout.CENTER);

        JPanel panelInferiorControlHilos = new JPanel(new BorderLayout(3, 3));
        panelInferiorControlHilos.setBorder(BorderFactory.createTitledBorder("Procesamiento estimado 5 segundos"));

        barraProgreso = new JProgressBar(10, 100);
        barraProgreso.setStringPainted(true);
        etiquetaEstadoHilo = new JLabel("Estado del Proceso: En espera...", JLabel.CENTER);

        JButton botonCrearYProcesar = new JButton("Crear y Procesar Orden ");
        botonCrearYProcesar.setBackground(new Color(46, 104, 213));
        botonCrearYProcesar.setForeground(Color.WHITE);

        JPanel panelBotonesHilos = new JPanel(new GridLayout(1, 1));
        panelBotonesHilos.add(botonCrearYProcesar);

        panelInferiorControlHilos.add(panelBotonesHilos, BorderLayout.NORTH);
        panelInferiorControlHilos.add(barraProgreso, BorderLayout.CENTER);
        panelInferiorControlHilos.add(etiquetaEstadoHilo, BorderLayout.SOUTH);

        panelCentral.add(panelInferiorControlHilos, BorderLayout.SOUTH);
        add(panelCentral, BorderLayout.CENTER);

        botonCrearYProcesar.addActionListener(e -> {
            try {
                String numeroOrden = campoNumeroOrden.getText().trim();
                String fecha = campoFecha.getText().trim();
                Cliente cliente = (Cliente) comboClientes.getSelectedItem();
                Vehiculo vehiculo = (Vehiculo) comboVehiculos.getSelectedItem();
                String diagnostico = campoDiagnostico.getText().trim();
                String mecanico = campoMecanico.getText().trim();
                ServicioMecanico servicioSel = (ServicioMecanico) comboServicios.getSelectedItem();
                Repuesto repuestoSel = (Repuesto) comboRepuestos.getSelectedItem();

                if (numeroOrden.isEmpty() || cliente == null || vehiculo == null || servicioSel == null) {
                    JOptionPane.showMessageDialog(this, "Complete los campos obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                List<ServicioMecanico> listaServicios = new ArrayList<>();
                listaServicios.add(servicioSel);

                List<Repuesto> listaRepuestos = new ArrayList<>();
                if (repuestoSel != null) {
                    listaRepuestos.add(repuestoSel);
                }

                OrdenTrabajo nuevaOrden = new OrdenTrabajo(numeroOrden, fecha, cliente, vehiculo, diagnostico, mecanico, listaServicios, listaRepuestos, "Pendiente");
                controlador.registrarOrdenTrabajo(nuevaOrden);
                actualizarTabla();

                botonCrearYProcesar.setEnabled(false);
                barraProgreso.setValue(0);

                ProcesoServicioHilo tareaHilo = new ProcesoServicioHilo(nuevaOrden, new ProcesoServicioHilo.ProcesoServicioListener() {
                    @Override
                    public void onInicio(String mensaje) {
                        SwingUtilities.invokeLater(() -> etiquetaEstadoHilo.setText(mensaje));
                    }

                    @Override
                    public void onProgreso(int porcentaje, String mensaje) {
                        SwingUtilities.invokeLater(() -> {
                            barraProgreso.setValue(porcentaje);
                            etiquetaEstadoHilo.setText(mensaje);
                        });
                    }

                    @Override
                    public void onExito(String mensaje) {
                        SwingUtilities.invokeLater(() -> {
                            etiquetaEstadoHilo.setText(mensaje);
                            actualizarTabla();
                            botonCrearYProcesar.setEnabled(true);
                            JOptionPane.showMessageDialog(PanelOrdenes.this, mensaje, "Proceso Finalizado", JOptionPane.INFORMATION_MESSAGE);
                        });
                    }

                    @Override
                    public void onError(String mensajeError) {
                        SwingUtilities.invokeLater(() -> {
                            etiquetaEstadoHilo.setText(mensajeError);
                            botonCrearYProcesar.setEnabled(true);
                            JOptionPane.showMessageDialog(PanelOrdenes.this, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
                        });
                    }
                });

                new Thread(tareaHilo).start();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                botonCrearYProcesar.setEnabled(true);
            }
        });
    }

    public void actualizarDatos() {
        comboClientes.removeAllItems();
        for (Cliente cliente : controlador.obtenerListaClientes()) {
            comboClientes.addItem(cliente);
        }

        comboVehiculos.removeAllItems();
        for (Vehiculo vehiculo : controlador.obtenerListaVehiculos()) {
            comboVehiculos.addItem(vehiculo);
        }

        comboServicios.removeAllItems();
        for (ServicioMecanico servicio : controlador.obtenerListaServicios()) {
            comboServicios.addItem(servicio);
        }

        comboRepuestos.removeAllItems();
        for (Repuesto repuesto : controlador.obtenerListaRepuestos()) {
            comboRepuestos.addItem(repuesto);
        }

        actualizarTabla();
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (OrdenTrabajo orden : controlador.obtenerListaOrdenes()) {
            modeloTabla.addRow(new Object[]{
                orden.getNumeroOrden(),
                orden.getFecha(),
                orden.getCliente().getNombreCompleto(),
                orden.getVehiculo().getPlaca(),
                orden.getMecanicoAsignado(),
                orden.getCostoTotal(),
                orden.getEstado()
            });
        }
    }
}
