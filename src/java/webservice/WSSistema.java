/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package webservice;

import controladores.Fabrica;
import controladores.ISistema;
import datatypes.DataActividad;
import datatypes.DataPaquete;
import datatypes.DataUsuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import logica.ActividadTuristica;
import logica.Categoria;
import logica.Departamento;
import logica.Inscripcion_general;
import logica.Inscripcion_paquete;
import logica.Paquete;
import logica.Usuario;
import logica.SalidasTuristicas;
import logica.Turista;

/**
 *
 * @author samuel
 */
@WebService(serviceName = "WSSistema")
public class WSSistema {

    ISistema sys = new Fabrica().getSistema();

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "confirmarCreacionPaquete")
    public boolean confirmarCreacionPaquete(
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "validez") int validez,
            @WebParam(name = "descuento") int descuento,
            @WebParam(name = "alta") String alta,
            @WebParam(name = "imagen") String imagen
    ) {
        return sys.confirmarCreacionPaquete(nombre, descripcion, validez, descuento, LocalDate.parse(alta), imagen);
    }

// Web method para registrar usuario
    @WebMethod(operationName = "registrarUsuario")
    public void registrarUsuario(
            @WebParam(name = "nick") String nick,
            @WebParam(name = "name") String name,
            @WebParam(name = "ap") String ap,
            @WebParam(name = "mail") String mail,
            @WebParam(name = "fecNac") String fecNac,
            @WebParam(name = "tipoUsuario") String tipoUsuario,
            @WebParam(name = "nacionalidad") String nacionalidad,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "web") String web,
            @WebParam(name = "imagenPerfil") String imagenPerfil,
            @WebParam(name = "password") String password
    ) {
        sys.registrarUsuario(nick, name, ap, mail, LocalDate.parse(fecNac), tipoUsuario, nacionalidad, descripcion, web, imagenPerfil, password);
    }

// Web method para dar de alta una actividad turística
    @WebMethod(operationName = "AltaActividadTuristica")
    public boolean AltaActividadTuristica(
            @WebParam(name = "correoProveedor") String correoProveedor,
            @WebParam(name = "nombreAct") String nombreAct,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "duracion") Integer duracion,
            @WebParam(name = "costo") double costo,
            @WebParam(name = "depto") String depto,
            @WebParam(name = "fechaA") String fechaA,
            @WebParam(name = "ciudad") String ciudad,
            @WebParam(name = "eleccionesCategoria") ArrayList<String> eleccionesCategoria,
            @WebParam(name = "imagen") String imagen,
            @WebParam(name = "urlVideo") String urlVideo
    ) {
        return sys.AltaActividadTuristica(correoProveedor, nombreAct, descripcion, duracion, costo, depto, LocalDate.parse(fechaA), ciudad, eleccionesCategoria, imagen, urlVideo);
    }

// Web method para modificar datos de un usuario
    @WebMethod(operationName = "modificarDatosUsuario")
    public void modificarDatosUsuario(
            @WebParam(name = "mail") String mail,
            @WebParam(name = "nuevoNombre") String nuevoNombre,
            @WebParam(name = "nuevoApellido") String nuevoApellido,
            @WebParam(name = "nuevaFechaNacimiento") String nuevaFechaNacimiento,
            @WebParam(name = "imagenP") String imagenP
    ) {
        sys.modificarDatosUsuario(mail, nuevoNombre, nuevoApellido, LocalDate.parse(nuevaFechaNacimiento), imagenP);
    }

    /*
// Web method para obtener el Entity Manager
    @WebMethod(operationName = "getEntityManager")
    public EntityManager getEntityManager() {
        return sys.getEntityManager();
    }*/
// Web method para ver información de un usuario por correo
    @WebMethod(operationName = "verInfoUsuario")
    public DataUsuario verInfoUsuario(@WebParam(name = "mail") String mail) {
        return sys.verInfoUsuario(mail);
    }

// Web method para obtener una lista de usuarios
    @WebMethod(operationName = "getUsuarios")
    public DataUsuario[] getUsuarios() {
        return sys.getUsuarios();
    }

