
package presentacion;

import controladores.Fabrica;
import controladores.ISistema;
import datatypes.DataPaquete;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import logica.ActividadTuristica;
import logica.Paquete;

public class ConsultaPaqueteActividadesTuristicas extends javax.swing.JInternalFrame {
    ISistema sys;
    String nombre;
    String paquete;
    String [] strings=new String[]{};
    String nombreact;
    String stringsA;
    
    ActividadTuristica [] act=new ActividadTuristica []{};
    
    public ConsultaPaqueteActividadesTuristicas() {
         sys = new Fabrica().getSistema();
         strings=sys.listarPaquetesArreglo();
         initComponents();
         this.setSize(700, 700);
         ListarACT.setVisible(false);
         selectACT.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jsi = new javax.swing.JRadioButton();
        jno = new javax.swing.JRadioButton();
        error = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        combolistarP = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableP = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableAct = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        selectACT = new javax.swing.JTable();
        ListarACT = new javax.swing.JComboBox<>();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("ConsultaPaqueteActividadesTuristicas");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Paquetes Registrados");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 270, -1));

        jLabel3.setText("Datos del paquete:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 420, -1));

        jLabel4.setText("Desea seleccionar una actividad turistica: ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        buttonGroup1.add(jsi);
        jsi.setText("Si");
        jsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsiActionPerformed(evt);
            }
        });
        getContentPane().add(jsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, -1, 40));

        buttonGroup1.add(jno);
        jno.setText("No");
        jno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jnoActionPerformed(evt);
            }
        });
        getContentPane().add(jno, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 400, -1, 30));
        getContentPane().add(error, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 450, 30));

        jLabel2.setText("Seleccione un paquete:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel5.setText("Actividades turisticas asociadas:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        combolistarP.setModel(new javax.swing.DefaultComboBoxModel<>(strings));
        combolistarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combolistarPActionPerformed(evt);
            }
        });
        getContentPane().add(combolistarP, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 350, 30));

        tableP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Descripcion", "Validez", "Descuento", "Alta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableP);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 560, 60));

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
        jScrollPane3.setViewportView(tableAct);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 560, 70));

        selectACT.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(selectACT);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, 560, 70));

        ListarACT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarACTActionPerformed(evt);
            }
        });
        getContentPane().add(ListarACT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 330, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsiActionPerformed
        if(jsi.isSelected()){
        ListarACT.setVisible(true);   
        selectACT.setVisible(true);
        }else {
        ListarACT.setVisible(false);
        selectACT.setVisible(false);
    }
    }//GEN-LAST:event_jsiActionPerformed

    private void jnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jnoActionPerformed
         ListarACT.setVisible(false);
          selectACT.setVisible(false);
         this.dispose();
    }//GEN-LAST:event_jnoActionPerformed

    private void combolistarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combolistarPActionPerformed
    // Obtener el nombre del paquete seleccionado en el ComboBox
    ListarACT.setVisible(false);
    nombre = combolistarP.getSelectedItem().toString();
    error.setText("Paquete " + nombre + " seleccionado.");

    // Limpiar la tabla tableP
    DefaultTableModel tableModel = (DefaultTableModel) tableP.getModel();
    tableModel.setRowCount(0); // Elimina todas las filas de la tabla

    // Obtener el paquete seleccionado
    Paquete paq = sys.selectPaquete(nombre);

    if (paq != null) {
        // Agregar detalles del paquete a la tabla tableP
        Object[] rowData = { paq.getNombre_paquete(), paq.getDescri(), paq.getValidez(), paq.getDescuento() };
        tableModel.addRow(rowData);

        // Limpiar la tabla tableAct
        DefaultTableModel tableModel1 = (DefaultTableModel) tableAct.getModel();
        tableModel1.setRowCount(0); // Elimina todas las filas de la tabla

        // Obtener y mostrar actividades turísticas asociadas al paquete
        act = sys.listarActPaquete(paq);

        if (act != null && act.length > 0) {
            String[] stringsA = new String[act.length];

            for (int i = 0; i < act.length; i++) {
                Object[] rowData1 = { act[i].getNombre(), act[i].getDescripcion(), act[i].getDuracionHoras(), act[i].getCostoPorTurista(), act[i].getCiudad(), act[i].getFechaAlta() };
                tableModel1.addRow(rowData1);
                stringsA[i] = act[i].getNombre();
            }

            // Actualizar el ComboBox ListarACT con las actividades
            ListarACT.setModel(new DefaultComboBoxModel<>(stringsA));
            ListarACT.setVisible(true); // Hacer visible el ComboBox
        } else {
            ListarACT.setModel(new DefaultComboBoxModel<>()); // Si no hay actividades, vaciar el ComboBox
            ListarACT.setVisible(false);
        }
    }
    }//GEN-LAST:event_combolistarPActionPerformed

    private void ListarACTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarACTActionPerformed
        if (act != null && act.length > 0) {
        int selectedIndex = ListarACT.getSelectedIndex();

        if (selectedIndex >= 0 && selectedIndex < act.length) {
            ActividadTuristica actividadSeleccionada = act[selectedIndex];

            DefaultTableModel tableModel3 = (DefaultTableModel) selectACT.getModel();
            int rowCount3 = tableModel3.getRowCount();

            for (int i = rowCount3 - 1; i >= 0; i--) {
                tableModel3.removeRow(i);
            }

            Object[] rowData3 = { actividadSeleccionada.getNombre(), actividadSeleccionada.getDescripcion(),
                actividadSeleccionada.getDuracionHoras(), actividadSeleccionada.getCostoPorTurista(),
                actividadSeleccionada.getCiudad(), actividadSeleccionada.getFechaAlta() };

            tableModel3.addRow(rowData3);
        }
    }
    }//GEN-LAST:event_ListarACTActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ListarACT;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> combolistarP;
    private javax.swing.JLabel error;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton jno;
    private javax.swing.JRadioButton jsi;
    private javax.swing.JTable selectACT;
    private javax.swing.JTable tableAct;
    private javax.swing.JTable tableP;
    // End of variables declaration//GEN-END:variables
}
