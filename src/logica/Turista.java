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
import javax.persistence.CascadeType;
import javax.persistence.JoinTable;

@Entity
public class Turista extends Usuario {

@ManyToMany(mappedBy = "turista")
    private Collection<Inscripcion_general> inscripciongeneral;

    @ManyToMany
    private Collection<Inscripcion_paquete> inscripcionpaquete;

    private String nacionalidad;

    public Turista() {
        super();
    }

    ;

    public Collection<Inscripcion_general> getInscripciongeneral() {
        return inscripciongeneral;
    }

    public void setInscripciongeneral(Collection<Inscripcion_general> inscripciongeneral) {
        this.inscripciongeneral = inscripciongeneral;
    }

    public Collection<Inscripcion_paquete> getInscripcionpaquete() {
        return inscripcionpaquete;
    }

    public void setInscripcionpaquete(Collection<Inscripcion_paquete> inscripcionpaquete) {
        this.inscripcionpaquete = inscripcionpaquete;
    }
    public Turista(String nick, String name, String apll, String mail, LocalDate fecNac, String nacionalidad, String imagenP, String password) {
        super(nick, name, apll, mail, fecNac, imagenP,password);
        this.nacionalidad = nacionalidad;
        inscripciongeneral = new ArrayList();
        inscripcionpaquete = new ArrayList();
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /*
    public void addTurista(Usuario usu) {//DESPUES DE PROBAR QUE FUNCIONA AGREGAR LOS GETTER
        //String mail = usu.getCorreo();
        usuariosMail.put("turista1@mail.com", new Turista("nick2", "NombreTurista1", "ApellidoTurista1", "turista1@mail.com", LocalDate.of(1985, 9, 20), "CubaLibre"));
    }
     */
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

    public DataUsuario devolverData() {
        DataUsuario dt = new DataUsuario(nickname, nombre, apellido, correo, fechaNacimiento, nacionalidad, null, null,imagenPerfil);
        dt.setTipo("Turista");
        return dt;
    }

    public static Turista[] getTuristas() {
        Turista[] turistasArr; //Arreglo de turistas a devolver

        Collection<Turista> turistas = new ArrayList(); //Coleccion para ir agregando y transformar a arreglo

        Object[] usuariosArr = usuariosMail.values().toArray(); //Array con todos los usuarios
        for (int i = 0; i < usuariosArr.length; i++) {
            if (usuariosArr[i] instanceof Turista) {
                turistas.add((Turista) usuariosArr[i]); // va por cada usuario y si es turista lo agrega
            }
        }
        turistasArr = new Turista[turistas.size()];
        int i = 0;
        for (Object t : turistas) {
            turistasArr[i] = (Turista) t;
            i++;
        }

// convierte la coleccion a array
        return turistasArr;
    }

    public void agregarInscripcionGral(Inscripcion_general nuevaInscripcion) {
        inscripciongeneral.add(nuevaInscripcion);
    }
    
    
        public Collection<SalidasTuristicas> getSalidasInscritas() {
    Collection<SalidasTuristicas> salidasInscritas = new ArrayList<>();
    
    for (Inscripcion_general inscripcion : inscripciongeneral) {
        SalidasTuristicas salida = inscripcion.getSalida();
        if (salida != null) {
            salidasInscritas.add(salida);
        }
    }
    
    return salidasInscritas;
}
     public void agregarInscripcionPaq(Inscripcion_paquete nuevaInscripcion) {
       inscripcionpaquete.add(nuevaInscripcion);
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
