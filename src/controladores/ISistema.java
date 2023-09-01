package controladores;

import datatypes.DataUsuario;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import java.time.LocalDate;

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
   
  public abstract DataUsuario[] getUsuarios() throws UsuarioNoExisteException;

}





