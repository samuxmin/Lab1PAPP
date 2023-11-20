package controladores;

import datatypes.DataActividad;
import datatypes.DataPaquete;
import datatypes.DataSalida;
import datatypes.DataUsuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import logica.ActividadTuristica;
import logica.Categoria;
import logica.Departamento;
import logica.Inscripcion_general;
import logica.Inscripcion_paquete;
import logica.Paquete;
import logica.Proveedor;
import logica.SalidasTuristicas;
import logica.Turista;
import logica.Usuario;

public interface ISistema {

// 1 POLPO
    public abstract Usuario getProveedorDB(String email);

    public abstract boolean confirmarCreacionPaquete(String nombre, String descripcion, int validez, int descuento, LocalDate alta, String imagen);

    public abstract void registrarUsuario(String nick, String name, String ap, String mail, LocalDate fecNac, String tipoUsuario, String nacionalidad, String descripcion, String web, String imagenPerfil, String password);

    public abstract boolean AltaActividadTuristica(String correoProveedor, String nombreAct, String descripcion, Integer duracion, double costo, String depto, LocalDate fechaA, String ciudad, ArrayList<String> eleccionesCategoria, String imagen, String urlVideo);

    public abstract void modificarDatosUsuario(String mail, String nuevoNombre, String nuevoApellido, LocalDate nuevaFechaNacimiento, String imagenP);

    public abstract EntityManager getEntityManager();

    public abstract DataUsuario verInfoUsuario(String mail);

    public abstract DataUsuario[] getUsuarios();

    public abstract void AltaSalidaTuristicaDepto(String nombre, String lugar, int cantidad, LocalDate alta, LocalDate fecSal, String nombreAct, String nombreDepto, String imagen, LocalTime hora);

    public DataActividad ConsultaActTuristica(String ciudad, String nombreAct);

    public abstract void confirmarInscripcion(int cantTurista, double costogral, String tipo);

    /*
    public abstract void confirmarAltaSalida();

    public abstract void AltaSalidaTuristica(String correoProveedor, String nombreSalida, String lugar, Integer cantTuristas, LocalDate fechaSalida, LocalDate fechaAlta);
     */
    // 2 SAMUEL
    public abstract Departamento selectDepartamento(String nombreDepartamento);

    public abstract ActividadTuristica selectActividad(String nombre);

    public abstract Paquete selectPaquete(String nombre_paquete);

    public abstract boolean inscripcionSalida(String mailTurista, String idSalida, int cantTurista, LocalDate fechaInscr);

    public abstract void getObjectsFromDB();

    public abstract boolean estaInscritoTuristaSalida(String mailTurista, String nombreSalida);

    public abstract boolean excedeLimiteInscripcion(String nombreSalida, int cantTurista);

    public abstract String[] obtenerSalidasInscritasTurista(String correoTurista);

    public abstract boolean validarCredenciales(String email, String password);

    DataUsuario verInfoUsuarioNick(String nick);

    public abstract List<Paquete> getAllPaqsFromBD();

    public abstract List<ActividadTuristica> getAllActsFromBD();

//3 BRUNO
    public abstract DataActividad infoActividad(String nombreActividad);

    public SalidasTuristicas getSalidaByNombre(String nombreSalida);

    public abstract String getTipo(String nick);

    public List<Categoria> getAllCatsFromBD();

    public abstract List<String> getAllActsFromPaqFromBD(String paquete);

    public abstract DataPaquete infoPaquete(String nombrePaquete);

    public abstract List<String> getAllActsdeCatFromBD(String categoria);

    public abstract List<Departamento> getAllDepFromBD();

    public abstract List<String> getAllActsdeDepFromBD(String depto);

    public abstract ActividadTuristica selectActFromDB(String nombreact);

    public abstract List<String> getAllCatsdeActFromBD(String act);

    public abstract Categoria selectCatFromDB(String nombreact);
//4 FACU

    public abstract List<String> getAllPaqsdeActFromBD(String act);

    public abstract List<String> getAllSaldeActFromBD(String act);

    public abstract SalidasTuristicas getSalidaFromBD(String nombre);

    public abstract Paquete getPaqFromBD(String nombrePaquete);

    public abstract void InscripcionPaquete(String nombreP1, String email1, float costoPaquete1, int cantidad1, LocalDate vencimiento1, LocalDate fechaCompra1);

    public abstract List<String> getAllPaqsConActFromBD();

    public abstract List<String> getAllCatFromPaqFromBD(String paquete);

    public abstract List<String> TurConPaqFromBD(String email, String nombrepaq);

    public abstract void mergeInscrPaq(Inscripcion_paquete ins);

    public List<String> listarPaqInscritoTurContieneAct(String mailTurista, String nombreActividad);

    public abstract String[] listarDepartamentos();

    public abstract String[] listarActividadesConfirmadasDeptoArreglo(String depto);

    public List<ActividadTuristica> getActsProveedorBD(String correoP);

    public abstract Usuario getUsuarioDB(String email);

    public abstract boolean estaSeguidofromDB(String Usuario, String UsuarioSeguido);

    public abstract List<String> getSeguidoresBD(String UsuarioSeguido);

    public abstract List<String> getSeguidosBD(String Usuario);

    public abstract void seguirUsuario(Usuario usr, Usuario seguir);

    public abstract void dejarDeSeguirUsuario(Usuario usr, Usuario noseguir);

    public abstract Turista getTuristaDB(String email);

    public boolean tieneSalidasActivasAct(String nombreAct);

    public void finalizarActividad(String nombreAct);

    public List<String> getActsFavoritasUsr(String correoUsr);

    public void toggleActFavorita(String correoUsr, String nombreAct, boolean favorito);

    public void aumentarVisitasActividad(String actividad);

    public void aumentarVisitasSalida(String salida);

    public List<ActividadTuristica> buscarActividades(String texto);

    public List<Paquete> buscarPaquetes(String texto);

    public boolean quedanPuestosPaqAct(String mailTur, String nombrePaq, String actividad, int cantidad);

    public void aumentarUsosCompraPaq(int cantidad, String mailTur, String nombrePaq);

    public Inscripcion_general getInscrGen(Long idInscr);

    public Inscripcion_paquete getInscrPaq(Long idInscr);
}
