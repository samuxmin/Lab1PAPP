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
    private String tipo = null;
    private String apellido;
    private String correo;
    private LocalDate fecNac;
    private String nacionalidad;
    private String web;
    private String descripcion;
    private String imagenP;
    
   
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
        public DataUsuario(String nick,String nombre, String apellido, String correo, LocalDate fecNac, String nacionalidad, String web, String descripcion, String imagenP) {
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.setFecNac(fecNac);
        this.setNick(nick);
        this.setNacionalidad(nacionalidad);
        this.setWeb(web);
        this.setDescripcion(descripcion);
        this.setImagenP(imagenP);
    }

    public String getTipo() {
        return tipo;
    }
    public String getImagenP(){
        return imagenP;
    }
    public void setImagenP(String imagenP){
        this.imagenP=imagenP;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getWeb() {
        return web;
    }
    public String GetWeb() {
        return web;
    }

    public String getCorreo() {
        return correo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public LocalDate getFecNac(){
        return fecNac;
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