// Web method para dar de alta una salida turística en un departamento
    @WebMethod(operationName = "AltaSalidaTuristicaDepto")
    public void AltaSalidaTuristicaDepto(
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "lugar") String lugar,
            @WebParam(name = "cantidad") int cantidad,
            @WebParam(name = "alta") String alta,
            @WebParam(name = "fecSal") String fecSal,
            @WebParam(name = "nombreAct") String nombreAct,
            @WebParam(name = "nombreDepto") String nombreDepto,
            @WebParam(name = "imagen") String imagen,
            @WebParam(name = "hora") String hora
    ) {
        sys.AltaSalidaTuristicaDepto(nombre, lugar, cantidad, LocalDate.parse(alta), LocalDate.parse(fecSal), nombreAct, nombreDepto, imagen, LocalTime.parse(hora));
    }

// Web method para consultar una actividad turística
    @WebMethod(operationName = "ConsultaActTuristica")
    public DataActividad ConsultaActTuristica(
            @WebParam(name = "ciudad") String ciudad,
            @WebParam(name = "nombreAct") String nombreAct
    ) {
        return sys.ConsultaActTuristica(ciudad, nombreAct);
    }

// Web method para confirmar inscripción en una salida turística
    @WebMethod(operationName = "confirmarInscripcion")
    public void confirmarInscripcion(
            @WebParam(name = "cantTurista") int cantTurista,
            @WebParam(name = "costogral") double costogral,
            @WebParam(name = "tipo") String tipo
    ) {
        sys.confirmarInscripcion(cantTurista, costogral, tipo);
    }
// Web method para seleccionar un departamento por nombre

    @WebMethod(operationName = "selectDepartamento")
    public Departamento selectDepartamento(@WebParam(name = "nombreDepartamento") String nombreDepartamento) {
        return sys.selectDepartamento(nombreDepartamento);
    }

// Web method para seleccionar una actividad turística por nombre
    @WebMethod(operationName = "selectActividad")
    public ActividadTuristica selectActividad(@WebParam(name = "nombre") String nombre) {
        return sys.selectActividad(nombre);
    }

// Web method para seleccionar un paquete por nombre
    @WebMethod(operationName = "selectPaquete")
    public Paquete selectPaquete(@WebParam(name = "nombre_paquete") String nombre_paquete) {
        return sys.selectPaquete(nombre_paquete);
    }

// Web method para inscribir a un turista en una salida turística
    @WebMethod(operationName = "inscripcionSalida")
    public boolean inscripcionSalida(
            @WebParam(name = "mailTurista") String mailTurista,
            @WebParam(name = "idSalida") String idSalida,
            @WebParam(name = "cantTurista") int cantTurista,
            @WebParam(name = "fechaInscr") String fechaInscr
    ) {
        return sys.inscripcionSalida(mailTurista, idSalida, cantTurista, LocalDate.parse(fechaInscr));
    }

// Web method para obtener objetos desde la base de datos
    @WebMethod(operationName = "getObjectsFromDB")
    public void getObjectsFromDB() {
        sys.getObjectsFromDB();
    }

// Web method para verificar si un turista está inscrito en una salida turística
    @WebMethod(operationName = "estaInscritoTuristaSalida")
    public boolean estaInscritoTuristaSalida(
            @WebParam(name = "mailTurista") String mailTurista,
            @WebParam(name = "nombreSalida") String nombreSalida
    ) {
        return sys.estaInscritoTuristaSalida(mailTurista, nombreSalida);
    }

// Web method para verificar si la inscripción excede el límite en una salida turística
    @WebMethod(operationName = "excedeLimiteInscripcion")
    public boolean excedeLimiteInscripcion(
            @WebParam(name = "nombreSalida") String nombreSalida,
            @WebParam(name = "cantTurista") int cantTurista
    ) {
        return sys.excedeLimiteInscripcion(nombreSalida, cantTurista);
    }

// Web method para obtener las salidas inscritas por un turista
    @WebMethod(operationName = "obtenerSalidasInscritasTurista")
    public String[] obtenerSalidasInscritasTurista(@WebParam(name = "correoTurista") String correoTurista) {
        return sys.obtenerSalidasInscritasTurista(correoTurista);
    }

