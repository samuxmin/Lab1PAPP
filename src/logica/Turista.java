/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datatypes.DataUsuario;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.ArrayList;
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
public class Turista extends Usuario {

    @ManyToMany
    private Collection<Inscripcion_general> inscripciongeneral;

    @ManyToMany
    private Collection<Inscripcion_paquete> inscripcionpaquete;

    private String nacionalidad;

    public Turista() {
        super();
    }

    ;
    public Turista(String nick, String name, String apll, String mail, LocalDate fecNac, String nacionalidad) {
        super(nick, name, apll, mail, fecNac);
        this.nacionalidad = nacionalidad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void addTurista(Usuario usu) {//DESPUES DE PROBAR QUE FUNCIONA AGREGAR LOS GETTER
        //String mail = usu.getCorreo();
        usuariosMail.put("turista1@mail.com", new Turista("nick2", "NombreTurista1", "ApellidoTurista1", "turista1@mail.com", LocalDate.of(1985, 9, 20), "CubaLibre"));
    }

    public Usuario obtenerTurista(String mail) {
        return ((Usuario) usuariosMail.get(mail));
    }

    public void getTurista() {
        for (Map.Entry<String, Usuario> entry : usuariosMail.entrySet()) {
            Usuario usuario = entry.getValue();
            if (usuario instanceof Turista) {
                Turista turista = (Turista) usuario;
                System.out.println("Nombre" + turista.getNombre() + " Apellido" + turista.getApellido() + " Correo" + turista.getCorreo() + " Nickname" + turista.getNick() + " Fecha de Nacimiento" + turista.getFecnac() + " Nacionalidad" + turista.getNacionalidad());
            }
        }
    }

    public static Turista[] getTuristas() {
        Turista[] turistasArr; //Arreglo de turistas a devolver
        if (usuariosMail.isEmpty()) {
            return null;
        } else {
            Collection turistas = new ArrayList(); //Coleccion para ir agregando y transformar a arreglo

            Usuario[] usuariosArr = (Usuario[]) usuariosMail.values().toArray(); //Array con todos los usuarios
            for(int i = 0; i<usuariosArr.length ;i++){
                if(usuariosArr[i] instanceof Turista){
                    turistas.add(usuariosArr[i]); // va por cada usuario y si es turista lo agrega
                }
            }
            turistasArr = (Turista[]) turistas.toArray(); // convierte la coleccion a array
        }
        return turistasArr;
    }
    
    public DataUsuario devolverData(){
        return new DataUsuario(nickname,nombre,apellido,correo,fechaNacimiento,nacionalidad,null,null);

    
    }
}


/*
   
 */
