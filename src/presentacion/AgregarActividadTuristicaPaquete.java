package presentacion;

import controladores.Sistema;
import controladores.Fabrica;
import controladores.ISistema;
import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.table.DefaultTableModel;
import logica.ActividadTuristica;
import logica.Departamento;
import logica.Paquete;

public class AgregarActividadTuristicaPaquete extends javax.swing.JInternalFrame {

    ISistema sys;
    String[] strings;
    String[] stringsD = new String[]{};
    String[] stringsA = new String[]{};
    ActividadTuristica[] act = new ActividadTuristica[]{};
    String nombre;
    String nombreact;
    String eleccion;
    String nombreDepto;
    String nombrePaquete;
    String paquete;
    ActividadTuristica actPaquete;

    public AgregarActividadTuristicaPaquete() {

        sys = new Fabrica().getSistema();
        strings = sys.listarPaquetesArreglo();
        stringsD = sys.listarDepartamentos();
        initComponents();
        this.setSize(780, 700);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        listarP = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableAct = new javax.swing.JTable();
        sact = new javax.swing.JComboBox<>();
        ok = new javax.swing.JLabel();
        yes = new javax.swing.JLabel();
        nombreD = new javax.swing.JComboBox<>();
        se = new javax.swing.JLabel();
        confirmar = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("AgregarActividadTuristicaPaquete");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Paquetes registrados:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, 230, 30));

        jLabel3.setText("Seleccione el departamento:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 230, 30));

        jLabel4.setText("Actividades Turisticas del departameno que no forman parte del paquete:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, 30));

        jLabel5.setText("Seleccione Actividad Turistica: ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, -1));

        jButton1.setText("Confirmar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 410, 160, 50));

        listarP.setModel(new javax.swing.DefaultComboBoxModel<>(strings));
        listarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarPActionPerformed(evt);
            }
        });
        getContentPane().add(listarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 400, 30));

        tableAct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Descripcion", "Duracion", "Costo", "Ciudad", "Fecha Alta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tableAct);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 480, 90));

        sact.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        sact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sactActionPerformed(evt);
            }
        });
        getContentPane().add(sact, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 390, 30));
        getContentPane().add(ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 490, 20));
        getContentPane().add(yes, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, -1, -1));

        nombreD.setModel(new javax.swing.DefaultComboBoxModel<>(stringsD));
        nombreD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreDActionPerformed(evt);
            }
        });
        getContentPane().add(nombreD, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 410, -1));
        getContentPane().add(se, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 460, -1, -1));

        confirmar.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        getContentPane().add(confirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 470, 470, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        actPaquete = sys.selectActividadBD(sact.getSelectedItem().toString());
        nombreDepto = nombreD.getSelectedItem().toString();
        nombrePaquete = listarP.getSelectedItem().toString();

        if (nombrePaquete == null || nombreDepto == null || actPaquete == null) {
            confirmar.setText("Faltan campos.");

        } else {
            // Si todos los campos están seleccionados, procede con la acción
            
            Paquete paque = sys.selectPaquete(nombrePaquete);
            if(!paque.tieneActividad(actPaquete.getNombre())){
            sys.AgregarActividadPaquete(actPaquete, paque);
            //sys.AltaCategoriaPaq(actPaquete.getNombre(), nombrePaquete);
            confirmar.setText("Registrado con exito.");
            }else{
                confirmar.setText("La actividad ya pertenece al paquete");
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void listarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarPActionPerformed
        nombre = listarP.getSelectedItem().toString();
        yes.setText("Paquete " + nombre + " seleccionado.");
        Paquete paq = sys.selectPaquete(listarP.getSelectedItem().toString());

    }//GEN-LAST:event_listarPActionPerformed

    private void sactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sactActionPerformed
        if (sact.getSelectedItem() == null) {
            return;
        }
        eleccion = sact.getSelectedItem().toString();
        actPaquete = sys.selectActividad(sact.getSelectedItem().toString());
        if(actPaquete!=null){
            se.setText("Actividad turistica " + actPaquete.getNombre() + " seleccionado.");
        }
        


    }//GEN-LAST:event_sactActionPerformed

    private void nombreDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreDActionPerformed
        nombreDepto = nombreD.getSelectedItem().toString();
        nombrePaquete = listarP.getSelectedItem().toString();

        Departamento dep = sys.selectDepartamento(nombreD.getSelectedItem().toString());

        ok.setText("Departamento " + nombreDepto + " seleccionado.");
        act = sys.listarActividadesDepNoPaquete(nombrePaquete, nombreDepto);

        DefaultTableModel tableModel = (DefaultTableModel) tableAct.getModel(); // Obtén el modelo de la JTable
        if (act.length == 0) {
            ok.setText("El departamento " + nombreDepto + " no tiene actividades turísticas.");
        }
        act = sys.listarActividadesDepNoPaquete(nombrePaquete, nombreDepto);
        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
        String[] stringsA = new String[act.length];
        for (int i = 0; i < act.length; i++) {
            Object[] rowData = {act[i].getNombre(), act[i].getDescripcion(), act[i].getDuracionHoras(), act[i].getCostoPorTurista(), act[i].getCiudad(), act[i].getFechaAlta()};
            tableModel.addRow(rowData);
            stringsA[i] = act[i].getNombre();

        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(stringsA);
        sact.setModel(model);
        sact.setSelectedItem(null);
    }//GEN-LAST:event_nombreDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel confirmar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JComboBox<String> listarP;
    private javax.swing.JComboBox<String> nombreD;
    private javax.swing.JLabel ok;
    private javax.swing.JComboBox<String> sact;
    private javax.swing.JLabel se;
    private javax.swing.JTable tableAct;
    private javax.swing.JLabel yes;
    // End of variables declaration//GEN-END:variables
}
