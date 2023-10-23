package presentacion;

import controladores.Fabrica;
import controladores.ISistema;
import datatypes.DataActividad;
import datatypes.DataSalida;
import datatypes.DataUsuario;
import excepciones.UsuarioNoExisteException;
import java.awt.Image;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import logica.Usuario;

public class ConsultaUsuario extends javax.swing.JInternalFrame {

    ISistema sys;

    public String tipo;
    public String correo;
    public String[] arreglo = new String[]{};
    public String[] arreglo2 = new String[]{};
    public String[] aux = new String[]{};
    DataUsuario usr;

    /*
    Turista tur;
    Proveedor prov;
     */
    public ConsultaUsuario() {
        sys = new Fabrica().getSistema();
        Usuario.usuariosMail.clear();
        sys.getAllUsersFromBD();
        initComponents();
        this.setSize(700, 300);

        jComboBox_correo.setSelectedItem(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox_correo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_info = new javax.swing.JTable();
        jLabel_imagen = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_actividades = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_salidas = new javax.swing.JTable();
        comboboxActividad = new javax.swing.JComboBox<>();
        comboboxSalida = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consulta de Usuario");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Ingrese correo a consultar:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        try{
            DataUsuario[] dataUsuarios = sys.getUsuarios();
            String[] correosU = new String[dataUsuarios.length];
            for(int i = 0; i< dataUsuarios.length;i++){
                correosU[i] = dataUsuarios[i].getCorreo();
            }
            jComboBox_correo.setModel(new javax.swing.DefaultComboBoxModel<>(correosU));
        }catch(UsuarioNoExisteException e){}
        jComboBox_correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_correoActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 320, -1));

        jTable_info.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nickname", "Nombre", "Apellido", "Correo", "Fecha Nac", "Usuario", "Nacionalidad", "Descripción", "Sitio Web"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable_info);
        if (jTable_info.getColumnModel().getColumnCount() > 0) {
            jTable_info.getColumnModel().getColumn(0).setResizable(false);
            jTable_info.getColumnModel().getColumn(1).setResizable(false);
            jTable_info.getColumnModel().getColumn(2).setResizable(false);
            jTable_info.getColumnModel().getColumn(3).setResizable(false);
            jTable_info.getColumnModel().getColumn(4).setResizable(false);
            jTable_info.getColumnModel().getColumn(5).setResizable(false);
            jTable_info.getColumnModel().getColumn(6).setResizable(false);
            jTable_info.getColumnModel().getColumn(7).setResizable(false);
            jTable_info.getColumnModel().getColumn(8).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 810, 90));
        getContentPane().add(jLabel_imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 110, 90));

        jTable_actividades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NombreAct", "Descripción", "Duración", "Precio"
            }
        ));
        jScrollPane2.setViewportView(jTable_actividades);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 640, 70));

        jTable_salidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre Salida", "Fecha de Alta", "Fecha de Salida", "Cantidad Máxima de Turistas", "Cantidad de Inscritos"
            }
        ));
        jScrollPane3.setViewportView(jTable_salidas);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 640, 80));

        comboboxActividad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        comboboxActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxActividadActionPerformed(evt);
            }
        });
        getContentPane().add(comboboxActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 330, -1));

        comboboxSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        comboboxSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxSalidaActionPerformed(evt);
            }
        });
        getContentPane().add(comboboxSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 330, -1));

        jLabel2.setText("Ingrese la actividad:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel3.setText("Ingrese la salida:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
    private void jComboBox_correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_correoActionPerformed
        if (jComboBox_correo.getSelectedItem() == null) {
            return;
        }
        correo = jComboBox_correo.getSelectedItem().toString();

        if (Usuario.obtenerUsuario(correo) instanceof Proveedor) {
            Proveedor prov = (Proveedor) Usuario.obtenerUsuario(correo);
            String arregloActsProv[] = sys.listarActividadesProveedor(prov);
            DefaultComboBoxModel<String> model =new DefaultComboBoxModel<>(arregloActsProv);
            jComboBox1.setModel(model);
        }

        DefaultTableModel tableModel = (DefaultTableModel) jTable_info.getModel();
        DefaultTableModel actividadesModel = (DefaultTableModel) jTable_actividades.getModel();
        DefaultTableModel salidasModel = (DefaultTableModel) jTable_salidas.getModel();

        // Elimina las filas existentes de las tablas antes de llenarlas nuevamente
        tableModel.setRowCount(0);
        actividadesModel.setRowCount(0);
        salidasModel.setRowCount(0);

        correo = jComboBox_correo.getSelectedItem().toString();

        Usuario usuario = Usuario.obtenerUsuario(correo);

        int i = 0;
        if (usuario instanceof Turista) {
            Turista turista = (Turista) usuario;
            arreglo = new String[turista.getSalidasInscritas().size()];
            for (SalidasTuristicas salida : turista.getSalidasInscritas()) {
                arreglo[i] = salida.getNombreSalida();
                System.out.println(salida.getNombreSalida());
                i++;
            }
                    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(arreglo);
         DefaultComboBoxModel<String> modelVacio = new DefaultComboBoxModel<>(new String [0]);
        jComboBox1.setModel(modelVacio);
        jComboBox2.setModel(model);
        }

        if (usuario != null) {
            // Llenar la información básica del usuario
            if (usuario instanceof Proveedor) {
                llenarInformacionBasicaProveedor((Proveedor) usuario, tableModel);
            } else if (usuario instanceof Turista) {
                llenarInformacionBasicaTurista((Turista) usuario, tableModel);
            }
            mostrarImagenPerfil(usuario.getImagenPerfil());
        }


    }//GEN-LAST:event_jComboBox_correoActionPerformed

    */
    private void jComboBox_correoActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (jComboBox_correo.getSelectedItem() == null) {
                return;
            }
            correo = jComboBox_correo.getSelectedItem().toString();

            usr = sys.verInfoUsuario(correo);
            //System.out.println(usr.getNombre());
            /*
            if (usr == null) {
                return;
            }*/
            if (usr.getTipo().equals("Proveedor")) {

                String arregloActsProv[] = sys.listarActividadesProveedor(correo);
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(arregloActsProv);
                comboboxActividad.setModel(model);
            }

            DefaultTableModel tableModel = (DefaultTableModel) jTable_info.getModel();
            DefaultTableModel actividadesModel = (DefaultTableModel) jTable_actividades.getModel();
            DefaultTableModel salidasModel = (DefaultTableModel) jTable_salidas.getModel();

            // Elimina las filas existentes de las tablas antes de llenarlas nuevamente
            tableModel.setRowCount(0);
            actividadesModel.setRowCount(0);
            salidasModel.setRowCount(0);

            int i = 0;
            if (usr.getTipo().equals("Turista")) {
                String[] arreglo = sys.obtenerSalidasInscritasTurista(correo);
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(arreglo);
                DefaultComboBoxModel<String> modelVacio = new DefaultComboBoxModel<>(new String[0]);
                comboboxActividad.setModel(modelVacio);
                comboboxSalida.setModel(model);
            }

            if (usr != null) {
                // Llenar la información básica del usuario
                if (usr.getTipo().equals("Proveedor")) {
                    llenarInformacionBasicaProveedor(usr, tableModel);
                } else if (usr.getTipo().equals("Turista")) {
                    llenarInformacionBasicaTurista(usr, tableModel);
                }
                mostrarImagenPerfil(usr.getImagenP());
            }
        } catch (UsuarioNoExisteException ex) {

        }
    }

    /*
    private void comboboxActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxActividadActionPerformed
        DefaultTableModel actividadesModel = (DefaultTableModel) jTable_actividades.getModel();
        DefaultTableModel salidasModel = (DefaultTableModel) jTable_salidas.getModel();
        actividadesModel.setRowCount(0);
        salidasModel.setRowCount(0);
        Usuario usuario = Usuario.obtenerUsuario(correo);
        
        
        int i = 0;

        if (usuario != null) {
            if (usuario instanceof Turista) {
                Turista turista = (Turista) usuario;
                arreglo = new String[turista.getSalidasInscritas().size()];
                for (SalidasTuristicas salida : turista.getSalidasInscritas()) {
                    arreglo[i] = salida.getNombreSalida();
                    System.out.println(salida.getNombreSalida());
                    i++;
                }
            }
            int j = 0;
            if (usuario instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) usuario;
                System.out.print(jComboBox1.getSelectedItem().toString());
                arreglo2 = sys.listarSalidasActividad(jComboBox1.getSelectedItem().toString());
                for (i = 0; i < arreglo.length; i++) {
                    System.out.print(arreglo[i] + "\n");
                }
            }
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(arreglo2);
        jComboBox2.setModel(model);

        if (usuario != null) {
            if (usuario instanceof Proveedor) {
                // Si es un proveedor, llenar las actividades turísticas y salidas asociadas
                llenarTablaActividadesProveedor((Proveedor) usuario, actividadesModel);
            } else if (usuario instanceof Turista) {
                // Si es un turista, llenar las salidas a las que se inscribió
                llenarTablaSalidasInscritasTurista((Turista) usuario, salidasModel);
            }
            mostrarImagenPerfil(usuario.getImagenPerfil());
        }

        

    }//GEN-LAST:event_comboboxActividadActionPerformed
*/
    private void comboboxActividadActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            DefaultTableModel actividadesModel = (DefaultTableModel) jTable_actividades.getModel();
            DefaultTableModel salidasModel = (DefaultTableModel) jTable_salidas.getModel();
            actividadesModel.setRowCount(0);
            salidasModel.setRowCount(0);

            usr = sys.verInfoUsuario(correo);

            if (usr.getCorreo() != null) {
                if (usr.getTipo().equals("Turista")) {

                    //arreglo = new String[turista.getSalidasInscritas().size()];
                    arreglo = sys.obtenerSalidasInscritasTurista(correo);

                }

                if (usr.getTipo().equals("Proveedor")) {

                    arreglo2 = sys.listarSalidasActividad(comboboxActividad.getSelectedItem().toString());
                }
            }
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(arreglo2);
            comboboxSalida.setModel(model);

            if (usr.getCorreo() != null) {
                if (usr.getTipo().equals("Proveedor")) {
                    // Si es un proveedor, llenar las actividades turísticas
                    llenarTablaActividadesProveedor(usr.getCorreo(), actividadesModel);
                } else if (usr.getTipo().equals("Turista")) {
                    // Si es un turista, llenar las salidas a las que se inscribió
                    llenarTablaSalidasInscritasTurista(usr.getCorreo(), salidasModel);
                }
                mostrarImagenPerfil(usr.getImagenP());
            }
        } catch (UsuarioNoExisteException ex) {

        }

    }


    private void comboboxSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxSalidaActionPerformed
        DefaultTableModel actividadesModel = (DefaultTableModel) jTable_actividades.getModel();
        DefaultTableModel salidasModel = (DefaultTableModel) jTable_salidas.getModel();
        actividadesModel.setRowCount(0);
        salidasModel.setRowCount(0);
        // Usuario otra vez para que???
        //Usuario usuario = Usuario.obtenerUsuario(correo);

        if (usr != null) {
            if (usr.getTipo().equals("Proveedor")) {
                // Si es un proveedor, llenar las actividades turísticas y salidas asociadas
                llenarTablaActividadesProveedor(usr.getCorreo(), actividadesModel);
                llenarTablaSalidasAsociadasProveedor(usr.getCorreo(), salidasModel);
            } else if (usr.getTipo().equals("Turista")) {
                // Si es un turista, llenar las salidas a las que se inscribió
                llenarTablaSalidasInscritasTurista(usr.getCorreo(), salidasModel);
            }
            mostrarImagenPerfil(usr.getImagenP());
        }
    }//GEN-LAST:event_comboboxSalidaActionPerformed

    private void mostrarImagenPerfil(String rutaImagenPerfil) {
        if (rutaImagenPerfil != null && !rutaImagenPerfil.isEmpty()) {
            ImageIcon icono = new ImageIcon(new ImageIcon(rutaImagenPerfil).getImage()
                    .getScaledInstance(jLabel_imagen.getWidth(), jLabel_imagen.getHeight(), Image.SCALE_SMOOTH));
            jLabel_imagen.setIcon(icono);
        } else {
            jLabel_imagen.setIcon(null);
        }
    }

    private void llenarInformacionBasicaProveedor(DataUsuario dataUsuario, DefaultTableModel tableModel) {
        Object[] rowData = {
            dataUsuario.getNick(),
            dataUsuario.getNombre(),
            dataUsuario.getApellido(),
            dataUsuario.getCorreo(),
            dataUsuario.getFecNac(),
            dataUsuario.getTipo(), // Utilizamos el campo "tipo" de DataUsuario
            null, // Nacionalidad no aplicable para proveedores
            dataUsuario.getDescripcion(),
            dataUsuario.GetWeb()
        };
        tableModel.addRow(rowData);
    }

    private void llenarInformacionBasicaTurista(DataUsuario dataUsuario, DefaultTableModel tableModel) {
        Object[] rowData = {
            dataUsuario.getNick(),
            dataUsuario.getNombre(),
            dataUsuario.getApellido(),
            dataUsuario.getCorreo(),
            dataUsuario.getFecNac(),
            dataUsuario.getTipo(), // Utilizamos el campo "tipo" de DataUsuario
            dataUsuario.getNacionalidad(),
            null, // Descripción no aplicable para turistas
            null // Sitio web no aplicable para turistas
        };
        tableModel.addRow(rowData);
    }

