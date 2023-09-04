package controladores;

import datatypes.DataActividad;
import datatypes.DataSalida;
import datatypes.DataUsuario;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import java.time.LocalDate;
import java.util.Map;
import logica.ActividadTuristica;
import logica.Departamento;
import logica.Paquete;

public interface ISistema {
    
    //public abstract void registrarUsuario(String nick,String name, String ap, String mail, LocalDate fecNac) throws UsuarioRepetidoException; //TAQUE
  
    public abstract void registrarUsuario(String nick, String name, String ap, String mail, LocalDate fecNac, String tipoUsuario, String nacionalidad, String descripcion, String web) throws UsuarioRepetidoException;
    //POLPO
    
    
    /*
     * Retorna la información de un usuario con la cédula indicada.
     * @param ci Cédula del usuario.
     * @return Información del usuario.
     * @throws UsuarioNoExisteException Si la cédula del usuario no está registrada en el sistema.
     */
    
   public abstract DataUsuario verInfoUsuario(String mail) throws UsuarioNoExisteException;
    
    /*
     * Retorna la información de todos los usuarios registrados en el sistema.
     * @return Información de los usuarios del sistema.
     * @throws UsuarioNoExisteException Si no existen usuarios registrados en el sistema.
     */
  public abstract void ConsultaPaqueteActTuistica(String nombrePaquete);
  public abstract DataUsuario[] getUsuarios() throws UsuarioNoExisteException;

  public abstract Map<String, DataUsuario> getTurista();
  
  public abstract Map<String, DataUsuario> getProveedor();
  
  //public abstract void listarTurista();
  
  //FACUNDOFT
  //public abstract void AgregarActividadTuristicaPaquete();
 // public abstract void  listarPaquete();
   public abstract void listarActividadesDepNoPaquete(String nombre_paquete);
   public abstract  Departamento selectDepartamento(String nombreDepartamento);
   public abstract  ActividadTuristica selectActividad(String nombre);
   public abstract Paquete selectPaquete(String nombre_paquete);
   
    public abstract DataActividad[] listarActividadesDepto(String n);
    public abstract String[] listarSalidasActividad(String actividad);
    public abstract DataSalida datosSalida(String salida, String actividad, String nombreDepto);
    
    public abstract DataSalida[] listarSalidasVigentes(String actividad);
  
}

    



