/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;

import java.lang.NumberFormatException;
import java.time.format.DateTimeParseException;
import controladores.Fabrica;
import controladores.ISistema;
import controladores.Sistema;
import datatypes.DataActividad;
import datatypes.DataUsuario;
import datatypes.DataSalida;
import java.time.LocalDate;

/**
 *
 * @author samuel
 */
public class AltaActividadTuristica extends javax.swing.JInternalFrame {
    ISistema sys;
    String nombreDepto, nombreAct, correoProv, descripcion, ciudad;
    Integer duracion; 
    double costo;
    LocalDate fechaA;
    String lugarActual = "departamentos";
    boolean excepcionLanzada=false;
    boolean excepcionLanzada2=false;
    String[] strings;
    String[] strings2;

    public AltaActividadTuristica() {
        sys = new Fabrica().getSistema();
        strings = sys.listarDepartamentos();
        strings2 = sys.listarCorreosProveedores();
        initComponents();
       // this.setSize(440, 440);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jTextField1.setVisible(false);
        jTextField2.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        confirmar.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        jButton2.setVisible(true);
        jLabel9.setVisible(false);
        jTextField6.setVisible(false);
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        confirmar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Alta de Actividad Turistica");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Seleccione el Proveedor:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 190, 20));

        jLabel2.setText("Seleccione el Departamento:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 20));

        jButton1.setText("siguiente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 150, -1));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 150, -1));
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 150, -1));

        jLabel3.setText("Nombre Actividad:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 150, -1));

        jLabel4.setText("Descripcion:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, -1, -1));

        jLabel5.setText("Duracion:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        jLabel6.setText("Costo:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        jLabel7.setText("Fecha (año-mes-dia):");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, -1));
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 150, -1));

        confirmar.setText("confirmar");
        confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarActionPerformed(evt);
            }
        });
        getContentPane().add(confirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, -1));
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 460, 30));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(strings2));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 140, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(strings));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 140, -1));

        jButton2.setText("cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, -1, -1));

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 150, -1));

        jLabel9.setText("Ciudad:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        nombreDepto = jComboBox1.getSelectedItem().toString();
        correoProv = jComboBox2.getSelectedItem().toString();
        if(jComboBox1.getSelectedItem().toString()!=null && jComboBox2.getSelectedItem().toString()!=null){
            jButton1.setVisible(false);
            jComboBox1.setVisible(false);
            jComboBox2.setVisible(false);
            jLabel1.setVisible(false);
            jLabel2.setVisible(false);
                 
            jLabel3.setVisible(true);
            jLabel4.setVisible(true);
            jLabel5.setVisible(true);
            jLabel6.setVisible(true);
            jLabel7.setVisible(true);
            jTextField1.setVisible(true);
            jTextField2.setVisible(true);
            jTextField3.setVisible(true);
            jTextField4.setVisible(true);
            jTextField5.setVisible(true);
            confirmar.setVisible(true);
            jLabel9.setVisible(true);
            jTextField6.setVisible(true);
            
            // sys.AltaActividadTuristica(String correoProveedor, String nombreAct, String descripcion, Integer duracion, double costo, String ciudad, LocalDate fechaA);
        }         

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
//
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarActionPerformed
            excepcionLanzada=false;
            excepcionLanzada2=false;
            jLabel8.setVisible(false);
            jLabel9.setVisible(false);
            jLabel3.setVisible(true);
            jLabel4.setVisible(true);
            jLabel5.setVisible(true);
            jLabel6.setVisible(true);
            jLabel7.setVisible(true);
            nombreDepto=jComboBox1.getSelectedItem().toString();
            correoProv=jComboBox2.getSelectedItem().toString();
            nombreAct=jTextField1.getText();
            descripcion=jTextField2.getText();
            ciudad=jTextField6.getText();
            try {
                duracion=Integer.parseInt(jTextField3.getText());
            } catch (NumberFormatException e) {
                jLabel8.setText("Debe poner solo numeros en el precio y costo");
                jLabel8.setVisible(true);
                excepcionLanzada = true;
            }
            
            try {
                costo = Double.parseDouble(jTextField4.getText());
            } catch (NumberFormatException ex) {
                jLabel8.setText("El formato es incorrecto. Debe ser un número válido.");
                jLabel8.setVisible(true);
            }
            
            String duracionTexto = jTextField3.getText();
            if (!duracionTexto.isEmpty() && duracionTexto.chars().allMatch(Character::isDigit)) {
                duracion = Integer.parseInt(duracionTexto);
            } else {
                jLabel8.setText("Debe ingresar solo números en la duración");
                jLabel8.setVisible(true);
                excepcionLanzada = true;
            }
            
            duracionTexto = jTextField4.getText();
            if (!duracionTexto.isEmpty() && duracionTexto.chars().allMatch(Character::isDigit)) {
                costo = Double.parseDouble(jTextField4.getText());
            } else {
                jLabel8.setText("Debe ingresar solo números en el costo");
                jLabel8.setVisible(true);
                excepcionLanzada = true;
            }
            
            try {
                fechaA = LocalDate.parse(jTextField5.getText());
            } catch (DateTimeParseException ex) {
                jLabel8.setText("El formato de fecha es incorrecto. Debe ser yyyy-MM-dd.");
                jLabel9.setVisible(true);
                excepcionLanzada2 = true;
            }

            if(!(excepcionLanzada || excepcionLanzada2)){
                if(nombreAct.isEmpty()||descripcion.isEmpty()||duracion==null||Double.isNaN(costo)||fechaA==null){
                    jLabel8.setText("Debe llenar todos los campos para confirmar");
                    jLabel8.setVisible(true);
                }
                else{ 
                    boolean actRegistrada=sys.AltaActividadTuristica(correoProv, nombreAct, descripcion, duracion, costo,nombreDepto, fechaA, ciudad);
                    if(actRegistrada){
                    jLabel8.setText("Actividad ya ingresada, ingrese otra actividad o ciere la ventana");
                    jLabel8.setVisible(true);
                    }
                    else{
                        jLabel8.setText("Actividad Registrada con Exito");
                        jLabel8.setVisible(true);
                    }
                }
            }

    }//GEN-LAST:event_confirmarActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
