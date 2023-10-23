/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datatypes.DataUsuario;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import logica.Usuario;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario implements Serializable {

    @Column(unique = true) // Aquí indicamos que el nick debe ser único
    protected String nickname;

    public static Map<String, Usuario> getUsuariosMail() {
        return usuariosMail;
    }

    public static void setUsuariosMail(Map<String, Usuario> usuariosMail) {
        Usuario.usuariosMail = usuariosMail;
    }
    protected String nombre;
    protected String apellido;

    protected String imagenPerfil;
    protected String password;

    //@GeneratedValue(strategy = GenerationType.TABLE)
    @Id
    protected String correo;
    protected LocalDate fechaNacimiento;

    public Usuario() {
    }

    ;
    
    public Usuario(String nick, String name, String apll, String correo, LocalDate fecNac, String imagen) {
        this.nickname = nick;
        this.nombre = name;
        this.apellido = apll;
        this.correo = correo;
        this.fechaNacimiento = fecNac;
        this.imagenPerfil = imagen;
    }

    public Usuario(String nick, String name, String apll, String correo, LocalDate fecNac, String imagen, String password) {
        this.nickname = nick;
        this.nombre = name;
        this.apellido = apll;
        this.correo = correo;
        this.fechaNacimiento = fecNac;
        this.imagenPerfil = imagen;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public DataUsuario devolverData() {
        String tipo = "";
        if (this instanceof Turista) {
            tipo = "Turista";
        } else if (this instanceof Proveedor) {
            tipo = "Proveedor";
        }
        DataUsuario dt = new DataUsuario(getNick(), getNombre(), getApellido(), getCorreo(), getFecnac(), null, null, null, imagenPerfil);
        dt.setTipo(tipo);
        return  dt;
    }

    public String getNick() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public LocalDate getFecnac() {
        return fechaNacimiento;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setNick(String nick) {
        nickname = nick;
    }

    public void setNombre(String nom) {
        nombre = nom;
    }

    public void setApellido(String ap) {
        apellido = ap;
    }

    public void setCorreo(String corr) {
        correo = corr;
    }

    public void setFecha(LocalDate fecha) {
        fechaNacimiento = fecha;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setImagenPerfil(String imagen) {
        this.imagenPerfil = imagen;
    }

    //protected Map<String, Usuario> usuariosMail;
    public static Map<String, Usuario> usuariosMail = new HashMap<>();

    public static void addUsuario(Usuario usu) {
        String mail = usu.getCorreo();
        usuariosMail.put(mail, usu);
    }

    public static Usuario obtenerUsuario(String mail) {
        return ((Usuario) usuariosMail.get(mail));
    }

    /*
    public static Usuario obtenerUsuarioBD(String mail, EntityManager em) {
    try {
        // Intenta obtener un usuario por su correo utilizando el EntityManager proporcionado
        Usuario usuario = em.find(Usuario.class, mail);

        // Si el usuario no es nulo, significa que existe en la base de datos
        return usuario;
    } catch (Exception e) {
        // Manejar excepciones si es necesario
        return null; // O retorna null en caso de error
    }
}*/
    public static Usuario obtenerUsuarioPorNick(String nick) {  //polpo
        Collection<Usuario> usrs = usuariosMail.values();
        for (Usuario u : usrs) {
            if (nick.equals(u.getNick())) {
                return u;
            }
        }
        return null;
    }

    public static Usuario[] getUsuarios() {
        if (usuariosMail.isEmpty()) {
            return null;
        } else {
            Collection<Usuario> usrs = usuariosMail.values();
            Object[] o = usrs.toArray();
            Usuario[] usuarios = new Usuario[o.length];
            for (int i = 0; i < o.length; i++) {
                usuarios[i] = (Usuario) o[i];
            }
            return usuarios;
        }
    }

    public String getPassword() {
        return password;
    }

    public boolean tienePswd(String p) {
        return (p == null ? password == null : p.equals(password));
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
