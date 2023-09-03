package datatypes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
    private String nacionalidad;
    private String web;
    private String descripcion;
/*
    public DataUsuario() {
        this.setNombre(new String());
        this.setApellido(new String());
        this.setCorreo(new String());
        this.setNick(new String());
    }
*/
    public DataUsuario(String nick,String nombre, String apellido, String correo, LocalDate fecNac, String nacionalidad, String web, String descripcion) {
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.setFecNac(fecNac);
        this.setNick(nick);
        this.setNacionalidad(nacionalidad);
        this.setWeb(web);
        this.setDescripcion(descripcion);
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String GetWeb() {
        return web;
    }

    public String getCorreo() {
        return correo;
    }
    
    public String getDescripcion() {
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setFecNac(LocalDate fecNac) {
        this.fecNac = fecNac;
    }
    
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setWeb(String web) {
        this.web = web;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
