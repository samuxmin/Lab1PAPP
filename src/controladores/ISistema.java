package controladores;

import datatypes.DataActividad;
import datatypes.DataDepto;
import datatypes.DataPaquete;
import datatypes.DataSalida;
import datatypes.DataUsuario;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import logica.ActividadTuristica;
import logica.Departamento;
import logica.Paquete;
import logica.Proveedor;

public interface ISistema {
    
    //public abstract void registrarUsuario(String nick,String name, String ap, String mail, LocalDate fecNac) throws UsuarioRepetidoException; //TAQUE
   public abstract boolean confirmarCreacionPaquete(String nombre, String descripcion, int validez, int descuento,LocalDate  alta, String imagen);
    //public abstract void registrarUsuario(String nick, String name, String ap, String mail, LocalDate fecNac, String tipoUsuario, String nacionalidad, String descripcion, String web) throws UsuarioRepetidoException;
    //POLPO ESTA FUNCIONA 
   // public boolean DepartamentoExisteBD(String nombre) ;
    public abstract void registrarUsuario(String nick, String name, String ap, String mail, LocalDate fecNac, String tipoUsuario, String nacionalidad, String descripcion, String web, String imagenPerfil,String password) throws UsuarioRepetidoException;
    public abstract String[] listarCorreosProveedores();
    public abstract String[] listarActividadesDeptoArreglo(String nombreAct);
    public abstract boolean AltaActividadTuristica(String correoProveedor, String nombreAct, String descripcion, Integer duracion, double costo, String depto, LocalDate fechaA, String ciudad, ArrayList<String> eleccionesCategoria, String imagen);
    //public abstract void modificarDatosUsuario(String mail, String nuevoNombre, String nuevoApellido, LocalDate nuevaFechaNacimiento, String nuevaNacionalidad, String nuevaDescripcion, String nuevoSitioWeb) throws UsuarioNoExisteException;
    public abstract void modificarDatosUsuario(String mail, String nuevoNombre, String nuevoApellido, LocalDate nuevaFechaNacimiento) throws UsuarioNoExisteException;
    /*
     * Retorna la información de un usuario con la cédula indicada.
     * @param ci Cédula del usuario.
     * @return Información del usuario.
     * @throws UsuarioNoExisteException Si la cédula del usuario no está registrada en el sistema.
     */
    
    //public abstract boolean crearPaquete(String nombre, String descripcion, int validez, int descuento, LocalDate  alta) ;
    public abstract String[] listarPaquetesArreglo();
   // public abstract boolean usuarioExisteBD(String correo);
   // public abstract boolean usuarioExisteBDPorNick(String nick);
    
    public abstract boolean existePaquete(String nombre);
    public abstract EntityManager getEntityManager();
    
   public abstract DataUsuario verInfoUsuario(String mail) throws UsuarioNoExisteException;
    
    /*
     * Retorna la información de todos los usuarios registrados en el sistema.
     * @return Información de los usuarios del sistema.
     * @throws UsuarioNoExisteException Si no existen usuarios registrados en el sistema.
     */
  public abstract DataPaquete ConsultaPaqueteActTuistica(String nombrePaquete);
  public abstract DataUsuario[] getUsuarios() throws UsuarioNoExisteException;

  public abstract Map<String, DataUsuario> getTurista();
  public abstract void AltaSalidaTuristicaDepto(String nombre, String lugar, int cantidad,LocalDate fecSal, LocalDate alta, String nombreAct,String nombreDepto);
  public abstract Map<String, DataUsuario> getProveedor();
  
    public DataActividad ConsultaActTuristica(String ciudad, String nombreAct);
  //public abstract void listarTurista();
  public abstract boolean AgregarActividadPaquete(ActividadTuristica act,Paquete paq);
  //FACUNDOFT
  //public abstract void AgregarActividadTuristicaPaquete();
 // public abstract void  listarPaquete();
   public  abstract   ActividadTuristica[] listarActividadesDepNoPaquete(String nombre_paquete,String nombreDepto);
   public abstract  Departamento selectDepartamento(String nombreDepartamento);
   public abstract  ActividadTuristica selectActividad(String nombre);
   public abstract Paquete selectPaquete(String nombre_paquete);
   public DataPaquete[] listarPaquetes() ;
    public abstract DataActividad[] listarActividadesDepto(String n);
    public abstract String[] listarSalidasActividad(String actividad);
    public abstract DataSalida datosSalida(String salida, String actividad, String nombreDepto);
    
