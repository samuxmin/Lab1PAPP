/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;

import controladores.Fabrica;
import controladores.ISistema;
import java.time.LocalDate;

/**
 *
 * @author samuel
 */
public class CrearPaqueteActividades extends javax.swing.JInternalFrame {

    String nombrePaq, descripcionPaq;
    ISistema sys;
    int validez, descuento;
    LocalDate fechaAlta;

    /**
     * Creates new form CrearPaqueteActividades
     */
    public CrearPaqueteActividades() {
        sys = new Fabrica().getSistema();
        initComponents();
        frameModificar.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameModificar = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        botonModificar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        labelNombre = new javax.swing.JLabel();
        labelDescripcion = new javax.swing.JLabel();
        labelValidez = new javax.swing.JLabel();
        labelDescuento = new javax.swing.JLabel();
        labelAlta = new javax.swing.JLabel();
        fieldNombre = new javax.swing.JTextField();
        fieldDescripcion = new javax.swing.JTextField();
        fieldValidez = new javax.swing.JTextField();
        fieldDescuento = new javax.swing.JTextField();
        fieldFecha = new javax.swing.JTextField();
        botonCrear = new javax.swing.JButton();
        mensajeLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Crear Paquete de Actividades Turisticas");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        frameModificar.setVisible(true);
        frameModificar.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Paquete ya existente, desea actualizar los datos?");
        frameModificar.getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 338, -1));

        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });
        frameModificar.getContentPane().add(botonModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 110, -1));

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });
        frameModificar.getContentPane().add(botonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 110, -1));

        getContentPane().add(frameModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 350, 130));

        labelNombre.setText("Nombre");
        getContentPane().add(labelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        labelDescripcion.setText("Descripción");
        getContentPane().add(labelDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        labelValidez.setText("Días de validez");
        getContentPane().add(labelValidez, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        labelDescuento.setText("% de descuento");
        getContentPane().add(labelDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        labelAlta.setText("Fecha de alta");
        getContentPane().add(labelAlta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        fieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNombreActionPerformed(evt);
            }
        });
        getContentPane().add(fieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 230, -1));

        fieldDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldDescripcionActionPerformed(evt);
            }
        });
        getContentPane().add(fieldDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 230, 60));

        fieldValidez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldValidezActionPerformed(evt);
            }
        });
        getContentPane().add(fieldValidez, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 230, -1));

        fieldDescuento.setText("0");
        getContentPane().add(fieldDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 230, -1));

        fieldFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFechaActionPerformed(evt);
            }
        });
        getContentPane().add(fieldFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 230, -1));

        botonCrear.setText("Crear");
        botonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearActionPerformed(evt);
            }
        });
        getContentPane().add(botonCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 100, -1));

        mensajeLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        mensajeLabel.setText(" ");
        getContentPane().add(mensajeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 250, -1));

        jLabel2.setText("(año-mes-dia)");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 290, 90, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNombreActionPerformed

    private void fieldDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldDescripcionActionPerformed

    private void fieldValidezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldValidezActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldValidezActionPerformed

    private void fieldFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldFechaActionPerformed

    private void botonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearActionPerformed
        nombrePaq = fieldNombre.getText();
        descripcionPaq = fieldDescripcion.getText();

        try {
            descuento = Integer.parseInt(fieldDescuento.getText());

            validez = Integer.parseInt(fieldValidez.getText());

           fechaAlta = LocalDate.parse(fieldFecha.getText());
        } catch (Exception e) {
            mensajeLabel.setText("Error, algún campo inválido");
            return;
        }
        if(sys.existePaquete(nombrePaq)){
                        frameModificar.setVisible(true);
                        mensajeLabel.setText("Paquete ya existente");
                        
        }else{
            mensajeLabel.setText("Creado exitosamente");
        sys.confirmarCreacionPaquete(nombrePaq, descripcionPaq, validez, descuento, fechaAlta);
        }
    }//GEN-LAST:event_botonCrearActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        sys.modificarPaquete(nombrePaq, descripcionPaq, validez, descuento, fechaAlta);
        frameModificar.setVisible(false);
        mensajeLabel.setText("Modificado exitosamente");
    }//GEN-LAST:event_botonModificarActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        frameModificar.setVisible(false);
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonCrear;
    private javax.swing.JButton botonModificar;
    private javax.swing.JTextField fieldDescripcion;
    private javax.swing.JTextField fieldDescuento;
    private javax.swing.JTextField fieldFecha;
    private javax.swing.JTextField fieldNombre;
    private javax.swing.JTextField fieldValidez;
    private javax.swing.JInternalFrame frameModificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelAlta;
    private javax.swing.JLabel labelDescripcion;
    private javax.swing.JLabel labelDescuento;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelValidez;
    private javax.swing.JLabel mensajeLabel;
    // End of variables declaration//GEN-END:variables
}
