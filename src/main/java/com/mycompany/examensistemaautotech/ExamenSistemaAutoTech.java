/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.examensistemaautotech;

import com.mycompany.examensistemaautotech.vista.VentanaPrincipal;
import javax.swing.SwingUtilities;
/**
 *
 * @author Cristhian Navarro
 */

public class ExamenSistemaAutoTech {

    public static void main(String[] args) {
       
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
