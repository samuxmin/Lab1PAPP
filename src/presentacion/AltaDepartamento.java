package presentacion;

import excepciones.DepartamentoExceptions;
import controladores.Fabrica;
import controladores.ISistema;
import controladores.Sistema;
import java.awt.event.KeyEvent;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.plaf.InternalFrameUI;
import logica.Departamento;
import static logica.Departamento.departamentos;

public class AltaDepartamento extends javax.swing.JInternalFrame {
    public String nombre;
    public String descripcion;
    public String url;
    ISistema sys;
    String aux;
    
    
    public AltaDepartamento() {
        initComponents();
        this.setSize(800, 500);     
        
        sys = new Fabrica().getSistema();
        
         
         jReingreso.setVisible(false);
         ButtonReingresar.setVisible(false);
         ButtonCancelar.setVisible(false);
         txtRname.setVisible(false);
         
         txtdescr.setVisible(true);
         txturl.setVisible(true);
         jUrl.setVisible(true);
         jDescr.setVisible(true); 
         jRname.setVisible(false); 
         ButtonConfirmar.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jDescr = new javax.swing.JLabel();
        jUrl = new javax.swing.JLabel();
        jReingreso = new javax.swing.JLabel();
        ButtonReingresar = new javax.swing.JRadioButton();
        ButtonCancelar = new javax.swing.JRadioButton();
        txturl = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        txtRname = new javax.swing.JTextField();
        txtdescr = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ButtonConfirmar = new javax.swing.JButton();
        error = new javax.swing.JLabel();
        errorname = new javax.swing.JLabel();
        jRname = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Alta Departamento");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Ingrese nombre del departamento: ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 260, 30));

        jDescr.setText("Ingrese descripcion:  ");
        getContentPane().add(jDescr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jUrl.setText("Ingrese URL: ");
        getContentPane().add(jUrl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 220, 30));

        jReingreso.setText("Reingrese el nombre:");
        getContentPane().add(jReingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 300, -1));

        buttonGroup1.add(ButtonReingresar);
        ButtonReingresar.setText("Reingresar nombre del departamento");
        ButtonReingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonReingresarActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonReingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, -1, -1));

        buttonGroup1.add(ButtonCancelar);
        ButtonCancelar.setText("Cancelar");
        ButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        txturl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txturlActionPerformed(evt);
            }
        });
        txturl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txturlKeyPressed(evt);
            }
        });
        getContentPane().add(txturl, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 430, 30));

        txtname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnameActionPerformed(evt);
            }
        });
        txtname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnameKeyPressed(evt);
            }
        });
        getContentPane().add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 13, 430, 30));

        txtRname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRnameActionPerformed(evt);
            }
        });
        txtRname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRnameKeyPressed(evt);
            }
        });
        getContentPane().add(txtRname, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 430, 30));

        txtdescr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdescrActionPerformed(evt);
            }
        });
        txtdescr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdescrKeyPressed(evt);
            }
        });
        getContentPane().add(txtdescr, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 430, 30));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 130, -1));

        ButtonConfirmar.setText("Confirmar");
        ButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonConfirmarActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, 160, 40));
        getContentPane().add(error, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 390, 30));
        getContentPane().add(errorname, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 460, 20));

        jRname.setText("Desea reingresar el nombre?");
        getContentPane().add(jRname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 320, 150, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonReingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonReingresarActionPerformed
        if(ButtonReingresar.isSelected()==true){
         jReingreso.setVisible(true);
         txtname.setVisible(false);
         jLabel1.setVisible(false);
         txtRname.setVisible(true);
         txtdescr.setVisible(true);
         txturl.setVisible(true);
         jUrl.setVisible(true);
         jDescr.setVisible(true); 
               
    }//GEN-LAST:event_ButtonReingresarActionPerformed
   }
    private void ButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCancelarActionPerformed
        jReingreso.setVisible(false);
         txtRname.setVisible(false);
         jReingreso.setVisible(true);
         txtdescr.setVisible(true);
         txturl.setVisible(false);
         jUrl.setVisible(false);
         jDescr.setVisible(false); 
         jRname.setVisible(false); 
        this.dispose();
    }//GEN-LAST:event_ButtonCancelarActionPerformed
    private void txturlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txturlActionPerformed
        url=txturl.getText();
    }//GEN-LAST:event_txturlActionPerformed
    private void txtnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnameActionPerformed
        nombre=txtname.getText();  
    }//GEN-LAST:event_txtnameActionPerformed

    private void txtRnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRnameActionPerformed
        nombre=txtRname.getText();
    }//GEN-LAST:event_txtRnameActionPerformed
    private void txtdescrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdescrActionPerformed
        descripcion=txtdescr.getText();
    }//GEN-LAST:event_txtdescrActionPerformed
    private void txtnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyPressed
 
    }//GEN-LAST:event_txtnameKeyPressed
    private void txtRnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRnameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRnameKeyPressed
    private void txtdescrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescrKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescrKeyPressed
    private void txturlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txturlKeyPressed
  

    }//GEN-LAST:event_txturlKeyPressed
    private void ButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonConfirmarActionPerformed
     nombre = txtname.getText();
    descripcion = txtdescr.getText();
    url = txturl.getText();

    if (url.isEmpty() || descripcion.isEmpty() || nombre.isEmpty()) {
        error.setText("ERROR: faltan campos por completar.");
    } else {
        if (Departamento.encontrarDepto(nombre) != null) {
            errorname.setText("El nombre del departamento ya existe.");
            txtname.setEnabled(false); // Deshabilitar el campo de nombre
            ButtonReingresar.setVisible(true);
            ButtonCancelar.setVisible(true);

            if (ButtonReingresar.isSelected()) {
                String nuevoNombre = txtRname.getText();
                if (nuevoNombre.isEmpty()) {
                    errorname.setText("ERROR: faltan campos por completar.");
                } else {
                    if (nombre.equals(nuevoNombre)) {
                        error.setText("El nombre del departamento también existe.");
                    } else if (Departamento.encontrarDepto(nuevoNombre) != null) { 
                        error.setText("El nombre del departamento ya existe.");
                    } else {
                        sys.AltaDepartamento(nuevoNombre, descripcion, url);
                        error.setText("Departamento registrado con éxito.");
                        this.dispose();
                    }
                }
            }
        } else {
            if (!nombre.isEmpty()) {
                sys.AltaDepartamento(nombre, descripcion, url);
                error.setText("Departamento registrado con éxito.");
                this.dispose();
            }
        }
    }
    }//GEN-LAST:event_ButtonConfirmarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton ButtonCancelar;
    private javax.swing.JButton ButtonConfirmar;
    private javax.swing.JRadioButton ButtonReingresar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JLabel error;
    private javax.swing.JLabel errorname;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jDescr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jReingreso;
    private javax.swing.JLabel jRname;
    private javax.swing.JLabel jUrl;
    private javax.swing.JTextField txtRname;
    private javax.swing.JTextField txtdescr;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txturl;
    // End of variables declaration//GEN-END:variables
}
