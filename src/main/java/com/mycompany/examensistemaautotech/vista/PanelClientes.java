/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.vista;

import com.mycompany.examensistemaautotech.controlador.TallerControlador;
import com.mycompany.examensistemaautotech.modelo.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Cristhian Navarro
 */


public class PanelClientes extends JPanel {
    private TallerControlador controlador;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    
    private JTextField campoCodigo;
    private JTextField campoNombre;
    private JTextField campoIdentidad;
    private JTextField campoTelefono;
    private JTextField campoDireccion;

    public PanelClientes(TallerControlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        initComponents();
        actualizarTabla();
    }

    private void initComponents() {
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registro de Clientes"));

        campoCodigo = new JTextField();
        campoNombre = new JTextField();
        campoIdentidad = new JTextField();
        campoTelefono = new JTextField();
        campoDireccion = new JTextField();

        panelFormulario.add(new JLabel("Código Único:"));
        panelFormulario.add(campoCodigo);
        panelFormulario.add(new JLabel("Nombre Completo:"));
        panelFormulario.add(campoNombre);
        panelFormulario.add(new JLabel("Identidad:"));
        panelFormulario.add(campoIdentidad);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(campoTelefono);
        panelFormulario.add(new JLabel("Dirección:"));
        panelFormulario.add(campoDireccion);

        JButton botonRegistrar = new JButton("Registrar Cliente");
        JButton botonLimpiar = new JButton("Limpiar Campos");
        
        panelFormulario.add(botonRegistrar);
        panelFormulario.add(botonLimpiar);

        add(panelFormulario, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new Object[]{"Código", "Nombre Completo", "Identidad", "Teléfono", "Dirección"}, 0);
        tablaClientes = new JTable(modeloTabla);
        add(new JScrollPane(tablaClientes), BorderLayout.CENTER);

        botonRegistrar.addActionListener(e -> {
            try {
                String codigo = campoCodigo.getText().trim();
                String nombre = campoNombre.getText().trim();
                String identidad = campoIdentidad.getText().trim();
                String telefono = campoTelefono.getText().trim();
                String direccion = campoDireccion.getText().trim();

                if (codigo.isEmpty() || nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El código y el nombre son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                controlador.registrarCliente(codigo, nombre, identidad, telefono, direccion);
                actualizarTabla();
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "¡Cliente registrado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        botonLimpiar.addActionListener(e -> limpiarCampos());
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        List<Cliente> lista = controlador.obtenerListaClientes();
        for (Cliente cliente : lista) {
            modeloTabla.addRow(new Object[]{
                cliente.getCodigoUnico(),
                cliente.getNombreCompleto(),
                cliente.getIdentidad(),
                cliente.getTelefono(),
                cliente.getDireccion()
            });
        }
    }

    private void limpiarCampos() {
        campoCodigo.setText("");
        campoNombre.setText("");
        campoIdentidad.setText("");
        campoTelefono.setText("");
        campoDireccion.setText("");
    }
}