// Web method para validar credenciales de inicio de sesión
    @WebMethod(operationName = "validarCredenciales")
    public boolean validarCredenciales(
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password
    ) {
        return sys.validarCredenciales(email, password);
    }

// Web method para obtener información de un usuario por nick
    @WebMethod(operationName = "verInfoUsuarioNick")
    public DataUsuario verInfoUsuarioNick(@WebParam(name = "nick") String nick) {
        return sys.verInfoUsuarioNick(nick);
    }

// Web method para obtener todos los paquetes desde la base de datos
    @WebMethod(operationName = "getAllPaqsFromBD")
    public List<Paquete> getAllPaqsFromBD() {
        return sys.getAllPaqsFromBD();
    }

// Web method para obtener todas las actividades turísticas desde la base de datos
    @WebMethod(operationName = "getAllActsFromBD")
    public List<ActividadTuristica> getAllActsFromBD() {
        return sys.getAllActsFromBD();
    }
// Web method para obtener información de una actividad turística por nombre

    @WebMethod(operationName = "infoActividad")
    public DataActividad infoActividad(@WebParam(name = "nombreActividad") String nombreActividad) {
        return sys.infoActividad(nombreActividad);
    }

// Web method para obtener una salida turística por nombre
    @WebMethod(operationName = "getSalidaByNombre")
    public SalidasTuristicas getSalidaByNombre(@WebParam(name = "nombreSalida") String nombreSalida) {
        return sys.getSalidaByNombre(nombreSalida);
    }

// Web method para obtener el tipo de un usuario por nick
    @WebMethod(operationName = "getTipo")
    public String getTipo(@WebParam(name = "nick") String nick) {
        return sys.getTipo(nick);
    }

// Web method para obtener todas las categorías desde la base de datos
    @WebMethod(operationName = "getAllCatsFromBD")
    public List<Categoria> getAllCatsFromBD() {
        return sys.getAllCatsFromBD();
    }

// Web method para obtener todas las actividades de un paquete desde la base de datos
    @WebMethod(operationName = "getAllActsFromPaqFromBD")
    public List<String> getAllActsFromPaqFromBD(@WebParam(name = "paquete") String paquete) {
        return sys.getAllActsFromPaqFromBD(paquete);
    }

// Web method para obtener información de un paquete por nombre
    @WebMethod(operationName = "infoPaquete")
    public DataPaquete infoPaquete(@WebParam(name = "nombrePaquete") String nombrePaquete) {
        return sys.infoPaquete(nombrePaquete);
    }

// Web method para obtener todas las actividades de una categoría desde la base de datos
    @WebMethod(operationName = "getAllActsdeCatFromBD")
    public List<String> getAllActsdeCatFromBD(@WebParam(name = "categoria") String categoria) {
        return sys.getAllActsdeCatFromBD(categoria);
    }

// Web method para obtener todos los departamentos desde la base de datos
    @WebMethod(operationName = "getAllDepFromBD")
    public List<Departamento> getAllDepFromBD() {
        return sys.getAllDepFromBD();
    }

// Web method para obtener todas las actividades de un departamento desde la base de datos
    @WebMethod(operationName = "getAllActsdeDepFromBD")
    public List<String> getAllActsdeDepFromBD(@WebParam(name = "depto") String depto) {
        return sys.getAllActsdeDepFromBD(depto);
    }

// Web method para seleccionar una actividad turística desde la base de datos por nombre
    @WebMethod(operationName = "selectActFromDB")
    public ActividadTuristica selectActFromDB(@WebParam(name = "nombreact") String nombreact) {
        return sys.selectActFromDB(nombreact);
    }

// Web method para obtener todas las categorías de una actividad desde la base de datos
    @WebMethod(operationName = "getAllCatsdeActFromBD")
    public List<String> getAllCatsdeActFromBD(@WebParam(name = "act") String act) {
        return sys.getAllCatsdeActFromBD(act);
    }

