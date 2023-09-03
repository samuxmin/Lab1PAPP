/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.time.LocalDate;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import java.util.Collection;
import logica.Usuario;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Turista extends Usuario{
    
    @ManyToMany
    private Collection<Inscripcion_general> inscripciongeneral;
    
    @ManyToMany
    private Collection<Inscripcion_paquete> inscripcionpaquete;
    
    private String nacionalidad;
    public Turista(){
        super();
    };
    public Turista(String nick, String name, String apll, String mail, LocalDate fecNac,String nacionalidad){
        super( nick,  name,  apll,  mail, fecNac);
        this.nacionalidad = nacionalidad;
    }
    public String getNacionalidad(){
    return nacionalidad;
    }

    public void addTurista(Usuario usu) {//DESPUES DE PROBAR QUE FUNCIONA AGREGAR LOS GETTER
        //String mail = usu.getCorreo();
        usuariosMail.put("turista1@mail.com", new Turista("nick2", "NombreTurista1", "ApellidoTurista1", "turista1@mail.com", LocalDate.of(1985, 9, 20), "CubaLibre"));
    }


    public Usuario obtenerTurista(String mail) {
        return ((Usuario) usuariosMail.get(mail));
    }
    public void getTurista(){
     for (Map.Entry<String, Usuario> entry : usuariosMail.entrySet()) {
            Usuario usuario = entry.getValue();
            if (usuario instanceof Turista) {
                Turista turista = (Turista) usuario;
                System.out.println("Nombre"+turista.getNombre()+" Apellido"+turista.getApellido()+" Correo"+turista.getCorreo()+" Nickname"+turista.getNick()+" Fecha de Nacimiento"+turista.getFecnac()+" Nacionalidad"+turista.getNacionalidad());
            } 
          }
        }
    }


    /*
    public Turista[] getTuristas() {
        if (usuariosMail.isEmpty())
            return null;
        else {
            Collection<Usuario> usrs = usuariosMail.values();
           if ( instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) usuario;
            Object[] o = usrs.toArray();
            Turista[] turistas = new Usuario[o.length];
            for (int i = 0; i < o.length; i++) {
                turistas[i] = (Turista) o[i];
            }
            return turistas;
        }
    }
*/

