package controladores;

import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import webservice.Categoria;
import webservice.DataActividad;
import webservice.Departamento;
import webservice.Paquete;
import webservice.SalidasTuristicas;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import webservice.ActividadTuristica;
import webservice.DataPaquete;
import webservice.DataUsuario;
import webservice.Turista;

import webservice.Usuario;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class Sistema {

    

    private static String hello(java.lang.String name) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.hello(name);
    }

    public static String helloaa(String name) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.hello(name);
    }

    public static boolean confirmarCreacionPaquete(String nombre, String descripcion, int validez, int descuento, LocalDate alta, String imagen) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.confirmarCreacionPaquete(nombre, descripcion, validez, descuento, alta.toString(), imagen);
    }

    public static void registrarUsuario(String nick, String name, String ap, String mail, LocalDate fecNac, String tipoUsuario, String nacionalidad, String descripcion, String web, String imagenPerfil, String password) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        try {
            port.registrarUsuario(nick, name, ap, mail, fecNac.toString(), tipoUsuario, nacionalidad, descripcion, web, imagenPerfil, password);
        } catch (Exception ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean AltaActividadTuristica(String correoProveedor, String nombreAct, String descripcion, Integer duracion, double costo, String depto, LocalDate fechaA, String ciudad, ArrayList<String> eleccionesCategoria, String imagen, String urlVideo) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.altaActividadTuristica(correoProveedor, nombreAct, descripcion, duracion, costo, depto, fechaA.toString(), ciudad, eleccionesCategoria, imagen, urlVideo);
    }

    public static void modificarDatosUsuario(String mail, String nuevoNombre, String nuevoApellido, LocalDate nuevaFechaNacimiento, String imagenP) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        try {
            port.modificarDatosUsuario(mail, nuevoNombre, nuevoApellido, nuevaFechaNacimiento.toString(), imagenP);
        } catch (Exception ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DataUsuario verInfoUsuario(String mail) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        try {
            return port.verInfoUsuario(mail);
        } catch (Exception ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<DataUsuario> getUsuarios() {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        try {
            return port.getUsuarios();
        } catch (Exception ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void AltaSalidaTuristicaDepto(String nombre, String lugar, int cantidad, LocalDate alta, LocalDate fecSal, String nombreAct, String nombreDepto, String imagen, LocalTime hora) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        port.altaSalidaTuristicaDepto(nombre, lugar, cantidad, alta.toString(), fecSal.toString(), nombreAct, nombreDepto, imagen, hora.toString());
    }

    public static DataActividad consultaActTuristica(String ciudad, String nombreAct) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.consultaActTuristica(ciudad, nombreAct);
    }

    public static void confirmarInscripcion(int cantTurista, double costogral, String tipo) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        port.confirmarInscripcion(cantTurista, costogral, tipo);
    }

    public static Departamento selectDepartamento(String nombreDepartamento) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.selectDepartamento(nombreDepartamento);
    }

    public static ActividadTuristica selectActividad(String nombre) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.selectActividad(nombre);
    }

    public static Paquete selectPaquete(String nombre_paquete) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.selectPaquete(nombre_paquete);
    }

    public static boolean inscripcionSalida(String mailTurista, String idSalida, int cantTurista, LocalDate fechaInscr) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.inscripcionSalida(mailTurista, idSalida, cantTurista, fechaInscr.toString());
    }

    public static void getObjectsFromDB() {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        port.getObjectsFromDB();
    }

    public static boolean estaInscritoTuristaSalida(String mailTurista, String nombreSalida) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.estaInscritoTuristaSalida(mailTurista, nombreSalida);
    }

    public static boolean excedeLimiteInscripcion(String nombreSalida, int cantTurista) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.excedeLimiteInscripcion(nombreSalida, cantTurista);
    }

    public static List<String> obtenerSalidasInscritasTurista(String correoTurista) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.obtenerSalidasInscritasTurista(correoTurista);
    }

    public static boolean validarCredenciales(String email, String password) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.validarCredenciales(email, password);
    }

    public static DataUsuario verInfoUsuarioNick(String nick) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.verInfoUsuarioNick(nick);
    }

    public static List<Paquete> getAllPaqsFromBD() {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getAllPaqsFromBD();
    }

    public static List<ActividadTuristica> getAllActsProveedorBD(String correoP) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getActsProveedorBD(correoP);
    }

    public static DataActividad infoActividad(String nombreActividad) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.infoActividad(nombreActividad);
    }

    public static SalidasTuristicas getSalidaByNombre(String nombreSalida) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getSalidaByNombre(nombreSalida);
    }

    public static String getTipo(String nick) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getTipo(nick);
    }

    public static List<Categoria> getAllCatsFromBD() {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getAllCatsFromBD();
    }

    public static List<String> getAllActsFromPaqFromBD(String paquete) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getAllActsFromPaqFromBD(paquete);
    }

    public static DataPaquete infoPaquete(String nombrePaquete) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.infoPaquete(nombrePaquete);
    }

    public static List<String> getAllActsdeCatFromBD(String categoria) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getAllActsdeCatFromBD(categoria);
    }

    public static List<Departamento> getAllDepFromBD() {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getAllDepFromBD();
    }

    public static List<String> getAllActsdeDepFromBD(String depto) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getAllActsdeDepFromBD(depto);
    }

    public static ActividadTuristica selectActFromDB(String nombreact) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.selectActFromDB(nombreact);
    }

    public static List<String> getAllCatsdeActFromBD(String act) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getAllCatsdeActFromBD(act);
    }

    public static Categoria selectCatFromDB(String nombreact) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.selectCatFromDB(nombreact);
    }

    public static List<String> getAllPaqsdeActFromBD(String act) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getAllPaqsdeActFromBD(act);
    }

    public static List<String> getAllSaldeActFromBD(String act) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getAllSaldeActFromBD(act);
    }

    public static SalidasTuristicas getSalidaFromBD(String nombre) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getSalidaFromBD(nombre);
    }

    public static Paquete getPaqFromBD(String nombrePaquete) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getPaqFromBD(nombrePaquete);
    }

    public static void InscripcionPaquete(String nombreP1, String email1, float costoPaquete1, int cantidad1, LocalDate vencimiento1, LocalDate fechaCompra1) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        port.inscribirTurAPaquete(nombreP1, email1, costoPaquete1, cantidad1, vencimiento1.toString(), fechaCompra1.toString());
    }

    public static List<String> listarPaqInscritoTurContieneAct(String mailTurista, String nombreActividad) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.listarPaqInscritoTurContieneAct(mailTurista, nombreActividad);
    }

    public static List<String> listarDepartamentos() {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.listarDepartamentos();
    }

    public static List<String> listarActividadesConfirmadasDeptoArreglo(String depto) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.listarActividadesConfirmadasDeptoArreglo(depto);
    }

    public static java.util.List<webservice.ActividadTuristica> getAllActsFromBD() {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getAllActsFromBD();
    }

    public static java.util.List<java.lang.String> getAllPaqsConActFromBD() {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getAllPaqsConActFromBD();
    }

    public static java.util.List<java.lang.String> turConPaqFromBD(java.lang.String email, java.lang.String nombrepaq) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.turConPaqFromBD(email, nombrepaq);
    }

    public Usuario getUsuarioDB(String email) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getUsuarioDB(email);
    }

    public boolean estaSeguidofromDB(String usuario, String usuarioSeguido) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.estaSeguidofromDB(usuario, usuarioSeguido);
    }

    public List<String> getSeguidoresBD(String usuarioSeguido) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getSeguidoresBD(usuarioSeguido);
    }

    public List<String> getSeguidosBD(String usuario) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getSeguidosBD(usuario);
    }

    public void seguirUsuario(String usr, String seguir) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        port.seguirUsuario(usr, seguir);
    }

    public void dejarDeSeguirUsuario(String usr, String noseguir) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        port.dejarDeSeguirUsuario(usr, noseguir);
    }

    public Turista getTuristaDB(String email) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getTuristaDB(email);
    }

    public boolean tieneSalidasActivasAct(String nombreAct) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.tieneSalidasActivasAct(nombreAct);
    }

    public void finalizarActividad(String nombreAct) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        port.finalizarActividad(nombreAct);
    }

    public List<String> getActsFavoritasUsr(String correoUsr) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getActsFavoritasUsr(correoUsr);
    }

    public void toggleActFavorita(String correoUsr, String nombreAct, boolean favorito) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        port.toggleActFavorita(correoUsr, nombreAct, favorito);
    }

    public void aumentarVisitasActividad(String actividad) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        port.aumentarVisitasActividad(actividad);
    }

    public void aumentarVisitasSalida(String salida) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        port.aumentarVisitasSalida(salida);
    }

    public List<ActividadTuristica> buscarActividades(String texto) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.buscarActividades(texto);
    }

    public List<Paquete> buscarPaquetes(String texto) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.buscarPaquetes(texto);
    }

    public void generatePDF(OutputStream outputStream, String touristName, String activityName, String tourName, LocalDate tourDateTime, LocalTime hora, int numOfTourists) {
        Document document = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            document.add(new Paragraph("Información de la Inscripción:"));
            document.add(new Paragraph("Nombre del Turista: " + touristName));
            document.add(new Paragraph("Actividad: " + activityName));
            document.add(new Paragraph("Salida Turística: " + tourName));
            document.add(new Paragraph("Fecha de la Salida: " + tourDateTime.toString()));
            document.add(new Paragraph("Hora de la Salida: " + getHoraSalida(tourName)));
            document.add(new Paragraph("Cantidad de Turistas: " + numOfTourists));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public boolean quedanPuestosPaqAct(String mailTur, String nombrePaq, String actividad, int cantidad) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.quedanPuestosPaqAct(mailTur, nombrePaq, actividad, cantidad);
    }

    public void aumentarUsosCompraPaq(int cantidad, String mailTur, String nombrePaq) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        port.aumentarUsosCompraPaq(cantidad, mailTur, nombrePaq);
    }

    public static String getFecNacUsr(java.lang.String mailUsr) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getFecNacUsr(mailUsr);
    }

    public static String getHoraSalida(java.lang.String nombreSalida) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getHoraSalida(nombreSalida);
    }

    public static String getFechaAltaActividad(java.lang.String nombreAct) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getFechaAltaActividad(nombreAct);
    }

    public static String getFechaAltaPaquete(java.lang.String nombrePaq) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getFechaAltaPaquete(nombrePaq);
    }

    public static String getFechaAltaSalida(java.lang.String nombreSalida) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getFechaAltaSalida(nombreSalida);
    }

    public static String getFechaSalida(java.lang.String nombreSalida) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getFechaSalida(nombreSalida);
    }

    public static java.util.List<java.lang.String> getAllCatFromPaqFromBD(java.lang.String paquete) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getAllCatFromPaqFromBD(paquete);
    }

    public static String getVencimientoInscripcionPaquete(java.lang.Long inscr) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getVencimientoInscripcionPaquete(inscr);
    }

    public static String getFechaInscripcionGeneral(java.lang.Long inscr) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getFechaInscripcionGeneral(inscr);
    }

    public static String getFechaInscripcionPaquete(java.lang.Long inscr) {
        webservice.WSSistema_Service service = new webservice.WSSistema_Service();
        webservice.WSSistema port = service.getWSSistemaPort();
        return port.getFechaInscripcionPaquete(inscr);
    }
}
