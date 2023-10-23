package presentacion;

import controladores.Fabrica;
import controladores.ISistema;

/*
 * @author facu
 */

public class AltaCategoria extends javax.swing.JInternalFrame {
    
    public String nombreCat;
    ISistema sys;
   
    public AltaCategoria() {
        initComponents();
        
        sys = new Fabrica().getSistema();
        
        
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_nombreCat = new javax.swing.JTextField();
        Label_nombreCat = new javax.swing.JLabel();
        JButton_registrar = new javax.swing.JButton();
        jButton_cancelar = new javax.swing.JButton();
        jLabel_mensaje = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(txt_nombreCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 229, -1));

        Label_nombreCat.setText("Nombre de la categoría:");
        getContentPane().add(Label_nombreCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        JButton_registrar.setText("Registrar");
        JButton_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButton_registrarActionPerformed(evt);
            }
        });
        getContentPane().add(JButton_registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, -1, -1));

        jButton_cancelar.setText("Cancelar");
        getContentPane().add(jButton_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, -1, -1));
        getContentPane().add(jLabel_mensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 229, 25));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JButton_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButton_registrarActionPerformed
       nombreCat = txt_nombreCat.getText();
       
       try{
       
          if(nombreCat.isEmpty()){
          throw new IllegalArgumentException("ERROR: faltan campos por completar.");
       }
       
       if(sys.getCategoriaDB(nombreCat)){
          throw new IllegalArgumentException("ERROR: categoría ya existe.");
       }
       else{
          sys.registrarCategoria(nombreCat);
          jLabel_mensaje.setText("Categoría registrada con éxito.");
       }
       
       }catch(Exception e){
        jLabel_mensaje.setText(e.getMessage());
       }
       
      
       
       
    }//GEN-LAST:event_JButton_registrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButton_registrar;
    private javax.swing.JLabel Label_nombreCat;
    private javax.swing.JButton jButton_cancelar;
    private javax.swing.JLabel jLabel_mensaje;
    private javax.swing.JTextField txt_nombreCat;
    // End of variables declaration//GEN-END:variables

}
