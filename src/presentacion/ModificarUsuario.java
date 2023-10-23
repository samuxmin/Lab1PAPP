package presentacion;

import controladores.Fabrica;
import controladores.ISistema;
import datatypes.DataUsuario;
import excepciones.UsuarioNoExisteException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class ModificarUsuario extends javax.swing.JInternalFrame {

    
    public String nombre;
    public String apellido;
    public LocalDate fecNac;
    public String correo;
    
    ISistema sys;
    
    public ModificarUsuario() {
        
        initComponents();
        sys = new Fabrica().getSistema();
        
        
        txt_nombre.setEnabled(false);
        txt_apellido.setEnabled(false);
        txt_fecnac.setEnabled(false);
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton_BUSCAR = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel_nombre = new javax.swing.JLabel();
        jLabel_apellido = new javax.swing.JLabel();
        jLabel_fecnac = new javax.swing.JLabel();
        txt_fecnac = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        txt_apellido = new javax.swing.JTextField();
        txt_correo = new javax.swing.JTextField();
        txt_fecnac_actual = new javax.swing.JTextField();
        txt_nombre_actual = new javax.swing.JTextField();
        txt_apellido_actual = new javax.swing.JTextField();
        jButton_CONFIRMAR = new javax.swing.JButton();
        jLabel_mensaje = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Modificar Usuario");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Correo del Usuario a modificar:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        jLabel2.setText("Nuevo Nombre: ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, -1));

        jLabel3.setText("Nueva Fecha de Nacimiento:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, -1, 20));

        jButton_BUSCAR.setText("Buscar");
        jButton_BUSCAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BUSCARActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_BUSCAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));

        jLabel4.setText("Nuevo Apellido:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, -1, -1));

        jLabel5.setText("Ingresar nuevos datos:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));

        jLabel_nombre.setText("Nombre:");
        getContentPane().add(jLabel_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 30));

        jLabel_apellido.setText("Apellido:");
        getContentPane().add(jLabel_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 30));

        jLabel_fecnac.setText("Fecha de Nacimiento:");
        getContentPane().add(jLabel_fecnac, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, 30));

        txt_fecnac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fecnacActionPerformed(evt);
            }
        });
        getContentPane().add(txt_fecnac, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 390, 210, -1));

        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 210, -1));

        txt_apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apellidoActionPerformed(evt);
            }
        });
        getContentPane().add(txt_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, 210, -1));

        txt_correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_correoActionPerformed(evt);
            }
        });
        getContentPane().add(txt_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 210, -1));

        txt_fecnac_actual.setEditable(false);
        getContentPane().add(txt_fecnac_actual, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 170, -1));

        txt_nombre_actual.setEditable(false);
        getContentPane().add(txt_nombre_actual, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 170, -1));

        txt_apellido_actual.setEditable(false);
        getContentPane().add(txt_apellido_actual, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 170, -1));

        jButton_CONFIRMAR.setText("Confirmar");
        jButton_CONFIRMAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CONFIRMARActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_CONFIRMAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 440, -1, -1));
        getContentPane().add(jLabel_mensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
   
    private void jButton_BUSCARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BUSCARActionPerformed

        try {
            correo = txt_correo.getText();
            
            
            DataUsuario info= sys.verInfoUsuario(correo);
            
            
            
            if (sys.obtenerCorreoUsuario(correo)) {
                
                //muestra info usuario
                txt_nombre_actual.setText(info.getNombre());
                txt_apellido_actual.setText(info.getApellido());
                txt_fecnac_actual.setText(info.getFecNac().toString());
                
                //borra el mensaje de error anterior si es que no existía antes
                jLabel_mensaje.setText("");
                
                //habilita campos
                txt_nombre.setEnabled(true);
                txt_apellido.setEnabled(true);
                txt_fecnac.setEnabled(true);
                
                txt_nombre.setText(info.getNombre());
                txt_apellido.setText(info.getApellido());
                txt_fecnac.setText(info.getFecNac().toString());
            } 
        } catch (UsuarioNoExisteException ex) {
                           //si no existe el usuario buscado
                jLabel_mensaje.setText("El usuario no existe");
                //limpia los campos de texto porque no se encontró el usuario
                txt_nombre_actual.setText("");
                txt_apellido_actual.setText("");
                txt_fecnac_actual.setText("");
                
                //deshabilita los campos si no lo encuentra al usuario
                txt_nombre.setEnabled(false);
                txt_apellido.setEnabled(false);
                txt_fecnac.setEnabled(false);
        }
    
    }//GEN-LAST:event_jButton_BUSCARActionPerformed

    

    
  
    
    

 
    

    private void txt_correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_correoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_correoActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void txt_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidoActionPerformed

    private void txt_fecnacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fecnacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fecnacActionPerformed

    
    
    
  
   
    private void jButton_CONFIRMARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CONFIRMARActionPerformed

    correo = txt_correo.getText();

    //Usuario usuario = Usuario.obtenerUsuario(correo); // usa el EntityManager
        
    if (sys.obtenerCorreoUsuario(correo)) {
        nombre = txt_nombre.getText();
        apellido = txt_apellido.getText();
        String fechaNacimientoTexto = txt_fecnac.getText();

        //verifica que no esten vacios
        if (!nombre.isEmpty() && !apellido.isEmpty() && !fechaNacimientoTexto.isEmpty()) {
            try {
                fecNac = LocalDate.parse(fechaNacimientoTexto);
                sys.modificarDatosUsuario(correo, nombre, apellido, fecNac);

                //actualiza texto
                txt_nombre_actual.setText(nombre);
                txt_apellido_actual.setText(apellido);
                txt_fecnac_actual.setText(fecNac.toString()); //convierte la fecha a string

                //borra el mensaje de error de antes
                jLabel_mensaje.setText("");

                //habilita los campos de texto de nuevo
                
                txt_nombre.setEnabled(true);
                txt_apellido.setEnabled(true);
                txt_fecnac.setEnabled(true);
                
                jLabel_mensaje.setText("Usuario actualizado correctamente.");
                
                txt_nombre.setText("");
                txt_apellido.setText("");
                txt_fecnac.setText("");
                
            } catch (DateTimeParseException ex) {
                jLabel_mensaje.setText("La fecha de nacimiento no es válida");
            } catch (UsuarioNoExisteException ex) {
                //si el usuario no existe
                jLabel_mensaje.setText("El usuario no existe");
            }
        } else {
            jLabel_mensaje.setText("Faltan campos");
        }
    } else {
        //si no se encontró el usuario y deshabilita los campos de texto
        
        jLabel_mensaje.setText("El usuario no existe");  
        txt_nombre.setEnabled(false);
        txt_apellido.setEnabled(false);
        txt_fecnac.setEnabled(false);
    }
    
    }//GEN-LAST:event_jButton_CONFIRMARActionPerformed
   
   


  
 

     

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_BUSCAR;
    private javax.swing.JButton jButton_CONFIRMAR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_apellido;
    private javax.swing.JLabel jLabel_fecnac;
    private javax.swing.JLabel jLabel_mensaje;
    private javax.swing.JLabel jLabel_nombre;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_apellido_actual;
    private javax.swing.JTextField txt_correo;
    private javax.swing.JTextField txt_fecnac;
    private javax.swing.JTextField txt_fecnac_actual;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_nombre_actual;
    // End of variables declaration//GEN-END:variables
}
