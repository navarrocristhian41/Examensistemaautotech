/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.vista;

import com.mycompany.examensistemaautotech.controlador.TallerControlador;
import com.mycompany.examensistemaautotech.modelo.ServicioMecanico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Cristhian Navarro
 */


public class PanelServicios extends JPanel {
    private TallerControlador controlador;
    private JTable tablaServicios;
    private DefaultTableModel modeloTabla;
    
    private JTextField campoCodigo;
    private JTextField campoDescripcion;
    private JTextField campoPrecioBase;
    private JTextField campoTiempoMinutos;

    public PanelServicios(TallerControlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        initComponents();
        actualizarTabla();
    }

    private void initComponents() {
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Servicios Mecánicos"));

        campoCodigo = new JTextField();
        campoDescripcion = new JTextField();
        campoPrecioBase = new JTextField();
        campoTiempoMinutos = new JTextField();

        panelFormulario.add(new JLabel("Código de Servicio:"));
        panelFormulario.add(campoCodigo);
        panelFormulario.add(new JLabel("Descripción:"));
        panelFormulario.add(campoDescripcion);
        panelFormulario.add(new JLabel("Precio Base ($):"));
        panelFormulario.add(campoPrecioBase);
        panelFormulario.add(new JLabel("Tiempo Estimado (Minutos):"));
        panelFormulario.add(campoTiempoMinutos);

        JButton botonRegistrar = new JButton("Registrar Servicio");
        JButton botonLimpiar = new JButton("Limpiar Campos");

        panelFormulario.add(botonRegistrar);
        panelFormulario.add(botonLimpiar);

        add(panelFormulario, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new Object[]{"Código", "Descripción", "Precio Base ($)", "Tiempo (Min)"}, 0);
        tablaServicios = new JTable(modeloTabla);
        add(new JScrollPane(tablaServicios), BorderLayout.CENTER);

        botonRegistrar.addActionListener(e -> {
            try {
                String codigo = campoCodigo.getText().trim();
                String descripcion = campoDescripcion.getText().trim();
                String precioStr = campoPrecioBase.getText().trim();
                String tiempoStr = campoTiempoMinutos.getText().trim();

                if (codigo.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty() || tiempoStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double precioBase = Double.parseDouble(precioStr);
                int tiempoMinutos = Integer.parseInt(tiempoStr);

                if (precioBase < 0) {
                    JOptionPane.showMessageDialog(this, "El precio base debe ser un valor positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                controlador.registrarServicio(codigo, descripcion, precioBase, tiempoMinutos);
                actualizarTabla();
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "¡Servicio registrado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El precio y el tiempo deben ser valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar servicio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        botonLimpiar.addActionListener(e -> limpiarCampos());
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        List<ServicioMecanico> listaServicios = controlador.obtenerListaServicios();
        for (ServicioMecanico servicio : listaServicios) {
            modeloTabla.addRow(new Object[]{
                servicio.getCodigoServicio(),
                servicio.getDescripcion(),
                servicio.getPrecioBase(),
                servicio.getTiempoEstimadoMinutos()
            });
        }
    }

    private void limpiarCampos() {
        campoCodigo.setText("");
        campoDescripcion.setText("");
        campoPrecioBase.setText("");
        campoTiempoMinutos.setText("");
    }
}
