/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.vista;

import com.mycompany.examensistemaautotech.controlador.TallerControlador;
import com.mycompany.examensistemaautotech.modelo.Repuesto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Cristhian Navarro
 */




public class PanelRepuestos extends JPanel {
    private TallerControlador controlador;
    private JTable tablaRepuestos;
    private DefaultTableModel modeloTabla;
    
    private JTextField campoCodigo;
    private JTextField campoNombre;
    private JTextField campoStock;
    private JTextField campoPrecio;
    private JTextField campoStockMinimo;

    public PanelRepuestos(TallerControlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        initComponents();
        actualizarTabla();
    }

    private void initComponents() {
        // Panel Superior
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Inventario de Repuestos"));

        campoCodigo = new JTextField();
        campoNombre = new JTextField();
        campoStock = new JTextField();
        campoPrecio = new JTextField();
        campoStockMinimo = new JTextField();

        panelFormulario.add(new JLabel("Código de Repuesto:"));
        panelFormulario.add(campoCodigo);
        panelFormulario.add(new JLabel("Nombre del Repuesto:"));
        panelFormulario.add(campoNombre);
        panelFormulario.add(new JLabel("Stock Actual:"));
        panelFormulario.add(campoStock);
        panelFormulario.add(new JLabel("Precio Unitario ($):"));
        panelFormulario.add(campoPrecio);
        panelFormulario.add(new JLabel("Stock Mínimo Alerta:"));
        panelFormulario.add(campoStockMinimo);

        JButton botonRegistrar = new JButton("Registrar Repuesto");
        JButton botonLimpiar = new JButton("Limpiar Campos");

        panelFormulario.add(botonRegistrar);
        panelFormulario.add(botonLimpiar);

        add(panelFormulario, BorderLayout.NORTH);

        // Panel Central
        modeloTabla = new DefaultTableModel(new Object[]{"Código", "Nombre", "Stock Actual", "Precio Unitario ($)", "Stock Mínimo"}, 0);
        tablaRepuestos = new JTable(modeloTabla);
        add(new JScrollPane(tablaRepuestos), BorderLayout.CENTER);

        // Eventos
        botonRegistrar.addActionListener(e -> {
            try {
                String codigo = campoCodigo.getText().trim();
                String nombre = campoNombre.getText().trim();
                String stockStr = campoStock.getText().trim();
                String precioStr = campoPrecio.getText().trim();
                String minStr = campoStockMinimo.getText().trim();

                if (codigo.isEmpty() || nombre.isEmpty() || stockStr.isEmpty() || precioStr.isEmpty() || minStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int stock = Integer.parseInt(stockStr);
                double precioUnitario = Double.parseDouble(precioStr);
                int stockMinimo = Integer.parseInt(minStr);

                if (stock < 0 || precioUnitario < 0 || stockMinimo < 0) {
                    JOptionPane.showMessageDialog(this, "Los valores numéricos de stock y precio deben ser positivos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                controlador.registrarRepuesto(codigo, nombre, stock, precioUnitario, stockMinimo);
                actualizarTabla();
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "¡Repuesto registrado con éxito en el inventario!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El stock, precio y stock mínimo deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar repuesto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        botonLimpiar.addActionListener(e -> limpiarCampos());
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
       List<Repuesto> listaRepuestos = controlador.obtenerListaRepuestos();
        for (Repuesto repuesto : listaRepuestos) {
            modeloTabla.addRow(new Object[]{
                repuesto.getCodigoRepuesto(),
                repuesto.getNombre(),
                repuesto.getCantidadStock(),
                repuesto.getPrecioUnitario(),
                repuesto.getStockMinimo()
            });
        }
    }

    private void limpiarCampos() {
        campoCodigo.setText("");
        campoNombre.setText("");
        campoStock.setText("");
        campoPrecio.setText("");
        campoStockMinimo.setText("");
    }
}