/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examensistemaautotech.vista;

import com.mycompany.examensistemaautotech.controlador.TallerControlador;
import com.mycompany.examensistemaautotech.modelo.OrdenTrabajo;
import com.mycompany.examensistemaautotech.modelo.ServicioMecanico;

import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Cristhian Navarro
 */


public class CanvasReporte2D extends JPanel {
    private TallerControlador controlador;

    public CanvasReporte2D(TallerControlador controlador) {
        this.controlador = controlador;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1100, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

      
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(40, 40, 40));
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Reporte Gráfico ", 30, 30);

        // Dibujo de Gráfica 
        dibujarGraficaOrdenes(g2d);
    }

    private void dibujarGraficaOrdenes(Graphics2D g2d) {
        List<OrdenTrabajo> listaOrdenes = controlador.obtenerListaOrdenes();
        
        int pendientes = 0;
        int enProceso = 0;
        int finalizadas = 0;
        int canceladas = 0;

        for (OrdenTrabajo orden : listaOrdenes) {
            switch (orden.getEstado()) {
                case "Pendiente": pendientes++; break;
                case "En proceso": enProceso++; break;
                case "Finalizada": finalizadas++; break;
                case "Cancelada": canceladas++; break;
            }
        }

        int x = 450;
        int yBase = 500;
        int anchoBarra = 150;
        int escalaAlto = 80; // Altura por cada unidad

        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString("Distribución de Órdenes Grafico", x, 70);

        
        g2d.drawLine(40, yBase, 450, yBase);
        g2d.drawLine(40, 90, 40, yBase);

        // Barra Pendientes
        g2d.setColor(new Color(241, 196, 15)); // Amarillo
        g2d.fillRect(x, yBase - (pendientes * escalaAlto), anchoBarra, pendientes * escalaAlto);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Pendientes (" + pendientes + ")", x, yBase + 20);

        // Barra Proceso
        x += 190;
        g2d.setColor(new Color(52, 152, 219)); // Azul
        g2d.fillRect(x, yBase - (enProceso * escalaAlto), anchoBarra, enProceso * escalaAlto);
        g2d.setColor(Color.BLACK);
        g2d.drawString("En Proceso (" + enProceso + ")", x, yBase + 20);

        // Barra Finalizadas
        x += 190;
        g2d.setColor(new Color(46, 204, 113)); // Verde
        g2d.fillRect(x, yBase - (finalizadas * escalaAlto), anchoBarra, finalizadas * escalaAlto);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Finalizadas (" + finalizadas + ")", x, yBase + 20);

        // Barra Canceladas
        x += 190;
        g2d.setColor(new Color(231, 76, 60)); // Rojo
        g2d.fillRect(x, yBase - (canceladas * escalaAlto), anchoBarra, canceladas * escalaAlto);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Canceladas (" + canceladas + ")", x, yBase + 20);
    }
}
