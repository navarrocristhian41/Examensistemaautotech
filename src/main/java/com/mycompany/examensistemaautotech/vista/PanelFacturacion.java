/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.vista;

import com.mycompany.examensistemaautotech.controlador.TallerControlador;
import com.mycompany.examensistemaautotech.modelo.Factura;
import com.mycompany.examensistemaautotech.modelo.OrdenTrabajo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Cristhian Navarro
 */


public class PanelFacturacion extends JPanel {
    private TallerControlador controlador;
    private JTable tablaFacturas;
    private DefaultTableModel modeloTabla;
    
    private JComboBox<OrdenTrabajo> comboOrdenes;
    private JTextField campoNumeroFactura;
    private JComboBox<String> comboMetodoPago;
    private JLabel etiquetaSubtotal;
    private JLabel etiquetaTotal;

    public PanelFacturacion(TallerControlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        initComponents();
        actualizarDatos();
    }

    private void initComponents() {
        // Panel Superior
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Módulo de Facturación y Pagos"));

        campoNumeroFactura = new JTextField("FAC-001");
        comboOrdenes = new JComboBox<>();
        comboMetodoPago = new JComboBox<>(new String[]{"Efectivo", "Tarjeta"});
        etiquetaSubtotal = new JLabel("$0.00");
        etiquetaTotal = new JLabel("$0.00");

        panelFormulario.add(new JLabel("Número de Factura:"));
        panelFormulario.add(campoNumeroFactura);
        panelFormulario.add(new JLabel("Seleccionar Orden de Trabajo:"));
        panelFormulario.add(comboOrdenes);
        panelFormulario.add(new JLabel("Método de Pago:"));
        panelFormulario.add(comboMetodoPago);
        panelFormulario.add(new JLabel("Subtotal Estimado:"));
        panelFormulario.add(etiquetaSubtotal);
        panelFormulario.add(new JLabel("Total con Impuesto (ISV):"));
        panelFormulario.add(etiquetaTotal);

        JButton botonFacturar = new JButton("Procesar y Emitir Factura");
        botonFacturar.setBackground(new Color(52, 152, 219));
        botonFacturar.setForeground(Color.WHITE);
        JButton botonLimpiar = new JButton("Actualizar / Limpiar");

        panelFormulario.add(botonFacturar);
        panelFormulario.add(botonLimpiar);

        add(panelFormulario, BorderLayout.NORTH);

        // Panel Central
        modeloTabla = new DefaultTableModel(new Object[]{"N° Factura", "N° Orden", "Cliente", "Subtotal ($)", "Total ($)", "Método de Pago"}, 0);
        tablaFacturas = new JTable(modeloTabla);
        add(new JScrollPane(tablaFacturas), BorderLayout.CENTER);

        // Eventos
        comboOrdenes.addActionListener(e -> {
            OrdenTrabajo ordenSel = (OrdenTrabajo) comboOrdenes.getSelectedItem();
            if (ordenSel != null) {
                double subtotal = ordenSel.getCostoTotal();
                double total = subtotal * 1.15; // Aplicando 15% de ISV
                etiquetaSubtotal.setText(String.format("$%.2f", subtotal));
                etiquetaTotal.setText(String.format("$%.2f", total));
            }
        });

        botonFacturar.addActionListener(e -> {
            try {
                String numFactura = campoNumeroFactura.getText().trim();
                OrdenTrabajo ordenSel = (OrdenTrabajo) comboOrdenes.getSelectedItem();
                String metodoPago = (String) comboMetodoPago.getSelectedItem();

                if (numFactura.isEmpty() || ordenSel == null) {
                    JOptionPane.showMessageDialog(this, "Seleccione una orden y verifique el número de factura.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double subtotal = ordenSel.getCostoTotal();
                

                
                Factura nuevaFactura = new Factura(numFactura, ordenSel, 15.0, metodoPago);
                controlador.registrarFactura(nuevaFactura);
                
                actualizarDatos();
                JOptionPane.showMessageDialog(this, "¡Factura emitida con éxito por un total de: $" + String.format("%.2f", nuevaFactura.getTotalPagar()) + "!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al procesar la factura: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        botonLimpiar.addActionListener(e -> actualizarDatos());
    }

    public void actualizarDatos() {
        comboOrdenes.removeAllItems();
        for (OrdenTrabajo orden : controlador.obtenerListaOrdenes()) {
            comboOrdenes.addItem(orden);
        }

        modeloTabla.setRowCount(0);
        List<Factura> listaFacturas = controlador.obtenerListaFacturas();
        for (Factura factura : listaFacturas) {
            // Sumar subtotales de servicios y repuestos 
            double subtotalCalculado = factura.getSubtotalServicios() + factura.getSubtotalRepuestos();
            
            modeloTabla.addRow(new Object[]{
                factura.getNumeroFactura(),
                factura.getOrdenTrabajo().getNumeroOrden(),
                factura.getOrdenTrabajo().getCliente().getNombreCompleto(),
                subtotalCalculado,
                factura.getTotalPagar(),
                factura.getMetodoPago()
            });
        }
    }
}