// Web method para seleccionar una categoría desde la base de datos por nombre
    @WebMethod(operationName = "selectCatFromDB")
    public Categoria selectCatFromDB(@WebParam(name = "nombreact") String nombreact) {
        return sys.selectCatFromDB(nombreact);
    }
// Web method para obtener todas las actividades de un paquete desde la base de datos

    @WebMethod(operationName = "getAllPaqsdeActFromBD")
    public List<String> getAllPaqsdeActFromBD(@WebParam(name = "act") String act) {
        return sys.getAllPaqsdeActFromBD(act);
    }

// Web method para obtener todas las salidas de una actividad desde la base de datos
    @WebMethod(operationName = "getAllSaldeActFromBD")
    public List<String> getAllSaldeActFromBD(@WebParam(name = "act") String act) {
        return sys.getAllSaldeActFromBD(act);
    }

// Web method para obtener una salida turística desde la base de datos por nombre
    @WebMethod(operationName = "getSalidaFromBD")
    public SalidasTuristicas getSalidaFromBD(@WebParam(name = "nombre") String nombre) {
        return sys.getSalidaFromBD(nombre);
    }

// Web method para obtener un paquete desde la base de datos por nombre
    @WebMethod(operationName = "getPaqFromBD")
    public Paquete getPaqFromBD(@WebParam(name = "nombrePaquete") String nombrePaquete) {
        return sys.getPaqFromBD(nombrePaquete);
    }

// Web method para inscribir a un turista en un paquete
    @WebMethod(operationName = "InscribirTurAPaquete")
    public void InscribirTurAPaquete(
            @WebParam(name = "nombreP1") String nombreP1,
            @WebParam(name = "email1") String email1,
            @WebParam(name = "costoPaquete1") float costoPaquete1,
            @WebParam(name = "cantidad1") int cantidad1,
            @WebParam(name = "vencimiento1") String vencimiento1,
            @WebParam(name = "fechaCompra1") String fechaCompra1) {
        sys.InscripcionPaquete(nombreP1, email1, costoPaquete1, cantidad1, LocalDate.parse(vencimiento1), LocalDate.parse(fechaCompra1));
    }

// Web method para obtener todos los paquetes que contienen una actividad
    @WebMethod(operationName = "getAllPaqsConActFromBD")
    public List<String> getAllPaqsConActFromBD() {
        return sys.getAllPaqsConActFromBD();
    }

// Web method para obtener todas las categorías de un paquete desde la base de datos
    @WebMethod(operationName = "getAllCatFromPaqFromBD")
    public List<String> getAllCatFromPaqFromBD(@WebParam(name = "paquete") String paquete) {
        return sys.getAllCatFromPaqFromBD(paquete);
    }

// Web method para obtener los turistas inscritos en un paquete
    @WebMethod(operationName = "TurConPaqFromBD")
    public List<String> TurConPaqFromBD(
            @WebParam(name = "email") String email,
            @WebParam(name = "nombrepaq") String nombrepaq) {
        return sys.TurConPaqFromBD(email, nombrepaq);
    }

// Web method para fusionar la inscripción de un paquete
    @WebMethod(operationName = "mergeInscrPaq")
    public void mergeInscrPaq(@WebParam(name = "ins") Inscripcion_paquete ins) {
        sys.mergeInscrPaq(ins);
    }

// Web method para listar paquetes inscritos por un turista que contienen una actividad
    @WebMethod(operationName = "listarPaqInscritoTurContieneAct")
    public List<String> listarPaqInscritoTurContieneAct(
            @WebParam(name = "mailTurista") String mailTurista,
            @WebParam(name = "nombreActividad") String nombreActividad) {
        return sys.listarPaqInscritoTurContieneAct(mailTurista, nombreActividad);
    }

// Web method para obtener la lista de departamentos
    @WebMethod(operationName = "listarDepartamentos")
    public String[] listarDepartamentos() {
        return sys.listarDepartamentos();
    }

// Web method para obtener la lista de actividades confirmadas en un departamento
    @WebMethod(operationName = "listarActividadesConfirmadasDeptoArreglo")
    public String[] listarActividadesConfirmadasDeptoArreglo(@WebParam(name = "depto") String depto) {
        return sys.listarActividadesConfirmadasDeptoArreglo(depto);
    }

