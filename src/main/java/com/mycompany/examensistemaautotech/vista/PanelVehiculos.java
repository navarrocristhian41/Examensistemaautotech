/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.vista;

import com.mycompany.examensistemaautotech.controlador.TallerControlador;
import com.mycompany.examensistemaautotech.modelo.Cliente;
import com.mycompany.examensistemaautotech.modelo.Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Cristhian Navarro
 */


public class PanelVehiculos extends JPanel {
    private TallerControlador controlador;
    private JTable tablaVehiculos;
    private DefaultTableModel modeloTabla;
    
    private JTextField campoPlaca;
    private JTextField campoMarca;
    private JTextField campoModelo;
    private JTextField campoAnio;
    private JTextField campoColor;
    private JComboBox<Cliente> comboPropietarios;

    public PanelVehiculos(TallerControlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        initComponents();
        actualizarDatos();
    }

    private void initComponents() {
        // Panel Superior
        JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registro de Vehículos"));

        campoPlaca = new JTextField();
        campoMarca = new JTextField();
        campoModelo = new JTextField();
        campoAnio = new JTextField();
        campoColor = new JTextField();
        comboPropietarios = new JComboBox<>();

        panelFormulario.add(new JLabel("Placa:"));
        panelFormulario.add(campoPlaca);
        panelFormulario.add(new JLabel("Marca:"));
        panelFormulario.add(campoMarca);
        panelFormulario.add(new JLabel("Modelo:"));
        panelFormulario.add(campoModelo);
        panelFormulario.add(new JLabel("Año:"));
        panelFormulario.add(campoAnio);
        panelFormulario.add(new JLabel("Color:"));
        panelFormulario.add(campoColor);
        panelFormulario.add(new JLabel("Propietario (Cliente):"));
        panelFormulario.add(comboPropietarios);

        JButton botonRegistrar = new JButton("Registrar Vehículo");
        JButton botonLimpiar = new JButton("Limpiar Campos");

        panelFormulario.add(botonRegistrar);
        panelFormulario.add(botonLimpiar);

        add(panelFormulario, BorderLayout.NORTH);

        // Panel Central
        modeloTabla = new DefaultTableModel(new Object[]{"Placa", "Marca", "Modelo", "Año", "Color", "Propietario"}, 0);
        tablaVehiculos = new JTable(modeloTabla);
        add(new JScrollPane(tablaVehiculos), BorderLayout.CENTER);

        // Botones
        botonRegistrar.addActionListener(e -> {
            try {
                String placa = campoPlaca.getText().trim();
                String marca = campoMarca.getText().trim();
                String modelo = campoModelo.getText().trim();
                String anioStr = campoAnio.getText().trim();
                String color = campoColor.getText().trim();
                Cliente propietarioSeleccionado = (Cliente) comboPropietarios.getSelectedItem();

                if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || anioStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor complete los campos obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (propietarioSeleccionado == null) {
                    JOptionPane.showMessageDialog(this, "Debe registrar y seleccionar un propietario existente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (controlador.buscarVehiculoPorPlaca(placa) != null) {
                    JOptionPane.showMessageDialog(this, "Ya existe un vehículo registrado con esta placa.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int anio = Integer.parseInt(anioStr);
                controlador.registrarVehiculo(placa, marca, modelo, anio, color, propietarioSeleccionado);
                actualizarDatos();
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "¡Vehículo registrado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El año del vehículo debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar vehículo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        botonLimpiar.addActionListener(e -> limpiarCampos());
    }

    public void actualizarDatos() {
        
        comboPropietarios.removeAllItems();
        List<Cliente> listaClientes = controlador.obtenerListaClientes();
        for (Cliente cliente : listaClientes) {
            comboPropietarios.addItem(cliente);
        }

        // Actualizar la tabla de vehículos
        modeloTabla.setRowCount(0);
        List<Vehiculo> listaVehiculos = controlador.obtenerListaVehiculos();
        for (Vehiculo vehiculo : listaVehiculos) {
            modeloTabla.addRow(new Object[]{
                vehiculo.getPlaca(),
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getAnio(),
                vehiculo.getColor(),
                vehiculo.getPropietario().getNombreCompleto()
            });
        }
    }

    private void limpiarCampos() {
        campoPlaca.setText("");
        campoMarca.setText("");
        campoModelo.setText("");
        campoAnio.setText("");
        campoColor.setText("");
    }
}