    public abstract DataSalida[] listarSalidasVigentes(String actividad);
      public abstract DataUsuario[] listarTuristas();
    public abstract boolean inscripcionSalida(String mailTurista, String idSalida, int cantTurista,LocalDate fechaInscr);
        public abstract void confirmarInscripcion(int cantTurista,double costogral);
        

        public abstract String[] listarDepartamentos();
        public abstract void confirmarAltaSalida();
       public abstract void AltaSalidaTuristica(String correoProveedor, String nombreSalida, String lugar, Integer cantTuristas, LocalDate fechaSalida, LocalDate fechaAlta); 
        public abstract DataUsuario[] listarProveedores();
        
        public abstract void AltaDepartamento(String nombreDepto, String descripcion, String url);
        
       public abstract void getObjectsFromDB();
        public abstract void modificarPaquete(String nombre, String descripcion, int validez, int descuento, LocalDate alta);
        public abstract String[] listarPaquetesActArreglo(String actur);
          public abstract boolean estaInscritoTuristaSalida(String mailTurista, String nombreSalida);
    
public abstract boolean excedeLimiteInscripcion(String nombreSalida, int cantTurista);

    public abstract void cargarDatosPrueba() throws UsuarioRepetidoException;

    public abstract ActividadTuristica[] listarActPaquete(Paquete paq);
    //public abstract String[] listarActividadesProveedor(Proveedor proveedor) ;
    
    
    public abstract boolean obtenerCorreoUsuario(String correo);
    
     public abstract String[] listarActividadesProveedor(String correo);
    
     public abstract String[] obtenerSalidasInscritasTurista(String correoTurista);
    
     public abstract DataSalida[] DataSalidasTurista(String turistaCorreo);
     public abstract DataActividad [] dataActividadesProveedor(String proveedorCorreo);
      public abstract DataSalida[] dataSalidasActividadesProveedor(String proveedorCorreo);
      public abstract String[] listarActividadesEnEspera();

    public abstract void aceptarActividad(String actSeleccionada);

    public abstract void rechazarActividad(String actSeleccionada);
    
    public abstract boolean getCategoriaDB(String nombreCat);
    
    public abstract void registrarCategoria(String nombreCat);
    public abstract String[] listarCategorias();
    public abstract boolean AltaCategoriaAct2(String actividad, ArrayList<String> eleccionesCategoria);
    public abstract void AltaCategoriaPaq(String nombreact,String nombre_paquete);
    public abstract void AltaCategoriaAct(String categoria, String actividad, String descripcion, int duracion, double costo, String nombreDepto, LocalDate fechaA, String ciudad, String imagen);
    public abstract ActividadTuristica selectActividadBD(String nombre);
    public abstract List<Paquete> getAllPaqsFromBD();
    public abstract List<ActividadTuristica> getAllActsFromBD();
    public abstract DataActividad infoActividad(String nombreActividad);
    public abstract void confirmarAltaSalida2();
    public abstract void AltaSalidaTuristica2(String correoProveedor, String nombreSalida, String lugar, Integer cantTuristas, LocalDate fechaSalida, LocalDate fechaAlta,String imagen,LocalTime hora); 
    public abstract void InscripcionPaquete(String nombreP1, String email1, float costoPaquete1, int cantidad1, LocalDate vencimiento1, LocalDate fechaCompra1) ;
    public abstract Paquete getPaqFromBD(String nombrePaquete);
 public abstract List <ActividadTuristica> getActDeProvFromBD (String correoProv);
public abstract void getAllUsersFromBD() ;
}

    