// Web method para obtener la lista de actividades de un proveedor desde la base de datos
    @WebMethod(operationName = "getActsProveedorBD")
    public List<ActividadTuristica> getActsProveedorBD(@WebParam(name = "correoP") String correoP) {
        return sys.getActsProveedorBD(correoP);
    }  // ... Otras funciones ...

    @WebMethod(operationName = "getUsuarioDB")
    public Usuario getUsuarioDB(@WebParam(name = "email") String email) {
        return sys.getUsuarioDB(email);
    }

    @WebMethod(operationName = "estaSeguidofromDB")
    public boolean estaSeguidoFromDB(@WebParam(name = "usuario") String usuario, @WebParam(name = "usuarioSeguido") String usuarioSeguido) {
        return sys.estaSeguidofromDB(usuario, usuarioSeguido);
    }

    @WebMethod(operationName = "getSeguidoresBD")
    public List<String> getSeguidoresBD(@WebParam(name = "usuarioSeguido") String usuarioSeguido) {
        return sys.getSeguidoresBD(usuarioSeguido);
    }

    @WebMethod(operationName = "getSeguidosBD")
    public List<String> getSeguidosBD(@WebParam(name = "usuario") String usuario) {
        return sys.getSeguidosBD(usuario);
    }

    @WebMethod(operationName = "seguirUsuario")
    public void seguirUsuario(@WebParam(name = "usr") String usr, @WebParam(name = "seguir") String seguir) {
        Usuario usru = sys.getUsuarioDB(usr);
        Usuario seguiru = sys.getUsuarioDB(seguir);
        sys.seguirUsuario(usru, seguiru);
    }

    @WebMethod(operationName = "dejarDeSeguirUsuario")
    public void dejarDeSeguirUsuario(@WebParam(name = "usr") String usr, @WebParam(name = "noseguir") String noseguir) {
        Usuario usru = sys.getUsuarioDB(usr);
        Usuario noseguiru = sys.getUsuarioDB(noseguir);
        sys.dejarDeSeguirUsuario(usru, noseguiru);
    }

    @WebMethod(operationName = "getTuristaDB")
    public Turista getTuristaDB(@WebParam(name = "email") String email) {
        return sys.getTuristaDB(email);
    }

    @WebMethod(operationName = "tieneSalidasActivasAct")
    public boolean tieneSalidasActivasAct(@WebParam(name = "nombreAct") String nombreAct) {
        return sys.tieneSalidasActivasAct(nombreAct);
    }

    @WebMethod(operationName = "finalizarActividad")
    public void finalizarActividad(@WebParam(name = "nombreAct") String nombreAct) {
        sys.finalizarActividad(nombreAct);
    }

    @WebMethod(operationName = "getActsFavoritasUsr")
    public List<String> getActsFavoritasUsr(@WebParam(name = "correoUsr") String correoUsr) {
        return sys.getActsFavoritasUsr(correoUsr);
    }

    @WebMethod(operationName = "toggleActFavorita")
    public void toggleActFavorita(@WebParam(name = "correoUsr") String correoUsr, @WebParam(name = "nombreAct") String nombreAct, @WebParam(name = "favorito") boolean favorito) {
        sys.toggleActFavorita(correoUsr, nombreAct, favorito);
    }

    @WebMethod(operationName = "aumentarVisitasActividad")
    public void aumentarVisitasActividad(@WebParam(name = "actividad") String actividad) {
        sys.aumentarVisitasActividad(actividad);
    }

    @WebMethod(operationName = "aumentarVisitasSalida")
    public void aumentarVisitasSalida(@WebParam(name = "salida") String salida) {
        sys.aumentarVisitasSalida(salida);
    }

    @WebMethod(operationName = "buscarActividades")
    public List<ActividadTuristica> buscarActividades(@WebParam(name = "texto") String texto) {
        return sys.buscarActividades(texto);
    }

    @WebMethod(operationName = "buscarPaquetes")
    public List<Paquete> buscarPaquetes(@WebParam(name = "texto") String texto) {
        return sys.buscarPaquetes(texto);
    }

    @WebMethod(operationName = "quedanPuestosPaqAct")
    public boolean quedanPuestosPaqAct(
            @WebParam(name = "mailTur") String mailTur,
            @WebParam(name = "nombrePaq") String nombrePaq,
            @WebParam(name = "actividad") String actividad,
            @WebParam(name = "cantidad") int cantidad) {
        return sys.quedanPuestosPaqAct(mailTur, nombrePaq, actividad, cantidad);
    }

    @WebMethod(operationName = "aumentarUsosCompraPaq")
    public void aumentarUsosCompraPaq(
            @WebParam(name = "cantidad") int cantidad,
            @WebParam(name = "mailTur") String mailTur,
            @WebParam(name = "nombrePaq") String nombrePaq) {
        sys.aumentarUsosCompraPaq(cantidad, mailTur, nombrePaq);
    }

    @WebMethod(operationName = "getFecNacUsr")
    public String getFecNacUsr(@WebParam(name = "mailUsr") String mailUsr) {
        Usuario usr = sys.getUsuarioDB(mailUsr);
        if (usr == null) {
            return "";
        } else {
            return usr.getFechaNacimiento().toString();
        }
    }

    @WebMethod(operationName = "getFechaAltaSalida")
    public String getFechaAltaSalida(@WebParam(name = "nombreSalida") String nombreSalida) {
        SalidasTuristicas s = sys.getSalidaFromBD(nombreSalida);
        if (s == null) {
            return "";
        } else {
            return s.getFechaAlta().toString();
        }

    }

    @WebMethod(operationName = "getFechaSalida")
    public String getFechaSalida(@WebParam(name = "nombreSalida") String nombreSalida) {
        SalidasTuristicas s = sys.getSalidaFromBD(nombreSalida);
        if (s == null) {
            return "";
        } else {
            return s.getFechaSalida().toString();
        }
    }

    @WebMethod(operationName = "getHoraSalida")
    public String getHoraSalida(@WebParam(name = "nombreSalida") String nombreSalida) {
        SalidasTuristicas s = sys.getSalidaFromBD(nombreSalida);
        if (s == null) {
            return "";
        } else {
            return s.getHora().toString();
        }
    }

    @WebMethod(operationName = "getFechaAltaActividad")
    public String getFechaAltaActividad(@WebParam(name = "nombreAct") String nombreAct) {
        ActividadTuristica a = sys.selectActFromDB(nombreAct);
        if (a == null) {
            return "";
        } else {
            return a.getFechaAlta().toString();
        }

    }

    @WebMethod(operationName = "getFechaAltaPaquete")
    public String getFechaAltaPaquete(@WebParam(name = "nombrePaq") String nombrePaq) {
        Paquete p = sys.getPaqFromBD(nombrePaq);
        if (p == null) {
            return "";
        } else {
            return p.getAlta().toString();
        }

    }
    
    
    
    @WebMethod(operationName = "getFechaInscripcionGeneral")
    public String getFechaInscripcionGeneral(@WebParam(name = "inscr") Long inscr) {
        
        Inscripcion_general in= sys.getInscrGen(inscr);
        if (in == null) {
            return "";
        } else {
            return in.getFechaCompra().toString();
        }

    }
     @WebMethod(operationName = "getFechaInscripcionPaquete")
    public String getFechaInscripcionPaquete(@WebParam(name = "inscr") Long inscr) {
        
        Inscripcion_paquete in= sys.getInscrPaq(inscr);
        if (in == null) {
            return "";
        } else {
       
            return in.getFechaCompra().toString();
        }

    }
    
      @WebMethod(operationName = "getVencimientoInscripcionPaquete")
    public String getVencimientoInscripcionPaquete(@WebParam(name = "inscr") Long inscr) {
        
        Inscripcion_paquete in= sys.getInscrPaq(inscr);
        if (in == null) {
            return "";
        } else {
       
            return in.getVencimiento().toString();
        }
    }
}