// Agrega lógica para llenar la tabla de actividades turísticas del proveedor
    private void llenarTablaActividadesProveedor(String correoProveedor, DefaultTableModel actividadesModel) {
        DataActividad[] acts = sys.dataActividadesProveedor(correoProveedor);
        for (DataActividad actividad : acts) {
            if (comboboxActividad.getSelectedItem() == actividad.getNombre()) {
                Object[] actividadData = {
                    actividad.getNombre(),
                    actividad.getDescripcion(),
                    actividad.getDuracionHoras(),
                    actividad.getCostoPorTurista()
                };
                actividadesModel.addRow(actividadData);
            }
        }
    }

// Agrega lógica para llenar la tabla de salidas asociadas al proveedor
    private void llenarTablaSalidasAsociadasProveedor(String correoProveedor, DefaultTableModel salidasModel) {
        DataSalida[] salidasProveedor = sys.dataSalidasActividadesProveedor(correoProveedor);
        String nombreSalida = comboboxSalida.getSelectedItem().toString();
        DataSalida salida = sys.datosSalida(nombreSalida, "", nombreSalida);

        if (comboboxSalida.getSelectedItem() == salida.getNombreSalida()) {
            Object[] salidaData = {
                salida.getNombreSalida(),
                salida.getFechaAlta(),
                salida.getFechaSalida(),
                salida.getCantidadMaximaTuristas(),
                salida.getCantInscritos()
            };
            salidasModel.addRow(salidaData);
        }
    }

// Agrega lógica para llenar la tabla de salidas a las que se inscribió el turista
    private void llenarTablaSalidasInscritasTurista(String correoTurista, DefaultTableModel salidasModel) {
        DataSalida salidasdata[] = sys.DataSalidasTurista(correo);
        for (DataSalida salida : salidasdata) {
            if (comboboxSalida.getSelectedItem() == salida.getNombreSalida()) {
                Object[] salidaData = {
                    salida.getNombreSalida(),
                    salida.getFechaAlta(),
                    salida.getFechaSalida(),
                    salida.getCantidadMaximaTuristas(),
                    salida.getCantInscritos()
                };
                salidasModel.addRow(salidaData);
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboboxActividad;
    private javax.swing.JComboBox<String> comboboxSalida;
    private javax.swing.JComboBox<String> jComboBox_correo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_imagen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_actividades;
    private javax.swing.JTable jTable_info;
    private javax.swing.JTable jTable_salidas;
    // End of variables declaration//GEN-END:variables
}
