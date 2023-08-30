package datatypes;

import java.time.LocalDate;

/**
 * Datatype para transportar la información de un usuario entre capa lógica y de presentación.
 * En Java los datatypes se definen con setters y getters, y se denominan JavaBean.
 * @author TProg2017
 *
 */
public class DataUsuario {
    private String nick;
    private String nombre;
    private String apellido;
    private String correo;
    private LocalDate fecNac;


    public DataUsuario() {
        this.setNombre(new String());
        this.setApellido(new String());
        this.setCorreo(new String());
        this.setNick(new String());
    }

    public DataUsuario(String nick,String nombre, String apellido, String correo, LocalDate fecNac ) {
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.setFecNac(fecNac);
        this.setNick(nick);
    }

    public String getNombre() {
        return nombre;
    }
     public String getNick() {
        return nick;
    }
    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }
    public String getFecNac(){
        return fecNac.toString();
    }

    /* Sirve para mostrar textualmente la información del usuario, por ejemplo en un ComboBox
     */
    public String toString() {
        return getNick() + " " +  getCorreo() +" (" + getNombre() + " " + getApellido() + getFecNac()+")";
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void setApellido(String apellido) {
        this.apellido = apellido;
    }

    private void setCorreo(String correo) {
        this.correo = correo;
    }
    
    private void setNick(String nick) {
        this.nick = nick;
    }

    private void setFecNac(LocalDate fecNac) {
        this.fecNac = fecNac;
    }
}
